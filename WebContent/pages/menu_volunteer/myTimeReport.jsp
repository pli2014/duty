<%--
  Created by IntelliJ IDEA.
  User: wangronghua
  Date: 14-3-15
  Time: 下午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../commonHeader.jsp"%>
<html>
<head>
    <title>我的工时</title>


    <link href="jslib/flatlab/css/bootstrap.min.css" rel="stylesheet">
    <link href="jslib/flatlab/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="jslib/flatlab/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_page.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/data-tables/DT_bootstrap.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/css/TableTools.css" rel="stylesheet" />
    <link href="jslib/flatlab/assets/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="jslib/flatlab/css/style.css" rel="stylesheet">
    <link href="jslib/flatlab/css/style-responsive.css" rel="stylesheet" />
    <link href="jslib/jquery-ui-1.10.4.custom/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />

    <script src="jslib/flatlab/js/html5shiv.js"></script>
    <script src="jslib/flatlab/js/respond.min.js"></script>
    <![endif]-->

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/js/jquery.js"></script>
    <script src="jslib/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script src="jslib/flatlab/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="jslib/flatlab/js/respond.min.js" ></script>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="jslib/flatlab/assets/advanced-datatable/media/js/jquery.dataTables.js" type="text/javascript" language="javascript" ></script>
    <script src="jslib/flatlab/assets/data-tables/DT_bootstrap.js" type="text/javascript" ></script>
    <!--common script for all pages-->
    <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/ZeroClipboard.js" type="text/javascript" charset="utf-8" ></script>
    <script src="jslib/flatlab/assets/advanced-datatable/extras/TableTools/media/js/TableTools.js" type="text/javascript" charset="utf-8" ></script>
    <script src="jslib/flatlab/assets/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript" charset="utf-8" ></script>

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
