package bl.mongobus;

import bl.UserBusiness;
import bl.beans.ActiveUserBean;
import bl.beans.ServicePlaceBean;
import bl.beans.UserBean;
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

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by wangronghua on 14-3-15.
 */
public class UserServiceBusiness extends MongoCommonBusiness<BeanContext, UserServiceBean>{


  ServicePlaceBusiness servicePlaceBus = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
  ActiveUserBusiness activeUserBus = (ActiveUserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_ACTIVEUSER);

  UserBusiness userBus = (UserBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_USER);

  private static Logger LOG = LoggerFactory.getLogger(UserServiceBusiness.class);
  public UserServiceBusiness() {
    this.clazz = UserServiceBean.class;
  }


  public BusinessResult getLeavesByUserId(String userId) {

    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    BusinessResult br = new BusinessResult();

    List<UserServiceBean> beanList = dc.find(this.clazz, "userId", userId).asList();

    UserBean user = null;
    if(StringUtils.isNotEmpty(userId)) {
      user = (UserBean) userBus.getLeaf(userId).getResponseData();
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
}
