/**
 * Created by jjungmac on 2015. 4. 3..
 */

ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
	var elTarget = document.querySelector(".container");
	var oDM = new ubuntudo.ui.DM(elTarget);
	

	$("button:first-child").on("click", function (e) {
		oDM.toggleLayer("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
	});
	$("button:nth-child(2)").on("click", function (e) {
		oDM.changeContentMsg("message is none...");
	});
	$("button:last-child").on("click", function (e) {
		oDM.setAnimation();
	});
});

