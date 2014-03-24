package actions.front;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import vo.NameValueVo;
import vo.report.DailyTimeReportVo;
import vo.report.MonthlyTimeReportVo;
import vo.report.YearlyTimeReportVo;
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
public class UserServiceAction extends BaseFrontAction {
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

  private YearlyTimeReportVo yearValues;
  private MonthlyTimeReportVo monthValues;
  private DailyTimeReportVo dayValues;

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
    servicePlaces = userServiceBus.getAvailableServicePlaces(user.getId());
    return SUCCESS;
  }

  public String checkInSubmit(){
    VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
    if(null == servicePlaceId) {
      checkIn();
      super.addActionError("请选择服务地点");
      return ERROR;
    }
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
    Calendar cal = Calendar.getInstance();
    yearValues = new YearlyTimeReportVo();
    yearValues.setThisYear(String.valueOf(cal.get(Calendar.YEAR)));
    yearValues.setLastYear(String.valueOf(cal.get(Calendar.YEAR) - 1 ));
    Map<String, Map> thisYearResultMap = userServiceBus.statisticTime(beanList, "yyyy", yearValues.getThisYear());
    Map<String, Map> lastYearResultMap = userServiceBus.statisticTime(beanList, "yyyy", yearValues.getLastYear());
    Map result = thisYearResultMap.get(user.getId());
    if(null != result){
      Long value = (Long) result.get(yearValues.getThisYear());
      yearValues.setThisYearValue ((null != value ? value : 0l) / 3600000);
    } else {
      yearValues.setThisYearValue(0l);
    }
    result = lastYearResultMap.get(user.getId());
    if(null != result){
      Long value = (Long) result.get(yearValues.getLastYear());
      yearValues.setLastYearValue((null != value ? value : 0l) / 3600000);
    } else {
      yearValues.setLastYearValue(0l);
    }
    return SUCCESS;
  }

  public String getMyMonthlyTimeReport() throws ParseException {
    String year = getRequest().getParameter("year");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    if (null != year) {
      Date yearStart = sdf.parse(year+"-01");
      Calendar cal = Calendar.getInstance();
      cal.setTime(yearStart);
      cal.add(Calendar.YEAR, 1);
      Date yearEnd = cal.getTime();

      VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);

      List<String> userIdList = new ArrayList<String>();
      userIdList.add(user.getId());
      List<UserServiceBean> userServiceBeanList = userServiceBus.queryUserServices(userIdList, null, yearStart, yearEnd);

      Map<String, Map> resultMap = userServiceBus.statisticTime(userServiceBeanList, "yyyy-MM", year);
      Map result = resultMap.get(user.getId());
      monthValues = new MonthlyTimeReportVo();
      cal.setTime(yearStart);
      while (cal.getTime().before(yearEnd)) {
        String key = sdf.format(cal.getTime());
        Long value = 0l;
        if(null != result){
          value = (Long) result.get(key);
        }
        monthValues.addNameValueVo(new NameValueVo(key, (value != null ? value : 0l)/3600000));
        cal.add(Calendar.MONTH, 1);
      }
    }
    return SUCCESS;
  }

  public String getMyDailyTimeReport() throws ParseException {
    String yearMonth = getRequest().getParameter("yearMonth");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    dayValues = new DailyTimeReportVo();
    if (null != yearMonth) {
      Date monthStart = sdf.parse(yearMonth+"-01");
      Calendar cal = Calendar.getInstance();
      cal.setTime(monthStart);
      cal.add(Calendar.MONTH, 1);
      Date monthEnd = cal.getTime();

      VolunteerBean user = (VolunteerBean)getSession().getAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);

      List<String> userIdList = new ArrayList<String>();
      userIdList.add(user.getId());
      List<UserServiceBean> userServiceBeanList = userServiceBus.queryUserServices(userIdList, null, monthStart, monthEnd);

      Map<String, Map> resultMap = userServiceBus.statisticTime(userServiceBeanList, "yyyy-MM-dd", yearMonth);
      Map result = resultMap.get(user.getId());
      cal.setTime(monthStart);
      while (cal.getTime().before(monthEnd)) {
        String key = sdf.format(cal.getTime());
        Long value = 0l;
        if(null != result){
          value = (Long) result.get(key);
          if(null != value) {
            dayValues.addNameValueVo(new NameValueVo(key, (value != null ? value : 0l)/3600000));
          }
        }
        cal.add(Calendar.DAY_OF_MONTH, 1);
      }
    }

    if(null == dayValues.getValueList() || (dayValues.getValueList().size() == 0)) {
      super.addActionMessage("本月没有查询到服务记录！");
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


  public DailyTimeReportVo getDayValues() {
    return dayValues;
  }

  public void setDayValues(DailyTimeReportVo dayValues) {
    this.dayValues = dayValues;
  }

  public YearlyTimeReportVo getYearValues() {
    return yearValues;
  }

  public void setYearValues(YearlyTimeReportVo yearValues) {
    this.yearValues = yearValues;
  }

  public MonthlyTimeReportVo getMonthValues() {
    return monthValues;
  }

  public void setMonthValues(MonthlyTimeReportVo monthValues) {
    this.monthValues = monthValues;
  }

}