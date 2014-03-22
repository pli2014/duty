package actions.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import util.ServerContext;
import util.StringUtil;
import webapps.WebappsConstants;
import actions.BaseAction;
import bl.beans.VolunteerBean;
import bl.mongobus.SequenceUidGenerator;
import bl.mongobus.VolunteerBusiness;

import com.opensymphony.xwork2.ActionContext;

/**
 * Created by wangronghua on 14-3-8.
 */
public class VolunteerAction extends BaseAction {
	private String code;
	private String name;
	private String state;
	private List<VolunteerBean> volunteers;
	private VolunteerBean volunteer;
	private VolunteerBusiness business;

	public VolunteerBean getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(VolunteerBean volunteer) {
		this.volunteer = volunteer;
	}

	public String query() {
		return SUCCESS;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<VolunteerBean> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<VolunteerBean> volunteers) {
		this.volunteers = volunteers;
	}

	public VolunteerBusiness getBusiness() {
		if (business == null) {
			business = new VolunteerBusiness();
		}
		return business;
	}

	/**
	 * login
	 * 
	 * @return
	 */
	public String login() {
		if (volunteer != null) {
			VolunteerBean userTmp = getBusiness().getVolunteerBeanByCode(
					volunteer.getCode());
			if (userTmp != null
					&& StringUtil.toMD5(volunteer.getPassword()).equals(
							userTmp.getPassword())) {
				getSession().setAttribute(
						WebappsConstants.LOGIN_USER_SESSION_ID, userTmp);
				return SUCCESS;
			} else {
				addActionError("密码错误");
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
		getSession().removeAttribute(WebappsConstants.LOGIN_USER_SESSION_ID);
		HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		HttpServletResponse resp = (HttpServletResponse) ActionContext
				.getContext().get(
						org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
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
			Map filter = new HashMap();
			filter.put("identityCard", volunteer.getIdentityCard());

			List<VolunteerBean> result = getBusiness().queryDataByCondition(
					filter, null);
			if (result != null && result.size() > 0) {
				addActionError("身份证已经被注册!");
				return FAILURE;
			}

			volunteer.set_id(ObjectId.get());
			volunteer.setPassword(StringUtil.toMD5(volunteer.getPassword()));
			getBusiness().createLeaf(volunteer);
			getSession().setAttribute(WebappsConstants.LOGIN_USER_SESSION_ID,
					volunteer);
			return SUCCESS;
		} else {
			volunteer = new VolunteerBean();
			volunteer.setCode(ServerContext
					.getValue(WebappsConstants.ID_PREFIX_KEY)
					+ SequenceUidGenerator.getNewUid());
			return FAILURE;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		volunteer = (VolunteerBean) getBusiness().getLeaf(getId())
				.getResponseData();
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if (StringUtils.isBlank(volunteer.getId())) {
			volunteer.set_id(ObjectId.get());
			getBusiness().createLeaf(volunteer);
		} else {
			VolunteerBean origUser = (VolunteerBean) getBusiness().getLeaf(
					volunteer.getId().toString()).getResponseData();
			volunteer.setPassword(origUser.getPassword());
			BeanUtils.copyProperties(origUser, volunteer);
			getBusiness().updateLeaf(origUser, origUser);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		volunteer = (VolunteerBean) getBusiness().getLeaf(getId())
				.getResponseData();
		getSession().setAttribute("dataId", volunteer.getId());
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
