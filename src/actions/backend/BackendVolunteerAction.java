/**
 * 
 */
package actions.backend;

import bl.beans.SourceCodeBean;
import bl.constants.BusTieConstant;
import bl.instancepool.SingleBusinessPoolManager;
import bl.mongobus.SourceCodeBusiness;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendVolunteerAction extends BaseBackendAction<VolunteerBusiness> {
  private static Logger log = LoggerFactory.getLogger(BackendVolunteerAction.class);
  private static SourceCodeBusiness SOURBUS = (SourceCodeBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SOURCECODE);
    private List<SourceCodeBean> listSource = null;
    private VolunteerBean volunteer;

    public List<SourceCodeBean> getListSource() {
        return listSource;
    }

    public void setListSource(List<SourceCodeBean> listSource) {
        this.listSource = listSource;
    }

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

    public String getCustomJsp() {
        return "/pages/volunteer/volunteer.jsp";
    };

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
      init.getAoColumns().add(new TableHeaderVo("occupation", "来源"));
    init.getAoColumns().add(new TableHeaderVo("registerFrom", "来自"));
    init.getAoColumns().add(new TableHeaderVo("sex", "性别").hidePhone());
    init.getAoColumns().add(new TableHeaderVo("cellPhone", "手机", false));
    init.getAoColumns().add(new TableHeaderVo("wechat", "微信", false));
    init.getAoColumns().add(new TableHeaderVo("email", "邮箱", false));
    return init;
  }

    /**
     *
     * @return
     * @throws Exception
     */
    public String index() throws Exception {
        this.listSource = (List<SourceCodeBean>) SOURBUS.getAllLeaves().getResponseData();
        return INDEX_SUCCESS;
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
    this.listSource = (List<SourceCodeBean>) SOURBUS.getAllLeaves().getResponseData();
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
