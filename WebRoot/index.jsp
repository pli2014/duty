<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for LoginForm form</title>
	</head>
	<body>
		<tr>
			<td>
				<center>
				<h1>企业员工考勤系统</h1>
				</center>
			</td>
		</tr>

		<center>
			<tr>
				<td>
					<hr width=80%>
				</td>
			</tr>

		</center>
  		<html:javascript formName="loginForm"/>
    	<html:form action="login" onsubmit="return validateLoginForm(this)">
    	<center>
    	
    	<table>
    	<tr>
    		<td>
			<bean:message key="user.name"/>
			<html:text property="name"></html:text>
			</td>
		</tr>
		
		<tr>
			<td>
			<bean:message key="user.password"/>
			<html:password property="password"></html:password>
			</td>
		</tr>
		<td></td>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<html:submit >登陆</html:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:cancel>取消</html:cancel></td>
		</tr>
		</table>
			</center>
		</html:form>
	</body>
</html>