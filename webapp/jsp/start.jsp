<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--  
                .o8                                  .                        .o8
               "888                                .o8                       "888
    oooo  oooo  888oooo.  oooo  oooo  ooo. .oo.  .o888oo oooo  oooo      .oooo888  .oooooo.
    `888  `888  d88' `88b `888  `888  `888P"Y88b   888   `888  `888     d88' `88b  d88' `88b
     888   888  888   888  888   888   888   888   888    888   888     888   888  888   888
     888   888  888   888  888   888   888   888   888 .  888   888     888   888  888   888
     `V88V"V8P' `Y8bod8P'  `V88V"V8P' o888o o888o  "888"  `V88V"V8P' '0 `Y8bod8P'  `Y8bod8P' 


초코바나나 화이팅! 
		                                                          					            -->
<!-- sp = start page = 시작 페이지 -->

<html lang="ko">

<head>
	<meta charset="utf-8">
	<title>ubuntudo에 오신 것을 환영합니다. | 로그인 및 회원가입</title>
	<link rel="stylesheet" type="text/css" href="commons/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="commons/css/startPage.css"/>
</head>
<body class="sp_body">
<div class="sp_wrap">
	<div class="bg_box">
		<img class="bg_img">
	</div>
	<div class="sp_container">
		<div class="sp_logo"></div>
		<div class="sp_content_wrap">
			<div class="sp_content">
				<div class="msg">함께 만드는<br>TODO LIST</div>
				<form method="post" action="/signup/ajax" class="signup_box" style="display:block">
					<fieldset class="signup_form">
						<legend class="blind">회원가입</legend>
						<div>
							<label><input type="hidden" id="RSAModulus" value="${RSAModulus}" /></label>
							<label><input type="hidden" id="RSAExponent" value="${RSAExponent}" /></label>
							<label><input type="text" id="signup_name" name="name" placeholder="이름" autocomplete="off" alt="이름" required></label>
							<label><input type="email" id="signup_email" name="email" placeholder="이메일" autocomplete="off" alt="이메일" required></label>
							<label><input type="password" id="signup_password" name="password" placeholder="비밀번호" maxlength="16" autocomplete="off" alt="비밀번호" required></label>
							<label><input type="password" id="password_check" placeholder="비밀번호 확인" maxlength="16" autocomplete="off" alt="비밀번호확인" required></label>
							<button type="submit" class="signup_btn">가입하기</button>
						</div>
					</fieldset>
					<div class="goto_login">로그인 바로가기</div>
				</form>

				<form method="post" action="/login.do" name="loginForm" class="login_box" style="display:none">
					<fieldset class="login_form">
						<legend class="blind">로그인</legend>
						<div>
							<label><input type="hidden" id="RSAModulus" value="${RSAModulus}" /></label>
							<label><input type="hidden" id="RSAExponent" value="${RSAExponent}" /></label>
							<label><input type="email" id="login_email" name="email" placeholder="이메일" autocomplete="off" alt="이메일" required></label>
							<label><input type="password" id="login_password" name="password" placeholder="비밀번호" maxlength="16" autocomplete="off" alt="비밀번호" required></label>
							<button type="submit" class="login_btn" value="로그인">로그인</button>
						</div>
					</fieldset>
					<div class="goto_signup">회원가입 바로가기</div>
				</form>
			</div>
		</div>
	</div>

	<div class="sp_footer">
		<div class="com_info">
			<ul>
				<li>
					<a>회사소개</a>
					<span class="bar"></span>
				</li>
				<li>
					<a>이용약관</a>
					<span class="bar"></span>
				</li>
				<li>
					<a>개인정보취급방침</a>
					<span class="bar"></span>
				</li>
				<li>
					<a>운영정책</a>
					<span class="bar"></span>
				</li>
			</ul>
			<p>©
				<a>CHOCOBANANA Corp.</a>
			</p>
		</div>
	</div>
</div>
</body>
<script language="javascript" type="text/javascript" src="commons/js/start/startPage.js"></script>

<!-- RSA 자바스크립트 라이브러리 -->
<script type="text/javascript" src="/commons/lib/js/RSA/jsbn.js"></script>
<script type="text/javascript" src="/commons/lib/js/RSA/rsa.js"></script>
<script type="text/javascript" src="/commons/lib/js/RSA/prng4.js"></script>
<script type="text/javascript" src="/commons/lib/js/RSA/rng.js"></script>
<!-- RSA 암호화 처리 스크립트 -->
<script type="text/javascript" src="/commons/js/start/signup.js"></script>
<!-- hash 스크립트 -->
<script type="text/javascript" src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha256.js"></script>
</html>