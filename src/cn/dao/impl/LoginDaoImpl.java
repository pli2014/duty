package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.DataBase.Oracle;
import cn.dao.LoginDao;
import cn.model.Department;
import cn.model.Employee;
import cn.model.Role;

public class LoginDaoImpl implements LoginDao
{
	public Employee checkUser(Employee user)
	{
		Oracle orcl = new Oracle();
		String sql = "SELECT * FROM c_employee WHERE c_user_name='"+user.getUser_name()+"' and c_password='"+user.getPassword()+"'";
		System.out.println(sql);
		ResultSet rs = orcl.executeQuery(sql);
		try 
		{
			if(rs.next())
			{
				user.setAdmin_flag(rs.getString("c_admin_flag"));
				user.setRole_id(rs.getString("c_role_id"));
				user.setDepartment_id(rs.getString("c_department_id"));
				System.out.println("-----getDepartment_id()----"+user.getDepartment_id());
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return user;
	}
	
	public List getDepartment()
	{
		List list = new ArrayList();
		Oracle orcl = new Oracle();
		String sql = "SELECT * FROM  c_department";
		ResultSet rs = orcl.executeQuery(sql);
		try {
			while(rs.next())
			{
				Department dep = new Department();
				dep.setDepartment_id(rs.getString("c_department_id"));
				dep.setDepartment_name(rs.getString("c_department_name"));
				list.add(dep);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e2)
		{
			e2.printStackTrace();
		}
		return list;
	}
	
	public List getRole()
	{
		List list = new ArrayList();
		Oracle orcl = new Oracle();
		String sql = "SELECT * FROM  c_role";
		ResultSet rs = orcl.executeQuery(sql);
		try {
			while(rs.next())
			{
				Role role = new Role();
				role.setRole_id(rs.getString("c_role_id"));
				role.setRole_name(rs.getString("c_role_name"));
				list.add(role);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e2)
		{
			e2.printStackTrace();
		}
		return list;
	}
	
	public boolean getUser(Employee user)
	{
		Oracle orcl = new Oracle();
		String id = String.valueOf(System.currentTimeMillis());
		
		String sql2 ="insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('"+id+"','"+user.getUser_name()+"','"+user.getPassword()+"','"+user.getSex()+"','"+user.getDepartment_id()+"','"+user.getAddress()+"','"+user.getTelephone()+"','"+user.getMsn()+"','"+user.getRole_id()+"','N')";
		System.out.println("-----UpdateUser c_employee-----"+sql2);
		String sql3 ="insert into c_duty(c_user_id,c_department_id) values('"+id+"','"+user.getDepartment_id()+"')";
		System.out.println("-----UpdateUser c_duty-----"+sql3);
		boolean temp = orcl.saveOrUpdateCommit(sql2, sql3);
		return temp;
	}
	
	public List getDepartment_single(Employee user)
	{
		List list = new ArrayList();
		Oracle orcl = new Oracle();
		String sql = "select t1.c_user_id,t1.c_user_name,t1.c_department_id,t2.c_department_name from c_employee t1,c_department t2 where t1.c_department_id=t2.c_department_id and t1.c_user_name='"+user.getUser_name()+"'";
		System.out.println(sql);
		ResultSet rs = orcl.executeQuery(sql);
		try {
			while(rs.next())
			{
				user.setDepartment_name(rs.getString("c_department_name"));
				user.setDepartment_id(rs.getString("c_department_id"));
				System.out.println("--------c_department_name:"+rs.getString("c_department_name"));
				list.add(user);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}