<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="img/favicon.png">

    <style type="text/css">
		 table tbody tr.even.row_selected td{
			background-color: #B0BED9 !important;
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
<body>

<!--main content start-->
  <section class="panel" style="padding-left: 15px;">
    <header class="panel-heading">
       <s:if test="volunteer.id.length() > 0">
        修改志愿者
      </s:if>
      <s:else>
        添加志愿者
      </s:else>
    </header>
    <s:actionerror/><s:actionmessage/>
     <form id="volunteerForm" class="form-horizontal tasi-form" action="backend/volunteer/save.action">
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">工号</label>
             <div class="col-lg-10">
                 <input name="volunteer.code" type="text" value="${volunteer.code}" readonly="readonly" class="form-control" />
             </div>
         </div>
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">志愿者名</label>
             <div class="col-lg-10">
                 <input name="volunteer.id" type="hidden" value="${volunteer.id}"/>
                 <input name="volunteer.registerFrom" type="hidden" value="${volunteer.registerFrom}"/>
                 <input name="volunteer.status" type="hidden" value="${volunteer.status}"/>
                 <input type="text" placeholder="志愿者名" name="volunteer.name" class="form-control" 
                        required="required" value="${volunteer.name}"/>
             </div>
         </div> 
         <s:if test="volunteer.id.length() > 0">
         </s:if>
        <s:else>
          <div class="form-group has-error">
           <label for="password" class="control-label col-lg-2">密码</label>
           <div class="col-lg-10">
               <input class="form-control " id="password" name="volunteer.password" type="password" placeholder="密码"  required="required"/>
           </div>
          </div>

          <div class="form-group has-error">
            <label for="confirm_password" class="control-label col-lg-2">再次输入密码</label>
           <div class="col-lg-10">
               <input class="form-control "  name="confirm_password" type="password" placeholder="请再次输入密码"  required="required"/>
           </div>
          </div>
        </s:else>

        <div class="form-group has-success">
             <label class="col-lg-2 control-label">性别</label>
             <div class="col-lg-10">
                 <label class="inline-block">
			        <input type="radio" name="volunteer.sex" value="1" checked
			          <s:if test="volunteer.sex==1">
			            checked
			          </s:if>
			         />
			        <span class="check"></span>
			        男
			    </label>
			    <label class="inline-block">
			        <input type="radio" name="volunteer.sex" value="2"
			          <s:if test="volunteer.sex==2">
			            checked
			          </s:if>
			        />
			        <span class="check"></span>
			        女
			    </label>   
             </div>
         </div>
         
        <div class="form-group has-success">
             <label class="col-lg-2 control-label">身份证号</label>
             <div class="col-lg-10">
                 <input type="text" class="form-control" placeholder="请输入身份证号" name="volunteer.identityCard" value="${volunteer.identityCard}" required="required"/>
             </div>
         </div>

         <div class="form-group has-success">
             <label class="col-lg-2 control-label">手机</label>
             <div class="col-lg-10">
                 <input type="text" class="form-control" placeholder="请输入手机" name="volunteer.cellPhone" value="${volunteer.cellPhone}" required="required"/>
             </div>
         </div>
         
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">微信</label>
             <div class="col-lg-10">
                 <input type="text" class="form-control" placeholder="微信" name="volunteer.wechat" class="form-control" 
                        value="${volunteer.wechat}"/>
             </div>
         </div>
         
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">邮箱</label>
             <div class="col-lg-10">
                 <input type="text" placeholder="邮箱" name="volunteer.email" class="form-control" 
                         value="${volunteer.email}"/>
             </div>
         </div>
         
         <div class="form-group">
             <div class="col-lg-offset-2 col-lg-10">
                 <button class="btn btn-danger" type="submit">保存</button>
                 <s:if test="volunteer.id.length() > 0">
                 <button class="btn btn-danger" type="button" onclick="window.location.href='backend/volunteer/resetPassword.action?id=${volunteer.id}'">重置密码</button>
                 </s:if>
                 <button class="btn btn-danger" type="button" onclick="window.location.href='backend/volunteer/index.action'">取消</button>
             </div>
         </div>
         
     </form>
  </section>
   <%@ include file="volunteerFieldsValidation.jsp"%> 
  <s:if test="volunteer.id.length() > 0">
	 <script type="text/javascript">
	 $(document).ready(function() {
	     $("form input[name='volunteer.name']").attr("readonly","readonly");
	 });
    </script>
  </s:if>
</body>
</html>