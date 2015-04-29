/**
 * Created by dahye.
 * Module Patternized by dahye on 2015. 4. 18
 */
ubuntudo.ui.todoManager = (function() {
    //HTML에 의존하는 클래스 네임 
    var CLASSNAME = {
		PAST: "past",
		TODAY: "today",
		FUTURE: "future",
	}; 
    
    //CSS에 의존하는 리스트 템플릿
    var LIST_TEMPLATE = '<li class="todo"><span class="tid"><%=tid%></span><div class="party_pavicon"><%=partyFirstAlphabet%></div><h2><%=title%></h2><p><%=date%>일 <%=explain%></p><a class="complete_btn"></a></li>';
      
    function TodoManager () {  
    };
    
    TodoManager.prototype.appendList = function (data, fieldName) {
        var elTargetList = {};
        var now = new Date();
        var today = new Date(now.yyyymmdd());
        var dataLength = data.length;
            
        //elTargetList mapping
        Object.keys(CLASSNAME).forEach( function(key) {
            var element = CLASSNAME[key];
            elTargetList[element] = document.querySelector("." + element);
            elTargetList[element].innerHTML = ""; //innerHTML 초기화
        });
        
        //duedate에 따라 각 섹션에 투두 추가
        for(var i = 0; i < dataLength; i++) {
            var todoInfo = data[i];
            var due = new Date(todoInfo[fieldName.DUEDATE]);
            var dayDiff = Math.abs(due.diffDays(today));
            var explain = "남음";
            var elTarget;             
            if(due < today) {
                explain = "지남";
                elTarget = elTargetList[CLASSNAME.PAST];
            }else if(due > today) {
                elTarget = elTargetList[CLASSNAME.FUTURE];
            }else {
                elTarget = elTargetList[CLASSNAME.TODAY];
            }
            
            elTarget.innerHTML += _makeInnerHTML(todoInfo, dayDiff, explain, fieldName);
        }
    };
    
    TodoManager.prototype.complete = function (ev, oDataManager) {
        ev.stopPropagation();
        var elTarget = ev.target;
        var elSibling = elTarget.parentElement.childNodes;
        var siblingCount = elTarget.parentElement.childElementCount;
        var util = ubuntudo.utility;
        var param;
        var i;
        var tid;
        var index;
        
        //sibling 중 classname 이 tid인 녀석을 찾아 value를 찾아 
        for(i = 0; i < siblingCount; i++){
            if(elSibling[i].className === "tid") {
                tid = elSibling[i].innerHTML;
            }
        }
        index = util.findIndex(oDataManager.getData(), "tid", tid);
        param = "tid=" + tid;
        
        function _callback (result) {
            var status = result.status;
            if(status === "success") {
                oDataManager.removeData(index);
            }
        }
        
        
        util.ajax({
            "method": "GET", 
            "uri": "/personal/todo/complete", 
            "param" : param, 
            "callback" : _callback; 
        });
        
        

        
    }
    
    TodoManager.prototype.add = function (elTarget) {
    
    }

    function _makeInnerHTML (todoInfo, dayDiff, explain, fieldName) {
        var field = fieldName;
        return LIST_TEMPLATE.replace("<%=tid%>", todoInfo[field.TID]).replace("<%=title%>", todoInfo[field.TITLE]).replace("<%=date%>", dayDiff).replace("<%=explain%>", explain).replace("<%=partyFirstAlphabet%>", todoInfo[field.PARTY_NAME].substring(0,1));
        
    };
    
    Date.prototype.diffDays = function (date) {
        var timeDiff = Math.abs(this.getTime() - date.getTime());
        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
        return diffDays;
    };
    
    Date.prototype.yyyymmdd = function() {
       var yyyy = this.getFullYear().toString();
       var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based
       var dd  = this.getDate().toString();
       return yyyy + "-" + (mm[1]?mm:"0"+mm[0]) + "-" + (dd[1]?dd:"0"+dd[0]); // padding
    };
    
    return TodoManager;
})();