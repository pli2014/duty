<%--
  User: peter
  Date: 14-3-16
  Time: 上午10:07
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../bootstrapHeader.jsp" %>
    <link href="../css/train.css" rel="stylesheet">
    <title>谁在这里</title>
    <script language="javascript" type="text/javascript">
        window.onload = function () {
            setInterval("document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
        }
    </script>

    <style type="text/css">

    </style>
</head>

<body>
<div class="home2">
    <div class="bg-user">
        <div class="bg-fh"><img src="../img/back.png" width="35" height="35" onclick="location='userFront/whoishere.action'"
                                style="cursor: pointer"/></div>
        <div class="bg-top">谁在这里</div>
        <div class="bg-username"><s:property value="#session['sessionUser'].name"/></div>
        <div class="bg-touxiang"><img src="<s:property value="#session['sessionUser'].iconpath"/>" width="50"
                                      height="50" onerror="this.src='img/volunteer.png';"/></div>

        <div class="bg-center2">
            <div class="bg-title4">
                <s:property value="servicePlaceBean.name"/>
            </div>
          <s:iterator value="volunteerBeans" var="vol">
                <div class="person-train2">
                    <div class="person-train-left2"><img src="<s:property value="#vol.iconpath"/>" onerror="this.src='img/volunteer.png';"/></div>
                    <div class="person-train-right2">
                        <div class="person-name"><s:property value="#vol.name"/></div>
                        <div class="person-num"><s:property value="#vol.code"/></div>
                        <div class="person-phone"><s:property value="#vol.cellPhone"/></div>
                    </div>
                </div>
          </s:iterator>
        </div>
        <div class="person-hr"></div>
</body>
</html>
