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
    
    <title>My JSP 'allSingleEmployeeDepartment.jsp' starting page</title>

  </head>
  
  <body>
    <html:form action="allSingleEmployeeDepartment">
  <%
  	//List EmployeeDepartmentList = (List)request.getSession().getAttribute("EmployeeDepartmentList");
  	//System.out.println("----EmployeeDepartmentList-----"+EmployeeDepartmentList);
  %>
  <center>
   	<table border="1">
   	<tr>
	    	<td><a href="DepartmentEntrance.do">返回</a></td>
	    </tr>
   	<tr>
   			<td>选择&nbsp;&nbsp;</td>
   			<td>姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>性别&nbsp;&nbsp;</td>
   			<td>部门&nbsp;&nbsp;</td>
   			<td>电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>MSN&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td colspan="2">操作</td>
   	</tr>
   	<logic:iterate id="aDepartmentlist" name="EmployeeDepartmentList">
   		<tr>
   		<td>
	   		<html:multibox property="user_id">
	   			<bean:write name="aDepartmentlist" property="user_id"/>
	   		</html:multibox>
   		</td>
   		<td><bean:write name="aDepartmentlist" property="user_name"/></td>
   		<td><bean:write name="aDepartmentlist" property="sex"/></td>
   		
   		
   		<td><bean:write name="aDepartmentlist" property="department_name"/></td>
   		<td><bean:write name="aDepartmentlist" property="telephone"/></td>
   		<td><bean:write name="aDepartmentlist" property="msn"/></td>
   		<td><a href="amendEmployee.do?method=amend&id=<bean:write name="aDepartmentlist" property="user_id"/>&dept_id=<bean:write name="aDepartmentlist" property="department_id"/>"><bean:message key="user.amend"/></a></td>
   		<td><a href="amendEmployee.do?method=delete&id=<bean:write name="aDepartmentlist" property="user_id"/>&dept_id=<bean:write name="aDepartmentlist" property="department_id"/>"><bean:message key="user.delete"/></a></td>
   		</tr>
   	</logic:iterate>
   	</table>
   	</center>
  </html:form>
  </body>
</html>
