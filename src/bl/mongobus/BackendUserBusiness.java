/**
 * 
 */
package bl.mongobus;

import bl.beans.BackendUserBean;
import bl.common.BeanContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-20$
 */
public class BackendUserBusiness extends MongoCommonBusiness<BeanContext, BackendUserBean> {
  private static Logger log = LoggerFactory.getLogger(BackendUserBusiness.class);

  public BackendUserBusiness() {
    this.dbName = "form";
    this.clazz = BackendUserBean.class;
  }
   
}
