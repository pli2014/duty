package cn.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Oracle 
{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public Oracle()
	{
		  try{
		    Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/duty?useUnicode=true&character_set_server=utf8&collation_server=utf8_unicode_ci&characterEncoding=utf-8","root","root");
		  
		  stmt =conn.createStatement(); 
		  } 
		  catch (Exception e)
		  { 
			  e.printStackTrace();
		  }	
	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("executeQuerty:" + e.getMessage());
		}
		return rs;
	}

	// ---------------------------��ݿ�ĸ��¡��޸ġ�ɾ�����-------------------------------------------------
	public boolean executeUpdate(String sql) // ���·���������ɾ���ģ�
	{
		boolean temp =false;
		try {
			
			int i =stmt.executeUpdate(sql);
			if(i>=1)
				temp=true;
		} catch (SQLException e) {
			System.err.println("executeUpdate:" + e.getMessage());
		}
		return temp;
	}
	
	public boolean saveOrUpdateCommit(String sql,String sql2)
	{
		boolean temp = true;
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				stmt.addBatch(sql);
				stmt.addBatch(sql2);
				stmt.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				temp =false;
			}
			//�ع�
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return temp;
	}
		
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(NullPointerException e2)
		{
			e2.printStackTrace();
		}
	}

	public void closeStmt() {
		// TODO Auto-generated method stub
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeRs() {
		// TODO Auto-generated method stub
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}
}
