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
                        <a class="btn btn-success btn-block" href="index.action">
                            返回主页
                        </a>
                    </div>
                </div>
                <section class="panel">
                    <div class="panel-body">
                        <div class="row state-overview">
                            <div class="col-lg-4 col-sm-6">
                                <section class="panel">
                                    <div class="symbol terques">
                                        <h4>今日</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class="count"><s:property value="dayHours"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </div>
                            <div class="col-lg-1 col-sm-0">
                            </div>
                            <div class="col-lg-4 col-sm-6">
                                <section class="panel">
                                    <div class="symbol red">
                                        <h4>本月</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count2"><s:property value="monthHours"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </div>
                        </div>

                        <div class="row state-overview">
                            <div class="col-lg-4 col-sm-6">
                                <section class="panel">
                                    <div class="symbol yellow">
                                        <h4>本年度</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count3"><s:property value="yearHours"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </div>
                            <div class="col-lg-1 col-sm-0">
                            </div>
                            <div class="col-lg-4 col-sm-6">
                                <section class="panel">
                                    <div class="symbol blue">
                                        <h4>总计</h4>
                                    </div>
                                    <div class="value">
                                        <h1 class=" count4"><s:property value="totalHours"/></h1>
                                        <p>小时</p>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </section>
    </section>
</body>
</html>
