package cn.services.impl;

import java.util.List;

import cn.dao.DutyDao;
import cn.dao.DutyDaoFactory;
import cn.model.Duty;
import cn.services.DutyServices;

public class DutyServicesImpl implements DutyServices
{
	public List getDutyDepartment(String dept_id)
	{
		DutyDao dutyDao = DutyDaoFactory.getDutyDao();
		List list = dutyDao.getDutyDepartment(dept_id);
		return list;
	}
	
	public Duty amendUserDuty(String user_id)
	{
		DutyDao dutyDao = DutyDaoFactory.getDutyDao();
		Duty duty = dutyDao.amendUserDuty(user_id);
		return duty;
	}
	public String getUpdateUserDuty(Duty duty)
	{
		DutyDao dutyDao = DutyDaoFactory.getDutyDao();
		String path = "";
		boolean temp = dutyDao.getUpdateUserDuty(duty);
		if(temp)
		{
			System.out.println("更新考勤数据成功");
			path="admin";
		}else
		{
			System.out.println("更新考勤数据失败");
		}
		return path;
	}
	public List getAllDutyDepartmentList(String dept_id)
	{
		DutyDao dutyDao = DutyDaoFactory.getDutyDao();
		List list = dutyDao.getAllDutyDepartmentList(dept_id);
		return list;
	}
}