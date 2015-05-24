ubuntudo.ui.TodoAddModal = (function() {
	'use strict';
    
    // HTML에 의존하는 name 캐싱
    var CLASSNAME = {
        SELECT_PARTY: "select_party"    
    };

    var IDNAME = {
        MODAL: "#add_wrap",
        SELECT_PARTY_LIST: "select_party_list",
        SELECTED_PARTY_NAME: "selected_party_name"
    }

    var SELECT_PARTY_TEMPLATE = '<label id="selected_party_name"><%=pname%></label><select id="select_party_list" onchange="showSelectedParty()"><%=optionList%></select>';
    var OPTION_TEMPLATE = '<option><%=pname%></option>';

    var PARTYLIST_FIELD = {
        PID: "pid",
        PNAME: "p_name"
    }
    
    function TodoAddModal () {
        this.elModal = document.querySelector(IDNAME.MODAL);
        this.partyList;
        this.setPartyList = setPartyList.bind(this);
    }

    function setPartyList (partyList) {
        this.partyList = partyList;
        var elTarget = document.querySelector("." + CLASSNAME.SELECT_PARTY);
        var optionList = _makeOptionList(this.partyList);
        var selectList = _makeSelectList(optionList, this.partyList);
        elTarget.innerHTML = selectList;
    }

    function _makeOptionList(partyList) {
        var result = '<option>개인</option>';
        for(var i = 0; i < partyList.length; i++) {
            var party = partyList[i];
            result += OPTION_TEMPLATE.replace("<%=pid%>", party[PARTYLIST_FIELD.PID]).replace("<%=pname%>", party[PARTYLIST_FIELD.PNAME]);
        }
        return result;
    }

    TodoAddModal.prototype.getPidFromPartyName = function (pname) {
        for(var i = 0; i < this.partyList.length; i++) {
            var party = this.partyList[i];
            if(pname === party[PARTYLIST_FIELD.PNAME]) {
                return party[PARTYLIST_FIELD.PID];
            }
        }
        return null;
    }

    function _makeSelectList(optionList, partyList) {
        var href = window.location.href;
        var page = href.split("\/")[3];
        if(page !== "party" || partyList.length < 1) {
            return SELECT_PARTY_TEMPLATE.replace("<%=pid%>", "-1").replace("<%=pname%>", "개인").replace("<%=optionList%>", optionList);
        }
        var pid = href.split("\/")[4];
        var index = ubuntudo.utility.findIndex(partyList, PARTYLIST_FIELD.PID, Number(pid));
        var pname = partyList[index][PARTYLIST_FIELD.PNAME];
        return SELECT_PARTY_TEMPLATE.replace("<%=pid%>", pid).replace("<%=pname%>", pname).replace("<%=optionList%>", optionList);
    }

    TodoAddModal.prototype.showSelectedParty = function () {
        var partyName = document.getElementById(IDNAME.SELECT_PARTY_LIST).value;
        document.getElementById(IDNAME.SELECTED_PARTY_NAME).innerHTML = partyName;
    }

    TodoAddModal.prototype.beforeShow = function(ev) {
        var elTarget = ev.target;
        var elCurTarget = ev.currentTarget;
        
        if(elTarget.className !== "add_todo") {
            elTarget = elTarget.parentElement;
        }
        
        //form내용 초기화
        if(elTarget.className !== "add_todo") {
            //elTarget === elCurTarget
            var form = document.querySelector(".add_todo");
            form.querySelector("#datepicker").value = "오늘";
            form.querySelector(".input_todoName").value = "";
            form.querySelector(".input_todoComment").value ="";
        }
    };
    
    return TodoAddModal;
})();
