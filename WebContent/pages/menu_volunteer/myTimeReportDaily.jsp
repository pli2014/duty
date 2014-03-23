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
                    <s:iterator value="dayValues.valueList" var="valueBean">
                        <div class="row state-overview">
                            <div class="col-lg-12">
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
                        </div>
                    </s:iterator>
                </div>
            </section>
        </div>
    </section>
</section>
</body>
</html>
