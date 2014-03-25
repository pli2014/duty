package actions.wechat;

import actions.BaseAction;
import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ActiveUserBusiness;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.VolunteerBusiness;
import util.StringUtil;
import wechat.access.AccessTokenManager;
import wechat.access.AccessToken;
import wechat.user.UerManager;
import wechat.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatUserAction extends BaseAction {

  private String openID;
  private String wechatUser;
  private String userName;
  private String identityCardNumber;
  private String password;

  private String servicePlaceId;
  private ServicePlaceBean servicePlaceBean;
  private List<ServicePlaceBean> places;
  private List<VolunteerBean> activeVolunteers;

  private UserInfo user;

  public UserInfo getUser() {
    return user;
  }

  public void setUser(UserInfo user) {
    this.user = user;
  }

  VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
  ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);

  public String binding() {
    String code = getRequest().getParameter("code");
    if (null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        wechatUser = info.getNickname();
        openID = info.getOpenid();
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * @return
   */
  public String myInfo() {
    user = new UserInfo();
    user.setNickname("gudong");
    user.setSex(1);
    user.setCity("chongqing");
    if (1 == 1)
      return SUCCESS;
    String code = getRequest().getParameter("code");
    if (null != code) {
      AccessToken token = AccessTokenManager.getAccessToken(code);
      UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
      if (null != info) {
        openID = info.getOpenid();
        if (null != openID) {
          VolunteerBean volunteer = vb.getVolunteerBeanByOpenID(openID);
          wechatUser = volunteer.getWechat();
          userName = volunteer.getName();
        }
      }
    }
    return SUCCESS;
  }

  public String bindingSubmit() {
    VolunteerBean userTmp = vb.getVolunteerBeanByCode(userName);
    if (userTmp != null && password != null && identityCardNumber != null && StringUtil.toMD5(password).equals(userTmp.getPassword())
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

  public String searchActiveUser() {
    places = (List<ServicePlaceBean>)sp.getAllLeaves().getResponseData();
    if(null == servicePlaceId) {
      String code = getRequest().getParameter("code");
      if (null != code) {
        AccessToken token = AccessTokenManager.getAccessToken(code);
        UserInfo info = UerManager.getUserInfo(token.getAccess_token(), token.getOpenid());
        if (null != info) {
          openID = info.getOpenid();
          VolunteerBean volunteer = vb.getVolunteerBeanByOpenID(openID);
          ActiveUserBean userBean = (ActiveUserBean) activeUserBus.getActiveUserByUserId(volunteer.getId()).getResponseData();
          if(null != userBean) {
            servicePlaceId = userBean.getServicePlaceId();
          }
        }
      }
    }
    if(null != servicePlaceId) {
      servicePlaceBean = (ServicePlaceBean) sp.getLeaf(servicePlaceId).getResponseData();
      List<ActiveUserBean> activeUserBeanList = (List<ActiveUserBean>) activeUserBus.getActiveUsersByServicePlace(servicePlaceId).getResponseData();
      activeVolunteers = new ArrayList<VolunteerBean>(activeUserBeanList.size());
      for(ActiveUserBean userBean : activeUserBeanList) {
        activeVolunteers.add((VolunteerBean) vb.getLeaf(userBean.getUserId()).getResponseData());
      }
    }
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


  public List<ServicePlaceBean> getPlaces() {
    return places;
  }

  public void setPlaces(List<ServicePlaceBean> places) {
    this.places = places;
  }


  public String getServicePlaceId() {
    return servicePlaceId;
  }

  public void setServicePlaceId(String servicePlaceId) {
    this.servicePlaceId = servicePlaceId;
  }

  public List<VolunteerBean> getActiveVolunteers() {
    return activeVolunteers;
  }

  public void setActiveVolunteers(List<VolunteerBean> activeVolunteers) {
    this.activeVolunteers = activeVolunteers;
  }

  public ServicePlaceBean getServicePlaceBean() {
    return servicePlaceBean;
  }

  public void setServicePlaceBean(ServicePlaceBean servicePlaceBean) {
    this.servicePlaceBean = servicePlaceBean;
  }

}
