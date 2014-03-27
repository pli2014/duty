<!DOCTYPE html>
<%@ include file="../frontHeader.jsp"%>
<html>
<head>
<title>列表</title>
</head>

<body>
	<div class="home2">
		<div class="bg-user">

			<div class="bg-username">${sessionUser.name}</div>
			<div class="bg-touxiang">
			  <s:if test="sessionUser.iconpath.length() > 0">
				<img src="${sessionUser.iconpath }" width="50" height="50" />
			  </s:if>
			  <s:else>
			    <img src="img/touxiang.png" width="50" height="50" />
			  </s:else>	
			</div>
		</div>
		<div class="bg-volu">
			<img src="img/volun2.jpg" width="410" height="200" />
		</div>
		<div class="bg-blue">
		  <a data-click="transform" class="tile bg-darkOrange"
				href="volunteer/view.action?id=${sessionUser.id}">
			<div class="bg-img">
				<img src="img/zc.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				我的注册
			</div>
         </a>
		</div>
		<div class="bg-green">
		  <a data-click="transform" class="tile bg-lightGreen"
				href="userFront/getCheckInRecords.action">
			<div class="bg-img">
				<img src="img/qd.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				<a href="#">我的签到</a>
			</div>
		  </a>	
		</div>
		<div class="bg-gay">
			<div class="bg-img">
				<img src="img/location.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				<a href="#">谁在这里</a>
			</div>

		</div>
		<div class="bg-green2">
		  <a data-click="transform" class="tile bg-darkViolet"
				href="trainCourse/index.action">
			<div class="bg-img">
				<img src="img/px.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				<a href="#">我的培训</a>
			</div>
		  </a>	
		</div>
		<div class="bg-black">
		  <a data-click="transform" class="tile bg-lightBlue"
				href="userFront/myTimeReport.action">
			<div class="bg-img">
				<img src="img/clock.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				<a href="#">我的工时</a>
			</div>
		  </a>	
		</div>

		<div class="bg-gray">
			<a data-click="transform" class="tile bg-lightOrange"
				href="logout.action">
			<div class="bg-img">
				<img src="img/exit.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				<a href="#">退出</a>
			</div>
           </a>
		</div>
	</div>
</body>
</html>