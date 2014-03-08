package cn.dao;

import java.util.List;

import cn.model.Employee;

public interface LoginDao 
{
	//检查用户登录
	public Employee checkUser(Employee user);
	//遍历出注册部门
	public List getDepartment();
	//遍历出注册角色
	public List getRole();
	//插入数据库
	public boolean getUser(Employee user);
	
	public List getDepartment_single(Employee user);
}
