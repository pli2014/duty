<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp" %>
<head>
    <title>
        批量导入志愿者
    </title>
</head>
<body>

<!--main content start-->
<section class="panel" style="padding-left: 15px;">
    <header class="panel-heading">
        批量导入志愿者
    </header>
    <%@include file="../strutsMessage.jsp" %>
    <form id="excelfile" class="form-horizontal tasi-form">
        <label style="color:green;font-size:20px">批量导入成功的信息列表 (<s:property value="arrayList.size"/>)</label>

        <div class="form-group has-success">
            <table class="table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>来源</th>
                    <th>联系电话</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="arrayList">
                    <tr>
                        <td><s:property value="code"/></td>
                        <td><s:property value="name"/></td>
                        <td>
                            <s:iterator value="listSource" var="list">
                                <s:if test="code==occupation">
                                    <s:property value="name"/>
                                </s:if>
                            </s:iterator>
                        </td>
                        <td><s:property value="cellPhone"/></td>
                    </tr>
                </tbody>
                </s:iterator>
            </table>
        </div>
        <div class="col-lg-offset-2 col-lg-10">
            <button class="btn btn-info" type="button" onclick="window.location.href='../../backend/volunteer/batchimportview.action'">继续导入</button>
            <button class="btn btn-info" type="button" onclick="window.location.href='../../backend/volunteer/index.action'">取消</button>
        </div>
    </form>
</body>
</html>