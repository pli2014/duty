/**
 * 
 */
package actions.backend;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import util.StringUtil;
import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import webapps.WebappsConstants;
import actions.BaseTableAction;
import bl.beans.BackendUserBean;
import bl.beans.VolunteerBean;
import bl.mongobus.BackendUserBusiness;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendUserAction extends BaseBackendAction<BackendUserBusiness> {
  /**
   * 
   */
  private static final long serialVersionUID = -5222876000116738224L;
  private static Logger log = LoggerFactory.getLogger(BackendUserAction.class);

  private BackendUserBean user;

  public BackendUserBean getUser() {
    return user;
  }

  public void setUser(BackendUserBean user) {
    this.user = user;
  }

  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/user";
  }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = new TableInitVo();
    init.getAoColumns().add(new TableHeaderVo("name", "后台用户名"));
    return init;
  }

  @Override
  public String getTableTitle() {
    return "后台用户管理";
  }

  @Override
  public String save() throws Exception {
    if (StringUtils.isBlank(user.getId())) {
      BackendUserBean userTmp = (BackendUserBean) getBusiness().getLeafByName(user.getName()).getResponseData();
      if (userTmp != null) {
        addActionError("用户已经存在");
        return FAILURE;
      } else {
        user.set_id(ObjectId.get());
        user.setPassword(StringUtil.toMD5(user.getPassword()));
        getBusiness().createLeaf(user);
      }
    } else {
      BackendUserBean origUser = (BackendUserBean) getBusiness().getLeaf(user.getId().toString()).getResponseData();
      user.setPassword(origUser.getPassword());
      BeanUtils.copyProperties(origUser, user);
      getBusiness().updateLeaf(origUser, origUser);
    }
    return SUCCESS;
  }

  @Override
  public String edit() throws Exception {
    user = (BackendUserBean) getBusiness().getLeaf(getId()).getResponseData();
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

  /**
   * login
   * 
   * @return
   */
  public String login() {
    if (user != null) {
      BackendUserBean userTmp = (BackendUserBean) getBusiness().getLeafByName(user.getName()).getResponseData();
      if (userTmp != null && StringUtil.toMD5(user.getPassword()).equals(userTmp.getPassword())) {
        getSession().setAttribute(WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID, userTmp);
        return SUCCESS;
      } else {
        addActionError("用户名或者密码错误");
      }
    }
    return FAILURE;
  }

  /**
   * login
   * 
   * @return
   */
  public String logout() {
    getSession().removeAttribute(WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID);
    HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
    eraseCookie(req, resp);
    return SUCCESS;
  }

  /**
   * register
   * 
   * @return
   * @throws Exception
   */
  public String register() throws Exception {
    if (user != null) {
      BackendUserBean userTmp = (BackendUserBean) getBusiness().getLeafByName(user.getName()).getResponseData();
      if (userTmp != null) {
        addActionError("用户已经存在");
      } else {
        user.set_id(ObjectId.get());
        user.setPassword(StringUtil.toMD5(user.getPassword()));
        getBusiness().createLeaf(user);
        getSession().setAttribute(WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID, user);
        return SUCCESS;
      }
    }
    return FAILURE;
  }

  /**
   * changePassword
   * 
   * @return
   * @throws Exception
   */
  public String changePassword() throws Exception {
    if (user != null) {
      BackendUserBean sessionUser = (BackendUserBean) getSession().getAttribute(WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID);
      if (sessionUser != null && sessionUser.getPassword().equals(StringUtil.toMD5(user.getPassword()))) {
        sessionUser.setPassword(StringUtil.toMD5(getRequest().getParameter("newPassword")));
        getBusiness().updateLeaf(sessionUser, sessionUser);
        getSession().setAttribute(WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID, sessionUser);
        return SUCCESS;
      } else {
        addActionError("原始密码错误");
      }
    }
    return FAILURE;
  }

  /**
   * 
   * @return
   */
  public String resetPassword() {
    user = (BackendUserBean) getBusiness().getLeaf(getId()).getResponseData();
    if (user != null) {
      user.setPassword(StringUtil.toMD5(user.getName()));
      getBusiness().updateLeaf(user, user);
      addActionMessage("密码重置成功！");
    } else {
      addActionMessage("获取用户失败！重置密码失败！");
    }
    return SUCCESS;
  }
  
  private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
    Cookie[] cookies = req.getCookies();
    if (cookies != null)
      for (int i = 0; i < cookies.length; i++) {
        cookies[i].setValue("");
        cookies[i].setMaxAge(0);
        resp.addCookie(cookies[i]);
      }
  }
}