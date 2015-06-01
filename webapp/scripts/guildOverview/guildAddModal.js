ubuntudo.ui.GuildAddModal = (function() {
	'use strict';
	var IDNAME = {
		ADD_GUILD_BOX: "guild_add_box_btn",
		INPUT_ADD_BTN: "input_guild_add_btn",
		INPUT_CANCEL_BTN: "input_guild_cancel_btn",
		MODAL_ID: "guild_input_box"
	};

	var CLASSNAME = {
		INPUT_GUILD_NAME: "input_guild_name"
	};


	var util = ubuntudo.utility;

	function GuildAddModal () {
		this.elModal = document.querySelector("#"+IDNAME.MODAL_ID);
		this.elTitle = this.elModal.querySelector("." + CLASSNAME.INPUT_GUILD_NAME);
		this.elAdd = this.elModal.querySelector("#" + IDNAME.INPUT_ADD_BTN);
		this.elCancel = this.elModal.querySelector("#" + IDNAME.INPUT_CANCEL_BTN);
		this.elAddBtn = document.querySelector("#" + IDNAME.ADD_GUILD_BOX);

		this.elAdd.addEventListener("click", this.addGuild.bind(this));
		this.elCancel.addEventListener("click", this.cancelGuild.bind(this));
	}

	GuildAddModal.prototype.addGuild = function () {
		var guildName = this.elTitle.value;
		util.ajax({
			"method": "POST",
			"uri": "/guild",
			"param":"guildName=" + guildName,
			"callback": this.addGuildCallback
		});
	};

	GuildAddModal.prototype.addGuildCallback = function () {
		window.location.reload();
	};

	GuildAddModal.prototype.cancelGuild = function () {
		this.hideModal();
	};

	GuildAddModal.prototype.showModal = function () {
		this.elAddBtn.style.display = "none";
		this.elModal.style.display = "block";
	};


	GuildAddModal.prototype.hideModal = function () {
		this.elModal.style.display = "none";
		this.elAddBtn.style.display = "block";
	};

	return GuildAddModal;
})();