
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