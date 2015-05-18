/**
 * Created by dahye on 2015. 5. 18
 */
ubuntudo.ui.SearchManager = (function() {
	'use strict';
    /* HTML에 의존하는 부분 캐싱*/
    var LIST_TEMPLATE = '<li>'
                        +'<a href="<%=uri%>"><%=gname%></a>'
                        +'<span class="gid" style="display:none"><%=gid%></span>'
                        +'</li>';
   
    /* 서버에 의존하는 부분 캐싱*/
    var URI_TEMPLATE = "/guild/";
    var GUILD_FIELD = {
        "GNAME": "guildName",
        "GID": "gid"
    }

    function SearchManager (elParent, elSearchInput) {
        this.elParent = elParent;
        this.elResult = elParent.parentElement;
        this.elSearchInput = elSearchInput;
        this.GUILD_FIELD = GUILD_FIELD;
        this.LIST_TEMPLATE = LIST_TEMPLATE;
        this.URI_TEMPLTE = URI_TEMPLATE;
    }
    
    SearchManager.prototype.autoComplete = function (ev) {
        var inputTimer = null;
        var searchText = ev.target.value;
        
        if(searchText.length < 1) {
            this.elResult.style.display = "none";
            return;
        }
        
        if(_isSearchable(ev))
        {
            if (inputTimer !== null) {
					clearTimeout(inputTimer);
            }
            inputTimer = setTimeout(function () {
				var util = ubuntudo.utility;
                util.ajax({
                    "method": "GET",
                    "uri": "/guild/search/" + searchText,
                    "param": null,
                    "callback": _showResult.bind(this)
                });
            }.bind(this), 300);
        }
    }
    
    SearchManager.prototype.removeAutoCompleteElement = function (ev) {
        if(!_isBackspaceOrDelete(ev.keyCode)){
            return;
        }
        if(this.elSearchInput.value === null) {
            this.elParent.style.display = "none";
        }
    }
    
    function _showResult(guildList) {
        var guildList = _makeSearchResultList.call(this, guildList);
        this.elParent.innerHTML = guildList;
        this.elResult.style.display = "block";
    }
    
    function _makeSearchResultList (guildList) {
        var result = "";
        //guildList를 돌면서 LIST_TEMPLATE 치환하여 result에 append
        for(var i = 0; i < guildList.length; i++) {
            var ginfo = guildList[i];
            var gid = ginfo[this.GUILD_FIELD.GID];
            var gname = ginfo[this.GUILD_FIELD.GNAME];
            var uri = this.URI_TEMPLATE + gid;
            result += LIST_TEMPLATE.replace("<%=gid%>", gid).replace("<%=gname%>", gname).replace("<%=uri%>", uri);
        }
        return result;
    }
    
    function _isSearchable(ev) {
        // return ture only on word characters, backspace or delete
        var c= String.fromCharCode(ev.keyCode);
        var keyCode = ev.keyCode;
        var searchText = ev.target.value;
        return (_isWordCharacter(c) || _isBackspaceOrDelete(keyCode)) && searchText.length > 0;
    }
     
    function _isWordCharacter (char) {
        return (char.match(/\w/) !== null);
    }
    
    function _isBackspaceOrDelete (keyCode){
        return (keyCode == 8 || keyCode == 46);
    }
    
    return SearchManager;
})();