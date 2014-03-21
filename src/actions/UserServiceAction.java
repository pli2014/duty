package actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import webapps.WebappsConstants;
import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.UserServiceBean;
import bl.beans.VolunteerBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ActiveUserBusiness;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.UserServiceBusiness;
import bl.mongobus.VolunteerBusiness;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceAction extends BaseAction {
  List<UserServiceBean> userServices = null;

  List<ServicePlaceBean> servicePlaces = null;
  UserServiceBusiness userServiceBus = (UserServiceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USERSERVICE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);
  ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
  VolunteerBusiness vb = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
  private String userId;
  private String servicePlaceId;
  private long dayHours;
  private long monthHours;
  private long yearHours;
  private long totalHours;
  private int type = 0;  // 0 院内 含有颜色显示信息  1 院外 含有坐标信息

  public int getType() {
      return type;
  }

  public void setType(int type) {
      this.type = type;
  }

  ActiveUserBean aub = null;
  List<ServicePlaceBean> servicePlaceBeans = null;
  HashMap<ServicePlaceBean,HashSet<VolunteerBean>> servicePlaceVolunteer = null;

  public HashMap<ServicePlaceBean, HashSet<VolunteerBean>> getServicePlaceVolunteer() {
      return servicePlaceVolunteer;
  }

  public List<ServicePlaceBean> getServicePlaceBeans() {
      return servicePlaceBeans;
  }

  public String getList(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
    if(null != user){
      userServices = (List<UserServiceBean>)userServiceBus.getOrderedLeavesByUserId(user.getId(), 10).getResponseData();
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
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
    servicePlaces = (List<ServicePlaceBean>)userServiceBus.getAvailableServicePlaces(user.getId()).getResponseData();
    return SUCCESS;
  }

  public String checkInSubmit(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
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
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
    userServiceBus.checkOut(user.getId());
    return SUCCESS;
  }

  public String getMyTimeReport() {
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
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

  public String whoIsHere(){
      List<ActiveUserBean> beanList = (List<ActiveUserBean>) activeUserBus.getAllLeaves().getResponseData();
      servicePlaceVolunteer = new HashMap<ServicePlaceBean,HashSet<VolunteerBean>>();
      // Because serviceplace isn't huge, loading all data one time.
      List<ServicePlaceBean> sb = (List<ServicePlaceBean>) sp.getAllLeaves().getResponseData();
      for (ActiveUserBean ub : beanList) {
         String spId = ub.getServicePlaceId();
         String volunteerId = ub.getUserId();
          ServicePlaceBean sbFetch = null;
          for(int i=0;i<sb.size();i++){
              if(sb.get(i).getId().equals(spId)){
                  sbFetch = sb.get(i);
                  break;
              }
          }
          //only display service places by type.
          if(sbFetch!=null && sbFetch.getType()==this.type){
          VolunteerBean vtb = (VolunteerBean) vb.getLeaf(volunteerId).getResponseData();
              if(!servicePlaceVolunteer.containsKey(sbFetch)){
                  if(vtb!=null){
                      HashSet<VolunteerBean> hv = new HashSet<VolunteerBean>();
                      hv.add(vtb);
                      servicePlaceVolunteer.put(sbFetch,hv);
                  }
              }else{
                  HashSet<VolunteerBean> hv = servicePlaceVolunteer.get(sbFetch);
                  if(vtb!=null && !hv.contains(vtb)){
                      hv.add(vtb);
                  }
              }
          }
      }
      return SUCCESS+this.type;
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