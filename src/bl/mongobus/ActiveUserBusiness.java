package bl.mongobus;

import bl.beans.ActiveUserBean;
import bl.beans.UserServiceBean;
import bl.common.BeanContext;
import bl.common.BusinessResult;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import dao.MongoDBConnectionFactory;
import org.mongodb.morphia.Datastore;

/**
 * Created by wangronghua on 14-3-15.
 */
public class ActiveUserBusiness extends MongoCommonBusiness<BeanContext, ActiveUserBean>{

  private static Logger LOG = LoggerFactory.getLogger(ActiveUserBusiness.class);
  public ActiveUserBusiness() {
    this.clazz = ActiveUserBean.class;
  }

  public BusinessResult getActiveUserByUserId(String userId) {
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    BusinessResult br = new BusinessResult();
    br.setResponseData(dc.find(this.clazz, "userId", userId).get());
    return br;
  }

  public BusinessResult getActiveUsersByServicePlace(String servicePlaceId) {
    Datastore dc = MongoDBConnectionFactory.getDatastore(this.dbName);
    BusinessResult br = new BusinessResult();
    br.setResponseData(dc.find(this.clazz, "servicePlaceId", servicePlaceId).asList());
    return br;
  }

}
