<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  
                .o8            			             .          			  .o8
               "888             			       .o8         				 "888
    oooo  oooo  888oooo.  oooo  oooo  ooo. .oo.  .o888oo oooo  oooo 	 .oooo888  .oooooo.
    `888  `888  d88' `88b `888  `888  `888P"Y88b   888   `888  `888 	d88' `88b  d88' `88b
     888   888  888   888  888   888   888   888   888    888   888 	888   888  888   888
     888   888  888   888  888   888   888   888   888 .  888   888  	888   888  888   888
     `V88V"V8P' `Y8bod8P'  `V88V"V8P' o888o o888o  "888"  `V88V"V8P' '0 `Y8bod8P'  `Y8bod8P' 


초코바나나 화이팅! 
		                                                           					            -->

<html lang="ko">
<head>
<meta charset="utf-8">
<title>ubuntudo에 오신 것을 환영합니다. | 로그인 및 회원가입</title>
</head>
<body>
	<!-- start page : 시작 페이지 -->
	<div class="sp_wrap">
		<div class="sp_container">
			<div class="sp_logo"></div>
			<div class="signup_box">
				<form>
					<ul>
						<!-- 서버에서 전달한값을 셋팅한다. -->
						<input type="hidden" id="RSAModulus" value="${RSAModulus}" />
						<input type="hidden" id="RSAExponent" value="${RSAExponent}" />
						<li><input type="text" id="name" name="name" placeholder="이름"
							required></li>
						<li><input type="email" id="email" name="email"
							placeholder="이메일" required></li>
						<li><input type="password" id="password" name="password"
							placeholder="비밀번호" required></li>
						<li><input type="password" placeholder="비밀번호 확인" required></li>
						<li><button class="signup_btn">가입하기</button></li>
					</ul>
				</form>
				<span>이미 계정이 있습니까? <a>로그인</a> <!-- <a href="/login">로그인</a> -->
				</span>
			</div>

			<!-- '로그인'버튼 누르면 이렇게 할거임 -->
			<!-- 회원가입이랑 로그인 모두 일단 name 속성은 기본으로 했어요! -->

			<!-- 		<div class="log_in_box">
			<form>
				<ul>
					<li><input type="email" name="email" placeholder="이메일" required></li>
					<li><input type="password" name="password" placeholder="비밀번호" required></li>
					<li><button>로그인</button></li>
				</ul>
			</form>
		</div> -->
		</div>
		<div class="sp_footer">
			<div class="com_info">
				<ul>
					<li><a>회사소개</a> <span class="bar"></span></li>
					<li><a>이용약관</a> <span class="bar"></span></li>
					<li><a>개인정보취급방침</a> <span class="bar"></span></li>
					<li><a>운영정책</a> <span class="bar"></span></li>
				</ul>
				책
				<p>
					© <a>CHOCOBANANA Corp.</a>
				</p>
			</div>
		</div>
	</div>
	<!-- //start page : 시작 페이지 -->


	<!-- RSA 자바스크립트 라이브러리 -->
	<script type="text/javascript" src="/commons/lib/js/RSA/jsbn.js"></script>
	<script type="text/javascript" src="/commons/lib/js/RSA/rsa.js"></script>
	<script type="text/javascript" src="/commons/lib/js/RSA/prng4.js"></script>
	<script type="text/javascript" src="/commons/lib/js/RSA/rng.js"></script>
	<!-- RSA 암호화 처리 스크립트 -->
	<script type="text/javascript" src="/commons/js/start/signup.js"></script>
</body>
</html>