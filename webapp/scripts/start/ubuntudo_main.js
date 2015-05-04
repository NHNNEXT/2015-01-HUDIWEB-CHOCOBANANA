/**
 * Created by jjungmac on 2015. 4. 3..
 */

ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};
ubuntudo.lib = {};

window.addEventListener("load", function () {
	'use strict';
	var elTarget = document.querySelector(".signup_box");
	var validateManager = new ubuntudo.ui.ValidateManager(elTarget);
	validateManager.validateForms();

});

