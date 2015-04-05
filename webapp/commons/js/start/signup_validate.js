/**
 * Created by jjungmac on 2015. 4. 3..
 */
ubuntudo.ui.validateManager = ( function () {

	var CLASSNAME = {
		email: "#signup_email",
		password: "#signup_password",
		passwordCheck: "#signup_password_check",
		signupBox: ".signup_box"
	};


	var util = ubuntudo.utility;

	function ValidateManager(elBase) {
		if(this instanceof ValidateManager) {
			this.elEmail = elBase.querySelector(CLASSNAME.email);
		} else {
			return new ValidateManager(elBase);
		}
	}

	ValidateManager.prototype.checkIntervalTime = function () {
		var elEmail = this.elEmail;
		var emailInputTimer = null;
		elEmail.addEventListener("keyup" , function () {
			if (emailInputTimer !== null) {
				clearTimeout(emailInputTimer);
				emailInputTimer = setTimeout(_checkExistEmail, 1000);
			}else {
				emailInputTimer = setTimeout(_checkExistEmail, 1000);
			}

		});

		function _checkExistEmail() {
			emailInputTimer = null;
			var email =  "email=" + elEmail.value;
			var result = test();
			_showValidationMessage(result);

		//	util.requestData("url",email, _showValidationMessage);
		}
		function test() {
			var result = {
				bool: true
			};
			return result;
		}
		function _showValidationMessage(result) {
			if (result.bool === true) {
				console.log("EXIST");
			}else {
				console.log(" NOT EXIST");
			}
		}
	};
	return ValidateManager;
})();