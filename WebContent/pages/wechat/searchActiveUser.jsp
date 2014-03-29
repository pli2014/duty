<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: wangronghua
  Date: 14-3-22
  Time: 下午1:34
  To change this template use File | Settings | File Templates.
--%>
<head>
    <%@ include file="/pages/miniwechatHeader.jsp" %>
    <script src="jslib/flatlab/js/jquery.scrollTo.min.js"></script>
    <script src="jslib/flatlab/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.cookie.js"></script>
    <script class="include" type="text/javascript" src="jslib/flatlab/js/jquery.dcjqaccordion.2.7.js"></script>
    <style type="text/css">
        .boder {
            border-top: 1px solid #DDDDDD;
        }

        p {
            margin: 0 0;
        }
    </style>
</head>
<body>
<section class="panel" style="margin-bottom: 0px;">
    <header class="panel-heading">
        查询条件
        <span class="tools pull-right">
            <a class="fa fa-chevron-down" href="javascript:;">收起/展开</a>
        </span>
    </header>
    <div class="panel-body" style="display: block;">
        <form id="queryForm" action="wechat/peopleHere.action" method="post" class="form-horizontal">
            <input type="hidden" name="openID" value="<s:property value='openID'/>" >
            <div class="form-group">
                <label class="col-xs-3  control-label" style="padding-right: 0px;">选择地点</label>
                <div class="col-xs-8">
                    <s:iterator value="places" var="place" status="L">
                        <div class="col-xs-6" style="padding-left: 0px">
                            <label class="radio-inline">
                                <input type="radio" name="servicePlaceId" value="<s:property value='%{#place.id}'/>"
                                        <s:if test="servicePlaceId == #place.id">
                                           checked
                                        </s:if>
                                        > <s:property value='%{#place.name}'/>
                            </label>
                        </div>
                    </s:iterator>
                </div>
                <div class="col-xs-1">
                    <a href="javascript:$('#queryForm').submit();" class="control-label fa fa-search"></a>
                </div>
            </div>
        </form>
    </div>
</section>
<section class="panel">


    <s:iterator value="activeVolunteers" var="bean">
        <div class="panel-body boder" style="padding-top: 0px; padding-bottom: 0px;">

            <a class="task-thumb" href="#">
                <img width="60px" height="50px" src="<s:property value='%{#bean.iconpath}'/>" class=""
                     onerror="this.src='img/volunteer.png'" style="margin-right: 10px; width: 60px; height: 50px; position: static;" disable="">
            </a>
            <div class="task-thumb-details" style="margin: 0 0 0 0;">
                <p style="padding: 0px"><s:property value="servicePlaceBean.name" /> </p>
                <p style="padding: 0px"><s:property value='%{#bean.name}'/></p>
                <p style="padding: 0px"><s:property value='%{#bean.cellPhone}'/></p>
            </div>
        </div>
    </s:iterator>
</section>
</body>

<script src="jslib/flatlab/js/common-scripts.js"></script>

</html>
