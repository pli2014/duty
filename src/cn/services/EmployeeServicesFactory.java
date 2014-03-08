package cn.services;

import cn.services.impl.EmployeeServicesImpl;

public class EmployeeServicesFactory 
{
	public static EmployeeServices getEmployeeServices()
	{
		EmployeeServices empSer = new EmployeeServicesImpl();
		return empSer;
	}
}