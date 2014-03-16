<!DOCTYPE html>
<%@ include file="../bootstrapHeader.jsp"%>
<html lang="en">
<html>
<head>
    <title>签到管理</title>
</head>
<body>
<section class="panel">
    <header class="panel-heading">
        签到管理
    </header>

    <section class="container">
        <div class="wrapper">

            <div class="row state-overview">
                <div class="col-lg-2 col-md-2 col-sm-3">
                    <a class="btn btn-success btn-block" href="index.action">
                        返回主页
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-3">
                    <a class="btn btn-success btn-block" href="userFront/checkIn.action">
                        我要签到
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-3">
                    <a class="btn btn-success btn-block" href="userFront/checkOut.action">
                        我要签出
                    </a>
                </div>
            </div>

            <%-- 消息引用 --%>
            <s:include value="../strutsMessage.jsp"/>

            <section class="panel">
                <div class="panel-body">
                    <div class="row state-overview">

                        <div class="adv-table dataTables_wrapper form-inline">
                            <table cellspacing="0" cellpadding="0" border="0" class="table table-striped table-advance table-hover table-bordered" id="userList">
                                <thead>
                                <tr>
                                    <th class="column-name">服务地点</th>
                                    <th class="column-datetime">签到时间</th>
                                    <th class="column-datetime">签出时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="userServices" var="us">
                                    <tr>
                                        <td><s:property value="%{#us.servicePlaceBean.name}"/> </td>
                                        <td><s:date name="#us.checkInTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                                        <td><s:date name="#us.checkOutTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>

                        </div>
                    </div>
                    <div class="row state-overview">
                        仅显示最近10条记录
                    </div>
                </div>
            </section>
        </div>
    </section>
</section>
</body>
</html>
