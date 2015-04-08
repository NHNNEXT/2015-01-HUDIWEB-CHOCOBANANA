/**
 * Created by jjungmac on 2015. 4. 3..
 */
ubuntudo.ui.validateManager = (function () {

	var CLASSNAME = {
		SIGNUP_BOX: "signup_box",
		ERROR: "error",
		OK: "fine"
	};

	var IDNAME = {
		EMAIL: "signup_email",
		PASSWORD: "signup_password",
		PASSWORD_CHECK: "signup_password_check",
		EMAIL_MESSAGE: "signup_email_message",
		PASSWORD_MESSAGE: "signup_password_message",
		PASSWORD_CHECK_MESSAGE: "signup_password_check_message",
		SIGNUP_FORM: "signup_form"
	}

	var MESSAGE = {
		RECHECK_EMAIL: "이메일 주소를 다시 확인해주세요.",
		GOOD_EMAIL: "멋진 이메일이다요!",
		EXIST_EMAIL: "이미 사용중인 이메일이다요.",
		DEFAULT: "필수 정보입니다.",
		WARNING_PASSWORD: "8자이상의 영문 대문자,소문자,숫자,특수문자를 사용하세요.",
		GOOD_PASSWORD: "아름다운 비밀번호다요!"
	}

	var util = ubuntudo.utility;

	function ValidateManager(elBase) {
		if (this instanceof ValidateManager) {
			this.elEmail = elBase.getElementById(IDNAME.EMAIL);
			this.elPassword = elBase.getElementById(IDNAME.PASSWORD);
			this.elPassword2 = elBase.getElementById(IDNAME.PASSWORD_CHECK);
			this.elForm = elBase.getElementById(IDNAME.SIGNUP_FORM);
		} else {
			return new ValidateManager(elBase);
		}
	}

	ValidateManager.prototype.validateForms = function () {

		this.elForm.addEventListener("keyup", function (e) {

			if(e.currentTarget === this.elEmail) {
				this.validateEmail();
			}
			else if(e.currentTarget === this.elPassword) {
				this.validatePassword();
			}
			else if(e.currentTarget === this.elPassword2) {
				this.passwordCheck();
			}
		}.bind(this));
	}

	ValidateManager.prototype.validateEmail = function () {
		var elEmail = document.getElementById(IDNAME.EMAIL);
		var emailInputTimer = null;
		var elMsg = document.getElementById(IDNAME.EMAIL_MESSAGE);

		elEmail.addEventListener("keyup", function (e) {
			if (emailInputTimer !== null) {
				clearTimeout(emailInputTimer);
			}
			emailInputTimer = setTimeout(function () {
				_checkRegexpEmail(e);
			}, 500);
		});

		function _checkRegexpEmail(e) {
			var email = elEmail.value;
			var isEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			var isHan = /[ㄱ-ㅎ가-힣]/g;
			if (!isEmail.test(email) || isHan.test(email)) {
				elMsg.style.display = "block";
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.RECHECK_EMAIL;
			} else {
				_ajaxSearchEmail(e)
			}
		}

		function _ajaxSearchEmail(e) {
			if (CheckKeyCode(e)) {
				emailInputTimer = null;
				var email = "email=" + elEmail.value;
				util.postJSONData("/validate.do", email, _showValidationMessage);
			}
		}

		function _showValidationMessage(ajaxResult) {
			if (ajaxResult.hasOwnProperty("isExistingUser")) {
				if (ajaxResult.isExistingUser === false) {
					elMsg.className = CLASSNAME.OK;
					elMsg.innerHTML = MESSAGE.GOOD_EMAIL;
				}
				else {
					elMsg.className = CLASSNAME.ERROR;
					elMsg.innerHTML = MESSAGE.EXIST_EMAIL;
				}
				elMsg.style.display = "block";
			} else {
			}
		}
	}

	ValidateManager.prototype.validatePassword = function () {

		function _isValidPassword(str) {
			if (str == "") {
				return false;
			}
			/* check whether input value is included space or not */
			if (checkSpace(str)) {
				return false;
			}
			if (str.length < 8) {
				return false;
			}
			var isPW = /^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%&'"*+/=?^_`{|}()~;:,@<>-]).*$/;
			if (!isPW.test(str)) {
				return false;
			}
			return true;
		}

		function _checkPassword1() {
			checkPassword2("check");

			var pw = document.getElementById(IDNAME.PASSWORD).value;
			var elMsg = document.getElementById(IDNAME.PASSWORD_MESSAGE);
			if (pw === "") {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.DEFAULT;
			}
			if (_isValidPassword(pw) === false) {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.WARNING_PASSWORD;
			} else {
				elMsg.className = CLASSNAME.OK;
				elMsg.innerHTML = MESSAGE.GOOD_PASSWORD;
			}
			elMsg.style.display = "block";
		}

		function checkPassword2(event) {
			var password1 = document.getElementById(IDNAME.PASSWORD).value;
			var password2 = document.getElementById(IDNAME.PASSWORD_CHECK).value;
			var elMsg = document.getElementById(IDNAME.PASSWORD_CHECK_MESSAGE);

			if (password2 == "") {
				oImg.className = "ps_box int_pass_check";
				elMsg.style.display = "block";
				elMsg.innerHTML = "필수 정보입니다.";
				return false;
			}
			if (password1 != password2) {
				oImg.className = "ps_box int_pass_check";
				elMsg.style.display = "block";
				elMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
				document.getElementById("password2").value = "";
				return false;
			} else {
				oImg.className = "ps_box int_pass_check2";
				elMsg.style.display = "none";
				return true;
			}

			return true;
		}
	}


	function CheckKeyCode(e) {
		var KeyCode = 0;
		if (window.event) { // IE
			KeyCode = e.keyCode;
		} else if (e.which) { // netscape ff opera
			KeyCode = e.which;
		}

		switch (KeyCode) {
			case 27: //esc
			case 38: // 방향키 위
			case 40: // 방향키 아래
			case 17: //ctrl
			case 25: //ctrl
			case 16: //Shift
			case 20: //Caps Lock
			case 18: //Alt
			case 21: //Alt
			case 145:  //Scroll Lock
			case 19: //Pause Break
			case 45: //Insert
			case 33: //Page Up
			case 34: //Page Down
			case 144: //Num Lock
			case 112: //F1
			case 113: //F2
			case 114: //F3
			case 115: //F4
			case 116: //F5
			case 117: //F6
			case 118: //F7
			case 119: //F8
			case 120: //F9
			case 121: //F10
			case 122: // F11
			case 123: // F12
				return false;
				break;
			default:
				return true;
		}
	}

// space 가 있으면 true, 없으면 false
	function checkSpace(str) {
		if (str.search(/\s/) != -1) {
			return true;
		} else {
			return false;
		}
	}

	return ValidateManager;
})();