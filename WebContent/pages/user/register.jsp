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
    <div class="example face" style="width: 30%;float: left;">
      <img id="personicon" src="${user.iconpath}" onerror="this.src='img/volunteer.png';">
      <div></div>
      <span onclick="jQuery('#cameraDialog').css({'width':'320px','height':'240px'});jQuery('#cameraDialog').center();" style="float:left">打开</span>
      <span onclick="jQuery('#cameraDialog').css({'width':'0px','height':'0px'});" style="float:left;margin-left:10px">关闭摄像头</span>
      <span style="clear: both"></span>
    </div>
    <div style="position:absolute;width:0px;height:0px;" id="cameraDialog">
        <%@ include file="../frontend_service/flashcamera.jsp" %>
    </div>
    <div class="example userinfo" style="width: 70%;float: left;">
       <form action="<%=request.getContextPath() %>/user/register.action">
           <input name="user.iconpath" id="iconpath" type="hidden" value="${user.iconpath}"/>
           <fieldset>
               <legend>志愿者注册</legend>
               <label>姓名</label>
               <div class="input-control text" data-role="input-control">
                   <input name="user.id" type="hidden" value="${user.id}"/>
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