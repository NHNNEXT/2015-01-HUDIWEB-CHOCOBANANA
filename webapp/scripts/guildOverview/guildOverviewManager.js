ubuntudo.ui.GuildOverviewManager = (function () {
	'use strict';
	// HTML에 의존하는 CLASS,ID 캐싱
	//var CLASSNAME = {
	//	SIGNUP_BOX: "signup_box",
	//	ERROR: "error",
	//	OK: "fine"
	//};

	var IDNAME = {
		OVERVIEW_HEADER: "gdo_header",
		ADD_GUILD_BOX: "guild_add_box_btn",
		INPUT_ADD_BTN: "input_guild_add_btn",
		INPUT_CANCEL_BTN: "input_guild_cancel_btn",
		MODAL_ID: "guild_input_box",
		GUILD_LIST: "gdo_list"

	};
	// 에러출력 메세지 캐싱
	//var MESSAGE = {
	//	NOT_CONFIRM_PASSWORD: "비밀번호가 일치하지 않는다요!"
	//};
	function GuildOverviewManager() {
		if (this instanceof GuildOverviewManager) {
			this.elGdoHeader = document.querySelector("#"+IDNAME.OVERVIEW_HEADER);
			this.guildModal = new ubuntudo.ui.GuildAddModal(this);
			this.elAddBtn = this.elGdoHeader.querySelector("#" + IDNAME.ADD_GUILD_BOX);
		} else {
			return new GuildOverviewManager();
		}
	}

	GuildOverviewManager.prototype.addEvents = function () {
		this.elAddBtn.addEventListener("click", this.showModal.bind(this));
	};

	GuildOverviewManager.prototype.showModal = function () {
		this.guildModal.showModal.bind(this.guildModal)();
	};

	GuildOverviewManager.prototype.hideModal = function () {
		this.guildModal.hideModal();
	};


	GuildOverviewManager.prototype.hideModal = function () {
		this.guildModal.hideModal();
	};


	GuildOverviewManager.prototype.setGuildInfo = function (guildListData) {
		var guilds = guildListData.result.guilds;
		var parties = guildListData.result.parties;
		var guild_list = document.querySelector("#" + IDNAME.GUILD_LIST);
		var GUILD_LIST_TEMPLATE =       '<li class="gdo_container">' +
										'<a href="/guild/<%= guild.gid %>">' +
										'<article class="guild" >' +
											'<header class="gdo_title">' +
											'<h1 class="gdo_list_title"><%= guild.name %></h1>' +
											'</header>' +
										'<section class="gdo pt_list_container">' +
										'<h1 class="blind"><%= guild.name %>의 파티리스트</h1>' +
										'<ul class="gdo_pt_list " data-gid="<%= guild.gid %>"> <%= guild.parties %> </ul>' +
										'</section></article></a></li>';

		var G_PARTY_LIST_TEMPLATE = '<li class="pt_container">' +
									'<a href="/party/<%= party.pid %>">' +
									'<article class="party">' +
										'<header>' +
											'<h1 class="gdo_pt_title"><%= party.name %></h1>' +
										'</header>' +
											'<div class="gdo pt_content">' +
											'</div> ' +
									'</article>' +
									'</a></li>';
		// repaceAll 확장
		String.prototype.replaceAll = function(param1, param2) {
			return this.split(param1).join(param2);
		};

		function _makeGuildList() {
			var pattern = {
				guild : {
					gid : '<%= guild.gid %>',
					name : '<%= guild.name %>',
					parties :'<%= guild.parties %>'
				},
				party : {
					pid : '<%= party.pid %>',
					name : '<%= party.name %>'
				}
			};
			var result = [];

			guilds.forEach(function(guild){
				var parties_list = [];
				parties.forEach(function(party) {
					if(guild.gid === party.gid) {
						parties_list.push(
							G_PARTY_LIST_TEMPLATE.replace(pattern.party.pid, party.pid)
												 .replace(pattern.party.name, party.p_name));
					}
				});
				var tmp2 = parties_list.join('');
				var tmp =	GUILD_LIST_TEMPLATE.replace(pattern.guild.parties,tmp2)
					.replaceAll(pattern.guild.gid, guild.gid)
					.replaceAll(pattern.guild.name, guild.g_name);
				result.push(tmp);
			});
			return result.join('');
		}
		guild_list.innerHTML = _makeGuildList();
	};

	return GuildOverviewManager;

})();
