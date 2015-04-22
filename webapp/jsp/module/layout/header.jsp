<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Ubuntu.do</title>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/todo_modal.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
</head>
<body>
	<header>
	<div class="logo">
		<!--<img src=""/>-->
	</div>
	<nav> <a href="">home</a> <a href="">guild</a> </nav>
	<div class="left_section">
		<div class="search_wrapper">
			<form method="post" action="">
				<input class="search_input" value="Search guilds" type="text">
				<input class="search_submit" type="submit" value="">
			</form>
		</div>
		<div class="addBtn_and_profile_container">
			<ul class="btn_wrapper">
				<li class="todo_add_btn">
					<div id="add_wrap" style="display:none;">
						<div class="modal_effect_shadow"></div>
						<div class="modal_effect"></div>
						<form class="add_todo">
							<!-- 파티 설정	-->
							<div class="select_party">
								<label id="selected_party_name">개인</label>
                                <select id="select_party_list" onchange="showSelectedParty()">
									<option>개인</option>
									<option>네트워크</option>
									<option>자구알</option>
								</select>
							</div>

							<!-- 날짜선택 -->
							<input id="datepicker" class="calendario calendar_box" name="date" value="만기기한이 없습니다.">

							<!-- 일정 이름 -->
							<input type="text" name="title" class="input_todoName"
								placeholder="일정 이름" autocomplete="off" alt="일정 이름" required="">

							<!-- 상세 내용 -->
							<textarea name="contents" class="input_todoComment" cols="50"
								rows="5" placeholder="상세내용을 입력해주세요."></textarea>

							<!-- 버튼 -->
							<div class="todo_modal_btn">
								<!-- <input type = "reset" value = "reset"/> -->
								<input type="submit" value="submit" class="submit_btn"/>
								<input type="button" value="cancel" class="cancel_btn" />
							</div>
						</form>
					</div>
				</li>
				<li class="profile"></li>
			</ul>
		</div>
	</div>




	<script type="text/javascript"
		src="/commons/js/add_todo_modal/todo_modal.js"></script> </header>