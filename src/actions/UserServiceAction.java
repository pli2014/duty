package actions;

import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.UserBean;
import bl.beans.UserServiceBean;
import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ActiveUserBusiness;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.UserServiceBusiness;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceAction extends BaseAction {
  List<UserServiceBean> userServices = null;
  ActiveUserBean  aub = null;
  List<ServicePlaceBean> servicePlaces = null;
  UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);
  ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);

  private String userId;
  private String servicePlaceId;

  public String getList(){
    UserBean user = (UserBean)getSession().getAttribute(UserAction.LOGIN_USER_SESSION_ID);
    if(null != user){
      userServices = (List<UserServiceBean>)userServiceBus.getLeavesByUserId(user.getId()).getResponseData();
      aub = (ActiveUserBean) activeUserBus.getActiveUserByUserId(user.getId()).getResponseData();
        if (aub != null) {
            ServicePlaceBean spb = (ServicePlaceBean) sp.getLeaf(aub.getServicePlaceId()).getResponseData();
            if (spb != null)
                super.addActionMessage("你现在在这里服务:" + spb.getName());
        }
    }
    return SUCCESS;
  }

  public String checkIn(){
    UserBean user = (UserBean)getSession().getAttribute(UserAction.LOGIN_USER_SESSION_ID);
    servicePlaces = (List<ServicePlaceBean>)userServiceBus.getAvailableServicePlaces(user.getId()).getResponseData();
    return SUCCESS;
  }

  public String checkInSubmit(){
    UserBean user = (UserBean)getSession().getAttribute(UserAction.LOGIN_USER_SESSION_ID);
    aub = (ActiveUserBean) activeUserBus.getActiveUserByUserId(user.getId()).getResponseData();
    if (aub != null) {
      ServicePlaceBean spb = (ServicePlaceBean) sp.getLeaf(aub.getServicePlaceId()).getResponseData();
      if (spb != null){
          //initialize data for checkin page.
          checkIn();
          super.addActionError("你已经在" + spb.getName()+"服务,同一时刻只允许签入一个服务地点");
      }
      return ERROR;
    }
    userServiceBus.checkIn(user.getId(), servicePlaceId);
    return SUCCESS;
  }

  public String checkOut() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    UserBean user = (UserBean)getSession().getAttribute(UserAction.LOGIN_USER_SESSION_ID);
    userServiceBus.checkOut(user.getId());
    return SUCCESS;
  }

  public List<UserServiceBean> getUserServices() {
    return userServices;
  }

  public void setUserServices(List<UserServiceBean> userServices) {
    this.userServices = userServices;
  }

  public UserServiceBusiness getUserServiceBus() {
    return userServiceBus;
  }

  public void setUserServiceBus(UserServiceBusiness userServiceBus) {
    this.userServiceBus = userServiceBus;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getServicePlaceId() {
    return servicePlaceId;
  }

  public void setServicePlaceId(String servicePlaceId) {
    this.servicePlaceId = servicePlaceId;
  }


  public List<ServicePlaceBean> getServicePlaces() {
    return servicePlaces;
  }

  public void setServicePlaces(List<ServicePlaceBean> servicePlaces) {
    this.servicePlaces = servicePlaces;
  }
}
