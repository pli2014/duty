package cn.services;

import java.util.List;

import cn.model.Duty;

public interface DutyServices 
{
	public List getDutyDepartment(String dept_id);
	public Duty amendUserDuty(String user_id);
	public String getUpdateUserDuty(Duty duty);
	public List getAllDutyDepartmentList(String dept_id);
}
