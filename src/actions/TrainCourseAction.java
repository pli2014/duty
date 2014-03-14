package actions;

import bl.beans.TrainCourseBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.TrainCourseBusiness;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by peter on 14-3-14.
 */
public class TrainCourseAction extends ActionSupport {
    List<TrainCourseBean> trainCourses = null;
    TrainCourseBean trainCourse = null;
    TrainCourseBusiness sp = (TrainCourseBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_TRAINCOURSE);

    public String trainCourseList() {
        this.trainCourses = (List<TrainCourseBean>) sp.getAllLeaves().getResponseData();
        return ActionSupport.SUCCESS;
    }

    public String trainCourseDelete() {
        if (this.trainCourse.get_id() != null) {
            sp.deleteLeaf(this.trainCourse.getId());
        }
        return ActionSupport.SUCCESS;
    }

    public String trainCourseAddEdit() {
        if (this.trainCourse != null) {
            String id = this.trainCourse.getId();
            this.trainCourse = (TrainCourseBean) this.sp.getLeaf(id).getResponseData();
        }
        return ActionSupport.SUCCESS;
    }

    public String trainCourseSubmit() {
        String id = this.trainCourse.getId();
        try {
            if (id != null && !id.isEmpty()) {
                TrainCourseBean originalBean = (TrainCourseBean) this.sp.getLeaf(id).getResponseData();
                TrainCourseBean newBean = (TrainCourseBean) originalBean.clone();
                BeanUtils.copyProperties(newBean, this.trainCourse);
                sp.updateLeaf(originalBean, newBean);
            } else {
                this.trainCourse.set_id(ObjectId.get());
                this.sp.createLeaf(this.trainCourse);
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
