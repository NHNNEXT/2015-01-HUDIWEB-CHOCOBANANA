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
		<div class="guildName_and_btn_wrap">
			<div class="g_name"></div>
			<!-- <h1 class="guild_name">길드명</h1> -->
			<div class="btn_wrap">
				<div class="guild_join_btn" style="display: block">가입하기</div>
				<div class="guild_leave_btn" style="display: none">탈퇴하기</div>
			</div>
		</div>
		<div class="guild_info">
		</div>
	</div>

	<section class="party_list_wrap">
		<div class="added_party"></div>
		<div class="add_party" id="party_add_box_btn" style="display:none">
			<div class="add_icon"></div>
			<div class="party_add_content">파티 추가하기</div>
		</div>
		<div class="add_party" id="partyName_input_box_btn" style="display:none">
			<form class="form_input_partyName">
                <input type="hidden" class="input_gid" name="gid" value="">
				<input type="text" class="input_partyName" name="partyName" placeholder="파티 이름 입력" required>
				<button type="button" class="btn_effect" id="inputPartyName_add_btn">추가</button>
 				<button type="button" class="btn_effect" id="inputPartyName_cancel_btn">취소</button>
			</form>
		</div>
	</section>
	</main>
</div>
<!-- // 여기까지 길드 페이지 쫘라락~~  -->
<script language="javascript" type="text/javascript" src="/commons/js/guild/ubuntudoGuild.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/guild/guildDataManager.js"></script>
<script language="javascript" type="text/javascript" src="/commons/js/utility.js"></script>

 <script language="javascript" type="text/javascript" src="/commons/js/dataManager.js"></script>
 <script language="javascript" type="text/javascript" src="/commons/js/modalManager.js"></script>   
 <script language="javascript" type="text/javascript" src="/commons/js/todoAddModal.js"></script>
 <script language="javascript" type="text/javascript" src="/commons/js/todoManager.js"></script>
 <script language="javascript" type="text/javascript" src="/commons/js/searchManager.js"></script>
</body>
</html>