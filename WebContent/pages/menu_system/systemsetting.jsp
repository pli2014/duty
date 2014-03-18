<%--
  User: peter
  Date: 14-3-18
  Time: 下午11:02
--%>
<html>
<head>
    <title>系统参数设定</title>
</head>
<body>
<!--main content start-->
<section class="panel">
    <header class="panel-heading">
        服务地点管理
    </header>
    <form role="form" method="post" class="form-horizontal tasi-form" action="backend/systemsettingsave.action">
        <%@ include file="../strutsMessage.jsp"%>
        <input type="hidden" name="systemSetting.id" value="${systemSetting.id}"/>
        <div class="form-group has-success">
            <label class="col-lg-2 control-label">百度地图序列号</label>
            <div class="col-lg-10">
                <input name="systemSetting.maptoken" type="text" class="form-control" value="${systemSetting.maptoken}"/>
            </div>
        </div>

        <div class="form-group has-success">
            <label class="control-label col-lg-2 col-sm-3">默认城市</label>
            <div class="col-lg-10">
                <input name="systemSetting.city" type="text" class="form-control" value="${systemSetting.city}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button class="btn btn-danger" type="submit">保存</button>
            </div>
        </div>
    </form>
</section>
</body>
</html>
