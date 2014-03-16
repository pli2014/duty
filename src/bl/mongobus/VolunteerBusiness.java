/**
 * 
 */
package bl.mongobus;

import bl.beans.VolunteerBean;
import bl.common.BeanContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-20$
 */
public class VolunteerBusiness extends MongoCommonBusiness<BeanContext, VolunteerBean> {
  private static Logger log = LoggerFactory.getLogger(VolunteerBusiness.class);

  public VolunteerBusiness() {
    this.dbName = "form";
    this.clazz = VolunteerBean.class;
  }
   
}
