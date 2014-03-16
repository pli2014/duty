/**
 * 
 */
package bl.mongobus;

import bl.beans.VolunteerTrainCourseBean;
import bl.common.BeanContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-03-16$
 */
public class VolunteerTrainCourseBusiness extends MongoCommonBusiness<BeanContext, VolunteerTrainCourseBean> {
  private static Logger log = LoggerFactory.getLogger(VolunteerTrainCourseBusiness.class);

  public VolunteerTrainCourseBusiness() {
    this.dbName = "form";
    this.clazz = VolunteerTrainCourseBean.class;
  }

}
