package cn.services;

import java.util.List;

import cn.model.Employee;

public interface EmployeeServices 
{
	public List getAllSingleDepartmentList(String dept_id);
	public Employee amendUser(String user_id);
	public String getUpdateUser(Employee user);
	public String getDeleteUser(Employee user);
}
