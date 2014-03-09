package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import org.apache.struts2.ServletActionContext;
import wechat.AccessValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangronghua on 14-3-9.
 */
public class WeChatAction  extends ActionSupport {
  protected final static Logger LOG = LoggerFactory.getLogger(WeChatAction.class);

  private static final String token = "wangronghua";
  private String signature;
  private String timestamp;

  private String nonce;
  private String echostr;

  public String checkAccess(){
    HttpServletRequest request = ServletActionContext.getRequest();
    signature = request.getParameter("signature");
    timestamp = request.getParameter("timestamp");
    nonce = request.getParameter("nonce");

    AccessValidator validator = new AccessValidator(signature, token, timestamp, nonce);
    if(validator.validate()) {
      echostr = request.getParameter("echostr");
      if(echostr == null) echostr = "null";
      return SUCCESS;
    } else {
      LOG.error("validate failed! signature:#0, token:#1, timestamp:#2, nonce:#3",
                                        signature, token, timestamp, nonce);
      return ERROR;
    }

  }




  public String getEchostr() {
    return echostr;
  }

  public void setEchostr(String echostr) {
    this.echostr = echostr;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getNonce() {
    return nonce;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
  }
}
