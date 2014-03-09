<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page trimDirectiveWhitespaces="true" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%-- Listing of all the taglibs that we reference in this application. --%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
    <%-- Force the pages to not be cached, so that they are always reloaded. --%>
    <%  response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <meta name="keyword" content="dynamicform,template">
    <title>志愿者管理系统服务端</title>

    <link rel="shortcut icon" href="jslib/flatlab/img/favicon.png">

    <!-- Bootstrap core CSS -->
    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <!-- Custom styles for this template -->
    <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/metro-ui/min/metro-bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/min/metro-bootstrap-responsive.min.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/min/iconFont.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/min/metro.min.js"></script>
</head>

<body>
<div id="dialog_message" title="系统消息区"></div>
</body>
</html>
