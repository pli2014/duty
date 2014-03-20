package actions.wechat;

import actions.BaseAction;
import wechat.accessmanagement.AccessTokenManager;
import wechat.accessmanagement.AccessToken;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatUserAction extends BaseAction {

  private String wechatUser;
  private String userName;
  private String identityCardNumber;

  public String binding() {
    String code = getRequest().getParameter("code");
    AccessToken token = AccessTokenManager.getAccessToken(code);
    return SUCCESS;
  }

  public String bindingSubmit() {
    return SUCCESS;
  }


  public String getWechatUser() {
    return wechatUser;
  }

  public void setWechatUser(String wechatUser) {
    this.wechatUser = wechatUser;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getIdentityCardNumber() {
    return identityCardNumber;
  }

  public void setIdentityCardNumber(String identityCardNumber) {
    this.identityCardNumber = identityCardNumber;
  }
}
