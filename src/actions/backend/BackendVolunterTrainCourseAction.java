package actions.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.TrainCourseServicePlaceBusiness;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import vo.table.TableDataVo;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.TrainCourseBean;
import bl.beans.VolunteerBean;
import bl.beans.VolunteerTrainCourseBean;
import bl.common.BusinessResult;
import bl.mongobus.TrainCourseBusiness;
import bl.mongobus.VolunteerBusiness;
import bl.mongobus.VolunteerTrainCourseBusiness;

/**
 * 
 * @author gudong
 * @since $Date:2013-03-20$
 */
public class BackendVolunterTrainCourseAction extends BaseBackendAction<VolunteerTrainCourseBusiness> {

  private static final VolunteerBusiness volunteerBus = (VolunteerBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_VOLUNTEER);
  private static final TrainCourseBusiness tcBus = (TrainCourseBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_TRAINCOURSE);

  private VolunteerTrainCourseBean volunteerTrainCourse;
  private List<TrainCourseBean> trainCourseList;
  private String volunteerId;
  private String traincourseId;

  public String getVolunteerId() {
    return volunteerId;
  }

  public void setVolunteerId(String volunteerId) {
    this.volunteerId = volunteerId;
  }

  public String getTraincourseId() {
    return traincourseId;
  }

  public void setTraincourseId(String traincourseId) {
    this.traincourseId = traincourseId;
  }

  public VolunteerTrainCourseBean getVolunteerTrainCourse() {
    return volunteerTrainCourse;
  }

  public void setVolunteerTrainCourse(VolunteerTrainCourseBean volunteerTrainCourse) {
    this.volunteerTrainCourse = volunteerTrainCourse;
  }

  public List<TrainCourseBean> getTrainCourseList() {
    return trainCourseList;
  }

  public void setTrainCourseList(List<TrainCourseBean> trainCourseList) {
    this.trainCourseList = trainCourseList;
  }

  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/volunterTrainCourse";
  }

  @Override
  public String getCustomJs() {
    return getRequest().getContextPath() + "/js/volunterTrainCourse.js";
  }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = new TableInitVo();
    init.getAoColumns().add(new TableHeaderVo("volunteerName", "志愿者").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("traincourseName", "课程名称").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("status", "状态"));
    init.getAoColumns().add(new TableHeaderVo("createTime", "培训时间"));

    return init;
  }

  @Override
  public String add() {
    if (volunteerId != null) {
      VolunteerBusiness volunteerBusiness = new VolunteerBusiness();
      BusinessResult result = volunteerBusiness.getLeaf(volunteerId);
      if (result != null && result.getResponseData() != null) {
        volunteerTrainCourse = new VolunteerTrainCourseBean();
        volunteerTrainCourse.setVolunteer((VolunteerBean) result.getResponseData());
      }
    }
    Map filter = new HashMap();
    filter.put("isDeleted_!=", true);
    trainCourseList = new TrainCourseBusiness().queryDataByCondition(filter, null);
    return SUCCESS;
  }

  @Override
  public String edit() throws Exception {
    volunteerTrainCourse = (VolunteerTrainCourseBean) getBusiness().getLeaf(getId()).getResponseData();
    TrainCourseBusiness trainCourseBusiness = new TrainCourseBusiness();
    VolunteerBusiness volunteerBusiness = new VolunteerBusiness();
    BusinessResult result;
    if (volunteerTrainCourse != null && volunteerTrainCourse.getTraincourseId() != null) {
      result = trainCourseBusiness.getLeaf(volunteerTrainCourse.getTraincourseId());
      if (result != null && result.getResponseData() != null) {
        volunteerTrainCourse.setTrainCourse((TrainCourseBean) result.getResponseData());
      }
    }
    if (volunteerTrainCourse.getVolunteerId() != null) {
      result = volunteerBusiness.getLeaf(volunteerTrainCourse.getVolunteerId());
      if (result != null && result.getResponseData() != null) {
        volunteerTrainCourse.setVolunteer((VolunteerBean) result.getResponseData());
      }
    }
    Map filter = new HashMap();
    filter.put("isDeleted_!=", true);
    trainCourseList = new TrainCourseBusiness().queryDataByCondition(filter, null);
    traincourseId = volunteerTrainCourse.getTraincourseId().toString();
    volunteerId = volunteerTrainCourse.getVolunteerId().toString();
    return SUCCESS;
  }

  @Override
  public String delete() throws Exception {
    getBusiness().deleteLeaf(getId());
    return SUCCESS;
  }

  @Override
  public String save() throws Exception {
    trainCourseList = new TrainCourseBusiness().queryDataByCondition(null, null);
    if (StringUtils.isNotBlank(traincourseId) && StringUtils.isNotBlank(volunteerId)) {
      volunteerTrainCourse.setTraincourseId(traincourseId);
      volunteerTrainCourse.setVolunteerId(volunteerId);
      VolunteerBean volunteer = (VolunteerBean)volunteerBus.getLeaf(volunteerId).getResponseData();
      TrainCourseBean tcBean = (TrainCourseBean)tcBus.getLeaf(traincourseId).getResponseData();
      if(null != volunteer) {
        volunteerTrainCourse.setVolunteerName(volunteer.getName());
      }
      if(null != tcBean) {
        volunteerTrainCourse.setTraincourseName(tcBean.getName());
      }
      if (StringUtils.isBlank(volunteerTrainCourse.getId())) {
        if (getBusiness().getVolunteerTrainCourseBean(volunteerId, traincourseId) != null) {
          addActionError("该志愿者已经添加了该培训教程!");
          return FAILURE;
        }
        volunteerTrainCourse.set_id(ObjectId.get());
        getBusiness().createLeaf(volunteerTrainCourse);
      } else {
        getBusiness().updateLeaf(volunteerTrainCourse, volunteerTrainCourse);
      }
      return SUCCESS;
    } else {
      addActionError("志愿者或者培训课程不能为空!");
      return FAILURE;
    }
  }

  @Override
  public String getTableTitle() {
    return "<ul class=\"breadcrumb\"><li>培训管理</li><li class=\"active\">培训记录</li></ul>";
  }
}
