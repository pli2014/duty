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
    <title>我的工时</title>

    <style type="text/css">
        .mt15 {
            margin-top: 15px;
        }

    </style>

    <script type="text/javascript">
        function cancel() {
            window.location.href = "index.action";
        }
    </script>
</head>
<body>
<section class="panel">
    <header class="panel-heading">
        我的工时
    </header>

    <section class="container">
        <div class="wrapper">
            <div class="row state-overview">
                <div class="col-lg-2 col-md-2 col-sm-3">
                    <a class="btn btn-success btn-block" href="javascript:window.history.go(-1);">
                        返回上一页
                    </a>
                </div>
            </div>

            <%-- 消息引用 --%>
            <s:include value="../strutsMessage.jsp"/>

            <section class="panel">
                <div class="panel-body">
                    <div class="row state-overview">
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[0].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #CDCDCD;">
                                        <h4>一月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[0].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[1].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #B0E0E6;">
                                        <h4>二月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[1].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[2].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #F5F5F5;">
                                        <h4>三月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[2].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[3].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #FFFFCD;">
                                        <h4>四月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count2"><s:property value="monthValues.valueList[3].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                    </div>

                    <div class="row state-overview">
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[4].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #DDA0DD;">
                                        <h4>五月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[4].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[5].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #FF69B4;">
                                        <h4>六月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[5].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[6].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #87CEFA;">
                                        <h4>七月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[6].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[7].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #2E8B57;">
                                        <h4>八月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count2"><s:property value="monthValues.valueList[7].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                    </div>

                    <div class="row state-overview">
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[8].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #FFD700;">
                                        <h4>九月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[8].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[9].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #D2B48C;">
                                        <h4>十月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[9].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[10].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #A020F0;">
                                        <h4>十一月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="monthValues.valueList[10].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                        <div class="col-lg-3 col-sm-3">
                            <a href="userFront/myDailyTimeReport.action?yearMonth=<s:property value='monthValues.valueList[11].name'/>">
                                <section class="panel">
                                    <div class="symbol" style="background: #FF0000;">
                                        <h4>十二月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count2"><s:property value="monthValues.valueList[11].value"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </a>
                        </div>
                    </div>

                </div>
            </section>
        </div>
    </section>
</section>
</body>
</html>
