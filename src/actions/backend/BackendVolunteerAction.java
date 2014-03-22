/**
 * 
 */
package actions.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import actions.BaseTableAction;
import bl.beans.VolunteerBean;
import bl.mongobus.VolunteerBusiness;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerAction extends BaseBackendAction<VolunteerBusiness> {
  private static Logger log = LoggerFactory.getLogger(BackendVolunteerAction.class);
  private VolunteerBean volunteer;

  public VolunteerBean getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(VolunteerBean volunteer) {
    this.volunteer = volunteer;
  }

  /**
   * 
   */
  private static final long serialVersionUID = -5222876000116738224L;

  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/volunteer";
  }

  @Override
  public String getCustomJs() {
    return getRequest().getContextPath() + "/js/volunteer.js";
  }

  @Override
  public String getTableTitle() {
    return "志愿者管理";
  }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = new TableInitVo();
    init.getAoColumns().add(new TableHeaderVo("name", "志愿者"));
    init.getAoColumns().add(new TableHeaderVo("status", "状态"));
    init.getAoColumns().add(new TableHeaderVo("registerFrom", "注册来源"));
    init.getAoColumns().add(new TableHeaderVo("sex", "性别").hidePhone());
    init.getAoColumns().add(new TableHeaderVo("cellPhone", "手机", false));
    init.getAoColumns().add(new TableHeaderVo("wechat", "微信", false));
    init.getAoColumns().add(new TableHeaderVo("email", "邮箱", false));
    return init;
  }

  @Override
  public String save() throws Exception {
    if (volunteer != null) {
      VolunteerBean volunteerTmp = (VolunteerBean) getBusiness().getLeafByName(volunteer.getName()).getResponseData();
      if (volunteerTmp != null) {
        addActionError(getText("volunteerExsited"));
        return FAILURE;
      } else {
        Map filter = new HashMap();
        filter.put("identityCard", volunteer.getIdentityCard());

        List result = getBusiness().queryDataByCondition(filter, null);
        if (result != null && result.size() > 0 && !volunteerTmp.getName().equals(volunteer.getName())) {
          addActionError(getText("idregistered"));
          return FAILURE;
        }
      }
    }
    if (StringUtils.isBlank(volunteer.getId())) {
      volunteer.set_id(ObjectId.get());
      getBusiness().createLeaf(volunteer);
    } else {
      VolunteerBean origUser = (VolunteerBean) getBusiness().getLeaf(volunteer.getId().toString()).getResponseData();
      volunteer.setPassword(origUser.getPassword());
      BeanUtils.copyProperties(origUser, volunteer);
      getBusiness().updateLeaf(origUser, origUser);
    }
    return SUCCESS;
  }

  @Override
  public String edit() throws Exception {
    volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    getSession().setAttribute("dataId", volunteer.getId());
    return SUCCESS;
  }

  @Override
  public String delete() throws Exception {
    if (getIds() != null) {
      for (String id : getIds()) {
        getBusiness().deleteLeaf(id);
      }
    }
    return SUCCESS;
  }
}
