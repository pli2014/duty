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
    
    <title>My JSP 'allSingleDutyDepartment.jsp' starting page</title>

  </head>
  
  <body>
    <html:form action="dutyDepartment">
  <%
  	//List DutyDepartmentlist = (List)request.getSession().getAttribute("DutyDepartmentlist");
  	//System.out.println("----DutyDepartmentlist-----"+DutyDepartmentlist);
  %>
   <center>
   	<table border="1">
  	<tr>
  		<td><a href="DutyEntrance.do">返回</a></td>
  	</tr>
  	<tr>
   			<td>选择&nbsp;&nbsp;</td>
   			<td>姓名&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>上班时间</td>
   			<td>下班时间</td>
   			<td>时间&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>部门&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>任务&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>迟到&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>早退&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td colspan="2">操作</td>
   	</tr>
   	<logic:iterate id="duty" name="DutyDepartmentlist">
   		<tr>
   		<td>
	   		<html:multibox property="user_id">
	   			<bean:write name="duty" property="user_id"/>
	   		</html:multibox>
   		</td>
   		<td><bean:write name="duty" property="user_name"/></td>
   		<td><bean:write name="duty" property="on_duty"/></td>
   		<td><bean:write name="duty" property="off_duty"/></td>
   		<td><bean:write name="duty" property="time"/></td>
   		<td><bean:write name="duty" property="department_name"/></td>
   		<td><bean:write name="duty" property="task"/></td>
   		<td><bean:write name="duty" property="late"/></td>
   		<td><bean:write name="duty" property="leave"/></td>
   		<td><a href="amendEmployeeDuty.do?method=amend&id=<bean:write name="duty" property="user_id"/>&dept_id=<bean:write name="duty" property="department_id"/>"><bean:message key="user.amend"/></a></td>
   		</tr>
   	</logic:iterate>
   	 </table>
 </center>
  </html:form>
  </body>
</html>
