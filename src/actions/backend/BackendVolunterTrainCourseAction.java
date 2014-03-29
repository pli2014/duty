package actions.backend;

import java.util.List;

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
  private VolunteerTrainCourseBean volunteerTrainCourse;
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
    init.getAoColumns().add(new TableHeaderVo("volunteer.name", "志愿者").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("trainCourse.name", "课程名称").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("status", "状态"));
    return init;
  }

  @Override
  public String queryTable() throws Exception {
    TrainCourseBusiness trainCourseBusiness = new TrainCourseBusiness();
    VolunteerBusiness volunteerBusiness = new VolunteerBusiness();

    long count = getBusiness().getCount(getModel());
    TableDataVo table = getBusiness().query(getModel());

    List<VolunteerTrainCourseBean> volunteerTrainCourseList = table.getAaData();
    BusinessResult result;
    if (volunteerTrainCourseList != null) {
      for (VolunteerTrainCourseBean volunteerTrainCourseBean : volunteerTrainCourseList) {
        if (volunteerTrainCourseBean.getTraincourseId() != null) {
          result = trainCourseBusiness.getLeaf(volunteerTrainCourseBean.getTraincourseId().toString());
          if (result != null && result.getResponseData() != null) {
            volunteerTrainCourseBean.setTrainCourse((TrainCourseBean) result.getResponseData());
          }
        }
        if (volunteerTrainCourseBean.getVolunteerId() != null) {
          result = volunteerBusiness.getLeaf(volunteerTrainCourseBean.getVolunteerId().toString());
          if (result != null && result.getResponseData() != null) {
            volunteerTrainCourseBean.setVolunteer((VolunteerBean) result.getResponseData());
          }
        }
      }
    }

    table.setsEcho(getModel().getSEcho());
    table.setiTotalDisplayRecords(count);
    table.setiTotalRecords(count);

    // json
    JSONObject jsonObject = JSONObject.fromObject(table);
    writeJson(jsonObject);
    return null;
  }

  @Override
  public String add() {
    return SUCCESS;
  }

  @Override
  public String edit() throws Exception {
    volunteerTrainCourse = (VolunteerTrainCourseBean) getBusiness().getLeaf(getId()).getResponseData();
    TrainCourseBusiness trainCourseBusiness = new TrainCourseBusiness();
    VolunteerBusiness volunteerBusiness = new VolunteerBusiness();
    BusinessResult result;
    if (volunteerTrainCourse != null && volunteerTrainCourse.getTraincourseId() != null) {
      result = trainCourseBusiness.getLeaf(volunteerTrainCourse.getTraincourseId().toString());
      if (result != null && result.getResponseData() != null) {
        volunteerTrainCourse.setTrainCourse((TrainCourseBean) result.getResponseData());
      }
    }
    if (volunteerTrainCourse.getVolunteerId() != null) {
      result = volunteerBusiness.getLeaf(volunteerTrainCourse.getVolunteerId().toString());
      if (result != null && result.getResponseData() != null) {
        volunteerTrainCourse.setVolunteer((VolunteerBean) result.getResponseData());
      }
    }
    return SUCCESS;
  }

  @Override
  public String delete() throws Exception {
    getBusiness().deleteLeaf(getId());
    return SUCCESS;
  }

  @Override
  public String save() throws Exception {
    if (StringUtils.isNotBlank(traincourseId) && StringUtils.isNotBlank(volunteerId)) {
      if (StringUtils.isBlank(volunteerTrainCourse.getId())) {
        if (getBusiness().getVolunteerTrainCourseBean(volunteerId, traincourseId) != null) {
          addActionError("该志愿者已经添加了该培训教程!");
          return FAILURE;
        }
        volunteerTrainCourse.set_id(ObjectId.get());
        volunteerTrainCourse.setTraincourseId(new ObjectId(traincourseId));
        volunteerTrainCourse.setVolunteerId(new ObjectId(volunteerId));
        getBusiness().createLeaf(volunteerTrainCourse);
      } else {
        volunteerTrainCourse.setTraincourseId(new ObjectId(traincourseId));
        volunteerTrainCourse.setVolunteerId(new ObjectId(volunteerId));
        getBusiness().updateLeaf(volunteerTrainCourse, volunteerTrainCourse);
      }
      return SUCCESS;
    } else {
      addActionError("志愿者或者培训课程不能为空!");
      return FAILURE;
    }
  }
}
