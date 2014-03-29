package bl.mongobus;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.UserServiceBean;
import bl.beans.VolunteerBean;
import bl.common.BeanContext;
import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import dao.MongoDBConnectionFactory;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceBusiness extends MongoCommonBusiness<BeanContext, UserServiceBean> {

  ServicePlaceBusiness servicePlaceBus = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);

  VolunteerBusiness userBus = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);

  private static Logger LOG = LoggerFactory.getLogger(UserServiceBusiness.class);

  public UserServiceBusiness() {
    this.clazz = UserServiceBean.class;
  }

  public BusinessResult getOrderedLeavesByUserId(String userId, int size) {

    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    BusinessResult br = new BusinessResult();

    List<UserServiceBean> beanList = dc.find(this.clazz, "userId", userId).order("-checkOutTime").limit(size).asList();

    VolunteerBean user = null;
    if (StringUtils.isNotEmpty(userId)) {
      user = (VolunteerBean) userBus.getLeaf(userId).getResponseData();
    }

    for (UserServiceBean bean : beanList) {
      bean.setUserBean(user);
      String servicePlaceId = bean.getServicePlaceId();
      if (StringUtils.isNotEmpty(servicePlaceId)) {
        bean.setServicePlaceBean((ServicePlaceBean) servicePlaceBus.getLeaf(servicePlaceId).getResponseData());
      }
    }
    br.setResponseData(beanList);
    return br;
  }

  public List<UserServiceBean> getLeavesByUserIds(List<String> userIdList, List<String> serviceIdList) {
    return queryUserServices(userIdList, serviceIdList, null, null);
  }

  public List<UserServiceBean> queryUserServices(List<String> userIdList, List<String> serviceIdList, Date start, Date end) {
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    Query query = dc.createQuery(this.clazz);
    if (null != userIdList && userIdList.size() > 0) {
      query = query.filter("userId in", userIdList);
    }
    if (null != serviceIdList && serviceIdList.size() > 0) {
      query = query.filter("servicePlaceId in", serviceIdList);
    }
    if (null != start) {
      query = query.filter("checkInTime >=", start);
    }
    if (null != end) {
      query = query.filter("checkOutTime <", end);
    }
    List<UserServiceBean> beanList = query.asList();
    return beanList;
  }

  /**
   * get serviced hours of current month
   * 
   * @param volunnteerId
   * @return
   */
  public double getServicedHoursForCurrentMonth(String volunnteerId) {
    Calendar startCalendar = Calendar.getInstance();
    startCalendar.set(Calendar.MILLISECOND, 0);
    startCalendar.set(Calendar.SECOND, 0);
    startCalendar.set(Calendar.MINUTE, 0);
    startCalendar.set(Calendar.HOUR, 0);
    startCalendar.set(Calendar.DAY_OF_MONTH, 1);

    Calendar endCalendar = Calendar.getInstance();
    startCalendar.set(Calendar.MILLISECOND, 0);
    startCalendar.set(Calendar.SECOND, 0);
    startCalendar.set(Calendar.MINUTE, 0);
    startCalendar.set(Calendar.HOUR, 0);
    startCalendar.set(Calendar.DAY_OF_MONTH, 1);
    endCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH) + 1);

    return getVolunteerServiceHours(volunnteerId, startCalendar, endCalendar);
  }

  /**
   * get time of current month
   * 
   * @param volunnteerId
   * @return
   */
  public double getServicedHoursForCurrentYear(String volunnteerId) {
    Calendar startCalendar = Calendar.getInstance();
    startCalendar.set(Calendar.MILLISECOND, 0);
    startCalendar.set(Calendar.SECOND, 0);
    startCalendar.set(Calendar.MINUTE, 0);
    startCalendar.set(Calendar.HOUR, 0);
    startCalendar.set(Calendar.MONTH, 0);
    startCalendar.set(Calendar.DAY_OF_MONTH, 1);

    Calendar endCalendar = Calendar.getInstance();
    startCalendar.set(Calendar.MILLISECOND, 0);
    startCalendar.set(Calendar.SECOND, 0);
    startCalendar.set(Calendar.MINUTE, 0);
    startCalendar.set(Calendar.HOUR, 0);
    startCalendar.set(Calendar.MONTH, 0);
    startCalendar.set(Calendar.DAY_OF_MONTH, 1);
    startCalendar.set(Calendar.YEAR, startCalendar.get(Calendar.YEAR) + 1);

    return getVolunteerServiceHours(volunnteerId, startCalendar, endCalendar);
  }

  public double getVolunteerServiceHours(String volunnteerId, Calendar startCalendar, Calendar endCalendar) {
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    Query query = dc.createQuery(this.clazz);

    query = query.filter("userId =", volunnteerId);

    query = query.filter("checkInTime >=", startCalendar.getTime());
    query = query.filter("checkOutTime <", endCalendar.getTime());
    List<UserServiceBean> userServiceList = query.asList();

    double time = 0;
    if (userServiceList != null) {
      for (UserServiceBean userServiceBean : userServiceList) {
        time += (userServiceBean.getCheckOutTime().getTime() - userServiceBean.getCheckInTime().getTime());
      }
    }
    time = time / 3600000;

    BigDecimal b = new BigDecimal(time);
    time = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    return time;
  }

  public BusinessResult getLeavesByUserId(String userId) {
    BusinessResult br = new BusinessResult();
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);

    List<UserServiceBean> beanList = dc.find(this.clazz, "userId", userId).asList();

    VolunteerBean user = null;
    if (StringUtils.isNotEmpty(userId)) {
      user = (VolunteerBean) userBus.getLeaf(userId).getResponseData();
    }

    for (UserServiceBean bean : beanList) {
      bean.setUserBean(user);
      String servicePlaceId = bean.getServicePlaceId();
      if (StringUtils.isNotEmpty(servicePlaceId)) {
        bean.setServicePlaceBean((ServicePlaceBean) servicePlaceBus.getLeaf(servicePlaceId).getResponseData());
      }
    }
    br.setResponseData(beanList);
    return br;
  }

  public BusinessResult checkIn(String userId, String servicePlaceId) {

    ActiveUserBean bean = new ActiveUserBean();
    bean.set_id(ObjectId.get());
    bean.setUserId(userId);
    bean.setServicePlaceId(servicePlaceId);
    bean.setCheckInTime(new Date());
    this.createLeaf(bean);
    return new BusinessResult();
  }

  public BusinessResult checkOut(String userId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    ActiveUserBean activeUserBean = (ActiveUserBean) activeUserBus.getActiveUserByUserId(userId).getResponseData();

    if (null != activeUserBean) {
      UserServiceBean usBean = new UserServiceBean();
      PropertyUtils.copyProperties(usBean, activeUserBean);
      usBean.set_id(ObjectId.get());
      usBean.setCheckOutTime(new Date());
      this.createLeaf(usBean);

      activeUserBus.deleteLeaf(activeUserBean.getId(), true);
    }
    return new BusinessResult();
  }

  public List<ServicePlaceBean> getAvailableServicePlaces(String userId) {
    // todo 根据userId和培训记录查询可展现的servicePlaces
    return (List<ServicePlaceBean>) servicePlaceBus.getAllLeaves().getResponseData();

  }

  /**
   * 
   * @param beanList
   * @param sdfString
   *          could be: yyyy-MM-dd, yyyy-MM, yyyy
   * @param baseTime
   *          the base compare time
   * @return
   */
  public Map<String, Map> statisticTime(List<UserServiceBean> beanList, String sdfString, String baseTime) {
    Map<String, Map> resultMap = new HashMap<String, Map>();
    SimpleDateFormat sdf = new SimpleDateFormat(sdfString);

    for (UserServiceBean bean : beanList) {
      Map userMap = resultMap.get(bean.getUserId());
      if (null == userMap) {
        userMap = new HashMap<String, Map>();
        userMap.put("user", bean.getUserBean());
        resultMap.put(bean.getUserId(), userMap);
      }
      String formatedTime = sdf.format(bean.getCheckOutTime());
      if (formatedTime.startsWith(baseTime)) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();
        if (null == userMap.get(formatedTime)) {
          userMap.put(formatedTime, 0l);
        }
        userMap.put(formatedTime, (long) userMap.get(formatedTime) + time);
      }
    }
    return resultMap;
  }

  public Map<String, Map> statisticTime(List<UserServiceBean> beanList) {
    Map<String, Map> resultMap = new HashMap<String, Map>();
    SimpleDateFormat day_sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat month_sdf = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat year_sdf = new SimpleDateFormat("yyyy");

    Date now = new Date();
    String baseDay = day_sdf.format(now);
    String baseMonth = month_sdf.format(now);
    String baseYear = year_sdf.format(now);

    for (UserServiceBean bean : beanList) {
      Map userMap = resultMap.get(bean.getUserId());
      if (null == userMap) {
        userMap = new HashMap();
        userMap.put("user", bean.getUserBean());
        userMap.put(Calendar.DAY_OF_MONTH, 0l);
        userMap.put(Calendar.MONTH, 0l);
        userMap.put(Calendar.YEAR, 0l);
        userMap.put(Calendar.ALL_STYLES, 0l);
        resultMap.put(bean.getUserId(), userMap);
      }

      if (baseDay.equals(day_sdf.format(bean.getCheckOutTime()))) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.DAY_OF_MONTH, (long) userMap.get(Calendar.DAY_OF_MONTH) + time);
        userMap.put(Calendar.MONTH, (long) userMap.get(Calendar.MONTH) + time);
        userMap.put(Calendar.YEAR, (long) userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long) userMap.get(Calendar.ALL_STYLES) + time);

      } else if (baseMonth.equals(month_sdf.format(bean.getCheckOutTime()))) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.MONTH, (long) userMap.get(Calendar.MONTH) + time);
        userMap.put(Calendar.YEAR, (long) userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long) userMap.get(Calendar.ALL_STYLES) + time);

      } else if (baseYear.equals(year_sdf.format(bean.getCheckOutTime()))) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.YEAR, (long) userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long) userMap.get(Calendar.ALL_STYLES) + time);

      } else {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.ALL_STYLES, (long) userMap.get(Calendar.ALL_STYLES) + time);

      }
    }

    return resultMap;
  }
}
