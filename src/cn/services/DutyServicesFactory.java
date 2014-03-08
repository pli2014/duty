package cn.services;

import cn.services.impl.DutyServicesImpl;

public class DutyServicesFactory 
{
	public static DutyServices getDutyServices()
	{
		DutyServices dutySer = new DutyServicesImpl();
		return dutySer;
	}
}