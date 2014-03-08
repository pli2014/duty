package cn.services;

import java.util.List;

import cn.model.Employee;

public interface LoginServices 
{
	//检查用户
	public String checkUser(Employee user);
	//查询部门
	public List getDepartment();
	public List getRole();
	//检查来自DAO的插入数据库方法是否正确，并输出正确的页面
	public String getUser(Employee user);
	public String DepartmentListEntrance();
	public String DutyListEntrance();
	public List getDepartment_single(Employee user);
}
