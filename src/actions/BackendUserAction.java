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
import bl.UserBusiness;
import bl.beans.BackendUserBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author gudong
 * @since $Date:2014-02-10$
 */
public class BackendUserAction extends BaseTableAction<UserBusiness> {
  private static Logger log = LoggerFactory.getLogger(BackendUserAction.class);
  public final static String LOGIN_BACKEND_USER_SESSION_ID = "backendSessionUser";
  private BackendUserBean user;

  public BackendUserBean getUser() {
    return user;
  }

  public void setUser(BackendUserBean user) {
    this.user = user;
  }

  /**
   * 
   */
  private static final long serialVersionUID = -5222876000116738224L;

  @Override
  public String getActionPrex() {
    return getRequest().getContextPath() + "/backend/user";
  }

  @Override
  public String getCustomJs() {
    return getRequest().getContextPath() + "/js/user.js";
  }

  @Override
  public TableInitVo getTableInit() {
    TableInitVo init = new TableInitVo();
    init.getAoColumns().add(new TableHeaderVo("name", "USERNAME"));
    return init;
  }

  @Override
  public String save() throws Exception {
    if (StringUtils.isBlank(user.getId())) {
      user.set_id(ObjectId.get());
      getBusiness().createLeaf(user);
    } else {
      BackendUserBean origUser = (BackendUserBean) getBusiness().getLeaf(user.getId().toString()).getResponseData();
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
        getSession().setAttribute(LOGIN_BACKEND_USER_SESSION_ID, userTmp);
        return SUCCESS;
      }
    }
    return "backend_login_failure";
  }

  /**
   * login
   * 
   * @return
   */
  public String logout() {
      getSession().removeAttribute(LOGIN_BACKEND_USER_SESSION_ID);
      HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
      HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
      eraseCookie(req, resp);
      return SUCCESS;
  }

  
  public String register() throws Exception {
    if (user == null) {
      return FAILURE;
    } else {
      user.set_id(ObjectId.get());
      getBusiness().createLeaf(user);
      getSession().setAttribute(LOGIN_BACKEND_USER_SESSION_ID, user);
      return SUCCESS;
    }
    
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
