<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../metrouiHeader.jsp"%>
    <style type="text/css">
        .metro .volunteerinfo:before {
            content: "个人信息";
        }
        .metro .face:before {
            content: "个人头像";
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
    <div  style="width: 360px;margin-right:10px; float: left;">
    <div class="example face" >
        <img id="personicon" src="${volunteer.iconpath}" onerror="this.src='img/volunteer.png';">
    </div>
    <div id="cameraDialog"  style="margin-top: -30px;">
        <%@ include file="../frontend_service/flashcamera.jsp" %>
    </div>
    </div>
    <div class="example volunteerinfo" style="float: left;">
    <form id="volunteerForm" action="volunteer/save.action" method="post">
      <input name="volunteer.iconpath" id="iconpath" type="hidden" value="${volunteer.iconpath}"/>
      <fieldset>
          <s:actionerror/><s:actionmessage/>

          <%@ include file="volunteerFields.jsp"%> 
          
          <button type="submit">保存</button>
          <button type="button" onclick="window.location.href='index.action'">取消</button>
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
