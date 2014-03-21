package wechat;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.ServerContext;
import wechat.accessmanagement.AccessValidator;
import wechat.utils.Constants;
import wechat.utils.WechatContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-10.
 */
public class WechatServlet extends HttpServlet {
  protected final static Logger LOG = LoggerFactory.getLogger(WechatServlet.class);

  public void init() throws ServletException{
    WechatContext.init();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 微信加密签名
    String signature = request.getParameter("signature");
    // 时间戳
    String timestamp = request.getParameter("timestamp");
    // 随机数
    String nonce = request.getParameter("nonce");

    if(signature == null || timestamp == null || nonce == null) {
      return;
    }
    // 随机字符串
    String echostr = request.getParameter("echostr");
    PrintWriter out = response.getWriter();
    // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
    AccessValidator validator = new AccessValidator(signature, ServerContext.getValue(Constants.APP_TOKEN), timestamp, nonce);
    if (validator.validate()) {
      out.print(echostr);
    }
    out.close();
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    // 微信加密签名
    String signature = request.getParameter("signature");
    // 时间戳
    String timestamp = request.getParameter("timestamp");
    // 随机数
    String nonce = request.getParameter("nonce");

    if(signature == null || timestamp == null || nonce == null) {
      return;
    }
    // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
    AccessValidator validator = new AccessValidator(signature, ServerContext.getValue(Constants.APP_TOKEN), timestamp, nonce);
    if (validator.validate()) {
      try {
        Map rMap = parseXml(request);
        DispatcherService dispatcher = new DispatcherService();
        String respMessage = dispatcher.process(rMap);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
      } catch (DocumentException e) {
        LOG.error(e.getMessage());
      }
    }
  }

  private Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException {
    // 将解析结果存储在HashMap中
    Map<String, String> map = new HashMap<String, String>();

    // 从request中取得输入流
    InputStream inputStream = request.getInputStream();
    // 读取输入流
    SAXReader reader = new SAXReader();
    Document document = reader.read(inputStream);
    // 得到xml根元素
    Element root = document.getRootElement();
    // 得到根元素的所有子节点
    List<Element> elementList = root.elements();

    // 遍历所有子节点
    for (Element e : elementList) {
      map.put(e.getName(), e.getText());
    }
    // 释放资源
    inputStream.close();

    return map;
  }
}
