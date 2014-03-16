<%--
  User: peter
  Date: 14-3-16
  Time: 上午10:07
--%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>签到管理</title>
    <%@ include file="../bootstrapHeader.jsp" %>
</head>
<body>
<section class="panel">
    <header class="panel-heading">
        谁在这里
    </header>

    <section class="container">

            <div class="row state-overview">
                <div class="col-lg-2 col-md-2 col-sm-3">
                    <a class="btn btn-success btn-block" href="index.action">
                        返回主页
                    </a>
                </div>
            </div>

            <%-- 消息引用 --%>
            <s:include value="../strutsMessage.jsp"/>

            <s:iterator value="servicePlaceVolunteer">
                    <div class="col-lg-3">
                        <label class="control-label" style="background:#7edfdf;font:bold"><s:property value="key.name"/></label>
                        <table cellspacing="0" cellpadding="0" border="0" class="mt15 table table-striped table-advance table-hover table-bordered" id="userList">
                            <thead>
                            <tr>
                                <th class="column-name">
                                    志愿者姓名
                                </th>
                                <th class="column-name">
                                    志愿者照片
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="value" var="volunteer" status="id">
                                <tr>
                                    <td><s:property value="#volunteer.name"/></td>
                                    <td><img src="<s:property value="#volunteer.iconpath"/>" width="80px" height="60px" onerror="this.src='img/volunteer.png';"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                  </div>
            </s:iterator>
    </section>
</section>
</body>
</html>
