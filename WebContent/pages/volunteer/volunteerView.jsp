<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../metrouiHeader.jsp"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<link rel="shortcut icon" href="img/favicon.png">

    <style type="text/css">
        .metro .volunteerinfo:before {
            content: "个人信息";
        }
        .metro .face:before {
            content: "个人头像";
        }
    </style>
<!--external css-->
<title>我的注册</title>
</head>

<body class="metro">
  <div class="container">
   <h1>
       <a href="index.action"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
       我的注册<small class="on-right"></small>
   </h1>
   <h2 id="__table__">个人信息</h2>
   <div class="example volunteerinfo">
    <form>
      <fieldset>
          <s:actionerror/><s:actionmessage/>
          
          <label>个人照片</label>
          <div class="input-control text" data-role="input-control">
			  <img src="${volunteer.iconpath}" />
		  </div>
          
          <%@ include file="volunteerFields.jsp"%> 
          
          <button type="button" onclick="window.location.href='volunteer/edit.action?id=${volunteer.id}'">修改</button>
          <button type="button" onclick="window.location.href='volunteer/changePassword.action'">修改密码</button>
          <button type="button" onclick="window.location.href='index.action'">取消</button>
          <div style="margin-top: 20px">
          </div>
      </fieldset>
  </form>
 </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function() {
     $("form input,select").attr("disabled","disabled");
 });
 </script>
</body>
</html>