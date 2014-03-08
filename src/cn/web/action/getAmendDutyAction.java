package cn.web.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.model.Duty;
import cn.services.DutyServices;
import cn.services.DutyServicesFactory;
import cn.web.form.DutyForm;

public class getAmendDutyAction extends DispatchAction
{
	public ActionForward amend(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
			System.out.println("\n");
			System.out.println("------getAmendDutyAction----");
			
			//从页面获得ID
			String user_id = request.getParameter("id");
			
			//通过用户信息查出考勤记录
			DutyServices dutySer = DutyServicesFactory.getDutyServices();
			Duty duty = dutySer.amendUserDuty(user_id);
			//通过Form查出考勤
			DutyForm dutyForm = (DutyForm)form;
			dutyForm.setUser_id(duty.getUser_id());
			dutyForm.setUser_name(duty.getUser_name());
			System.out.println("-----User_name()----"+duty.getUser_name());
			dutyForm.setOn_duty(duty.getOn_duty());
			dutyForm.setOff_duty(duty.getOff_duty());
			dutyForm.setTime(duty.getTime());
			dutyForm.setTask(duty.getTask());
			dutyForm.setDepartment_id(duty.getDepartment_id());
			System.out.println("----dept_id---"+duty.getDepartment_id());
			dutyForm.setLate(duty.getLate());
			dutyForm.setLeave(duty.getLeave());
			dutyForm.setMessage(duty.getMessage());
			ActionForward forward = mapping.findForward("updateEmployeeDutyUser");
			return forward;
		}

		public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse respons) throws Exception {
			// TODO Auto-generated method stub
			//管理员不允许删除考勤
			return null;
		}
}
