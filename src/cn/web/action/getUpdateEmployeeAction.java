package cn.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.model.Employee;
import cn.services.EmployeeServices;
import cn.services.EmployeeServicesFactory;
import cn.web.form.EmployeeForm;

public class getUpdateEmployeeAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("----getUpdateEmployeeAction------");
		EmployeeForm empForm = (EmployeeForm)form;
		Employee user = new Employee();
		user.setUser_id(empForm.getUser_id());
		System.out.println("---user_id---"+empForm.getUser_id());
		user.setUser_name(empForm.getUser_name());
		user.setPassword(empForm.getPassword());
		user.setDepartment_id(empForm.getDepartment_id());
		System.out.println("----Department_id---"+empForm.getDepartment_id());
		user.setAddress(empForm.getAddress());
		user.setRole_id(empForm.getRole_id());
		user.setTelephone(empForm.getTelephone());
		user.setMsn(empForm.getMsn());
		
		EmployeeServices empSer = EmployeeServicesFactory.getEmployeeServices();
		String path = empSer.getUpdateUser(user);
		System.out.println("----path---"+path);
		return new ActionForward("/getEmployeeDepartment2_3.do?dept_id="+empForm.getDepartment_id());
	}
}