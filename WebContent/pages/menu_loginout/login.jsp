<!DOCTYPE html>
<html lang="en">
<head>
 <%@ page trimDirectiveWhitespaces="true" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%-- Listing of all the taglibs that we reference in this application. --%>
	<%@ taglib uri="/struts-tags" prefix="s" %>
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
  
   <style type="text/css">
	.metro .logininfo:before {
       content: "登录";
     }
     
   </style>
   <title>志愿者登录</title>
</head>

<body class="metro" style="padding-left: 50%;padding-top: 20px;margin-left: -250px;">
  <div  class="example logininfo" style="width: 500px;">
    <form action="<%=request.getContextPath() %>/login.action" method="post">
      <fieldset>
          <legend>志愿者登录</legend>
          <label>姓名</label>
          <div class="input-control text" data-role="input-control">
              <input type="text" placeholder="请输入姓名" name="user.name" value="${user.name}"/>
              <button class="btn-clear" tabindex="-1"></button>
          </div>
          
          <label>密码</label>
          <div class="input-control password" data-role="input-control">
              <input type="password" placeholder="请输入密码" name="user.password" autofocus/>
              <button class="btn-reveal" tabindex="-1"></button>
          </div>
          
          <input type="submit" value="登录"/>
          <input type="button" value="指纹登录"/>
          <div style="margin-top: 20px">
          </div>
      </fieldset>
  </form>
 </div>
</body>
</html>