package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangronghua on 14-3-9.
 */
public class WeChatAction  extends ActionSupport {

  private String token = "wangronghua";
  private String signature;
  private String timestamp;

  private String nonce;
  private String echostr;

  public String checkAccess(){
    HttpServletRequest request = ServletActionContext.getRequest();
    echostr = request.getParameter("echostr");
    if(echostr == null) echostr = "null";
    return SUCCESS;
  }




  public String getEchostr() {
    return echostr;
  }

  public void setEchostr(String echostr) {
    this.echostr = echostr;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
