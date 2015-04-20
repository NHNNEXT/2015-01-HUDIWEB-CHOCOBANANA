(function () {	
	var signupBtn = document.querySelector("button.signup_btn");
	var loginBtn = document.querySelector("button.login_btn");
	signupBtn.addEventListener("click", function(e) { rsa(e, signupIds, URI.SIGNUP); }, false);
	loginBtn.addEventListener("click", function(e) { rsa(e, loginIds, URI.LOGIN); }, false);
	
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
	
	var URI = {
		SIGNUP : "/signup",
		LOGIN : "/login"
	};
	
	function rsa(e, ids, uri) {
		 e.preventDefault();
		
		// 사용자 계정정보 암호화전 평문
		var name = ((ids.name !== "") ? document.getElementById(ids.name).value : ""); //login일 때는 없음
		var email = document.getElementById(ids.email).value;
		var pwd = CryptoJS.SHA256(document.getElementById(ids.password).value).toString(CryptoJS.enc.Hex); //pwd는 hashing

		// RSA 암호화 생성
		var rsa = new RSAKey();
		rsa.setPublic(document.getElementById("RSAModulus").value,  document.getElementById("RSAExponent").value);

		// 사용자 계정정보를 암호화 처리
		email = rsa.encrypt(email);
		pwd = rsa.encrypt(pwd);
		
		var request = new XMLHttpRequest();
		var params = "name=" + name + "&email=" + email + "&password=" + pwd;

		request.open("POST" , uri , true);
//	    request.setRequestHeader("Content-type","application/json;charset=UTF-8");
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send(params);
		
		request.onreadystatechange = function() {
			// 응답이 도착했고, 정상적인 응답이라면, 응답데이터를 파싱하기 시작한다. 
			if(request.readyState === 4 && request.status === 200) {
				var result = request.responseText;
				result = JSON.parse(result);
				if(result.status === "success") {
				     window.location = result.uri;
				}
			}
		}
	}
})();





