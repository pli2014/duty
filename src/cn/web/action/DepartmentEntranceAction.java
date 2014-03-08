package cn.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.model.Employee;
import cn.services.LoginServices;
import cn.services.LoginServicesFactory;

public class DepartmentEntranceAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("----------DepartmentEntranceAction--------");
		Employee user = new Employee();
		LoginServices loginSer = LoginServicesFactory.getServices();
		List departmentList = loginSer.getDepartment();
		request.setAttribute("departmentList",departmentList);
		//System.out.println("------departmentList----"+departmentList);
		ActionForward forward =null;
		String path = loginSer.DepartmentListEntrance();
		forward = mapping.findForward(path);
		return forward;
	}
}