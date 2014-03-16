package actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import bl.beans.ServicePlaceBean;
import bl.beans.UserServiceBean;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.UserServiceBusiness;

import common.Constants;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceAction extends BaseAction {
  List<UserServiceBean> userServices = null;

  List<ServicePlaceBean> servicePlaces = null;
  UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
  private String userId;
  private String servicePlaceId;
  private long dayHours;
  private long monthHours;
  private long yearHours;
  private long totalHours;

  public String getList(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(Constants.LOGIN_USER_SESSION_ID);
    if(null != user){
      userServices = (List<UserServiceBean>)userServiceBus.getOrderedLeavesByUserId(user.getId(), 10).getResponseData();
    }
    return SUCCESS;
  }

  public String checkIn(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(Constants.LOGIN_USER_SESSION_ID);
    servicePlaces = (List<ServicePlaceBean>)userServiceBus.getAvailableServicePlaces(user.getId()).getResponseData();
    return SUCCESS;
  }

  public String checkInSubmit(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(Constants.LOGIN_USER_SESSION_ID);
    userServiceBus.checkIn(user.getId(), servicePlaceId);
    return SUCCESS;
  }

  public String checkOut() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(Constants.LOGIN_USER_SESSION_ID);
    userServiceBus.checkOut(user.getId());
    return SUCCESS;
  }

  public String getMyTimeReport() {
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(Constants.LOGIN_USER_SESSION_ID);
    List<UserServiceBean> beanList = (List<UserServiceBean>)userServiceBus.getLeavesByUserId(user.getId()).getResponseData();
    Map<String, Map> resultMap = userServiceBus.statisticTime(beanList);
    Map result = resultMap.get(user.getId());
    if(null != result){
      dayHours = (long)result.get(Calendar.DAY_OF_MONTH) / 3600000 ;
      monthHours = (long)result.get(Calendar.MONTH) / 3600000 ;
      yearHours = (long)result.get(Calendar.YEAR) / 3600000 ;
      totalHours = (long)result.get(Calendar.ALL_STYLES) / 3600000 ;
    }
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


  public long getDayHours() {
    return dayHours;
  }

  public long getMonthHours() {
    return monthHours;
  }

  public long getYearHours() {
    return yearHours;
  }

  public long getTotalHours() {
    return totalHours;
  }
}
