package bl.mongobus;

import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.VolunteerBean;
import bl.beans.UserServiceBean;
import bl.common.BeanContext;
import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import dao.MongoDBConnectionFactory;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceBusiness extends MongoCommonBusiness<BeanContext, UserServiceBean>{


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
    if(StringUtils.isNotEmpty(userId)) {
      user = (VolunteerBean) userBus.getLeaf(userId).getResponseData();
    }

    for(UserServiceBean bean: beanList) {
      bean.setUserBean(user);
      String servicePlaceId = bean.getServicePlaceId();
      if(StringUtils.isNotEmpty(servicePlaceId)) {
        bean.setServicePlaceBean((ServicePlaceBean)servicePlaceBus.getLeaf(servicePlaceId).getResponseData());
      }
    }
    br.setResponseData(beanList);
    return br;
  }

  public List<UserServiceBean> getLeavesByUserIds(List<String> userIdList, List<String> serviceIdList) {
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    Query query = dc.createQuery(this.clazz);
    if(null != userIdList && userIdList.size() > 0) {
      query = query.filter("userId in", userIdList);
    }
    if(null != serviceIdList && serviceIdList.size() > 0) {
      query = query.filter("servicePlaceId in", serviceIdList);
    }
    List<UserServiceBean> beanList = query.asList();
    return beanList;
  }

  public BusinessResult getLeavesByUserId(String userId) {
    BusinessResult br = new BusinessResult();
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);

    List<UserServiceBean> beanList = dc.find(this.clazz, "userId", userId).asList();

    VolunteerBean user = null;
    if(StringUtils.isNotEmpty(userId)) {
      user = (VolunteerBean) userBus.getLeaf(userId).getResponseData();
    }

    for(UserServiceBean bean: beanList) {
      bean.setUserBean(user);
      String servicePlaceId = bean.getServicePlaceId();
      if(StringUtils.isNotEmpty(servicePlaceId)) {
        bean.setServicePlaceBean((ServicePlaceBean)servicePlaceBus.getLeaf(servicePlaceId).getResponseData());
      }
    }
    br.setResponseData(beanList);
    return br;
  }

  public BusinessResult checkIn(String userId, String servicePlaceId){

    ActiveUserBean bean = new ActiveUserBean();
    bean.set_id(ObjectId.get());
    bean.setUserId(userId);
    bean.setServicePlaceId(servicePlaceId);
    bean.setCheckInTime(new Date());
    this.createLeaf(bean);
    return new BusinessResult();
  }


  public BusinessResult checkOut(String userId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    ActiveUserBean activeUserBean = (ActiveUserBean)activeUserBus.getActiveUserByUserId(userId).getResponseData();

    if(null != activeUserBean) {
      UserServiceBean usBean = new UserServiceBean();
      PropertyUtils.copyProperties(usBean, activeUserBean);
      usBean.set_id(ObjectId.get());
      usBean.setCheckOutTime(new Date());
      this.createLeaf(usBean);

      activeUserBus.deleteLeaf(activeUserBean.getId());
    }
    return new BusinessResult();
  }

  public BusinessResult getAvailableServicePlaces(String userId){
    //todo 根据userId和培训记录查询可展现的servicePlaces
    return servicePlaceBus.getAllLeaves();

  }

  public Map<String, Map> statisticTime(List<UserServiceBean> beanList){
    Map<String, Map> resultMap = new HashMap<String, Map>();
    SimpleDateFormat day_sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat month_sdf = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat year_sdf = new SimpleDateFormat("yyyy");

    Date now = new Date();
    String baseDay = day_sdf.format(now);
    String baseMonth = month_sdf.format(now);
    String baseYear = year_sdf.format(now);

    for(UserServiceBean bean: beanList) {
      Map userMap = resultMap.get(bean.getUserId());
      if(null == userMap) {
        userMap = new HashMap<String, Map>();
        userMap.put("user", bean.getUserBean());
        userMap.put(Calendar.DAY_OF_MONTH, 0l);
        userMap.put(Calendar.MONTH, 0l);
        userMap.put(Calendar.YEAR, 0l);
        userMap.put(Calendar.ALL_STYLES,0l);
        userMap.put("user", bean.getUserBean());
        userMap.put("user", bean.getUserBean());
        resultMap.put(bean.getUserId(), userMap);
      }

      if(baseDay.equals(day_sdf.format(bean.getCheckOutTime()))){
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.DAY_OF_MONTH, (long)userMap.get(Calendar.DAY_OF_MONTH) + time);
        userMap.put(Calendar.MONTH, (long)userMap.get(Calendar.MONTH) + time);
        userMap.put(Calendar.YEAR, (long)userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long)userMap.get(Calendar.ALL_STYLES) + time);

      } else if(baseMonth.equals(month_sdf.format(bean.getCheckOutTime()))) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.MONTH, (long)userMap.get(Calendar.MONTH) + time);
        userMap.put(Calendar.YEAR, (long)userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long)userMap.get(Calendar.ALL_STYLES) + time);

      } else if(baseYear.equals(year_sdf.format(bean.getCheckOutTime()))) {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.YEAR, (long)userMap.get(Calendar.YEAR) + time);
        userMap.put(Calendar.ALL_STYLES, (long)userMap.get(Calendar.ALL_STYLES) + time);

      } else {
        long time = bean.getCheckOutTime().getTime() - bean.getCheckInTime().getTime();

        userMap.put(Calendar.ALL_STYLES, (long)userMap.get(Calendar.ALL_STYLES) + time);

      }
    }

    return resultMap;
  }
}
