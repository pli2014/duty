<!DOCTYPE html>
<html lang="en">
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="/pages/commonHeader.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="dynamic manager technique">
    <meta name="author" content="LiLimin,GuDong,WangRonghua">
    <title>志愿者注册</title>

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
            <form id="volunteerForm" action="/wechat/user/register.action" method="post" class="form-horizontal">
                <input type="hidden" name="openID" value="<s:property value='openID'/>">
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volCode">工号</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volCode" name="vol.code" value="${vol.code}" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volName">姓名</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volName" name="vol.name" value="${vol.name}" required="required"/>
                    </div>
                </div>
                  
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volIdentityCard">身份证号</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volIdentityCard" name="vol.identityCard" value="${vol.identityCard}" required="required"/>
                    </div>
                </div> 
                 
                <div class="form-group">
                    <label class="col-xs-4  control-label" for="volCellPhone">手机号</label>
                    <div class="col-xs-8">
                        <input class="form-control" id="volCellPhone" name="vol.cellPhone" value="${vol.cellPhone}" required="required"/>
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
    
    <script type="text/javascript" src="js/checkUtil.js"></script>
<script type="text/javascript">
    //please refer to form-validation-script.js
    $(document).ready(function() {
        $("#volunteerForm").validate({
            rules: {
				'vol.identityCard':{ 
				   required:true, 
                   idCardNo:true 
				}, 
				'vol.cellPhone':{ 
				   required:true, 
                   cellPhone:true 
				}
            },
            messages: {
                'vol.name': {
                    required: "请输入用户名"
                },
                'vol.identityCard': {
                    required: "请输入身份证号",
                    idCardNo: "请输入正确的身份证号"
                },
                'vol.cellPhone': {
                    required: "请输入手机",
                    cellPhone: "请输入正确的手机号, 例如：13912332122"
                }
            }
        }); 
    });
    </script>
</body>
</html>
