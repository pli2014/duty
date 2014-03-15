package bl.mongobus;

import bl.beans.ServicePlaceBean;
import bl.beans.TrainCourseServicePlaceBean;
import bl.common.BeanContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class TrainCourseServicePlaceBusiness extends MongoCommonBusiness<BeanContext, TrainCourseServicePlaceBean> {
    private static Logger LOG = LoggerFactory.getLogger(TrainCourseServicePlaceBusiness.class);

    public TrainCourseServicePlaceBusiness() {
        this.clazz = TrainCourseServicePlaceBean.class;
    }
}
