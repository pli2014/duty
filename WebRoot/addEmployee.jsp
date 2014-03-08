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
    
    <title>My JSP 'addEmployee.jsp' starting page</title>
  
  <body>
  
     <html:form action="AddEmployee">
     	<table border="1">
     	<tr>
     		<td><a href="admin.jsp">返回</a></td>
     	</tr>
     	<tr>
     		<td>添加员工</td>
     	</tr>
    	<tr>
    		<td>
			<bean:message key="user.name"/>
			<html:text property="user_name"></html:text>
			</td>
		</tr>
		
		<tr>
			<td>
			<bean:message key="user.password"/>
			<html:password property="password"></html:password>
			</td>
		</tr>
		
		<tr>
			<td>
			<bean:message key="user.sex_M"/>
				<html:radio property="sex" value="男"></html:radio>
			<bean:message key="user.sex_F"/>
				<html:radio property="sex" value="女"></html:radio>
			</td>
		</tr>
		
		<%
			//List departmentList = (List)request.getAttribute("getDepartmentList");
			//List roleList = (List)request.getAttribute("getRoleList");
		 %>
		<tr>
			<td colspan="2"><bean:message key="user.department"/>
			<html:select property="department_id">
  			<html:options collection="getDepartmentList" property="department_id" labelProperty="department_name"/>
  			</html:select>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><bean:message key="user.role"/>
			<html:select property="role_id">
  			<html:options collection="getRoleList" property="role_id" labelProperty="role_name"/>
  			</html:select>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><bean:message key="user.home"/>
				<html:text property="address"></html:text>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><bean:message key="user.telephone"/>
				<html:text property="telephone"></html:text>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><bean:message key="user.msn"/>
				<html:text property="msn"></html:text>
			</td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<td><html:submit>提交</html:submit>
		</tr>
		</table>
     </html:form>
  </body>
</html>