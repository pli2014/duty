/**
 * 
 */
package actions;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.VolunteerBean;
import bl.mongobus.VolunteerBusiness;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerAction extends BaseTableAction<VolunteerBusiness> {
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
    init.getAoColumns().add(new TableHeaderVo("status", "状态").addSearchOptions(new String[][] { { "-1", "0", "1","2", "3" ,"4" }, { "----", "已注册","已审核","已面试","正在服务期","已注销" } }));
    init.getAoColumns().add(new TableHeaderVo("registerFrom", "注册来源").addSearchOptions(new String[][] { { "-1", "1", "2" }, { "----", "医院", "微信" } }));
    init.getAoColumns().add(new TableHeaderVo("sex", "性别").hidePhone().addSearchOptions(new String[][] { { "-1", "1", "2" }, { "----", "男", "女" } }));
    init.getAoColumns().add(new TableHeaderVo("cellPhone", "手机", false));
    init.getAoColumns().add(new TableHeaderVo("wechat", "微信", false));
    init.getAoColumns().add(new TableHeaderVo("email", "邮箱", false));
    return init;
  }

  @Override
  public String save() throws Exception {
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
