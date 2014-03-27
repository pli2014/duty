<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../frontHeader.jsp"%>
<title>登陆</title>
</head>
<body class="metro">
	<div class="home">
		<div class="nav-login" >
			<div class="login-font">指纹登陆</div>
			<div class="nav-img2" style="width: 350px;">
				<img src="img/fingerprint_scan.png" width="100" height="100" />
			</div>
		</div>
		<div class="nav-login2" >
			<form id="loginForm" action="login.action" method="post">
				<div class="login-font">普通登陆</div>
				<%@ include file="../strutsMessage.jsp"%>
				<div class="nav-img2">
					<input type="text" class="login-text" style="margin-left: 40px;" placeholder="请输入工号,不是姓名"
						name="volunteer.code" autofocus required="required" />
				    <input type="password" class="login-password" style="margin-left: 40px;" placeholder="请输入密码"
						name="volunteer.password" autofocus required="required" />
				</div>
				
				<div class="login-anniu" style="margin-top: 50px;margin-right: 20px;">
					<img src="img/in.png" width="34" height="34" style="cursor: pointer;" onclick="$('#loginForm').submit()"/>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
        //please refer to form-validation-script.js
        $(document).ready(function() {
            $("#loginForm").validate({
                messages : {
                    'volunteer.code' : {
                        required : "请输入工号"
                    },
                    'volunteer.password' : {
                        required : "请输入密码"
                    }
                }
            });
        });
    </script>

	<script>
        window.figureNumber = [];
        <s:iterator value="volunteerCodes" var="index">
        window.figureNumber.push({
            code : "<s:property value="#index[0]"/>",
            md5 : "<s:property value="#index[1]"/>"
        });
        </s:iterator>
        function printMessage(message) {
            jQuery("#console_message").html(message);
        }
        function callBackSubmit(code, md5) {
            jQuery("[name='volunteer.code']").val(code);
            jQuery("[name='volunteer.password']").val(md5);
            jQuery("form").submit();
        }
    </script>
	<%@include file="../finger_function/fingerverification.jsp"%>
</body>
</html>