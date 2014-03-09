<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ page trimDirectiveWhitespaces="true" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%-- Listing of all the taglibs that we reference in this application. --%>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%-- Force the pages to not be cached, so that they are always reloaded. --%>
	<%  response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	    String path = request.getContextPath();
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	%>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <meta name="keyword" content="dynamicform,template">
    <title>注册新用户</title>
    
    <link rel="shortcut icon" href="jslib/flatlab/img/favicon.png">

    <!-- Bootstrap core CSS -->
    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_page.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/data-tables/DT_bootstrap.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/css/TableTools.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="jslib/flatlab/css/style.css" rel="stylesheet">
    <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />
    <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
    <style type="text/css">
		.ui-dialog {
			z-index: 2000;
		}
        .form-control{
            color: #000000;
        }
    </style>
    
    <style type="text/css">
		 table tbody tr.even.row_selected td{
			background-color: #B0BED9 !important;
		 }
    </style>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
      <script src="jslib/flatlab/js/html5shiv.js"></script>
      <script src="jslib/flatlab/js/respond.min.js"></script>
    <![endif]-->
    
    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="jslib/flatlab/js/respond.min.js" ></script>

   <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/assets/advanced-datatable/media/js/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
    <script src="jslib/flatlab/assets/data-tables/DT_bootstrap.js" type="text/javascript" ></script>
    <!--common script for all pages-->
    <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/ZeroClipboard.js" type="text/javascript" charset="utf-8" ></script>
    <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/TableTools.js" type="text/javascript" charset="utf-8" ></script>
    <script src="jslib/flatlab/assets/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" charset="utf-8" ></script>
  </head>

  <body>

  <section id="container" >
      <!--main content start-->
  <section class="panel">
    <header class="panel-heading">
        注册
    </header>
     <form role="form" class="form-horizontal tasi-form" action="<%=request.getContextPath() %>/user/register.action">
         <div class="form-group has-success">
             <label class="col-lg-2 control-label">姓名</label>
             <div class="col-lg-10">
                 <input name="user.id" type="hidden" value="${user.id}"/>
                 <input type="text" placeholder="" id="f-name" name="user.name" class="form-control" required="required" value="${user.name}">
                 <p class="help-block">请输入姓名</p>
             </div>
         </div>
         <div class="form-group has-error">
             <label class="col-lg-2 control-label">性别</label>
             <div class="col-lg-10">
                 <select class="form-control m-bot15" name="user.sex">
	                 <option value="1"
	                  <s:if test="user.sex==1">
	                  selected="selected"
	                 </s:if>
	                 >男</option>
	                 <option value="2"
	                 <s:if test="user.sex==2">
	                  selected="selected"
	                 </s:if>
	                 >女</option>
	               </select>
                 <p class="help-block">请选择性别</p>
             </div>
         </div>

         <div class="form-group has-error">
             <label for="password" class="control-label col-lg-2">密码</label>
           <div class="col-lg-10">
               <input class="form-control " id="password" name="user.password" type="password" />
           </div>
         </div>

         <div class="form-group has-error">
            <label for="confirm_password" class="control-label col-lg-2">确认密码</label>
           <div class="col-lg-10">
               <input class="form-control " id="confirm_password" name="confirm_password" type="password" />
           </div>
         </div>

         <div class="form-group has-warning">
             <label class="col-lg-2 control-label">邮箱</label>
             <div class="col-lg-10">
                 <input type="email" placeholder="" name="user.email"  id="email2" class="form-control" value="${user.email}">
                 <p class="help-block">请输入邮箱</p>
             </div>
         </div>

        <div class="form-group has-warning">
             <label class="col-lg-2 control-label">手机</label>
             <div class="col-lg-10">
                 <input  placeholder="" name="user.cellPhone"  class="form-control" value="${user.cellPhone}">
                 <p class="help-block">请输入手机</p>
             </div>
         </div>

         <div class="form-group">
             <div class="col-lg-offset-2 col-lg-10">
                 <button class="btn btn-danger" type="submit">注册</button>
                 <button class="btn btn-danger" type="button" onclick="history.go(-1);">取消</button>
             </div>
         </div>
     </form>
  </section>
      <!--footer start-->
   <footer class="site-footer" style="position:fixed;bottom:1px;width:100%;z-index:-1">
       <div class="text-center">
           2014-01 &copy; 版权所有
       </div>
   </footer>
      <!--footer end-->
  </section>
  <!--script for this page-->
  <script type="text/javascript" src="<%=request.getContextPath() %>/jslib/flatlab/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath() %>/jslib/flatlab/js/form-validation-script.js"></script>
      <!--common script for all pages-->
      <script src="jslib/flatlab/js/common-scripts.js"></script>
      <div id="dialog_message" title="系统消息区"></div>
  </body>
</html>