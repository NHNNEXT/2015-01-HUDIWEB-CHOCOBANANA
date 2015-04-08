(function () {	
	var signupBtn = document.querySelector("button.signup_btn");
	var loginBtn = document.querySelector("button.login_btn");
	signupBtn.addEventListener("click", function(e) { rsa(e, signupIds); }, false);
	loginBtn.addEventListener("click", function(e) { rsa_login(e, loginIds); }, false);
	
	var signupIds = {
			name : "signup_name",
			email: "signup_email",
			password: "signup_password"
	}
	
	var loginIds = {
			name: "",
			email: "login_email",
			password: "login_password"
	}
	
	function rsa(e, ids) {
		 e.preventDefault();
		
		// 사용자 계정정보 암호화전 평문
		var name = document.getElementById(ids.name).value; //login일 때는 없음
		var email = document.getElementById(ids.email).value;
		var pwd = document.getElementById(ids.password).value;
		var result;

		// RSA 암호화 생성
		var rsa = new RSAKey();
		rsa.setPublic(document.getElementById("RSAModulus").value,  document.getElementById("RSAExponent").value);

		// 사용자 계정정보를 암호화 처리
		email = rsa.encrypt(email);
		pwd = rsa.encrypt(pwd);
		
		var url = "/signup.do"; //login이면 login.do 로 바꿔야 함
		var request = new XMLHttpRequest();
		var params = "name=" + name + "&email=" + email + "&password=" + pwd;

		request.open("POST" , url , true);
	    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send(params);
		
		request.onreadystatechange = function() {
			// 응답이 도착했고, 정상적인 응답이라면, 응답데이터를 파싱하기 시작한다. 
			if(request.readyState === 4 && request.status === 200) {
				result = request.responseText;
				result = JSON.parse(result);
				if(result.status === "success") {
				     window.location = result.uri;
				}
			}
		}
	}
	
	
	function rsa_login(e, ids) {
		 e.preventDefault();
		
		// 사용자 계정정보 암호화전 평문
		var email = document.getElementById(ids.email).value;
		var pwd = document.getElementById(ids.password).value;

		// RSA 암호화 생성
		var rsa = new RSAKey();
		rsa.setPublic(document.getElementById("RSAModulus").value,  document.getElementById("RSAExponent").value);

		// 사용자 계정정보를 암호화 처리
		email = rsa.encrypt(email);
		pwd = rsa.encrypt(pwd);
		
		var url = "/login.do"; //login이면 login.do 로 바꿔야 함
		var request = new XMLHttpRequest();
		var params = "&email=" + email + "&password=" + pwd;

		request.open("POST" , url , true);
	    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send(params);
		
		request.onreadystatechange = function() {
			// 응답이 도착했고, 정상적인 응답이라면, 응답데이터를 파싱하기 시작한다. 
			if(request.readyState === 4 && request.status === 200) {
				result = request.responseText;
				result = JSON.parse(result);
				if(result.status === "success") {
				     window.location = result.uri;
				}
			}
		}
	}
})();





