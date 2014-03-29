<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: wangronghua
  Date: 14-3-22
  Time: 下午1:34
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ include file="/pages/miniwechatHeader.jsp" %>

</head>
<body>
    <section class="panel">
        <%-- 消息引用 --%>
        <s:include value="../strutsMessage.jsp"/>

        <div class="panel-body">
            <form action="wechat/checkInSubmit.action" method="post" class="form-horizontal">
                <input type="hidden" name="openID" value="<s:property value='openID'/>">
                <input type="hidden" name="userID" value="<s:property value='userID'/>">
                <div class="form-group">
                    <label class="col-xs-4  control-label">微信用户</label>
                    <div class="col-xs-8">
                        <p class="form-control-static"><s:property value='wechatUser'/></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" >姓名</label>
                    <div class="col-xs-8">
                        <p class="form-control-static"><s:property value='userName'/></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label" >时间</label>
                    <div class="col-xs-8">
                        <p class="form-control-static">
                            <s:date name="currentTime" format="yyyy-MM-dd HH:mm:ss"/>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4  control-label">地点</label>
                    <div class="col-xs-8">

                        <s:iterator value="places" var="place" status="L">
                            <div class="col-xs-6" style="padding-left: 0px">
                                <label class="radio-inline">
                                    <input type="radio" name="servicePlaceId" checked value="<s:property value='%{#place.id}'/>"> <s:property value='%{#place.name}'/>
                                </label>
                            </div>
                        </s:iterator>
                    </div>
                </div>
                <button type="submit" class="btn btn-info btn-block">我要签入</button>
                <%--<button type="button" class="btn btn-info btn-block" onclick="custom_close()">返回微信</button>--%>
            </form>
        </div>
    </section>
</body>
</html>
