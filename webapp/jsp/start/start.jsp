<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
				<div class="signup_box" style="display:block">
					<form>
						<ul>
							<li><input type="hidden" id="RSAModulus" value="${RSAModulus}" /></li>
							<li><input type="hidden" id="RSAExponent" value="${RSAExponent}" /></li>
							<li><input type="text" id="signup_name" name="name" placeholder="이름" required></li>
							<li><input type="email" id="signup_email" name="email" placeholder="이메일" required></li>
							<li><input type="password" id="signup_password" name="password" placeholder="비밀번호" required></li>
							<li><input type="password" placeholder="비밀번호 확인" required></li>
							<li><button class="signup_btn">가입하기</button></li>
						</ul>
					</form>
					<a class="goto_login">로그인 바로가기</a>
				</div>
			<div class="login_box" style="display:none">
					<form name="loginForm" action="/login.do" method="POST">>
						<ul>
							<li><input type="email" id="login_email" name="email" placeholder="이메일" required></li>
							<li><input type="password" id="login_password" name="password" placeholder="비밀번호" required></li>
							<li><button class="login_btn" type="submit" value="로그인"></button></li>
						</ul>
					</form>
					<a class="goto_signup">회원가입 바로가기</a>
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
	
	<script language="javascript" type="text/javascript" src="commons/js/start/ubuntudo_main.js"></script>
	<script language="javascript" type="text/javascript" src="commons/js/start/signup_validate.js"></script>
	<script language="javascript" type="text/javascript" src="commons/js/start/utility.js"></script>
	
</html>