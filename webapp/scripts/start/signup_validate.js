/**
 * Created by jjungmac on 2015. 4. 3..
 */
ubuntudo.ui.validateManager = (function () {

	// HTML에 의존하는 CLASS,ID 캐싱
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

	// 에러출력 메세지 캐싱
	var MESSAGE = {
		RECHECK_EMAIL: "이메일 주소를 다시 확인해주세요.",
		GOOD_EMAIL: "멋진 이메일이다요!",
		EXIST_EMAIL: "이미 사용중인 이메일이다요.",
		DEFAULT: "필수 정보입니다.",
		WARNING_PASSWORD: "8자이상의 영문 대문자,소문자,숫자,특수문자를 사용하세요.",
		GOOD_PASSWORD: "아름다운 비밀번호다요!",
		CONFIRM_PASSWORD: "비밀번호가 일치한다요!",
		NOT_CONFIRM_PASSWORD: "비밀번호가 일치하지 않는다요!"

	}

	//ajax 모듈을 위해 util 모듈 가져옴.
	var util = ubuntudo.utility;

	// DOM 탐색부분 캐싱함.
	// ValidateManager와 DOM문서를 분리시킴.
	function ValidateManager(elBase) {
		if (this instanceof ValidateManager) {
			this.elForm = document.getElementById(IDNAME.SIGNUP_FORM);
			this.elEmail = document.getElementById(IDNAME.EMAIL);
			this.elPassword = document.getElementById(IDNAME.PASSWORD);
			this.elPassword2 = document.getElementById(IDNAME.PASSWORD_CHECK);
			this.elEmailMsg = document.getElementById(IDNAME.EMAIL_MESSAGE);
			this.elPasswordMsg = document.getElementById(IDNAME.PASSWORD_MESSAGE);
			this.elPassword2Msg = document.getElementById(IDNAME.PASSWORD_CHECK_MESSAGE);

		} else {
			return new ValidateManager(elBase);
		}
	}

	// 폼전체에 이벤트를 걸어서 event-delegation 으로 3개 폼을 Validate함.
	// CheckKeyCode는 유효한 입력에만 이벤트를 발생.
	// 타이핑마다 validate가 되지 않도록 0.3초 타이머를 걸어놈.
	// TODO delegation을 했더니 0.3초 내에 다른 폼으로 넘어가니까 Timer가 오작동함.
	ValidateManager.prototype.validateForms = function () {
		var inputTimer = null;
		this.elForm.addEventListener("keyup", function (e) {
			if (CheckKeyCode(e)) {
				if (inputTimer !== null) {
					clearTimeout(inputTimer);
				}
				inputTimer = setTimeout(function () {
					if(e.target.id === IDNAME.EMAIL) {
						this.validateEmail();
					}
					else if(e.target.id === IDNAME.PASSWORD) {
						this.validatePassword();
					}
					else if(e.target.id === IDNAME.PASSWORD_CHECK) {
						this.validatePassword2();
					}
				}.bind(this), 300);
			}
		}.bind(this));
	}


	ValidateManager.prototype.validateEmail = function () {

		var elMsg = this.elEmailMsg;
		var elEmail = this.elEmail;

		// 정규표현식으로 검사
		// isEmail, isHan은 naver 회원가입js 참고함.
		function _checkRegexpEmail() {
			var email = elEmail.value;
			var isEmail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			var isHan = /[ㄱ-ㅎ가-힣]/g;
			if (!isEmail.test(email) || isHan.test(email)) {
				elMsg.style.display = "block";
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.RECHECK_EMAIL;
			} else {
				_ajaxSearchEmail();
			}
		}

		//_showValidationMessage를 익명함수로 하려다가 그냥 뻄.
		//postJSONData(URL, param, callback)임.
		function _ajaxSearchEmail() {
			var email = "email=" + elEmail.value;
			util.postJSONData("/validate", email, _showValidationMessage);
		}

		function _showValidationMessage(ajaxResult) {
			if (ajaxResult.hasOwnProperty("status")) {
				if (ajaxResult.status === "false") {
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
		_checkRegexpEmail();
	}

	ValidateManager.prototype.validatePassword = function () {

		var password = this.elPassword.value;
		var elMsg = this.elPasswordMsg;


		//TODO 각 check 항목별로 Message를 다르게 분리해야함.
		function _isValidPassword(str) {
			//empty check
			if (str == "") {
				return false;
			}
			/* check whether input value is included space or not */
			if (checkSpace(str)) {
				return false;
			}
			// 8자리 미만은 안돼 !
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

			if (password === "") {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.DEFAULT;
			}
			if (_isValidPassword(password) === false) {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.WARNING_PASSWORD;
			} else {
				elMsg.className = CLASSNAME.OK;
				elMsg.innerHTML = MESSAGE.GOOD_PASSWORD;
			}
			elMsg.style.display = "block";
		}

		_checkPassword1();
	}

	//TODO function이름이 validatePassword2인데 뭔가 다른거로 바꿔야할듯.
	ValidateManager.prototype.validatePassword2 = function () {
		var password1 = this.elPassword.value;
		var password2 = this.elPassword2.value;
		var elMsg = this.elPassword2Msg;

		function checkPassword2() {
			if (password2 == "") {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.DEFAULT;
			}
			if (password1 != password2) {
				elMsg.className = CLASSNAME.ERROR;
				elMsg.innerHTML = MESSAGE.NOT_CONFIRM_PASSWORD;
			} else {
				elMsg.className = CLASSNAME.OK;
				elMsg.innerHTML = MESSAGE.CONFIRM_PASSWORD;
			}
			elMsg.style.display = "block";
		}
		checkPassword2();
	}


	function CheckKeyCode(e) {
		var KeyCode = 0;
		if (window.event) { // IE Chrome Safari
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