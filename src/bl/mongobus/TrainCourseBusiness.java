package bl.mongobus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public TrainCourseBusiness() {
		this.clazz = TrainCourseBean.class;
	}

}
