<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'employeeDepartment.jsp' starting page</title>
  </head>
  
  <body>
	    <center>
	     <a href="admin.jsp">返回</a>
		  	<%
		  		//List departmentList = (List)request.getAttribute("departmentList");
		  	 %>
		    <logic:iterate id="department" name="departmentList">
		     <table border="0">
			    <tr>
			    </tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			    <tr>
			    	<td>
		  				<a href="getEmployeeDepartment2_3.do?dept_id=<bean:write name="department" property="department_id" />"><bean:write name="department" property="department_name" /></a>
		  			</td>
			  	</tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	<tr></tr>
			  	 </table>
		 	</logic:iterate>
	    	
	     </center>
  </body>
</html>