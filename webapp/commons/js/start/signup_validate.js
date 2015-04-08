/**
 * Created by jjungmac on 2015. 4. 3..
 */
ubuntudo.ui.validateManager = (function () {

	var CLASSNAME = {
		email: "#signup_email",
		password: "#signup_password",
		passwordCheck: "#signup_password_check",
		signupBox: ".signup_box"
	};
	var util = ubuntudo.utility;
	function ValidateManager(elBase) {
		if (this instanceof ValidateManager) {
			this.elEmail = elBase.querySelector(CLASSNAME.email);
		} else {
			return new ValidateManager(elBase);
		}
	}

	ValidateManager.prototype.checkExistingEmail = function () {
		var elEmail = this.elEmail;
		var emailInputTimer = null;
		elEmail.addEventListener("keyup", function (e) {
			if (emailInputTimer !== null) {
				clearTimeout(emailInputTimer);
				emailInputTimer = setTimeout(function(){ _ajaxSearchEmail(e); }, 500);
			} else {
				emailInputTimer = setTimeout(function(){ _ajaxSearchEmail(e); }, 500);
			}
		});

		function _ajaxSearchEmail(e) {
			if(_CheckKeyCode(e)) {
				emailInputTimer = null;
				var email = "email=" + elEmail.value;
				util.postData("/validate.do", email, _showValidationMessage);
			}
		}
	}

	// isExistingUser는 서버에서 밀어넣어주는 값인데. unresolved 워닝은 어쩔 수 없는건가요 ?
	function _showValidationMessage(ajaxResult) {
		console.log(ajaxResult.isExistingUser);
		if (ajaxResult.isExistingUser == true) {
			console.log("EXIST");
		} else {
			console.log(" NOT EXIST");
		}
	}
	function _CheckKeyCode(e) {
		switch (e.keyCode) {
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
	};
return ValidateManager;
})
();