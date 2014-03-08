package cn.services;

import cn.services.impl.LoginServicesImpl;

public class LoginServicesFactory 
{
	public static LoginServices getServices()
	{
		LoginServices logSer = new LoginServicesImpl();
		return logSer;
	}
}