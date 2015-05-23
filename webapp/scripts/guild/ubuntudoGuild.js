/*
retrieveGuildDetailAndPartyListController()

// 길드 헤더에서
길드 이름 = result[0].g_name
리더 = result[0].leader_id
멤버수 = result[0].members
파티수 = result[0].parties

// 파티목록에서 (파티 목록 나열은 pid순으로??)
파티이름 = result[1].p_name
내가 가입한 파티(state = 1) / 내가 가입하지 않은 파티(state = -1)에 대해서는 파티 색깔로 구분하기

// 파티 추가할때 
파티이름으로 입력한 값을 p_name에 저장해야함.
pid도 같이 새로 추가해야
이 파티를 만든 사람 아이디 저장해야 (로그인되어있는 user)

*/

ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};
// ubuntudo.dataChangedEvent = new CustomEvent("dataChanged");

window.addEventListener("load", function () {
    'use strict';
    var joinBtn = document.querySelector(".guild_join_btn");
    var leaveBtn = document.querySelector(".guild_leave_btn");
    
    var href = window.location.href;
    var gid = href.substr(href.lastIndexOf("/")+1);
    var util = ubuntudo.utility;
    util.ajax({
        "method": "GET",
        "uri": "/guild/info/"+ gid,
        "param": null,
        "callback": setGuildInfo
    });
    
    function setGuildInfo(guildInfoData) {
    	var data = guildInfoData;
    	var guildName = guildInfoData["result"].guildDetail[0].g_name;
    	
    	
    	var G_NAME_TEMPLATE = '<h1 class="guild_name"><%=g_name%></h1>';
    	
    	document.getElementsByClassName("g_name")[0].innerHTML = G_NAME_TEMPLATE.replace("<%=g_name%>", guildName);
    	
    }
    
    
    
    // 길드 가입하기 버튼 클릭 시
    // 멤버수 늘려야함 
    joinBtn.addEventListener("click", function () {
        var util = ubuntudo.utility;
        
        /*test용도 gid, uid*/
        var gid = 1;
        var uid = 1;
        var param = "guildId=" + gid + "&userId=" + uid;
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