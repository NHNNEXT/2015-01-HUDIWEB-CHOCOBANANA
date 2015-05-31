
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
    'use strict';

	var oGuildOverviewManager = new ubuntudo.ui.GuildOverviewManager();
	var util = ubuntudo.utility;


	oGuildOverviewManager.addEvents();
	util.ajax({
	    "method": "GET",
	    "uri": "/guild/overview/info",
	    "param": null,
	    "callback": oGuildOverviewManager.setGuildInfo
	});
});