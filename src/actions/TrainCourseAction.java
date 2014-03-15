package actions;

import bl.beans.ServicePlaceBean;
import bl.beans.TrainCourseBean;
import bl.beans.TrainCourseServicePlaceBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.ServicePlaceBusiness;
import bl.mongobus.TrainCourseBusiness;
import bl.mongobus.TrainCourseServicePlaceBusiness;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * Created by peter on 14-3-14.
 */
public class TrainCourseAction extends ActionSupport {
    List<TrainCourseBean> trainCourses = null;
    TrainCourseBean trainCourse = null;
    List<TrainCourseServicePlaceBean> trainCourseServicePlaces = null;
    List<ServicePlaceBean> servicePlaceBeans = null;

    ServicePlaceBusiness sp = (ServicePlaceBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SERVICEPLACE);
    TrainCourseBusiness tc = (TrainCourseBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_TRAINCOURSE);
    TrainCourseServicePlaceBusiness tcp = (TrainCourseServicePlaceBusiness)SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_TRAINCOURSESERVICEPLACE);

    public List<TrainCourseServicePlaceBean> getTrainCourseServicePlaces() {
        return trainCourseServicePlaces;
    }

    public void setTrainCourseServicePlaces(List<TrainCourseServicePlaceBean> trainCourseServicePlaces) {
        this.trainCourseServicePlaces = trainCourseServicePlaces;
    }

    public String trainCourseList() {
        this.trainCourses = (List<TrainCourseBean>) tc.getAllLeaves().getResponseData();
        return ActionSupport.SUCCESS;
    }

    public String trainCourseDelete() {
        if (this.trainCourse.get_id() != null) {
            tc.deleteLeaf(this.trainCourse.getId());
        }
        return ActionSupport.SUCCESS;
    }

    public String trainCourseAddEdit() {
        servicePlaceBeans = (List<ServicePlaceBean>) sp.getAllLeaves().getResponseData();
        if (this.trainCourse != null) {
            String id = this.trainCourse.getId();
            this.trainCourse = (TrainCourseBean) this.tc.getLeaf(id).getResponseData();
            if(this.trainCourse!=null){
                HashMap<String,Object> filter = new HashMap<String,Object>();
                filter.put("trainCourseId",this.trainCourse.get_id().toString());
                this.trainCourseServicePlaces = tcp.queryDataByCondition(filter,null);
            }
        }
        return ActionSupport.SUCCESS;
    }

    public String trainCourseSubmit() {
        String id = this.trainCourse.getId();
        try {
            if (id != null && !id.isEmpty()) {
                TrainCourseBean originalBean = (TrainCourseBean) this.tc.getLeaf(id).getResponseData();
                TrainCourseBean newBean = (TrainCourseBean) originalBean.clone();
                BeanUtils.copyProperties(newBean, this.trainCourse);
                tc.updateLeaf(originalBean, newBean);

                //update data relationship table.
                HashMap<String,Object> filter = new HashMap<String,Object>();
                filter.put("trainCourseId",this.trainCourse.get_id().toString());
                tcp.deleteByCondition(filter);

            } else {
                this.trainCourse.set_id(ObjectId.get());
                this.tc.createLeaf(this.trainCourse);
            }

            //insert data relationship table.
            for (int i = 0; i < this.trainCourseServicePlaces.size(); i++) {
                this.trainCourseServicePlaces.get(i).setTrainCourseId(this.trainCourse.getId());
                tcp.createLeaf(this.trainCourseServicePlaces.get(i));
            }

        } catch (Exception e) {
            LOG.error("this exception [#0]", e.getMessage());
        }
        return ActionSupport.SUCCESS;
    }

    public List<TrainCourseBean> getTrainCourses() {
        return trainCourses;
    }

    public void setTrainCourses(List<TrainCourseBean> trainCourses) {
        this.trainCourses = trainCourses;
    }

    public TrainCourseBean getTrainCourse() {
        return trainCourse;
    }

    public void setTrainCourse(TrainCourseBean trainCourse) {
        this.trainCourse = trainCourse;
    }
}
