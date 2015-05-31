<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section id="container" class="gdo_page">
	<h1 class="blind">길드 리스트 페이지</h1>
	<header id="gdo_header">
		<div class="gdo_header_content">
			<div class="gdo_header_avatar">
				<div class="avatar person large"></div>
			</div>
			<div class="gdo_header_person">
				<span class="large"> 회원님의 그룹들 </span> <span class="small"> </span>
			</div>
		</div>
		<div id="gdo_header_navbar">
			<snb class="sub_nav_content">
			<h2 class="blind">그룹 서브 네비게이션 바</h2>
			<div class="links">
				<a class="link selected"
					href="/group/list" role="menuitem">그룹 목록</a>
				<h3 class="blind">그룹목록</h3>
                <div class="add_party" id="party_add_box_btn" style="display:block">
                <div class="add_icon"></div>
                <div class="party_add_content">길드 추가하기</div>
                <h3 class="blind">그룹 생성</h3>
         </div>
			</div>
            <div class="add_guild" id="partyName_input_box_btn" style="display:none">
                <form class="form_input_partyName">
                    <input type="text" class="input_partyName" name="fname" placeholder="길드 이름 입력" required>
                    <button type="button" class="btn_effect" id="inputPartyName_add_btn">추가</button>
                    <button type="button" class="btn_effect" id="inputPartyName_cancel_btn">취소</button>
                </form>
            </div>
			<div class="tools"></div>
			</snb>
		</div>
	</header>
	<main>
		<h1 class="blind">길드 페이지 본문</h1>
		<section id="gdo_list_container">
			<h1>내가 속한 길드들</h1>
			<ul id="gdo_list">
			</ul>
		</section>
    </main>
</section>
<script language="javascript" type="text/javascript"
	src="/commons/js/guildOverview/ubuntudoGuildOverview.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/guildOverview/guildOverviewManager.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/guildOverview/guildAddModal.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/utility.js"></script>
    
    <script language="javascript" type="text/javascript" src="/commons/js/dataManager.js"></script>
    <script language="javascript" type="text/javascript" src="/commons/js/modalManager.js"></script>   
    <script language="javascript" type="text/javascript" src="/commons/js/todoAddModal.js"></script>
    <script language="javascript" type="text/javascript" src="/commons/js/searchManager.js"></script>
</body>
</html>