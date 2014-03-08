package cn.dao;

import java.util.List;

import cn.model.Duty;
import cn.model.Employee;

public interface DutyDao 
{
	public List getDutyDepartment(String dept_id);
	public Duty amendUserDuty(String user_id);
	public boolean getUpdateUserDuty(Duty duty);
	public List getAllDutyDepartmentList(String dept_id);
}
