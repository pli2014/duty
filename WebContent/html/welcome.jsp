<!DOCTYPE html>
<html>
<head>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- Listing of all the taglibs that we reference in this application. --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%-- Force the pages to not be cached, so that they are always reloaded. --%>
<%
  response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
  response.setHeader("Pragma", "no-cache"); //HTTP 1.0
  response.setDateHeader("Expires", 0); //prevents caching at the proxy server
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet">
<title>首页</title>

</head>

<body>
	<div class="home">
		<div class="leftimg">
			<img src="img/volunteer.jpg" width="400" height="300" />
		</div>
		<div class="nav-banner1">
		  <a href="login.action">
			<div class="nav-img">
				<img src="img/contacts.png" width="100" height="100" />
			</div>
			<div class="nav-font">
				我要登陆
			</div>
			</a>
		</div>
		<div class="nav-banner2">
		   <a href="register.action">
			<div class="nav-img">
				<img src="img/sign_up.png" width="100" height="100" />
			</div>
			<div class="nav-font">
				我要注册
			</div>
			</a>
		</div>
	</div>
</body>
</html>
