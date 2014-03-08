package cn.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.model.Employee;
import cn.services.EmployeeServices;
import cn.services.EmployeeServicesFactory;
import cn.services.LoginServices;
import cn.services.LoginServicesFactory;
import cn.web.form.EmployeeForm;

public class BothMethodEmployeeAction extends DispatchAction
{
	
	//用户部门单个修改
	public ActionForward amend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println();
		System.out.println("----BothMethodEmployeeAction--amend--");
		String user_id = request.getParameter("id");
		LoginServices logSer = LoginServicesFactory.getServices();
		List departmentList = logSer.getDepartment();
		request.setAttribute("departmentList", departmentList);
		//System.out.println("------departmentList-------"+departmentList);
		List roleList = logSer.getRole();
		request.setAttribute("roleList", roleList);
		
		//从方法中查出信息，通过form显示出来
		EmployeeForm employeeForm = (EmployeeForm)form;
		EmployeeServices empSer = EmployeeServicesFactory.getEmployeeServices();
		Employee user = empSer.amendUser(user_id);
		request.getSession().setAttribute("user", user);
		//System.out.println("---user---"+user);
		employeeForm.setUser_id(user.getUser_id());
		employeeForm.setUser_name(user.getUser_name());
		System.out.println("---user_name----"+user.getUser_name());
		employeeForm.setPassword(user.getPassword());
		employeeForm.setSex(user.getSex());
		employeeForm.setDepartment_id(user.getDepartment_id());
		System.out.println("-------Department_id-----"+user.getDepartment_id());
		employeeForm.setAddress(user.getAddress());
		employeeForm.setTelephone(user.getTelephone());
		employeeForm.setRole_id(user.getRole_id());
		employeeForm.setMsn(user.getMsn());
		System.out.println("---user_msn----"+user.getMsn());
		ActionForward forward = mapping.findForward("updateuser");
		return forward;
	}
	//用户部门单个删除
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		// TODO Auto-generated method stub
		String user_id = request.getParameter("id");
		String dept_id = request.getParameter("dept_id");
		Employee user = new Employee();
		user.setUser_id(user_id);
		EmployeeServices empSer = EmployeeServicesFactory.getEmployeeServices();
		String path = empSer.getDeleteUser(user);
		return new ActionForward("/getEmployeeDepartment2_3.do?dept_id="+dept_id);
	}
}