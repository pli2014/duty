package actions.wechat;

import actions.BaseAction;
import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ActiveUserBusiness;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.UserServiceBusiness;
import bl.mongobus.VolunteerBusiness;
import vo.NameValueVo;
import wechat.access.AccessToken;
import wechat.access.AccessTokenManager;
import wechat.user.UerManager;
import wechat.user.UserInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by wangronghua on 14-3-22.
 */
public class WechatServiceAction extends BaseAction {

  private String openID;
  private String userID;
  private String wechatUser;
  private String userName;
  private Date currentTime;
  private String servicePlaceId;
  private List<ServicePlaceBean> places;

  VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
  UserServiceBusiness usb = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);
  ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);

  public String checkIn(){
    currentTime = new Date();
    String code = getRequest().getParameter("code");
    if(null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        openID = info.getOpenid();
        if(null != openID) {
          VolunteerBean volunteer = vb.getVolunteerBeanByOpenID(openID);
          if(null != volunteer) {
            wechatUser = volunteer.getWechat();
            userName = volunteer.getName();
            userID = volunteer.getId();
            ActiveUserBean aub = (ActiveUserBean) activeUserBus.getActiveUserByUserId(userID).getResponseData();
            if (aub != null) {
              ServicePlaceBean spb = (ServicePlaceBean) sp.getLeaf(aub.getServicePlaceId()).getResponseData();
              if (spb != null){
                super.addActionMessage("您当前正在：" + spb.getName() + " 参与服务！");
                return "checkout";
              }
            }
            places = usb.getAvailableServicePlaces(userID);
            return SUCCESS;
          } else {
            return LOGIN;
          }
        }
      }
    }
    return ERROR;
  }

  public String checkInSubmit(){
    if(null != userID && null != servicePlaceId) {
      usb.checkIn(userID, servicePlaceId);
      ServicePlaceBean spb = (ServicePlaceBean) sp.getLeaf(servicePlaceId).getResponseData();
      if (spb != null){
        super.addActionMessage("您当前正在：" + spb.getName() + " 参与服务！");
      }
    }
    return SUCCESS;
  }

  public String checkOut(){
    String code = getRequest().getParameter("code");
    if(null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        openID = info.getOpenid();
        if(null != openID) {
          VolunteerBean volunteer = vb.getVolunteerBeanByOpenID(openID);
          if(null != volunteer) {
            wechatUser = volunteer.getWechat();
            userName = volunteer.getName();
            userID = volunteer.getId();
            ActiveUserBean aub = (ActiveUserBean) activeUserBus.getActiveUserByUserId(userID).getResponseData();
            if (aub != null) {
              ServicePlaceBean spb = (ServicePlaceBean) sp.getLeaf(aub.getServicePlaceId()).getResponseData();
              if (spb != null){
                super.addActionMessage("您当前正在：" + spb.getName() + " 参与服务！");
                return SUCCESS;
              }
            }
          }
        }
      }
    }
    super.addActionError("您当前未在任何地点参与服务！");
    return ERROR;
  }

  public String checkOutSubmit() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    if(null != userID) {
      usb.checkOut(userID);
      ActiveUserBean aub = (ActiveUserBean) activeUserBus.getActiveUserByUserId(userID).getResponseData();
      if(null != aub) {
        super.addActionError("未成功签出，发生未知错误！");
      } else {
        super.addActionMessage("成功签出！");
      }
    }
    return SUCCESS;
  }


  public List<ServicePlaceBean> getPlaces() {
    return places;
  }

  public void setPlaces(List<ServicePlaceBean> places) {
    this.places = places;
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

  public Date getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(Date currentTime) {
    this.currentTime = currentTime;
  }


  public String getServicePlaceId() {
    return servicePlaceId;
  }

  public void setServicePlaceId(String servicePlaceId) {
    this.servicePlaceId = servicePlaceId;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }
}
