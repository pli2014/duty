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
	.metro .userinfo:before {
       content: "个人信息";
     }
     .metro .face:before {
       content: "上传头像";
     }
    </style>
    <title>注册新用户</title>
</head>
<body  class="metro" style="padding: 10px;">
    <div class="example face" style="width: 50%;float: left;">
    fasfsafsafsaf
    </div>
    <div class="example userinfo" style="width: 50%;float: left;">
       <form action="<%=request.getContextPath() %>/user/register.action">
           <fieldset>
               <legend>志愿者注册</legend>
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
               <div class="input-control password" data-role="input-control">
                   <input type="password" placeholder="请再次输入密码" autofocus/>
                   <button class="btn-reveal" tabindex="-1"></button>
               </div>
               
               <label>性别</label>
               <div class="input-control radio default-style inline-block" data-role="input-control">
                   <label class="inline-block">
                       <input type="radio" name="user.sex" value="1" checked
                         <s:if test="user.sex==1">
                           checked
                         </s:if>
                        />
                       <span class="check"></span>
                       男
                   </label>
                   <label class="inline-block">
                       <input type="radio" name="user.sex" value="2"
                         <s:if test="user.sex==2">
                           checked
                         </s:if>
                       />
                       <span class="check"></span>
                       女
                   </label>
               </div>
               <label>手机</label>
               <div class="input-control text" data-role="input-control">
                   <input type="text" placeholder="请输入手机" name="user.cellPhone" value="${user.cellPhone}"/>
                   <button class="btn-clear" tabindex="-1"></button>
               </div>
               
               <label>邮箱</label>
               <div class="input-control text" data-role="input-control">
                   <input type="text" placeholder="请输入邮箱" name="user.email" value="${user.email}"/>
                   <button class="btn-clear" tabindex="-1"></button>
               </div>

               <input type="submit" value="注册"/>
               <input type="button" onclick="history.go(-1);" value="取消"/>

               <div style="margin-top: 20px">
               </div>
           </fieldset>
       </form>
    </div>
   
   <footer class="site-footer" style="position:fixed;bottom:1px;width:100%;z-index:-1">
       <div class="text-center">
           2014-01 &copy; 版权所有
       </div>
   </footer>
   <script src="jslib/flatlab/assets/metro-ui/js/hitua.js"></script>
  </body>
</html>