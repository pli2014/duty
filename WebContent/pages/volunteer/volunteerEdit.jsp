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
        志愿者修改
      </s:if>
      <s:else>
        志愿者添加
      </s:else>
</title>
</head>
<body class="metro">
<div class="container">
   <h1>
       <a href="index.action"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>
       <s:if test="volunteer.id.length() > 0">
        志愿者修改
      </s:if>
      <s:else>
        志愿者添加
      </s:else><small class="on-right"></small>
   </h1>
   <h2 id="__table__">个人信息</h2>
   <div class="example volunteerinfo">
    <form id="volunteerForm" action="volunteer/save.action" method="post">
      <fieldset>
          <h5 style="color: red;text-align: center;"><s:actionerror/><s:actionmessage/></h5>

          <%@ include file="volunteerFields.jsp"%> 
          
          <button type="submit">保存</button>
          <button type="button" onclick="window.location.href='index.action'">取消</button>
          <div style="margin-top: 20px">
          </div>
      </fieldset>
  </form>
 </div>
 </div>
 <%@ include file="volunteerFieldsValidation.jsp"%> 
  <s:if test="volunteer.id.length() > 0">
	 <script type="text/javascript">
	 $(document).ready(function() {
	     $("form input[name='volunteer.name']").attr("readonly","readonly");
	     $("form input[name='volunteer.name']").next("button").attr("disabled","disabled");
	     $("form input[name='volunteer.identityCard']").attr("readonly","readonly");
	     $("form input[name='volunteer.identityCard']").next("button").attr("disabled","disabled");
	 });
    </script>
  </s:if>
</body>
</html>