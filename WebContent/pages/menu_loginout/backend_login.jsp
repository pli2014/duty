<!DOCTYPE html>
<%  response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <meta name="keyword" content="dynamicform,template">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>志愿者后台登录</title>

    <!-- Bootstrap core CSS -->
    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="jslib/flatlab/css/style.css" rel="stylesheet">
    <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="jslib/flatlab/js/html5shiv.js"></script>
    <script src="jslib/flatlab/js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-body">

    <div class="container">

      <form class="form-signin" action="backend/login.action" method="post">
        <h2 class="form-signin-heading">后台登录</h2>
        <div class="login-wrap">
            <input type="text" name="user.name" class="form-control" placeholder="用户名" autofocus>
            <input type="password" name="user.password" class="form-control" placeholder="密码">
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
                <span class="pull-right">
                    
                </span>
            </label>
            <button class="btn btn-lg btn-login btn-block" type="submit">登录</button>
        </div> 
      </form> 
    </div> 
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script> 
  </body>
</html>
