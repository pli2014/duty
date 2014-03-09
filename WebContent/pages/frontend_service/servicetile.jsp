<!DOCTYPE html>
<%@ include file="../metrouiHeader.jsp" %>
<html>

<head>

    <title>志愿者服务</title>
    <style>
        body {
            background: url('img/zhiyuanzhebackgroud.jpg');
            background-size:cover;
            background-repeat: no-repeat;
            position: relative;
            overflow:hidden;
        }

        #bar {
            position: relative;
            top: 15%;
            left: 50%
        }

    </style>
    <script>

    </script>
</head>

<body class="metro">
<div id="bar" class="tile-area">
    <div class="tile-group three" style="">
        <a data-click="transform" class="tile bg-darkOrange" href="user/edit.action?id=${sessionUser.id}">
            <div class="tile-content icon">
                <span class=" icon-clipboard-2"></span>
            </div>
            <div class="brand">
                <div class="label">我的注册</div>
            </div>
        </a>
        <a data-click="transform" class="tile bg-lightGreen" href="#">
            <div class="tile-content icon">
                <span class="icon-pencil"></span>
            </div>
            <div class="brand">
                <div class="label">我的签到</div>
            </div>
        </a>
        <a data-click="transform" class="tile bg-darkViolet" href="#">
            <div class="tile-content icon">
                <span class="icon-headphones"></span>
            </div>
            <div class="brand">
                <div class="label">我的培训</div>
            </div>
        </a>

        <a data-click="transform" class="tile bg-lightBlue" href="#">
            <div class="tile-content icon">
                <span class="icon-chronometer"></span>
            </div>
            <div class="brand">
                <div class="label">我的工时</div>
            </div>
        </a>
        <a data-click="transform" class="tile bg-darkPink" href="#">
            <div class="tile-content icon">
                <span class="icon-thumbs-up"></span>
            </div>
            <div class="brand">
                <div class="label">我的荣誉</div>
            </div>
        </a>
        <a data-click="transform" class="tile bg-lightOrange" href="#">
            <div class="tile-content icon">
                <span class="icon-libreoffice"></span>
            </div>
            <div class="brand">
                <div class="label">我的建议</div>
            </div>
        </a>
    </div>


</div>
</body>

</html>