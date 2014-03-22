package actions.wechat;

import actions.BaseAction;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.VolunteerBusiness;
import wechat.access.AccessToken;
import wechat.access.AccessTokenManager;
import wechat.user.UerManager;
import wechat.user.UserInfo;

/**
 * Created by wangronghua on 14-3-22.
 */
public class WechatServiceAction extends BaseAction {

  private String openID;
  private String wechatUser;
  private String userName;
  private String place;

  VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);

  public String checkIn(){
    String code = getRequest().getParameter("code");
    if(null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        openID = info.getOpenid();
        if(null != openID) {
          VolunteerBean volunteer = vb.getVolunteerBeanByOpenID(openID);
          wechatUser = volunteer.getWechat();
          userName = volunteer.getName();

        }

      }
    }
    return SUCCESS;
  }

  public String checkInSubmit(){
    return SUCCESS;
  }

  public String checkOut(){
    return SUCCESS;
  }

  public String checkOutSubmit(){
    return SUCCESS;
  }


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getOpenID() {
    return openID;
  }

  public void setOpenID(String openID) {
    this.openID = openID;
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


}
