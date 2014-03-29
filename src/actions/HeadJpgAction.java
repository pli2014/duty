package actions;

import bl.beans.TemplateBean;
import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.FormBusiness;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.ServletActionContext;
import sun.misc.BASE64Decoder;
import util.ServerContext;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 14-3-9.
 */
public class HeadJpgAction {
    private String code = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String image = null;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public InputStream getPathStream() {
        return pathStream;
    }

    public void setPathStream(InputStream pathStream) {
        this.pathStream = pathStream;
    }

    protected final static Logger LOG = LoggerFactory.getLogger(HeadJpgAction.class);

    //写一个输出流
    private InputStream pathStream;

    public String uploadPersonPicture() {
        if (image != null) {

            try {
                String realstorepngdirectory = ServerContext.getValue("realstorepngdirectory");
                String vitualstorepngdirectory = ServerContext.getValue("vitualstorepngdirectory");
                String requestPath = null;
                if (Files.notExists(Paths.get(realstorepngdirectory))) {
                    Files.createDirectories(Paths.get(realstorepngdirectory));
                }

                String filename = this.code + "_header.png";
                requestPath = realstorepngdirectory + File.separator + filename;

                decodeBase64Img(this.image.substring("data:image/png;base64,".length()), requestPath);

                ServletActionContext.getResponse().getWriter().print(vitualstorepngdirectory + filename);
                ServletActionContext.getResponse().getWriter().close();
                return null;

            } catch (Exception e) {
                LOG.error("this exception [#0]", e.getMessage());
            }

        }
        return null;
    }

    public static boolean decodeBase64Img(String imgStr, String imgFilePath) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Path imgStr = Paths.get("E:\\workspace\\duty\\src\\actions\\base64png.txt");
        byte[] bytes = Files.readAllBytes(imgStr);
        //remove prefixed string data:image/png;base64,
        HeadJpgAction.decodeBase64Img(new String(bytes).substring("data:image/png;base64,".length()), "E:\\workspace\\duty\\src\\actions\\base64png.png");
    }
}
