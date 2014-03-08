package cn.dao;

import cn.dao.impl.EmployeeDaoImpl;

public class EmployeeDaoFactory 
{
	public static EmployeeDao getEmployeeDao()
	{
		EmployeeDao empDao = new EmployeeDaoImpl();
		return empDao;
	}
}