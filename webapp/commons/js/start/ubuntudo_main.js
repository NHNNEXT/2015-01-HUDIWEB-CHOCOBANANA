/**
 * Created by jjungmac on 2015. 4. 3..
 */

ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {

	var elTarget = document.querySelector(".signup_box");
	var validateManager = new ubuntudo.ui.validateManager(elTarget);
	validateManager.validateForms();

});

