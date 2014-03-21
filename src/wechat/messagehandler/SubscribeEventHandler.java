package wechat.messagehandler;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import util.ServerContext;
import wechat.BaseEvent;
import wechat.BaseMessage;
import wechat.utils.URLManager;
import wechat.servicemessage.ServiceMessage;
import wechat.servicemessage.ServiceMessageUtils;
import wechat.servicemessage.TextServiceMessage;

import java.io.UnsupportedEncodingException;

/**
 * Created by wangronghua on 14-3-19.
 */
public class SubscribeEventHandler implements EventHandler {

  String content = "你可以在此处绑定横渡志愿者服务平台: <a href='%s'>点击这里</a>";
  String url = ServerContext.getValue("domainname") + "/wechat/userBinding.action";
  protected final static Logger LOG = LoggerFactory.getLogger(SubscribeEventHandler.class);

  @Override
  public boolean acceptEvent(BaseMessage message) {
    if("event".equals(message.getMsgType())) {
      if("subscribe".equals(((BaseEvent) message).getEvent())) {
        return true;
      }
    } 
    if("text".equals(message.getMsgType())) {
      return true;
    }
    return false;
  }

  @Override
  public void handle(BaseMessage message) {
    try {
      ServiceMessage response = new TextServiceMessage(message.getFromUserName(),
          String.format(content, URLManager.getUrl_OAuthRedirect(url, ServerContext.getValue("appID"), "snsapi_userinfo")));
      ServiceMessageUtils.sendMessage(response);
    } catch (UnsupportedEncodingException e) {
      LOG.error(e.getMessage());
    }
  }
}
