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
		<div class="bg-blue" onclick="window.location.href='volunteer/edit.action?id=${sessionUser.id}'">
			<div class="bg-img">
				<img src="img/zc.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				我的注册
			</div>
		</div>
		<div class="bg-green" onclick="window.location.href='userFront/getCheckInRecords.action'">
			<div class="bg-img">
				<img src="img/qd.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				 我的签到
			</div>
		</div>
		<div class="bg-gay" onclick="window.location.href='userFront/whoishere.action'">
			<div class="bg-img">
				<img src="img/location.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				 谁在这里
			</div>
		</div>
		<div class="bg-green2" onclick="window.location.href='frontTraincourse/index.action'">
			<div class="bg-img">
				<img src="img/px.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				 我的培训
			</div>
		</div>
		<div class="bg-black" onclick="window.location.href='userFront/myMonthlyTimeReport.action'">
	      <div class="bg-img">
	        <img src="img/clock.png" width="100" height="100" />
	      </div>
	      <div class="bg-font">
	        我的工时
	      </div>
		</div>

		<div class="bg-gray" onclick="window.location.href='logout.action'">
			<div class="bg-img">
				<img src="img/exit.png" width="100" height="100" />
			</div>
			<div class="bg-font">
				退出
			</div>
		</div>
	</div>
</body>
</html>