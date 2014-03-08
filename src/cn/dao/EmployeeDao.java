package cn.dao;

import java.util.List;

import cn.model.Employee;

public interface EmployeeDao 
{
	//筛选表中单个部门
	public List getAllSingleDepartmentList(String dept_id);
	
	public Employee amendUser(String user_id);
	
	public boolean getUpdateUser(Employee user);
	public boolean getDeleteUser(Employee user);
}
