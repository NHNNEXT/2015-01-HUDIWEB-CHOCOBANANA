/* (화면의 크기에 따른) 배경이미지의 크기를 조절하는 함수 */
function changeSizeOfBg(){
	if (1280 > document.documentElement.clientWidth) {
		document.querySelector(".bg_box").style.height= "100%";
	} 
	else {
		document.querySelector(".bg_box").style.height= "";	
	}	
}

/* 처음 화면을 load할 때, 배경이미지 크기 조절 */
document.addEventListener("DOMContentLoaded", changeSizeOfBg);

/* 화면을 resize할 때, 배경이미지 크기 조절 */
window.addEventListener("resize", changeSizeOfBg);


/* '로그인 바로가기' 버튼 누를 때, 로그인 폼 열기 */
document.querySelector(".goto_login").addEventListener("click", function(){
	document.querySelector(".login_box").style.display= "block";
	document.querySelector(".signup_box").style.display= "none";
});

/* '회원가입 바로가기' 버튼 누를 때, 회원가입 폼 열기 */
document.querySelector(".goto_signup").addEventListener("click", function(){
	document.querySelector(".login_box").style.display= "none";
	document.querySelector(".signup_box").style.display= "block";
});
