
ubuntudo = {};
ubuntudo.utility = {};
(function () {
    'use strict';
    var joinBtn = document.querySelector(".guild_join_btn");
    var leaveBtn = document.querySelector(".guild_leave_btn");
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
            "callback": callback
	   });
    });
    
    function callback () {
        joinBtn.style.display = "none";
        leaveBtn.style.display = "block";
    }
    
})();