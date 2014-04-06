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
    <script language="javascript" type="text/javascript">
        window.onload = function () {
            setInterval("document.getElementById('timewatcher').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
        }
    </script>
    <link href="css/train.css" rel="stylesheet">
</head>
<body>
<div class="home2">
    <div class="bg-user">
        <div class="bg-fh"><img src="img/back.png" width="35" height="35" onclick="location='index.action'"
                                style="cursor: pointer"/></div>
        <div class="bg-top">谁在这里</div>

        <div class="bg-username"><s:property value="#session['sessionUser'].name"/></div>
        <div class="bg-touxiang"><img src="<s:property value="#session['sessionUser'].iconpath"/>" width="50"
                                      height="50" onerror="this.src='img/volunteer.png';"/></div>
    </div>
    <div class="bg-right2">
        <div class="bg-title2">院内</div>
        <div class="bg-time" id="timewatcher">加载当前时间</div>

        <s:iterator value="servicePlaceVolunteer">
            <s:if test="key.type==0">
                <div class="hosp-green" onclick="location='userFront/whoisherelist.action?servicePlaceId=<s:property value="key.id"/>'">
                    <div class="plase-img"><img src="<s:property value="key.serviceicon"/>"
                                                style="width:100px;height:80px">
                    </div>
                    <div class="plase-font" style="width:150px"><s:property value="key.name"/><span
                            style="float:right;color:red;font-weight:900;font-size:20px"><s:property
                            value="value.size"/></span></div>
                </div>
            </s:if>
        </s:iterator>
    </div>


    <div class="bg-right2">
        <div class="bg-title2">院外:<span id="notfoundmessage" style="color:red"></span></div>

        <style type="text/css">
            #allmap {width:900px; float: left;height:500px;margin-bottom:10px}
        </style>
        <s:set name="found" value="false"/>
        <script type="text/javascript">
            jQuery(document).ready(function () {
                <s:iterator value="servicePlaceVolunteer">
                <s:if test="key.type==1">
                <s:set name="found" value="true"/>
                var buffer = [];
                buffer.push('<div onclick="location=\'userFront/whoisherelist.action?servicePlaceId=<s:property value="key.id"/>\'">');
                buffer.push('<s:property value="key.name"/><span class="badge bg-success"><s:property value="value.size"/></span>');
                buffer.push('</div>');
                mapMutiplePosition(buffer.join(""), "<s:property value="key.longitude"/>", "<s:property value="key.latitude"/>");
                </s:if>
                </s:iterator>
            });

        </script>
        <%@ include file="../menu_serviceplace/foundpositionmap.jsp" %>
        <s:if test="#found==false">
            <script>
                jQuery("#notfoundmessage").html('没有志愿者服务!');
                //初始化地图
                initializePosition("", "", "");
            </script>
        </s:if>

    </div>
</div>
</div>
</body>
</html>
