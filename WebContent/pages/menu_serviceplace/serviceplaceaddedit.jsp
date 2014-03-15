<!DOCTYPE html>
<%@ include file="../commonHeader.jsp"%>
<html lang="en">
<html>
<head>
    <title>服务地点</title>
</head>
<body>
        <!--main content start-->
        <section class="panel">
            <header class="panel-heading">
                服务地点管理
            </header>
            <form role="form" method="post" class="form-horizontal tasi-form" action="backend/serviceplace/serviceplacesubmit.action">
                <div class="form-group has-success">
                    <label class="col-lg-2 control-label">地点名称</label>
                    <div class="col-lg-10">
                        <input name="servicePlace.id" type="hidden" value="${servicePlace.id}"/>
                        <input name="servicePlace.name" type="text" class="form-control" value="${servicePlace.name}"/>
                    </div>
                </div>
                <div class="form-group has-success">
                    <label class="control-label col-lg-2 col-sm-3">地点描述</label>
                    <div class="col-lg-10">
                        <s:textarea name="servicePlace.description" cols="50" rows="10"></s:textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-danger" type="submit">保存</button>
                        <button class="btn btn-danger" type="button" onclick="history.go(-1);">取消</button>
                    </div>
                </div>
            </form>
        </section>
</body>
</html>
