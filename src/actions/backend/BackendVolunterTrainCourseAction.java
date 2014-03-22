package actions.backend;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import vo.table.TableDataVo;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import actions.BaseTableAction;
import bl.beans.ServicePlaceBean;
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
    init.getAoColumns().add(new TableHeaderVo("volunteer.name", "志愿者"));
    init.getAoColumns().add(new TableHeaderVo("trainCourse.name", "课程名称"));
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

  public String add() {
    return SUCCESS;
  }

  @Override
  public String save() throws Exception {
    if (StringUtils.isBlank(volunteerTrainCourse.getId())) {
      volunteerTrainCourse.set_id(ObjectId.get());
      getBusiness().createLeaf(volunteerTrainCourse);
    } else {
      getBusiness().updateLeaf(volunteerTrainCourse, volunteerTrainCourse);
    }
    return SUCCESS;
  }
}
