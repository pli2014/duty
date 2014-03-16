/**
 * 
 */
package actions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import vo.table.TableHeaderVo;
import vo.table.TableInitVo;
import bl.beans.BackendUserBean;
import bl.mongobus.BackendUserBusiness;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import common.Constants;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendUserAction extends BaseTableAction<BackendUserBusiness> {
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
  public String save() throws Exception {
    if (StringUtils.isBlank(user.getId())) {
      BackendUserBean userTmp = (BackendUserBean) getBusiness().getLeafByName(user.getName()).getResponseData();
      if (userTmp != null) {
        addActionError("用户已经存在");
        return FAILURE;
      } else {
        user.set_id(ObjectId.get());
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
    getSession().setAttribute("dataId", user.getId());
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
      if (userTmp != null && userTmp.getPassword().equals(user.getPassword())) {
        getSession().setAttribute(Constants.LOGIN_BACKEND_USER_SESSION_ID, userTmp);
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
    getSession().removeAttribute(Constants.LOGIN_BACKEND_USER_SESSION_ID);
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
        getBusiness().createLeaf(user);
        getSession().setAttribute(Constants.LOGIN_BACKEND_USER_SESSION_ID, user);
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
      BackendUserBean sessionUser = (BackendUserBean) getSession().getAttribute(Constants.LOGIN_BACKEND_USER_SESSION_ID);
      if (sessionUser != null && sessionUser.getPassword().equals(user.getPassword())) {
        sessionUser.setPassword(getRequest().getParameter("newPassword"));
        getBusiness().updateLeaf(sessionUser, sessionUser);
        getSession().setAttribute(Constants.LOGIN_BACKEND_USER_SESSION_ID, sessionUser);
        return SUCCESS;
      } else {
        addActionError("原始密码错误");
      }
    }
    return FAILURE;
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
