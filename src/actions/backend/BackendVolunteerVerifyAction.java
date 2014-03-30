/**
 * 
 */
package actions.backend;

import vo.table.TableInitVo;
import vo.table.TableQueryVo;
import webapps.WebappsConstants;
import bl.beans.VolunteerBean;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerVerifyAction extends BackendVolunteerAction {
  @Override
  public String getTableTitle() {
    return "志愿者管理 / 审核";
  }

  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/volunteerVerify";
  }

  @Override
  public String getCustomJs() {
    return getRequest().getContextPath() + "/js/volunteerVerify.js";
  }

  @Override
  public TableQueryVo getModel() {
    // 0=已注册、1=已审核、2=已面试、3=正在服务期、4=已注销
    TableQueryVo model = super.getModel();
    model.getFilter().put("status", VolunteerBean.REGISTERED);
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
  public String verify() {
    VolunteerBean volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    if (volunteer != null) {
      volunteer.setStatus(VolunteerBean.VIERFIED);
      getBusiness().updateLeaf(getRequest(), volunteer);
    }
    return SUCCESS;
  }
}
