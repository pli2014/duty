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

public class getDutyDepartmentListAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("----getDutyDepartmentListAction----");
		System.out.println("-----½øÀ´ÁËÂð------");
		
		ActionForward forward = null;
		String dept_id = request.getParameter("dept_id");
		System.out.println("-----department_id----"+dept_id);
		DutyServices dutySer = DutyServicesFactory.getDutyServices();
		List DutyDepartmentlist = dutySer.getDutyDepartment(dept_id);
		request.setAttribute("DutyDepartmentlist", DutyDepartmentlist);
		forward = mapping.findForward("allsingledutydepartment");
		return forward;
	}
}
