<%--
  Created by IntelliJ IDEA.
  User: wangronghua
  Date: 14-3-22
  Time: 下午1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/pages/commonHeader.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <title>志愿者服务微信平台</title>

    <link rel="shortcut icon" href="jslib/flatlab/img/favicon.png">

    <!-- Bootstrap core CSS -->
    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="jslib/flatlab/css/style.css" rel="stylesheet">
    <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />
    <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="jslib/flatlab/js/respond.min.js" ></script>

    <!--common script for all pages-->
    <script src="jslib/flatlab/js/jquery.validate.min.js" type="text/javascript"></script>

</head>
<body>
    <section class="panel">
        <%-- 消息引用 --%>
        <s:include value="../strutsMessage.jsp"/>

        <div class="panel-body">
            <form action="wechat/userBindingSubmit.action" method="post" class="form-horizontal">
                <input type="hidden" name="openID" value="<s:property value='openID'/>">
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="wechatUser">微信用户</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="wechatUser" name="wechatUser" readonly="true" value="<s:property value='wechatUser'/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="userName">姓名</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="userName" name="userName" value="<s:property value='userName'/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="currentTime">时间</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="currentTime" name="currentTime" value="<s:property value='currentTime'/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="place">地点</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="place" name="place">
                    </div>
                </div>
                <button type="submit" class="btn btn-info btn-block">我要签入</button>
                <%--<button type="button" class="btn btn-info btn-block" onclick="custom_close()">返回微信</button>--%>
            </form>
        </div>
    </section>
</body>
</html>
