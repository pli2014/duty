/**
 * 
 */
package actions.backend;

import net.sf.json.JSONArray;
import util.ServerContext;
import util.StringUtil;
import vo.table.TableDataVo;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import vo.table.TableQueryVo;
import webapps.WebappsConstants;
import bl.beans.VolunteerBean;
import bl.common.BusinessResult;
import bl.mongobus.SequenceUidGenerator;
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

  @Override
  public VolunteerBusiness getBusiness() {
    if (business == null) {
      business = new VolunteerBusiness();
    }
    return (VolunteerBusiness) business;
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
        return "<ul class=\"breadcrumb\"><li>志愿者管理</li><li class=\"active\">志愿者</li></ul>";
    }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = new TableInitVo();
    init.getAoColumns().add(new TableHeaderVo("name", "志愿者").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("code", "工号").enableSearch());
    init.getAoColumns().add(new TableHeaderVo("identityCard", "身份证").enableSearch());
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
    BusinessResult result = getBusiness().save(getRequest(), volunteer);
    if (result.getErrors().size() > 0) {
      for (Object error : result.getErrors()) {
        addActionError(error.toString());
      }
      return FAILURE;
    }
    if (result.getMessages().size() > 0) {
      for (Object message : result.getMessages()) {
        addActionMessage(message.toString());
      }
      return SUCCESS;
    }
    return SUCCESS;
  }

  @Override
  public String edit() throws Exception {
    volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    return SUCCESS;
  }

  @Override
  public String delete() throws Exception {
    if (getIds() != null) {
      for (String id : getIds()) {
        getBusiness().deleteLeaf(getRequest(), id);
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * @return
   */
  public String resetPassword() {
    volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    if (volunteer != null) {
      volunteer.setPassword(StringUtil.toMD5(volunteer.getCode()));
      getBusiness().updateLeaf(volunteer, volunteer);
      addActionMessage("密码重置成功！");
    } else {
      addActionMessage("获取用户失败！重置密码失败！");
    }
    return SUCCESS;
  }

  @Override
  public String add() {
    volunteer = new VolunteerBean();
    volunteer.setCode(ServerContext.getValue(WebappsConstants.ID_PREFIX_KEY) + SequenceUidGenerator.getNewUid());
    return SUCCESS;
  }

  public String search() {
    TableQueryVo param = new TableQueryVo();
    param.getFilter().put("name", volunteer.getName());
    param.setIDisplayLength(10);
    param.setIDisplayStart(0);
    TableDataVo dataVo = getBusiness().query(param);
    JSONArray jsonArray = JSONArray.fromObject(dataVo.getAaData());
    writeJson(jsonArray);
    return null;
  }
}
