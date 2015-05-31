<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="styles/gulid.css">

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
				<li class="gdo_container list1">
					<article class="guild_01">
						<header class="gdo_title">
							<h1 class="gdo_list_title">길드명_1</h1>
							<!_ 다른 요소들. 날짜? 멤버? 등등 _>
						</header>
						<section class="gdo pt_list_container">
							<h1 class="blind">길드명_1의 파티리스트</h1>
							<ul class="gdo pt_list_01">
								<li class="pt_container_01">
									<article class="party_01">
										<header>
											<h1 class="gdo_pt_title">파티명_1</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티1의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt_container 02">
									<article class="party_02">
										<header>
											<h1 class="gdo_pt_title">파티명_2</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티2의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt_container 03">
									<article class="party_03">
										<header>
											<h1 class="gdo_pt_title">파티명_3</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티3의 내용들</p>
										</div>
									</article>
								</li>
							</ul>
						</section>
					</article>
				</li>
				<li class="gdo_container 02">
					<article class="guild_02">
						<p>${guild.gid}</p>
						<header class="gdo_title">
							<h1 class="gdo_list_title">${guild.guildName}</h1>
							<!_ 다른 요소들. 날짜? 멤버? 등등 _>
						</header>
						<section class="gdo pt_list_container">
							<h1 class="blind">${guild.gid}의party_list</h1>
							<ul class="gdo pt_list_01">
								<li class="pt_container_01">
									<article class="party_01">
										<header>
											<h1 class="gdo_pt_title">${party.partyName}</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티1의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt_container 02">
									<article class="party_02">
										<header>
											<h1 class="gdo_pt_title">파티명_2</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티2의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt_container 03">
									<article class="party_03">
										<header>
											<h1 class="gdo_pt_title">파티명_3</h1>
										</header>
										<div class="gdo pt_content">
											<p>파티3의 내용들</p>
										</div>
									</article>
								</li>
							</ul>
						</section>
					</article>
				</li>
				<li class="gdo_container 03"></li>
			</ul>
			</article>
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
</body>
</html>