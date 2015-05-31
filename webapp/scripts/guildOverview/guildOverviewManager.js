ubuntudo.ui.GuildOverviewManager = (function () {
	'use strict';
	// HTML에 의존하는 CLASS,ID 캐싱
	var CLASSNAME = {
		SIGNUP_BOX: "signup_box",
		ERROR: "error",
		OK: "fine"
	};

	var IDNAME = {
		OVERVIEW_HEADER: "gdo_header",
		ADD_GUILD_BOX: "party_add_box_btn",
		INPUT_ADD_BTN: "inputPartyName_add_btn",
		INPUT_CANCEL_BTN: "inputPartyName_cancel_btn",
		MODAL_ID: "partyName_input_box_btn",
		EMAIL_MESSAGE: "signup_email_message",
		PASSWORD_MESSAGE: "signup_password_message",
		PASSWORD_CHECK_MESSAGE: "signup_password_check_message",
		GUILD_LIST: "gdo_list"

	};

	// 에러출력 메세지 캐싱
	var MESSAGE = {
		RECHECK_EMAIL: "이메일 주소를 다시 확인해주세요.",
		GOOD_EMAIL: "멋진 이메일이다요!",
		EXIST_EMAIL: "이미 사용중인 이메일이다요.",
		DEFAULT: "필수 정보입니다.",
		WARNING_PASSWORD: "8자이상의 영문 대문자,소문자,숫자,특수문자를 사용하세요.",
		GOOD_PASSWORD: "아름다운 비밀번호다요!",
		CONFIRM_PASSWORD: "비밀번호가 일치한다요!",
		NOT_CONFIRM_PASSWORD: "비밀번호가 일치하지 않는다요!"
	};

	//ajax 모듈을 위해 util 모듈 가져옴.
	var util = ubuntudo.utility;
	var oGuildDataManager = ubuntudo.ui.GuildDataManager;

	function GuildOverviewManager() {
		if (this instanceof GuildOverviewManager) {
			this.elGdoHeader = document.querySelector("#"+IDNAME.OVERVIEW_HEADER);
			this.guildModal = new ubuntudo.ui.GuildAddModal(this);
			this.elAddBtn = this.elGdoHeader.querySelector("#" + IDNAME.ADD_GUILD_BOX);
			this.elGuildList = document.querySelector("#" + IDNAME.GUILD_LIST);
			this.guildInfo = {};
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




	GuildOverviewManager.prototype.setGuildInfo = function (guildListData) {
		var guilds= guildListData["result"].guilds;
		var parties = guildListData["result"].parties;
		var guild_list = document.querySelector("#" + IDNAME.GUILD_LIST);
		var GUILD_LIST_TEMPLATE ='<a href="/guild/<%= guild.gid %>">' +
										'<li class="gdo_container <%= guild.index %>"> ' +
										'<article class="guild <%= guild.index %>" >' +
											'<header class="gdo_title">' +
											'<h1 class="gdo_list_title"><%= guild.name %></h1> ' +
											'</header>' +
										'<section class="gdo pt_list_container">' +
										'<h1 class="blind"><%= guild.name %>의 파티리스트</h1>' +
										'<ul class="gdo pt_list <%= guild.index %>" data-gid="<%= guild.gid %>"> <%= guild.parties %> </ul>' +
										'</section></article></li></a>';
									//'<ul class="gdo pt_list <%= guild.index %>">';

		var G_PARTY_LIST_TEMPLATE =
									'<a href="/party/<%= party.pid %>">' +
									'<li class="pt_container ">' +
									'<article class="party ">' +
										'<header>' +
											'<h1 class="gdo_pt_title"><%= party.name %></h1>' +
										'</header>' +
											'<div class="gdo pt_content">' +
												'<p><%= party.name %>의 내용들</p>' +
											'</div> ' +
									'</article>' +
									'</li>';
		// repaceAll 확장
		Object.prototype.replaceAll = function(param1, param2) {
			return this.split(param1).join(param2);
		};

		function _makeGuildList() {

			var pattern = {
				guild : {
					gid : '<%= guild.gid %>',
					index : '<%= guild.index %>',
					name : '<%= guild.name %>',
					parties :'<%= guild.parties %>'
				},
				party : {
					pid : '<%= party.pid %>',
					name : '<%= party.name %>'
				}
			};
			var result = [];

			guilds.forEach(function(guild, index){

				var tmp =	GUILD_LIST_TEMPLATE.replaceAll(pattern.guild.gid, guild.gid)
							.replaceAll(pattern.guild.index, index)
							.replaceAll(pattern.guild.name, guild.g_name);


				var parties_list = [];
				parties.forEach(function(party) {
					if(guild.gid === party.gid) {

						parties_list.push(
							G_PARTY_LIST_TEMPLATE.replaceAll(pattern.party.pid, party.pid)
												 .replaceAll(pattern.party.name, party.name));
					}
				});
				var tmp2 = parties_list.join('');
				result.push(tmp.replace(pattern.guild.parties,tmp2));
			});
			var final = result.join('');
			return final;
		}

		guild_list.innerHTML += _makeGuildList();


	};

	return GuildOverviewManager;

})();
