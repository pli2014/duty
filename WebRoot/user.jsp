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
    
    <title>My JSP 'user.jsp' starting page</title>
  </head>
  
  <body>
    <center>
	  <tr></tr>
	  <tr></tr>
	    <tr>
	   		<td><h1><font face="华文中宋" size="6">考勤员登陆</font></h1></td>
	    </tr>
	    <tr>
			<td>
				<hr width=50%>
			</td>
		</tr>
	    <tr></tr>

	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	     <table border="0">
	    <tr>
	    	<td align="center"><font color="#ff0000" face="微软雅黑">所在部门</font></td>
	    </tr>
	   
	   <%
    		//List departmentSingleList = (List)request.getAttribute("departmentSingleList");
    		//System.out.println("---jsp+++departmentSingleList---"+departmentSingleList);
	    %>
	    <tr>
	    	<td>
	    	 <logic:iterate id="department" name="departmentSingleList">
  				<bean:write name="department" property="department_name"/>
 			</logic:iterate>
 			</td>
	    </tr>
	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	    <tr>
	    	<td align="center"><font color="#ff0000" face="微软雅黑">员工管理</font></td>
	    </tr>
	    <tr>
	    	<td><a href="lookDut.do?dept_id=<bean:write name="department" property="department_id"/>">查看考勤</td>
	    </tr>
   </table>
   </center>
  </body>
</html>
