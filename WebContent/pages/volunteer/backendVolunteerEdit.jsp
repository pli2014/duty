<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp"%>
<head>
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
     <form id="volunteerForm" class="form-horizontal tasi-form" action="backend/volunteer/save.action" method="post">
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">志愿者名</label>
             <div class="col-lg-10">
                 <input name="volunteer.id" type="hidden" value="${volunteer.id}"/>
                 <input name="volunteer.registerFrom" type="hidden" value="${volunteer.registerFrom}"/>
                 <input type="text" placeholder="志愿者名" name="volunteer.name" class="form-control" 
                        required="required" value="${volunteer.name}"/>
             </div>
         </div> 
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">工号</label>
             <div class="col-lg-10">
                <input name="volunteer.code" type="text" value="${volunteer.code}" class="form-control" required="required" placeholder="请输入工号"/>
             </div>
         </div>
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">状态</label>
             <div class="col-lg-10">
                <select name="volunteer.status" class="form-control">
                  <option value="0" <s:if test="volunteer.status == 0">selected="selected"</s:if>>已注册</option>
                  <option value="1" <s:if test="volunteer.status == 1">selected="selected"</s:if>>已审核</option>
                  <option value="2" <s:if test="volunteer.status == 2">selected="selected"</s:if>>已面试</option>
                  <option value="3" <s:if test="volunteer.status == 3">selected="selected"</s:if>>正在服务期</option>
                  <option value="4" <s:if test="volunteer.status == 4">selected="selected"</s:if>>已注销</option>
                </select>
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
               <input class="form-control "  id="confirm_password" name="confirm_password" type="password" placeholder="请再次输入密码"  required="required"/>
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
             <label class="col-lg-2 control-label">来源</label>
             <div class="col-lg-10">
                 <s:select name="volunteer.occupation" list="listSource" listKey="code" listValue="name" value="%{volunteer.occupation}"/>
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