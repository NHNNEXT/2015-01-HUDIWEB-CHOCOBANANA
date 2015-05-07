/**
 * Created by jjungmac on 2015. 4. 3..
 */

require(["start/validate","start/encrypt"], function(vm,encrypt) {
	'use strict';
	window.addEventListener("load", function () {
		var elTarget = document.querySelector(".signup_box");
		var validateManager = new vm.ValidateManager(elTarget);
		validateManager.validateForms();
		encrypt();
	});
});

