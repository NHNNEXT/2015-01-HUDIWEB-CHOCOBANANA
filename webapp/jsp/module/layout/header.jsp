
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="UTF-8">
<title>Ubuntu.do</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="shortcut icon" href="/favicon.ico">
    <!-- build:css /styles/main.min.css -->
<link rel="stylesheet" href="/../../../styles/reset.css">
<link rel="stylesheet" href="/../../../styles/style.css">
<link rel="stylesheet" href="/../../../styles/guild.css">
<link rel="stylesheet" type="text/css" href="/../../../styles/todo_modal.css">
<link rel="stylesheet" href="/../../../styles/party.css">
    <link rel="stylesheet" href="/../../../styles/guildOverview.css">
    <!-- endbuild -->
<!-- 
<script src="bower_components/jquery/dist/jquery.js"></script>
<script src="bower_components/jquery-ui/jquery-ui.js"></script>
 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

</head>
<body>
	<h1 class="blind">우분투두</h1>
	<!--[if lt IE 10]>
        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a
        href="http://browsehappy.com/">upgrade
        your browser</a> to improve your experience.</p>
        <![endif]-->
	<header id="global-header">
		<div class="logo">
			<!--<img src=""/>-->
		</div>
		<nav>
			<h1 class="blind">페이지 메뉴</h1>
			<a href="/personal">home</a>
			<h2 class="blind">홈</h2>
			<a href="/guild/overview" class="guild_position">guild</a>
			<h2 class="blind">길드</h2>

		</nav>
		<section class="left_section">
			<h1 class="blind">검색창,Todo추가,내정보</h1>

			<div class="search_wrapper">
				<h2 class="blind">검색창</h2>

				<form method="post" action="">
					<input class="search_input" placeholder="Search guilds" type="text">
					<input class="search_submit" type="submit" value="">
				</form>
			</div>
            <div class="search_result" style="display: none">
                <ul class="search_result_list">
                </ul>
            </div>
			<div class="addBtn_and_profile_container">
				<h2 class="blind">투두 추가</h2>
				<ul class="btn_wrapper">
					<li class="todo_add_btn">
						<div id="add_wrap" style="display: none;">
							<div class="todo_modal_effect_shadow"></div>
							<div class="todo_modal_effect"></div>
							<form class="add_todo">
								<!-- 파티 설정 -->
								<div class="select_party">
									<label id="selected_party_name"></label> 
									<select id="select_party_list" onchange="showSelectedParty()">
										<option>개인</option>
									</select>
								</div>

								<!-- 날짜선택 -->
								<input id="datepicker" class="calendario calendar_box"
									name="date" value="만기기한이 없습니다.">

								<!-- 일정 이름 -->
								<input type="text" name="title" class="input_todoName"
									placeholder="일정 이름" autocomplete="off" alt="일정 이름" required="">

								<!-- 상세 내용 -->
								<textarea name="contents" class="input_todoComment" cols="50"
									rows="5" placeholder="상세내용을 입력해주세요."></textarea>

								<!-- 버튼 -->
								<div class="todo_modal_btn">
									<!-- <input type = "reset" value = "reset"/> -->
									<input type="submit" value="submit" class="submit_btn">
									<input type="button" value="cancel" class="cancel_btn">
								</div>
							</form>
						</div>
					</li>
					<li class="profile">
						<div class="my_modal_wrap">
							<div class="my_modal_effect_shadow"></div>
							<div class="my_modal_effect"></div>
							<div class="my_modal">
								<div class="my_each_box">${user.name}</div>
								<div class="my_each_box">내 정보 수정</div>
								<div class="my_each_box" id="my_modal_logout">로그아웃</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</section>
	</header>
	
	<div class="detail_modal" id="todo_modal"style="display:none">
        <div class="light_box"></div>
        <div class="task">
            <div class="title" id="title_edit"><textarea type="text" placeholder="To do..."></textarea></div>
            <div class="detail_wrapper">
                <span class="tid" id="tid_edit"></span>
                <span class="party" id="party_edit"></span>
                <span class="due_date" id="due_date_edit"></span>
                <textarea class="note" id="note_edit"></textarea>
            </div>
            <div class="btn_wrapper">
                <button class="edit_btn">save</button>
                <button class="delete_btn">delete</button>
            </div>
        </div>
    </div>