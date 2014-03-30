<%--
  Created by IntelliJ IDEA.
  User: wangronghua
  Date: 14-3-15
  Time: 下午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../bootstrapHeader.jsp" %>
<html>
<head>
    <link href="css/train.css" rel="stylesheet">
    <title>我的日工时</title>

    <style type="text/css">
        .mt15 {
            margin-top: 15px;
        }

    </style>

    <script type="text/javascript">

        function handlePrev(yearMonth) {
            //year = year - 1;
            window.location.href = "userFront/myDailyTimeReport.action?step=-1&yearMonth=" + yearMonth;
        }

        function handleNext(yearMonth) {
            //year = year + 1;
            window.location.href = "userFront/myDailyTimeReport.action?step=1&yearMonth=" + yearMonth;
        }
    </script>
</head>
<body>
    <div class="home2">
        <div class="bg-user">
            <div class="bg-fh">
                <a href="userFront/myMonthlyTimeReport.action?year=<s:property value="year" />">
                    <img src="img/back.png" width="35" height="35" />
                </a>
            </div>
            <div class="bg-top">我的日工时</div>
            <div class="bg-username">${volunteer.name}</div>
            <div class="bg-touxiang"><img src="${volunteer.iconpath}" onerror="this.src='img/volunteer.png';" width="50" height="50" /></div>
        </div>
        <div class="bg-center">

            <div class="bg-title3">
                <div class="time-pre" style="cursor: pointer;" onclick="handlePrev('<s:property value="yearMonth" />')"></div>
                <s:property value="yearMonth" />
            </div>
            <div class="bg-time"> <div class="time-next" style="cursor: pointer;" onclick="handleNext('<s:property value="yearMonth" />')"></div></div>


            <section class="container">
                <section class="panel">
                    <div class="panel-body col-xs-6">
                        <s:include value="../strutsMessage.jsp"/>
                        <s:iterator value="dayValues.valueList" var="valueBean">
                            <div class="row state-overview">
                                <section class="panel">
                                    <div class="symbol terques">
                                        <h4><s:property value="%{#valueBean.name}"/></h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="%{#valueBean.value}"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </div>
                        </s:iterator>
                    </div>
                </section>
            </section>
        </div>
    </div>


</body>
</html>
