(function () {	
	var signupBtn = document.querySelector("button.signup_btn");
	signupBtn.addEventListener("click", signup, false);
})();
			
function signup(e) {
	 e.preventDefault();
	
	// 사용자 계정정보 암호화전 평문
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var pwd = document.getElementById("password").value;

	// RSA 암호화 생성
	var rsa = new RSAKey();
	rsa.setPublic(document.getElementById("RSAModulus").value,  document.getElementById("RSAExponent").value);

	// 사용자 계정정보를 암호화 처리
	email = rsa.encrypt(email);
	pwd = rsa.encrypt(pwd);
	
	var url = "/signup.do";
	var request = new XMLHttpRequest();
	var params = "name=" + name + "&email=" + email + "&password=" + pwd;

	request.open("POST" , url , true);
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	request.send(params);
}


