<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    //please refer to form-validation-script.js
    $(document).ready(function() {
        $("#volunteerForm").validate({
            rules: {
                confirm_password: {
                    equalTo: "#password"
                }
            },
            messages: {
                'volunteer.name': {
                    required: "请输入用户名"
                },
                'volunteer.password': {
                    required: "请输入密码"
                },
                'volunteer.identityCard': {
                    required: "请输入身份证号"
                },
                confirm_password: {
                    required: "请再次输入密码",
                    equalTo: "密码两次输入不一致"
                },
                'volunteer.cellPhone': {
                    required: "请输入手机"
                },
                'volunteer.wechat': {
                    required: "请输入微信"
                },
                'volunteer.email': {
                    required: "请输入邮箱"
                }
            }
        }); 
    });
    </script>