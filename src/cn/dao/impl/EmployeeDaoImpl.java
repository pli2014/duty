package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.DataBase.Oracle;
import cn.dao.EmployeeDao;
import cn.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao
{
	public List getAllSingleDepartmentList(String dept_id)
	{
		Oracle orcl = new Oracle();
		List aDepartmentlist = new ArrayList();
		Employee emp = null;
		String sql = "select C_USER_ID,C_USER_NAME,C_SEX,c_employee.c_department_id,C_DEPARTMENT_NAME,C_ADDRESS,C_TELEPHONE,C_MSN from c_employee,c_department where c_employee.C_DEPARTMENT_ID ='"+dept_id+"' and c_employee.C_DEPARTMENT_ID=c_department.C_DEPARTMENT_ID";
		//System.out.println("-----getAllDepartmentList----"+sql);
		ResultSet rs = orcl.executeQuery(sql);
		try {
			while (rs.next())
			{
				emp = new Employee();
				emp.setUser_id(rs.getString("C_USER_ID"));
				emp.setUser_name(rs.getString("C_USER_NAME"));
				emp.setSex(rs.getString("C_SEX"));
				emp.setDepartment_id(rs.getString("c_department_id"));
				emp.setDepartment_name(rs.getString("C_DEPARTMENT_NAME"));
				emp.setAddress(rs.getString("C_ADDRESS"));
				emp.setTelephone(rs.getString("C_TELEPHONE"));
				emp.setMsn(rs.getString("C_MSN"));
				aDepartmentlist.add(emp);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aDepartmentlist;
	}
	
	public Employee amendUser(String user_id)
	{
		Oracle orcl = new Oracle();
		Employee emp = null;
		String sql = "select C_USER_ID,C_USER_NAME,C_PASSWORD,C_SEX,C_DEPARTMENT_ID,C_ADDRESS,C_TELEPHONE,C_MSN,C_ROLE_ID from c_employee where C_USER_ID='"+user_id+"'";
		System.out.println("---------sql---------"+sql);
		ResultSet rs = orcl.executeQuery(sql);
		try {
			if (rs.next())
			{
				emp = new Employee();
				emp.setUser_id(rs.getString("C_USER_ID"));
				emp.setUser_name(rs.getString("C_USER_NAME"));
				System.out.println("----c_user_name---"+rs.getString("c_user_name"));
				emp.setPassword(rs.getString("C_PASSWORD"));
				emp.setSex(rs.getString("C_SEX"));
				emp.setDepartment_id(rs.getString("C_DEPARTMENT_ID"));
				emp.setAddress(rs.getString("C_ADDRESS"));
				emp.setTelephone(rs.getString("C_TELEPHONE"));
				emp.setMsn(rs.getString("C_MSN"));
				emp.setRole_id(rs.getString("C_ROLE_ID"));
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	public boolean getUpdateUser(Employee user)
	{
		Oracle orcl = new Oracle();
		//Employee emp = new Employee();
		String sql = "update c_employee set C_PASSWORD='"+user.getPassword()+"',C_DEPARTMENT_ID='"+user.getDepartment_id()+"',C_ADDRESS='"+user.getAddress()+"',C_TELEPHONE='"+user.getTelephone()+"',C_MSN='"+user.getMsn()+"',C_ROLE_ID="+user.getRole_id()+" where c_user_id='"+user.getUser_id()+"'";
		System.out.println("----getUpdateUser----"+sql);
		boolean temp = orcl.executeUpdate(sql);
		orcl.closeConn();
		return temp;
	}
	
	public boolean getDeleteUser(Employee user)
	{
		Oracle orcl = new Oracle();
		String sql2 = "delete from c_employee where c_user_id='"+user.getUser_id()+"'";
		//System.out.println("----getDeleteUser----"+sql);
		String sql3 ="delete from c_duty where c_user_id='"+user.getUser_id()+"'";
		boolean temp = orcl.saveOrUpdateCommit(sql2, sql3);
		orcl.closeConn();
		return temp;
	}
}
