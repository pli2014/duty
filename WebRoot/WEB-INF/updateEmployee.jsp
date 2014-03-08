<%@ page language="java" import="java.util.*,cn.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateEmployee.jsp' starting page</title>
    
  </head>
  
  <body>
    <html:form action="getUpdateUser">
    	<table border="1">
    	<tr><td>修改页面</td></tr>
    	<tr>	
    		<td><bean:message key="user.name"/>
			<html:text property="name"></html:text></td>
		</tr>
		
		<tr>
			<td><bean:message key="user.password"/>
			<html:password property="password"></html:password></td>
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
			List departmentlist = (List)request.getAttribute("departmentList");
			List roleList = (List)request.getAttribute("roleList");
		%>
		
		<%
			Employee user = (Employee)session.getAttribute("emp");
		 %>
		<tr>
    		<td>部门
    			<select name="dept_id">
    			<%for(int i=0; i<departmentlist.size(); i++) 
    			{
    				Department dp = (Department)departmentlist.get(i);
    			%>
    				<option value="<%=dp.getDepartment_id()%>"
    				<%
    				 	if(user.getDepartment_id()==dp.getDepartment_id())out.print("selected"); %>>
    				
    				<%=dp.getDepartment_name() %></option>
    			<%} %>
    			</select>
    		</td>
    	</tr>
    	
    	
		<tr>
    		<td>角色
    			<select name="role" > 
    			<%for(int i=0; i<roleList.size(); i++) 
    			{
    				Role re = (Role)roleList.get(i);
    			%>
    				<option value="<%=re.getRole_id()%>"
    			
    				<%
    					if(user.getRole_id()==(re.getRole_id()))out.print("selected"); %>>
    				<%=re.getRole_name() %></option>
    			<%} %>
    		</td>
    	</tr>
		<tr>
			<td><bean:message key="user.home"/>
			<html:text property="address"></html:text></td>
		</tr>
		
		<tr>
			<td><bean:message key="user.telephone"/>
			<html:text property="telephone"></html:text></td>
		</tr>
		
		<tr>
			<td><bean:message key="user.msn"/>
			<html:text property="msn"></html:text></td>
			
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<td><html:submit>修改</html:submit></td>
		</tr>
		</table>
    </html:form>
  </body>
</html>
