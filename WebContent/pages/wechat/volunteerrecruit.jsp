<%--
  User: peter
  Date: 14-3-19
  Time: 下午9:32
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/pages/miniwechatHeader.jsp" %>

    <title>志愿者服务微信平台</title>


    <script type="text/javascript">

        function custom_close(){
            WeixinJSBridge.call('closeWindow');
        }
    </script>
</head>
<body>

<section class="panel">

    <div class="panel-body">
        <button type="button" class="btn btn-info btn-block" onclick="custom_close()">点击此处，返回微信！</button>

    <form action="wechat/volunteerRecruitSave.action" method="post" class="form-horizontal">
        <br>
        <input type="hidden" name="register.openID" value="${openID}">
        <div class="form-group">
            <label class="col-xs-4  control-label">工号</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" name="register.code" readonly="true" value="${register.code}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4  control-label">微信用户</label>
            <div class="col-xs-8">
                <input class="form-control" id="wechatUser" name="register.wechat" readonly="true" value="${wechatUser}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4  control-label" >姓名</label>
            <div class="col-xs-8">
                <p class="form-control-static"><input class="form-control" type="text" name="register.name" value=""/></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4  control-label" >性别</label>
            <div class="col-xs-8" data-role="input-control">
                <label class="inline-block">
                    <input type="radio" name="register.sex" value="1"/>
                    男
                </label>
                <label class="inline-block">
                    <input type="radio" name="register.sex" value="2"/>
                    女
                </label>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4  control-label" >密码</label>
            <div class="col-xs-8">
                <p class="form-control-static"><input class="form-control" id="password" type="password" name="register.password" value=""/></p>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4  control-label" >请再次输入密码</label>
            <div class="col-xs-8">
                <p class="form-control-static"><input class="form-control" type="password" name="confirm_password" value=""/></p>
            </div>
        </div>
        <script>
            $(document).ready(function() {
                $("form").validate({
                    rules: {
                        "register.name": {
                            required:true
                        },
                        "register.password": {
                            required:true
                        },
                        confirm_password: {
                            equalTo: "#password"
                        }
                    },
                    messages: {
                        'register.name': {
                            required: "请输入姓名"
                        },
                        'register.password': {
                            required: "请输入密码"
                        },
                        'confirm_password': {
                            required: "请再次输入密码",
                            equalTo: "密码两次输入不一致"
                        }
                    }
                });
            });
        </script>
         <button type="submit" class="btn btn-info btn-block">提交申请</button>
     </form>
    </div>
</section>
</body>
</html>
