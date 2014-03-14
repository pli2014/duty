<!DOCTYPE html>
<html lang="en">
  <head>
      <%@ include file="../metrouiHeader.jsp" %>
 
  
   <style type="text/css">
	.metro .userinfo:before {
       content: "个人信息";
     }
    </style>
    <title>后台用户注册</title>
</head>
<body  class="metro" style="padding: 10px;">
    <div class="example userinfo" style="width: 100%;">
       <form action="<%=request.getContextPath() %>/backend/register.action">
           <fieldset>
               <legend>后台用户注册</legend>
               <label>用户名</label>
               <div class="input-control text" data-role="input-control">
                   <input type="text" placeholder="请输入用户名" name="user.name" value="${user.name}"/>
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