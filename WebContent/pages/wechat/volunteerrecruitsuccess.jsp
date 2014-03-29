<%--
  User: peter
  Date: 14-3-19
  Time: 下午9:32
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/pages/miniwechatHeader.jsp" %>

    <script type="text/javascript">

        function custom_close() {
            WeixinJSBridge.call('closeWindow');
        }
    </script>
</head>
<body>

<section class="panel">

    <div class="panel-body">
        <button type="button" class="btn btn-info btn-block" onclick="custom_close()">点击此处，返回微信！</button>
        <br>
        <br>
        <p>请记住你的工号<span style="color:red">${register.code}</span>和刚刚填写的密码，然后前往医院本部指纹和图像采集系统完成后续注册信息，感谢您在线申请志愿者服务。</p>
    </div>

</section>
</body>
</html>
