/* (화면의 크기에 따른) 배경이미지의 크기를 조절하는 함수 */
function changeSizeOfBg(){
	if (1280 > document.documentElement.clientWidth) {
		document.querySelector(".bg_box").style.height = "100%";
	} 
	else {
		document.querySelector(".bg_box").style.height = "";	
	}	
}

/* 처음 화면을 load할 때, 배경이미지 크기 조절 */
document.addEventListener("DOMContentLoaded", changeSizeOfBg);

/* 화면을 resize할 때, 배경이미지 크기 조절 */
window.addEventListener("resize", changeSizeOfBg);


/* '로그인 바로가기' 버튼 누를 때, 로그인 폼 열기 */
document.querySelector(".goto_login").addEventListener("click", function(){
	document.querySelector(".login_box").style.display = "block";
	document.querySelector(".signup_box").style.display = "none";
});

/* '회원가입 바로가기' 버튼 누를 때, 회원가입 폼 열기 */
document.querySelector(".goto_signup").addEventListener("click", function(){
	document.querySelector(".login_box").style.display = "none";
	document.querySelector(".signup_box").style.display = "block";
});


var bgImages = new Array();
var numImages = 3; // 이미지 개수
for (i = 0; i < numImages; i++){
	bgImages[i] = new Image();
	bgImages[i].src = "commons/img/sp_bg"+(i+1)+".jpg";
}

var step = 0;
var step2 = 1;
var elImg = document.querySelectorAll('.bg_img');
function slideBg(){
	if (!document.images) return;

	/* opacity 효과 */
	elImg[0].style.opacity = 0.5;

	nTime1 = setInterval(function(){
		var _nPre = parseFloat(elImg[0].style.opacity);
		if(_nPre > 1.0) 
			clearInterval(nTime1);
    	elImg[0].style.opacity = _nPre + 0.01;
  	},0.17);
	/* //opacity 효과 */

	
	// /* opacity 효과 */
	// elImg[1].style.opacity = 0.5;

	// nTime2 = setInterval(function(){
	// 	var _nPre = parseFloat(elImg[1].style.opacity);
	// 	if(_nPre = 0.0) 
	// 		clearInterval(nTime2);
 //    	elImg[1].style.opacity = _nPre - 0.01;
 //  	},0.17);
	// /* //opacity 효과 */



	elImg[0].src = bgImages[step].src;
	if (step < 2){
		step++;
	} 
	else {
		step = 0;
	}
	setTimeout("slideBg()",3000);	// 8초 마다 반복


	// elImg[1].src = bgImages[step2].src;
	// if (step2 < 2){
	// 	step2++;
	// } 
	// else {
	// 	step2 = 0;
	// }
	// setTimeout("slideBg()",3000);	// 8초 마다 반복
}
slideBg();


// function Button(sSelector, aColor) {
//   this.elBg = document.querySelector(sSelector);
//   this.aColor = aColor || ["red", "green", "blue"];
//   this.nLen = this.aColor.length;
//   this.init();
// }
// // Button.prototype.init = function() {
// //   this.elBase.addEventListener("click", function(ev) {
// // 			this.changeImgOfBg();
// //   }.bind(this));
// // };

// var changeImgOfBg = function() {
//   if(this.nTime) clearInterval(this.nTime);

//   var nIndex = myUtil.getRandomNumber.call(this);
//   this.elBase.style.backgroundColor = this.aColor[nIndex];
//   this.elBase.style.opacity = 0;

//   this.nTime = setInterval(function(){
//       var _nPre = parseFloat(this.elBase.style.opacity);
//       if(_nPre > 1.0) clearInterval(this.nTime);
//     this.elBase.style.opacity = _nPre + 0.05;
//   }.bind(this),100);
// };

// // //Utility methods
// // var myUtil = {
// //   getRandomNumber : function() {
// //    	return Math.floor(Math.random() * this.nLen);
// //   }
// // };
// //service Code
// var oB = new Button(".bg_img", ["red", "blue", "orange", "pink"]);	