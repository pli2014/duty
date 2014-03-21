package actions.wechat;

import actions.BaseAction;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.VolunteerBusiness;
import util.StringUtil;
import webapps.WebappsConstants;
import wechat.accessmanagement.AccessTokenManager;
import wechat.accessmanagement.AccessToken;
import wechat.usermanagement.UerManager;
import wechat.usermanagement.UserInfo;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatUserAction extends BaseAction {

  private String openID;
  private String wechatUser;
  private String userName;
  private String identityCardNumber;
  private String password;

  VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);

  public String binding() {
    String code = getRequest().getParameter("code");
    if(null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        wechatUser = info.getNickname();
        openID = info.getOpenid();
      }
    }
    return SUCCESS;
  }

  public String bindingSubmit() {
    VolunteerBean userTmp = (VolunteerBean) vb.getLeafByName(userName).getResponseData();
    if (userTmp != null && password != null && identityCardNumber != null
            && StringUtil.toMD5(password).equals(userTmp.getPassword())
            && identityCardNumber.equals(userTmp.getIdentityCard())) {
      userTmp.setOpenID(openID);
      userTmp.setWechat(wechatUser);
      vb.updateLeaf(userTmp, userTmp);
      return SUCCESS;
    } else {
      super.addActionError("你输入的信息有误，请重新输入！");
      return ERROR;
    }
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

  public String getOpenID() {
    return openID;
  }

  public void setOpenID(String openID) {
    this.openID = openID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
