<!DOCTYPE html>
<html lang="en">
  <head>
      <%@ include file="../metrouiHeader.jsp" %>

     <script>

         (function($) {
             jQuery.fn.center = function () {
                 this.css('position','absolute');
                 this.css('top', ( $(window).height() - this.height() ) / +$(window).scrollTop() + 'px');
                 this.css('left', ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + 'px');
                 return this;
             }
         })(jQuery);
         jQuery("#cameraDialog").css('display','');
         jQuery("#cameraDialog").center();

     </script>
     
  
   <style type="text/css">
	.metro .volunteerinfo:before {
       content: "个人信息";
     }
     .metro .face:before {
       content: "上传头像";
     }
    </style>
    <title>注册新用户</title>
</head>
<body  class="metro" style="padding: 10px;">
    <div class="example face" style="width: 30%;float: left;">
      <img id="personicon" src="${volunteer.iconpath}" onerror="this.src='img/volunteer.png';">
    </div>
    <div id="cameraDialog" style="position:absolute;left:20px;top:260px">
        <%@ include file="../frontend_service/flashcamera.jsp" %>
    </div>
    <div class="example volunteerinfo" style="width: 70%;float: left;">
       <form  id="registerForm" action="register.action" method="post">
           <input name="volunteer.iconpath" id="iconpath" type="hidden" value="${volunteer.iconpath}"/>
           <fieldset>
               <legend>志愿者注册</legend>
               <h5 style="color: red;text-align: center;"><s:actionerror/><s:actionmessage/></h5>
               
               <%@ include file="volunteerFieldsAndPwd.jsp"%>
               
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
   <%@ include file="volunteerFieldsValidation.jsp"%>
  </body>
</html>