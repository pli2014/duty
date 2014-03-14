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
               培训课程管理
            </header>
            <form role="form" method="post" class="form-horizontal tasi-form" action="traincourse/traincoursesubmit.action">
                <div class="form-group has-success">
                    <label class="col-lg-2 control-label">课程名称</label>
                    <div class="col-lg-10">
                        <input name="trainCourse.id" type="hidden" value="${trainCourse.id}"/>
                        <input name="trainCourse.name" type="text" class="form-control" value="${trainCourse.name}"/>
                    </div>
                </div>
                <div class="form-group has-success">
                    <label class="control-label col-lg-2 col-sm-3">课程描述</label>
                    <div class="col-lg-10">
                        <s:textarea name="trainCourse.description" cols="50" rows="10"></s:textarea>
                    </div>
                </div>

                <div class="form-group has-success">
                    <label class="control-label col-lg-3">服务地点选择</label>
                    <div class="col-lg-8">
                        <table cellspacing="0" cellpadding="0" border="0" class="mt15 table table-striped table-advance table-hover table-bordered" id="userList">
                            <thead>
                            <tr>
                                <th class="column-name">选项</th>
                                <th class="column-name">地点名称</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="servicePlaces" var="sp">
                                <tr>
                                    <td><s:property value="%{#sp.name}"/> </td>
                                </tr>
                                <tr>
                                    <td><s:property value="%{#sp.name}"/> </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
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
