ubuntudo.ui.GuildAddModal = (function() {
	'use strict';
	var IDNAME = {
		ADD_GUILD_BOX: "party_add_box_btn",
		INPUT_ADD_BTN: "inputPartyName_add_btn",
		INPUT_CANCEL_BTN: "inputPartyName_cancel_btn",
		MODAL_ID: "partyName_input_box_btn",
		SUCCESS_MESSAGE: "signup_email_message",
		FAIL_MESSAGE: "signup_password_message"
	};

	var CLASSNAME = {
		INPUT_GUILD_NAME: "input_partyName"
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
		console.log("addGuildCallback");
	};

	GuildAddModal.prototype.cancelGuild = function () {
		this.hideModal();
	};

	GuildAddModal.prototype.showModal = function () {
		this.elAddBtn.style.display = "none";
		this.elModal.style.display = "block";
	};


	GuildAddModal.prototype.hideModal = function () {
		this.elAddBtn.style.display = "block";
		this.elModal.style.display = "none";
	};

	return GuildAddModal;
})();