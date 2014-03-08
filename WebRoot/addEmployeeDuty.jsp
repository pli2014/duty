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
    
    <title>My JSP 'addEmployeeDuty.jsp' starting page</title>
  </head>
  
  <body>
    <html:form action="addEmployeeDuty">
    	<table border="1">
    		<tr>
    			<td>
    				<bean:message key="user.name"/>
					<html:text property=""></html:text>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.onduty"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.offduty"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.timeduty"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				遍历出部门
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.task"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.late"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<bean:message key="duty.leave"/>
    				<html:text property=""/>
    			</td>
    		</tr>
    	</table>
    </html:form>
  </body>
</html>
