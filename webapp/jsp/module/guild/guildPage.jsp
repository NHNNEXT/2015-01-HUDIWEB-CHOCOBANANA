<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 여기부터 길드 페이지 쫘라락~~  -->
<div id="container" class="ns_guild">
	<main>
	<div class="guild_header">
		<div class="guild_cover_img">
			<div class="guild_cover_img_gradient"></div>
		</div>
		<div class="setting_icon"></div>
		<div class="guild_name_wrap">
			<h1 class="guild_name">길드명</h1>
			<div class="btn_wrap">
				<div class="guild_join_btn" style="display: block">가입하기</div>
				<div class="guild_leave_btn" style="display: none">탈퇴하기</div>
			</div>
		</div>
		<div class="guild_info">
			<span class="leader_name">리더이름</span> <span>•</span> <span>멤버
				540명</span> <span>•</span> <span>파티 0</span>
		</div>
	</div>

	<section class="party_list_wrap">
		<div class="party_list" id="party_add_btn">
			<div class="add_icon"></div>
			<div class="party_name">파티 추가하기</div>
		</div>
	</section>
	</main>
</div>
<!-- // 여기까지 길드 페이지 쫘라락~~  -->
<script language="javascript" type="text/javascript" src="/commons/js/guild/ubuntudoGuild.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/guild/addParty.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/utility.js"></script>
</body>
</html>