<%--
  User: peter
  Date: 14-3-19
  Time: 下午9:32
--%>
<!DOCTYPE html>
<html lang="en">
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

    <style type="text/css">
        img.flashimg {
            width:80px;
            height:80px;
            float : left;
        }
    </style>
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="jslib/flatlab/js/respond.min.js" ></script>

    <!--common script for all pages-->
    <script src="jslib/flatlab/js/jquery.validate.min.js" type="text/javascript"></script>

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
    </div>

    <p>
    <h3>南京横渡医疗技术有限公司</h3>
    <div style="width:100%;height:100%">
        <img class="flashimg" src="data:image/jpg;base64,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"/>
        <p>南京横渡医疗技术有限公司注册于南京高新区软件园，专业从事医疗卫生行
            业相关系统软件研发和销售。公司50人的公司团队中，硕士及以上学历者达
            到10%，公司团队中既有在IT领域中造诣很深的专家，又有精通医疗行业的专
            业人员，形成了一支高水平的专业化研发和实施队伍。</p>
    </div>
</section>
</body>
</html>
