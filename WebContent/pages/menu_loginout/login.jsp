<!DOCTYPE html>
<html lang="en">
<head>
   <%@ include file="../metrouiHeader.jsp" %>
   <style type="text/css">
	.metro .logininfo:before {
       content: "登录";
     }
     
   </style>
   <title>志愿者登录</title>
</head>

<body class="metro" >
<div class="container">
   <h1>
       <a href="html/welcome.jsp"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
       志愿者登录<small class="on-right"></small>
   </h1>
   <h2 id="__table__">登录</h2>
  <div  class="example logininfo" >
    <form id="loginForm" action="<%=request.getContextPath() %>/login.action" method="post">
      <fieldset>
          <h5 style="color: red;text-align: center;"><s:actionerror/><s:actionmessage/></h5>
          <label>工号</label>
          <div class="input-control text" data-role="input-control">
              <input type="text" placeholder="请输入工号,不是姓名" name="volunteer.code" autofocus required="required"/>
              <button class="btn-clear" tabindex="-1"></button>
          </div>
          
          <label>密码</label>
          <div class="input-control password" data-role="input-control">
              <input type="password" placeholder="请输入密码" name="volunteer.password" autofocus required="required"/>
              <button class="btn-reveal" tabindex="-1"></button>
          </div>
          
          <input type="submit" value="登录"/>
          <input type="button" value="指纹登录"/>
          <div style="margin-top: 20px">
          </div>
      </fieldset>
  </form>
 </div>
 </div>
 <script type="text/javascript">
    //please refer to form-validation-script.js
    $(document).ready(function() {
        $("#loginForm").validate({
            messages:{
                'volunteer.name': {
                    required: "请输入姓名"
                },
                'volunteer.password': {
                    required: "请输入密码"
                }
            }
        }); 
    });
    </script>
</body>
</html>