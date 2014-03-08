package cn.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.model.Duty;
import cn.services.DutyServices;
import cn.services.DutyServicesFactory;
import cn.web.form.DutyForm;

public class getUpdateEmployeeDutyEmployeeAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("-------getUpdateDutyEmployeeAction-----");
		
		DutyForm dutyForm = (DutyForm)form;
		Duty duty = new Duty();
		duty.setUser_id(dutyForm.getUser_id());
		duty.setUser_name(dutyForm.getUser_name());
		duty.setOn_duty(dutyForm.getOn_duty());
		duty.setOff_duty(dutyForm.getOff_duty());
		duty.setTime(dutyForm.getTime());
		duty.setDepartment_id(dutyForm.getDepartment_id());
		System.out.println("------dept_id-----"+dutyForm.getDepartment_id());
		duty.setTask(dutyForm.getTask());
		duty.setLate(dutyForm.getLate());
		duty.setMessage(dutyForm.getMessage());
		
		DutyServices dutySer = DutyServicesFactory.getDutyServices();
		String path = dutySer.getUpdateUserDuty(duty);
		return new ActionForward("/getDutyDepartment2_2.do?dept_id="+dutyForm.getDepartment_id());
	}
}