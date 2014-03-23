package actions.front;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import util.ServerContext;
import util.StringUtil;
import webapps.WebappsConstants;
import bl.beans.VolunteerBean;
import bl.common.BusinessResult;
import bl.mongobus.SequenceUidGenerator;
import bl.mongobus.VolunteerBusiness;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author gudong
 * @since $Date:2014-03-22$
 */
public class VolunteerAction extends BaseFrontAction<VolunteerBusiness> {
  private static final long serialVersionUID = 2565111276150636692L;
  private VolunteerBean volunteer;
  private String oldPassword;
  private String[][] volunteerCodes = null;

    public String[][] getVolunteerCodes() {
        return volunteerCodes;
    }

    public void setVolunteerCodes(String[][] volunteerCodes) {
        this.volunteerCodes = volunteerCodes;
    }

    public VolunteerBean getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(VolunteerBean volunteer) {
    this.volunteer = volunteer;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  /**
   * login
   * 
   * @return
   */
  public String login() {
    if (volunteer != null) {
      VolunteerBean userTmp = getBusiness().getVolunteerBeanByCode(volunteer.getCode());
      if (userTmp != null && StringUtil.toMD5(volunteer.getPassword()).equals(userTmp.getPassword())) {
        getSession().setAttribute(WebappsConstants.LOGIN_USER_SESSION_ID, userTmp);
        return SUCCESS;
      } else if(userTmp != null && volunteer.getPassword().equals(userTmp.getPassword())){
          //这是通过指纹的方式拿到MD5密码然后登陆，类似于token
          getSession().setAttribute(WebappsConstants.LOGIN_USER_SESSION_ID, userTmp);
          return SUCCESS;
      }else {
        addActionError("密码错误");
      }
    }else{
        List<VolunteerBean> volunteers = (List<VolunteerBean>)getBusiness().getAllLeaves().getResponseData();
        String[][] vols = new String[volunteers.size()][2];
        int i=0;
        for(VolunteerBean vt:volunteers){
            vols[i][0]= vt.getCode();
            vols[i][1]= vt.getPassword();
            i++;
        }
        this.volunteerCodes = vols;
    }
    return FAILURE;
  }

  /**
   * login
   * 
   * @return
   */
  public String logout() {
    getSession().removeAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
    HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
    eraseCookie(req, resp);
    return SUCCESS;
  }

  /**
   * Register
   * 
   * @return
   * @throws Exception
   */
  public String register() throws Exception {
    if (volunteer != null) {
      BusinessResult result = getBusiness().save(volunteer,getRequest().getServletContext());
      if (result.getErrors().size() > 0) {
        for (Object error : result.getErrors()) {
          addActionError(error.toString());
        }
        return FAILURE;
      }
      getSession().setAttribute(WebappsConstants.LOGIN_USER_SESSION_ID, volunteer);
      return SUCCESS;
    } else {
      volunteer = new VolunteerBean();
      volunteer.setCode(ServerContext.getValue(WebappsConstants.ID_PREFIX_KEY) + SequenceUidGenerator.getNewUid());
      return FAILURE;
    }
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String view() throws Exception {
    volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    return SUCCESS;
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String save() throws Exception {
    BusinessResult result = getBusiness().save(volunteer,getRequest().getServletContext());
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

  /**
   * 
   * @return
   * @throws Exception
   */
  public String edit() throws Exception {
    volunteer = (VolunteerBean) getBusiness().getLeaf(getId()).getResponseData();
    return SUCCESS;
  }

  /**
   * changePassword
   * 
   * @return
   * @throws Exception
   */
  public String changePassword() throws Exception {
    if (volunteer != null) {
      VolunteerBean loginedUser = getLoginedVolunteer();
      if (loginedUser != null && loginedUser.getPassword().equals(StringUtil.toMD5(oldPassword))) {
        loginedUser.setPassword(StringUtil.toMD5(volunteer.getPassword()));
        getBusiness().updateLeaf(loginedUser, loginedUser);
        setLoginVolunteer(loginedUser);
        addActionMessage("密码修改成功");
        return SUCCESS;
      } else {
        addActionError("原始密码错误");
      }
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
