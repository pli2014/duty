package bl.mongobus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bl.common.BusinessResult;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import bl.beans.TrainCourseBean;
import bl.beans.VolunteerBean;
import bl.beans.VolunteerTrainCourseBean;
import bl.common.BeanContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MongoDBConnectionFactory;

public class TrainCourseBusiness extends
		MongoCommonBusiness<BeanContext, TrainCourseBean> {
	private static Logger LOG = LoggerFactory
			.getLogger(TrainCourseBusiness.class);


  private static final VolunteerTrainCourseBusiness vtcb
      = (VolunteerTrainCourseBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEERTRAINCOURSE);

  public TrainCourseBusiness() {
		this.clazz = TrainCourseBean.class;
	}

  @Override
  public BusinessResult updateLeaf(BeanContext origBean, BeanContext newBean) {
    BusinessResult result = super.updateLeaf(origBean, newBean);
    TrainCourseBean bean = (TrainCourseBean)newBean;
    vtcb.updateCourseName(bean.getId(), bean.getName());
    return result;
  }
}
