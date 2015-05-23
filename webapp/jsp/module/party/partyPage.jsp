<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="container" class="ns_party">
	<main>
	<div class="left_side">
		<div class="party_profile_zone">
			<div class="cover">
				<!-- 현재는 cover클래스의 css로 커버사진을 지정했지만, 나중에 파티 설정창이 생기면.. 커버사진 업로드 될 때 img태그로 커버 사진 넣어야 하지 않나요? -->
			</div>
			<div class="setting_icon"></div>
			<div class="profile_img"></div>
			<div class="party_of_guild">
				<h1 class="party_name">${partyInfo.partyName}</h1>
				<span class="guild_name">${partyInfo.guildName}</span>
			</div>
			<ul class="profile_info">
				<li><span class="party_member">멤버</span> <span
					class="party_memberNum">${partyInfo.memberNum}</span></li>
				<li><span class="party_todo">투두</span> <span
					class="party_todoNum">${partyInfo.todoNum}</span></li>
			</ul>
			<c:choose>
				<c:when test="${partyInfo.isSignUp  == 0}">
					<div class="party_join_btn">가입하기</div>
					<div class="party_leave_btn" style="display: none">탈퇴하기</div>
				</c:when>
				<c:otherwise>
					<div class="party_join_btn" style="display: none">가입하기</div>
					<div class="party_leave_btn">탈퇴하기</div>
				</c:otherwise>
			</c:choose>
		</div>

		<section class="party_all_todos">
			<div class="category">
				<h1 class="name">TODO LIST</h1>
			</div>
			<div class="party_todo_list">
				<ul class="todo_list">
				</ul>
			</div>
		</section>
	</div>

	<div class="right_side">
		<section class="party_detail">
			<div class="category">
				<h1 class="name">파티 정보</h1>
			</div>
			<div class="left_column">
				<div class="notification">
					<div class="static_title">NOTIFICATION</div>

					<li>새로운 투두가 등록되었습니다. "DB final exam"</li>
					<li>새로운 투두가 등록되었습니다. "DB final report"</li>
					<li>새로운 투두가 등록되었습니다. "DB final report"</li>
					<li>새로운 투두가 등록되었습니다. "DB final report"</li>
				</div>
				<div class="complete_ratio">
					<div class="static_title">완료율</div>
					<canvas id="myChart"></canvas>
					<span id="complete_ratio_value" style="display: none">${partyInfo.completeRatio}</span>
				</div>
			</div>
			<div class="right_column">
				<div class="static_title">완료율 우수 멤버 리스트</div>
				<ol class="top_user_list">
					<c:forEach var="topUser" items="${partyInfo.topUserList}">
						<li class="top_user"><span class="top_user_name">${topUser.name}
								/ </span> <span class="top_user_email">${topUser.email} / </span> <span
							class="top_user_todoNum">${topUser.todoCount}</span></li>
					</c:forEach>
				</ol>
			</div>
		</section>
	</div>
	</main>
</div>
<script language="javascript" type="text/javascript" src="/commons/js/party/ubuntudoParty.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/dataManager.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/todoManager.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/modalManager.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/utility.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/searchManager.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<script>
	Chart.defaults.global.responsive = true;
	Chart.defaults.global.tooltipXOffset = 0;
	var ratio = document.getElementById("complete_ratio_value").innerText;
	var data = [ {
		value : Math.round(ratio * 1000) / 10,
		color : "#46BFBD",
		highlight : "#5AD3D1",
		label : "완료"
	}, {
		value : Math.round((1 - ratio) * 1000) / 10,
		color : "#F7464A",
		highlight : "#FF5A5E",
		label : "미완료"
	} ]
	var canvas = document.getElementById('myChart');
	var ctx = canvas.getContext("2d");
	var myPieChart = new Chart(ctx).Pie(data);
</script>
</body>
</html>