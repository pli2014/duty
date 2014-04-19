/**
 * 
 */
package actions.backend;

import vo.table.TableInitVo;
import vo.table.TableQueryVo;
import bl.beans.VolunteerBean;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerInterviewAction extends BackendVolunteerAction {

    @Override
    public String getTableTitle() {
        return "<ul class=\"breadcrumb\"><li>志愿者管理</li><li class=\"active\">面试</li></ul>";
    }


  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/volunteerInterview";
  }

  @Override
  public String getCustomJs() {
    return getRequest().getContextPath() + "/js/volunteerInterview.js";
  }

  @Override
  public TableQueryVo getModel() {
    // 0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    TableQueryVo model = super.getModel();
    model.getFilter().put("status", VolunteerBean.VIERFIED);
    return model;
  }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = super.getTableInit();
    init.setDisableTools(true);
    return init;
  }

  /**
   * 
   * @return
   */
  public String interview() {
    VolunteerBean volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    setVolunteer(volunteer);
    return SUCCESS;
  }
}