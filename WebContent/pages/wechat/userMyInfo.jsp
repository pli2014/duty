<!DOCTYPE html>
<html lang="en">
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="/pages/commonHeader.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <title>我的资料</title>

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
            <form action="/wechat/user/save.action" method="post" class="form-horizontal">
                <input type="hidden" name="openID" value="<s:property value='openID'/>">
                <div class="form-group">
                    <input type="hidden" name="vol.id" value="${volunteer.id}"/>
                    <label class="col-xs-4  control-label" for="volWechat">微信用户</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volWechat" name="vol.wechat" value="${volunteer.wechat}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volCode">工号</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volCode" name="vol.code" value="${volunteer.code}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volName">姓名</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volName" name="vol.name" value="${volunteer.name}" readonly="readonly"/>
                    </div>
                </div>
                 
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volCellPhone">手机号</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volCellPhone" name="vol.cellPhone" value="${volunteer.cellPhone}"/>
                    </div>
                </div> 
                <div class="form-group">
		             <div class="col-lg-offset-2 col-lg-10">
		                 <button class="btn btn-info btn-block" type="submit">保存</button>
		                 <button class="btn btn-info btn-block" type="button" onclick="WeixinJSBridge.call('closeWindow')">取消</button>
		             </div>
		        </div> 
            </form>
        </div>
    </section>
</body>
</html>
