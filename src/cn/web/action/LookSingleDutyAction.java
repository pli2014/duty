package cn.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.services.DutyServices;
import cn.services.DutyServicesFactory;

public class LookSingleDutyAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		String dept_id = request.getParameter("dept_id");
		
		DutyServices dutySer = DutyServicesFactory.getDutyServices();
		List getDutyDepartmentList = dutySer.getDutyDepartment(dept_id);
		request.setAttribute("getDutyDepartmentList", getDutyDepartmentList);
		forward = mapping.findForward("userSingleDutyDepartment");
		return forward;
	}
}
