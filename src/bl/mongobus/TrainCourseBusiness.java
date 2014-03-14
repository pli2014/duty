package bl.mongobus;

import bl.beans.ServicePlaceBean;
import bl.beans.TrainCourseBean;
import bl.common.BeanContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class TrainCourseBusiness extends MongoCommonBusiness<BeanContext, TrainCourseBean> {
    private static Logger LOG = LoggerFactory.getLogger(TrainCourseBusiness.class);
    public TrainCourseBusiness() {
        this.clazz = TrainCourseBean.class;
    }
}
