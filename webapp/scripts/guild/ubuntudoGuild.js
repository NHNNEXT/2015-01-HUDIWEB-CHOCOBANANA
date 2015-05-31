
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


    var joinBtn = document.querySelector(".guild_join_btn");
    var leaveBtn = document.querySelector(".guild_leave_btn");
    
    // 길드 가입하기 버튼 클릭 시
    // 멤버수 늘려야함 
    joinBtn.addEventListener("click", function () {
        var util = ubuntudo.utility;
        
        /*test용도 gid, uid*/
        var gid = 1;
        var param = "guildId=" + gid;
        util.ajax({
            "method": "POST",
            "uri": "/guild/user",
            "param": param,
            "callback": callbackSuccessJoin
	   });
    });
    
    function callbackSuccessJoin () {
        joinBtn.style.display = "none";
        leaveBtn.style.display = "block";
    }
    
    // 길드 탈퇴하기 버튼 클릭 시
    // 멤버수 한 명 빼야함 
    leaveBtn.addEventListener("click", function () {
        var util = ubuntudo.utility;
        
        /*test용도 gid, uid*/
        var gid = 1;
        var uid = 1;
        var param = "guildId=" + gid + "&userId=" + uid;
        util.ajax({
            "method": "POST",
            "uri": "/guild/user",
            "param": param,
            "callback": callbackSuccessLeave
	   });
    });
    
    function callbackSuccessLeave () {
        joinBtn.style.display = "block";
        leaveBtn.style.display = "none";
    }
});