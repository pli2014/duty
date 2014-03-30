<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../pages/frontHeader.jsp"%>
<title>首页</title>
</head>
<body>
	<div class="home">
		<div class="leftimg">
			<img src="img/volunteer.jpg" width="400" height="300" />
		</div>
		<div class="nav-banner1" onclick="window.location.href='login.action'">
			<div class="nav-img">
				<img src="img/contacts.png" width="100" height="100" />
			</div>
			<div class="nav-font">我要登陆</div>
		</div>
		<div class="nav-banner2" onclick="window.location.href='register.action'">
				<div class="nav-img">
					<img src="img/sign_up.png" width="100" height="100" />
				</div>
				<div class="nav-font">我要注册</div>
		</div>
	</div>
</body>
</html>
