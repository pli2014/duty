package cn.services.impl;

import java.util.List;

import cn.dao.LoginDao;
import cn.dao.LoginDaoFactory;
import cn.model.Employee;
import cn.services.LoginServices;

public class LoginServicesImpl implements LoginServices
{
	public String checkUser(Employee user)
	{
		LoginDao loginDao = LoginDaoFactory.getDaoImpl();
		user = loginDao.checkUser(user);
		String admin_flag = user.getAdmin_flag();
		System.out.println("----admin_flag---"+admin_flag);
		String role_id = user.getRole_id();
		
		String department_id = user.getDepartment_id();
		String path="login";
		if(admin_flag!=null)
		{
			System.out.println("-----admin_flage------"+admin_flag);
			System.out.println("--------role_id-------"+role_id);
			System.out.println("---department_id----"+department_id);
			if("Y".equals(admin_flag))
			{
				System.out.println("管理员登陆");
				path="admin";
			}
			if("N".equals(admin_flag) && "1".equals(role_id))
			{
				System.out.println("考勤员登陆");
				path="user";
			}
			if("N".equals(admin_flag) && "2".equals(role_id))
			{
				System.out.println("部门经理登录");
				path="department";
			}
		}
		return path;
	}
	
	public List getDepartment()
	{
		LoginDao loginDao = LoginDaoFactory.getDaoImpl();
		List list = loginDao.getDepartment();
		return list;
	}
	public List getRole()
	{
		LoginDao loginDao = LoginDaoFactory.getDaoImpl();
		List list = loginDao.getRole();
		return list;
	}
	public String getUser(Employee user)
	{
		LoginDao loginDao = LoginDaoFactory.getDaoImpl();
		String path="";
		boolean temp = loginDao.getUser(user);
		if(temp)
		{
			System.out.println("插入数据库成功");
			path="admin";
		}else
		{
			System.out.println("插入数据库失败");
			path="adduser";
		}
		return path;
	}
	public String DepartmentListEntrance()
	{
		String path="";
		path="DepartmentListEntrance";
		return path;
	}
	public String DutyListEntrance()
	{
		String path="";
		path="DutyListEntrance";
		return path;
	}
	
	public List getDepartment_single(Employee user)
	{
		LoginDao loginDao = LoginDaoFactory.getDaoImpl();
		List list  = loginDao.getDepartment_single(user);
		return list;
	}
}