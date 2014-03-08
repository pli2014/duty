package cn.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.services.LoginServices;
import cn.services.LoginServicesFactory;

public class AddEmployeeAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("-----AddEmployeeAction-----");
		ActionForward forward =null;
		
		LoginServices loginSer = LoginServicesFactory.getServices();
		List getDepartmentList = loginSer.getDepartment();
		request.setAttribute("getDepartmentList", getDepartmentList);
		//System.out.println(getDepartmentList);
		
		List getRoleList = loginSer.getRole();
		request.setAttribute("getRoleList", getRoleList);
		//System.out.println(getRoleList);
		forward = mapping.findForward("addemployee");
		return forward;
	}
}
