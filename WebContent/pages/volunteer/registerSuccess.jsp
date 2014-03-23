<!DOCTYPE html>
<html lang="en">
  <head>
      <%@ include file="../metrouiHeader.jsp" %>
   <style type="text/css">
	.metro .volunteerinfo:before {
       content: "注册成功";
     }
    </style>
    <title>注册成功</title>
</head>
<body class="metro">
 <div class="container">
   <h1>
       <a href="index.action"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
       注册成功<small class="on-right"></small>
   </h1>
   <h2 id="__table__">注册成功</h2>
    <div class="example volunteerinfo">
       <h2 id="__table__">注册成功, 请牢记你的工号：<span class="red">${volunteer.code }</span>，这将作为你的登录凭证！</h2>
    </div>
  </div> 
   <footer class="site-footer" style="position:fixed;bottom:1px;width:100%;z-index:-1">
       <div class="text-center">
           2014-01 &copy; 版权所有
       </div>
   </footer> 
  </body>
</html>