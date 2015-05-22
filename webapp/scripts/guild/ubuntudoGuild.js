/*
retrieveGuildDetailAndPartyListController()

// 길드 헤더에서
길드 이름 = result[0].g_name
리더 = result[0].leader_id
멤버수 = result[0].members
파티수 = result[0].parties

// 파티목록에서 (파티 목록 나열은 pid순으로??)
파티이름 = result[1].p_name

// 파티 추가할때 
파티이름으로 입력한 값을 p_name에 저장해야함.
pid도 같이 새로 추가해야
이 파티를 만든 사람 아이디 저장해야 (로그인되어있는 user)

*/


ubuntudo = {};
ubuntudo.utility = {};

(function () {
    'use strict';
    var joinBtn = document.querySelector(".guild_join_btn");
    var leaveBtn = document.querySelector(".guild_leave_btn");
    
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
    
})();