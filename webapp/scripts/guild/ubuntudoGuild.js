
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
    'use strict';

	var util = ubuntudo.utility;
	var href = window.location.href;
	var gid = href.substr(href.lastIndexOf("/")+1);
	var oGuildDataManager = new ubuntudo.ui.GuildDataManager();

	util.ajax({
	    "method": "GET",
	    "uri": "/guild/info/"+ gid,
	    "param": null,
	    "callback": oGuildDataManager.setGuildInfo
	});
	document.getElementsByClassName("input_gid")[0].value = gid;
		// 파티 추가하기 박스를 눌렀을 때
    document.getElementById("party_add_box_btn").addEventListener("click", inputPartyName);

    //파티 추가하기 박스에서, 추가 버튼을 눌렀을 때
    document.getElementById("inputPartyName_add_btn").addEventListener("click", addParty);

    //파티 추가하기 박스에서, 취소 버튼을 눌렀을 때
    document.getElementById("inputPartyName_cancel_btn").addEventListener("click", oGuildDataManager.originalPartyAddBox);

    function inputPartyName() {
        document.getElementById("party_add_box_btn").style.display = "none";
        document.getElementById("partyName_input_box_btn").style.display = "block"; 
    }

    function addParty() {
        var partyName = document.getElementsByClassName("input_partyName")[0].value;
        util.ajax({
            "method": "POST",
            "uri": "/party",
            "param": "gid="+gid+"&partyName="+ partyName,
            "callback": oGuildDataManager.addPartyCallback
        });
    }
    
    
    var joinBtn = document.querySelector(".guild_join_btn"); // 가입하기 버튼 
    var leaveBtn = document.querySelector(".guild_leave_btn"); // 탈퇴하기 버튼 
    
    // 가입하기 버튼을 누르면 
    joinBtn.addEventListener("click", function () {
        util.ajax({
            "method": "POST",
            "uri": "/guild/user",
            "param": "guildId=" + gid,
            "callback": callbackSuccessJoin
	   });
    });
    
    function callbackSuccessJoin (result) {
    	if (result.status === "success"){
    		joinBtn.style.display = "none";
    		leaveBtn.style.display = "block";
    		
    		// 길드 헤더에 멤버수 추가 (프론트) 
    		var presentMemberNum = Number(document.getElementsByClassName("memberNum")[0].innerHTML.split(" ")[1]);
    		presentMemberNum = presentMemberNum + 1;
			document.getElementsByClassName("memberNum")[0].innerHTML = "멤버 " + presentMemberNum;
    	}
    }
    
    // 탈퇴하기 버튼을 누르면 
    leaveBtn.addEventListener("click", function () {
    	// 현재 멤버 탈퇴 기능 구현이 아직 되어있지 않습니다. 
    	// 그 멤버가 탈퇴해야할 경우 고려할 요소(예를들면 그 멤버가 등록했던 투두리스트 등)를 아직 정하지 못했으며 쉽게 delete되지 못하게 하기 위함.....
//        util.ajax({
//            "method": "POST",
//            "uri": "/guild/user",
//            "param": "guildId=" + gid,
//            "callback": callbackSuccessLeave
//	   });
    	alert("오늘 가입한 카페는 탈퇴할 수 없습니다. 24시간이 지난 후에 다시 탈퇴해주십시오.");
    });
    
    function callbackSuccessLeave () {
    	if (result.status === "success"){
            joinBtn.style.display = "block";
            leaveBtn.style.display = "none";
    		
    		// 길드 헤더에 멤버수 추가 (프론트) 
    		var presentMemberNum = Number(document.getElementsByClassName("memberNum")[0].innerHTML.split(" ")[1]);
    		presentMemberNum = presentMemberNum - 1;
			document.getElementsByClassName("memberNum")[0].innerHTML = "멤버 " + presentMemberNum;
    	}
    	
    }
});