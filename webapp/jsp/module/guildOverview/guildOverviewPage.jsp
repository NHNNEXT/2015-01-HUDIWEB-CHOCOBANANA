<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<section id="gdo_container" class="gdo_page">
	<h1 class="blind">길드 리스트 페이지</h1>
	<header id="gdo_header">
		<div id="gdo_header_content">
			<span id="gdo_header_title"> Guild List </span>
		</div>
		<snb id="gdo_header_navbar">
			<div class="btn_wrap">
                    <h3 class="blind">길드 추가하기</h3>
                    <a class="ghost_btn" id="guild_add_box_btn" >
                        <div class="guild_add_content">New Guild</div>
				    </a>
			</div>
			<div class="add_guild" id="guild_input_box" style="display: none">
				<form class="form_input_guild_name">
					<input type="text" class="input_guild_name" name="guildName"
						placeholder="길드 이름 입력" required>
					<a  class="ghost_btn btn_effect"
						id="input_guild_add_btn">추가</a>
					<a  class="ghost_btn btn_effect"
						id="input_guild_cancel_btn">취소</a>
				</form>
			</div>
		</snb>
	</header>
	<main id="gdo_main">
	<h1 class="blind">길드 페이지 본문</h1>
	<section id="gdo_list_container">
		<h1 class="blind">내가 속한 길드들</h1>
		<ul id="gdo_list">
		</ul>
	</section>
	</main>
</section>
    <!-- build:js /scripts/guildOverview.min.js -->
<script language="javascript" type="text/javascript"
	src="/../../../scripts/guildOverview/ubuntudoGuildOverview.js"></script>
<script language="javascript" type="text/javascript"
	src="/../../../scripts/guildOverview/guildOverviewManager.js"></script>
<script language="javascript" type="text/javascript"
	src="/../../../scripts/guildOverview/guildAddModal.js"></script>
    <!-- endbuild -->

    <!-- build:js /scripts/main.min.js -->
    <script language="javascript" type="text/javascript" src="/../../../scripts/utility.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/dataManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/modalManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/todoAddModal.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/todoManager.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/detailModal.js"></script>
    <script language="javascript" type="text/javascript" src="/../../../scripts/searchManager.js"></script>
    <!-- endbuild -->
</body>
</html>