package cn.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.services.EmployeeServices;
import cn.services.EmployeeServicesFactory;

public class getEmployeeDepartmentListAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("----getEmployeeDepartmentListAction----");
		System.out.println("-----½øÀ´ÁËÂð------");
		
		ActionForward forward = null;
		String department_id = request.getParameter("dept_id");
		System.out.println("-----department_id----"+department_id);
		EmployeeServices empSer = EmployeeServicesFactory.getEmployeeServices();
		List EmployeeDepartmentList = empSer.getAllSingleDepartmentList(department_id);
		request.setAttribute("EmployeeDepartmentList", EmployeeDepartmentList);
		forward = mapping.findForward("allsingledepartment");
		return forward;
	}
}