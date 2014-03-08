package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.DataBase.Oracle;
import cn.dao.DutyDao;
import cn.model.Duty;
import cn.model.Employee;

public class DutyDaoImpl implements DutyDao
{
	public List getDutyDepartment(String dept_id)
	{
		Oracle orcl = new Oracle();
		List aDutyDepartmentlist = new ArrayList();
		Duty duty = null;
		String sql = "select c_duty.c_user_id,c_user_name,c_on_duty,c_off_duty,c_time,c_duty.c_department_id,c_department.c_department_name,c_task,c_finish_id,c_late,c_leave from c_duty,c_employee,c_department where c_duty.c_user_id=c_employee.c_user_id and c_duty.C_DEPARTMENT_ID='"+dept_id+"'and c_duty.c_department_id=c_department.c_department_id";
		System.out.println(sql);
		ResultSet rs = orcl.executeQuery(sql);
		System.out.println(rs);
		try {
			while(rs.next())
			{
				duty = new Duty();
				duty.setUser_id(rs.getString("c_user_id"));
				duty.setUser_name(rs.getString("c_user_name"));
				duty.setOn_duty(rs.getString("c_on_duty"));
				duty.setOff_duty(rs.getString("c_off_duty"));
				duty.setTime(rs.getString("c_time"));
				duty.setDepartment_id(rs.getString("c_department_id"));
				duty.setDepartment_name(rs.getString("c_department_name"));
				duty.setTask(rs.getString("c_task"));
				duty.setFinish_id(rs.getString("c_finish_id"));
				duty.setLate(rs.getInt("c_late"));
				duty.setLeave(rs.getInt("c_leave"));
				aDutyDepartmentlist.add(duty);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aDutyDepartmentlist;
	}
	
	public Duty amendUserDuty(String user_id)
	{
		Oracle orcl = new Oracle();
		Duty duty = null;
		//String sql = "select C_USER_ID,C_USER_NAME,C_PASSWORD,C_SEX,C_DEPARTMENT_ID,C_ADDRESS,C_TELEPHONE,C_MSN,C_ROLE_ID from c_employee where C_USER_ID='"+user_id+"'";
		String sql = "select c_duty.c_user_id,c_user_name,c_on_duty,c_off_duty,c_time,c_duty.c_department_id,c_task,c_late,c_leave,c_message from c_duty,c_employee where c_duty.c_user_id=c_employee.c_user_id and c_duty.c_user_id='"+user_id+"'";
		System.out.println("---------sql---------"+sql);
		ResultSet rs = orcl.executeQuery(sql);
		try {
			if (rs.next())
			{
				duty = new Duty();
				duty.setUser_id(rs.getString("c_user_id"));
				duty.setUser_name(rs.getString("c_user_name"));
				System.out.println("----user_name------"+rs.getString("c_user_name"));
				duty.setOn_duty(rs.getString("c_on_duty"));
				duty.setOff_duty(rs.getString("c_off_duty"));
				duty.setTime(rs.getString("c_time"));
				duty.setDepartment_id(rs.getString("c_department_id"));
				duty.setTask(rs.getString("c_task"));
				duty.setLate(rs.getInt("c_late"));
				duty.setLeave(rs.getInt("c_leave"));
				duty.setMessage(rs.getString("c_message"));
				System.out.println("-----c_message-----"+rs.getString("c_message"));
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return duty;
	}
	
	public boolean getUpdateUserDuty(Duty duty)
	{
		Oracle orcl = new Oracle();
		//Employee emp = new Employee();
		//String sql = "update c_duty set c_on_duty='"+duty.getOn_duty()+"',c_off_duty='"+duty.getOn_duty()+"',c_time='"+duty.getTime()+"',c_task='"+duty.getTask()+"',c_late="+duty.getLate()+",c_leave="+duty.getLeave()+" where c_user_id='"+duty.getUser_id()+"'";
		//日期类型有问题
		String sql = "update c_duty set c_on_duty=to_date('"+duty.getOn_duty()+"','YYYY-MM-DD hh24:mi:ss'),c_off_duty=to_date('"+duty.getOff_duty()+"','YYYY-MM-DD hh24:mi:ss'),c_time=to_date('"+duty.getTime()+"','YYYY-MM-DD hh24:mi:ss'),c_task='"+duty.getTask()+"',c_late="+duty.getLate()+",c_leave="+duty.getLeave()+",c_message='"+duty.getMessage()+"' where c_user_id='"+duty.getUser_id()+"'";
		System.out.println("----getUpdateUserDuty----"+sql);
		boolean temp = orcl.executeUpdate(sql);
		orcl.closeConn();
		return temp;  
	}
	
	public List getAllDutyDepartmentList(String dept_id)
	{
		Oracle orcl = new Oracle();
		List aDutyDepartmentlist = new ArrayList();
		Duty duty = null;
		//String sql = "select c_duty.c_user_id,c_user_name,to_char(c_on_duty,'YYYY-MM-DD hh24:mi:ss') c_on_duty,to_char(c_off_duty,'YYYY-MM-DD hh24:mi:ss') c_off_duty,to_char(c_time,'YYYY-MM-DD hh24:mi:ss') c_time,c_duty.c_department_id,c_task,c_finish_id,c_late,c_leave from c_duty,c_employee where c_duty.c_user_id=c_employee.c_user_id and c_employee.C_DEPARTMENT_ID="+dept_id+";";
		//String sql = "select c_duty.c_user_id,c_user_name,c_on_duty,c_off_duty,c_time,c_duty.c_department_id,c_task,c_finish_id,c_late,c_leave from c_duty,c_employee where c_duty.c_user_id=c_employee.c_user_id and c_employee.C_DEPARTMENT_ID ="+dept_id+"";
		String sql = "select c_duty.c_user_id,c_user_name,c_on_duty,c_off_duty,c_time,c_duty.c_department_id,c_department_name,c_task,c_finish_id,c_late,c_leave,c_message from c_duty,c_employee,c_department where c_duty.c_user_id=c_employee.c_user_id and c_duty.C_DEPARTMENT_ID='"+dept_id+"'";
		System.out.println(sql);
		ResultSet rs = orcl.executeQuery(sql);
		System.out.println(rs);
		try {
			while(rs.next())
			{
				duty = new Duty();
				duty.setUser_id(rs.getString("c_user_id"));
				duty.setUser_name(rs.getString("c_user_name"));
				duty.setOn_duty(rs.getString("c_on_duty"));
				duty.setOff_duty(rs.getString("c_off_duty"));
				duty.setTime(rs.getString("c_time"));
				duty.setDepartment_id(rs.getString("c_department_id"));
				duty.setDepartment_name(rs.getString("c_department_name"));
				duty.setTask(rs.getString("c_task"));
				duty.setFinish_id(rs.getString("c_finish_id"));
				duty.setLate(rs.getInt("c_late"));
				duty.setLeave(rs.getInt("c_leave"));
				duty.setMessage(rs.getString("c_message"));
				aDutyDepartmentlist.add(duty);
			}
			rs.close();
			orcl.closeStmt();
			orcl.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aDutyDepartmentlist;
	}
}