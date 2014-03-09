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

    <link href="jslib/flatlab/assets/metro-ui/css/metro-bootstrap.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/css/metro-bootstrap-responsive.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/css/iconFont.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/css/docs.css" rel="stylesheet">
    <link href="jslib/flatlab/assets/metro-ui/js/prettify/prettify.css" rel="stylesheet">

    <!-- Load JavaScript Libraries -->
    <script src="jslib/flatlab/assets/metro-ui/js/jquery/jquery.min.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/js/jquery/jquery.widget.min.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/js/jquery/jquery.mousewheel.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/js/prettify/prettify.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/js/holder/holder.js"></script>

    <!-- Metro UI CSS JavaScript plugins -->
    <script src="jslib/flatlab/assets/metro-ui/js/load-metro.js"></script>

    <!-- Local JavaScript -->
    <script src="jslib/flatlab/assets/metro-ui/js/docs.js"></script>
    <script src="jslib/flatlab/assets/metro-ui/js/github.info.js"></script>

</head>

<body>
<div id="dialog_message" title="系统消息区"></div>
</body>
</html>
