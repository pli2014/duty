package cn.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.model.Employee;
import cn.services.LoginServices;
import cn.services.LoginServicesFactory;
import cn.web.form.EmployeeForm;

public class getAddEmployeeAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------getAddEmployeeAction------");
		
		ActionForward forward =null;
		EmployeeForm employeeForm = (EmployeeForm)form;
		Employee user = new Employee();
		user.setUser_id(employeeForm.getUser_id());
		user.setUser_name(employeeForm.getUser_name());
		user.setPassword(employeeForm.getPassword());
		user.setSex(employeeForm.getSex());
		System.out.println("----getSex----"+user.getSex());
		user.setDepartment_id(employeeForm.getDepartment_id());
		user.setRole_id(employeeForm.getRole_id());
		user.setAddress(employeeForm.getAddress());
		user.setTelephone(employeeForm.getTelephone());
		System.out.println("----getaddress----"+user.getAddress());
		user.setMsn(employeeForm.getMsn());
		System.out.println("----getmsn----"+user.getMsn());
		//≤Â»Î ˝æ›ø‚
		LoginServices loginSer = LoginServicesFactory.getServices();
		String path = loginSer.getUser(user);
		forward = mapping.findForward(path);
		return forward;
	}
}
