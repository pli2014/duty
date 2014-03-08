<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin.jsp' starting page</title>
  </head>
  
  <body>
    <html:form action="admin" method="post">
  <center>
  	<tr></tr>
	  <tr></tr>
	    <tr>
	   		<td><h1><font face="华文中宋" size="6">管理员登陆</font></h1></td>
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
	        <td align="center"><font color="#ff0000" face="微软雅黑">添&nbsp;&nbsp;&nbsp;加</font></td>
	    </tr>
	    <tr></tr>
	    <tr>
	    	<td>
	    		<center>
	    		<a href="AddEmp.do">员&nbsp;工</a>
	    		</center>
	    	</td>
	    </tr>
	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	    <tr>
		</tr>
	    <tr></tr>
	    <tr></tr>
	    <tr></tr>
	    <tr>
	    	<td align="center"><font color="#ff0000" face="微软雅黑">员工管理</font></td>
	    </tr>
	    <tr>
	    	<td><a href="DutyEntrance.do">考勤管理</a></td>
	    </tr>
	    <tr></tr>
  		<tr>
	    	<td><a href="DepartmentEntrance.do">员工管理</a></td>
	    </tr>
	</table>
 </center>
 </html:form>
  </body>
</html>