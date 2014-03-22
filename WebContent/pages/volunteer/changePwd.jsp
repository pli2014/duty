<!DOCTYPE html>
<html lang="en">
  <head>
      <%@ include file="../metrouiHeader.jsp" %>
 
   <style type="text/css">
	.metro .volunteerinfo:before {
       content: "修改密码";
     }
    
    </style>
    <title>修改密码</title>
</head>
<body class="metro" style="padding-left: 50%;padding-top: 20px;">
   <div class="example volunteerinfo" style="width: 500px;margin-left: -250px;">
     <form  id="volunteerForm" action="volunteer/changePassword.action" method="post">
           <fieldset>
               <legend>修改密码</legend>
               <h5 style="color: red;text-align: center;"><s:actionerror/><s:actionmessage/></h5>
               
               <label>旧密码</label>
				<div class="input-control password" data-role="input-control">
				    <input type="password" placeholder="请输入旧密码" name="oldPassword" autofocus required="required"/>
				    <button class="btn-reveal" tabindex="-1"></button>
				</div>
				
               <label>新密码</label>
				<div class="input-control password" data-role="input-control">
				    <input type="password" placeholder="请输入新密码" id="password" name="volunteer.password" autofocus required="required"/>
				    <button class="btn-reveal" tabindex="-1"></button>
				</div>
				<div class="input-control password" data-role="input-control">
				    <input type="password" placeholder="请再次输入新密码" name="confirm_password" autofocus required="required"/>
				    <button class="btn-reveal" tabindex="-1"></button>
				</div>
               
               <input type="submit" value="修改"/>
               <input type="button" onclick="window.location.href='index.action'" value="取消"/>

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
   <%@ include file="volunteerFieldsValidation.jsp"%>
  </body>
</html>