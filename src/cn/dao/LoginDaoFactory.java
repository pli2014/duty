package cn.dao;

import cn.dao.impl.LoginDaoImpl;

public class LoginDaoFactory 
{
	public static LoginDao getDaoImpl()
	{
		LoginDao logDao = new LoginDaoImpl();
		return logDao;
	}
}