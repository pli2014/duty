<%--
  User: peter
  Date: 14-3-16
  Time: 上午10:07
--%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>院内服务地点</title>
    <%@ include file="../bootstrapHeader.jsp" %>
</head>
<body>
<section class="panel">
    <header class="panel-heading">
        院内服务地点
    </header>

    <section class="container">

        <div class="row state-overview" style="margin-bottom:50px">
            <div class="col-lg-2 col-md-2 col-sm-3">
                <a class="btn btn-success btn-block" href="pages/menu_whoishere/serviceplaceview.jsp">
                    返回谁在这里
                </a>
            </div>
        </div>
        <s:if test="servicePlaceVolunteer.size()==0">
            <p style="color:red">院内服务地点没有志愿者服务！</p>
        </s:if>
        <%-- 消息引用 --%>
        <s:include value="../strutsMessage.jsp"/>
        <s:iterator value="servicePlaceVolunteer">
            <ul class="nav top-menu" style="float:left;margin:20px">
                <!-- settings start -->
                <li class="dropdown">
                    <div class="dropdown-toggle" data-toggle="dropdown">
                        <div style="width:150px;height:100px;border:1px solid #62ad14">
                            <div>地点编码：<s:property value="key.code"/></div>
                            <div>地点名称：<s:property value="key.name"/></div>
                            <div>服务人数：<span class="badge bg-success"><s:property value="value.size"/></span></div>
                            <div style="idth:150px;height:40px;background-color:${key.color}"></div>
                        </div>
                    </div>
                    <ul class="dropdown-menu extended tasks-bar" style="width:500px !important;max-width:500px !important;">
                        <div class="notify-arrow notify-arrow-green"></div>
                        <li>
                            <p class="green">总共<s:property value="value.size"/>个志愿者在此服务</p>
                        </li>
                        <s:iterator value="value" var="volunteer" status="id">
                            <li>
                                <span class="photo"><img src="<s:property value="#volunteer.iconpath"/>"
                                                         width="50px" height="50px"
                                                         onerror="this.src='img/volunteer.png';"/></span>
                                    <span>姓名：<s:property value="#volunteer.name"/>&nbsp;</span>
                                    <span>工号：<s:property value="#volunteer.code"/>&nbsp;</span>
                                    <span>手机：<s:property value="#volunteer.cellPhone"/></span>
                            </li>
                        </s:iterator>
                    </ul>
                </li>
            </ul>
        </s:iterator>
    </section>
</section>
</body>
</html>
