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
    
    <title>My JSP 'updateEmployeeDutyUser.jsp' starting page</title>
  </head>
  
  <body>
    <html:form action="updateEmployeeDutyEmployee">
    	<table border="1">
    		<tr>
    			<html:hidden property="user_id"/>
    			<td>
    				<bean:message key="user.name"/>
					<html:text property="user_name"></html:text>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.onduty"/>
    				<html:text property="on_duty"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.offduty"/>
    				<html:text property="off_duty"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.timeduty"/>
					<html:text property="time"></html:text>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<html:hidden property="department_id"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.task"/>
    				<html:text property="task"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.late"/>
    				<html:text property="late"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.leave"/>
    				<html:text property="leave"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.message"/>
    				<html:text property="message"/>
    			</td>
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
