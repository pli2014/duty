/**
 * 
 */
package bl.mongobus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

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

  /**
   * 
   * @param volunteerId
   * @param traincourseId
   * @return
   */
  public VolunteerTrainCourseBean getVolunteerTrainCourseBean(String volunteerId, String traincourseId) {
    Map filter = new HashMap();
    filter.put("volunteerId", new ObjectId(volunteerId));
    filter.put("traincourseId", new ObjectId(traincourseId));
    List list = super.queryDataByCondition(filter, null);
    if (list == null || list.size() == 0) {
      return null;
    } else {
      return (VolunteerTrainCourseBean) list.get(0);
    }
  }
}
