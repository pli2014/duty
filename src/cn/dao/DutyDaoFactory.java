package cn.dao;

import cn.dao.impl.DutyDaoImpl;

public class DutyDaoFactory 
{
	public static DutyDao getDutyDao()
	{
		DutyDao dutyDao = new DutyDaoImpl();
		return dutyDao;
	}
}