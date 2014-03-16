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
    </style>
<!--external css-->
<title>
   <s:if test="volunteer.id.length() > 0">
        修改志愿者
      </s:if>
      <s:else>
        添加志愿者
      </s:else>
</title>
</head>
<body class="metro" style="padding-left: 50%;padding-top: 20px;">
   <div class="example volunteerinfo" style="width: 500px;margin-left: -250px;">
    <form id="volunteerForm" action="volunteer/save.action" method="post">
      <fieldset>
          <legend>个人信息</legend>
          <h5 style="color: red;text-align: center;"><s:actionerror/><s:actionmessage/></h5>

          <%@ include file="volunteerFields.jsp"%> 
          
          <button type="submit">保存</button>
          <button type="button" onclick="window.location.href='index.action'">取消</button>
          <div style="margin-top: 20px">
          </div>
      </fieldset>
  </form>
 </div>
 <%@ include file="volunteerFieldsValidation.jsp"%> 
  <s:if test="volunteer.id.length() > 0">
	 <script type="text/javascript">
	 $(document).ready(function() {
	     $("form input[name='volunteer.name']").attr("readonly","readonly");
	     $("form input[name='volunteer.name']").next("button").attr("disabled","disabled");
	 });
    </script>
  </s:if>
</body>
</html>