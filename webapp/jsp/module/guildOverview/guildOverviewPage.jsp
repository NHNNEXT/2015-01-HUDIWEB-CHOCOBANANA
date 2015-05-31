<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="styles/gulid.css">

<section id="container" class="gd_page">
	<h1 class="blind">길드 리스트 페이지</h1>
	<header id="gd-header">
		<div class="gd-header-content">
			<div class="gd-header-avatar">
				<div class="avatar person large"></div>
			</div>
			<div class="gd-header-person">
				<span class="large"> 회원님의 그룹들 </span> <span class="small"> </span>
			</div>
		</div>
		<div class="gd-header-navbar">
			<snb class="sub-nav-content">
			<h2 class="blind">그룹 서브 네비게이션 바</h2>
			<div class="links">
				<a class="link selected"
					href="/group/list" role="menuitem">그룹 목록</a>
				<h3 class="blind">그룹목록</h3>
				<a class="link"
					href="/group/new" role="menuitem">그룹 생성</a>
				<h3 class="blind">그룹 생성</h3>
			</div>
			<div class="tools"></div>
			</snb>
		</div>
	</header>
	<section id="gd-body">
		<h1 class="blind">길드 페이지 본문</h1>
		<section id="gd-list-container">
			<h1>내가 속한 길드들</h1>
			<ul id="gd-list">
				<li class="gd-container list1">
					<article class="guild-01">
						<header class="gd-title">
							<h1 class="gd-list-title">길드명-1</h1>
							<!-- 다른 요소들. 날짜? 멤버? 등등 -->
						</header>
						<section class="gd pt-list-container">
							<h1 class="blind">길드명-1의 파티리스트</h1>
							<ul class="gd pt-list-01">
								<li class="pt-container-01">
									<article class="party-01">
										<header>
											<h1 class="gd-pt-title">파티명-1</h1>
										</header>
										<div class="gd pt-content">
											<p>파티1의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt-container 02">
									<article class="party-02">
										<header>
											<h1 class="gd-pt-title">파티명-2</h1>
										</header>
										<div class="gd pt-content">
											<p>파티2의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt-container 03">
									<article class="party-03">
										<header>
											<h1 class="gd-pt-title">파티명-3</h1>
										</header>
										<div class="gd pt-content">
											<p>파티3의 내용들</p>
										</div>
									</article>
								</li>
							</ul>
						</section>
					</article>
				</li>
				<li class="gd-container 02">
					<article class="guild-02">
						<p>${guild.gid}</p>
						<header class="gd-title">
							<h1 class="gd-list-title">${guild.guildName}</h1>
							<!-- 다른 요소들. 날짜? 멤버? 등등 -->
						</header>
						<section class="gd pt-list-container">
							<h1 class="blind">${guild.gid}의party-list</h1>
							<ul class="gd pt-list-01">
								<li class="pt-container-01">
									<article class="party-01">
										<header>
											<h1 class="gd-pt-title">${party.partyName}</h1>
										</header>
										<div class="gd pt-content">
											<p>파티1의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt-container 02">
									<article class="party-02">
										<header>
											<h1 class="gd-pt-title">파티명-2</h1>
										</header>
										<div class="gd pt-content">
											<p>파티2의 내용들</p>
										</div>
									</article>
								</li>
								<li class="pt-container 03">
									<article class="party-03">
										<header>
											<h1 class="gd-pt-title">파티명-3</h1>
										</header>
										<div class="gd pt-content">
											<p>파티3의 내용들</p>
										</div>
									</article>
								</li>
							</ul>
						</section>
					</article>
				</li>
				<li class="gd-container 03"></li>
			</ul>
			</article>
		</section>
	</section>
</section>
<script language="javascript" type="text/javascript"
	src="/commons/js/personal/ubuntudoPersonal.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/personal/dataManager.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/personal/todoManager.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/personal/modalManager.js"></script>
<script language="javascript" type="text/javascript"
	src="/commons/js/start/utility.js"></script>
</body>
</html>