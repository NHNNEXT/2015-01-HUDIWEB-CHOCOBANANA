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
    
    var appendList = function (data) {
        var elTargetList = {};
        var today = new Date();
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
            var due = new Date(todoInfo[this.FIELD_NAME.DUEDATE]);
            var dayDiff = Math.abs(today.diffDays(due));
            var explain = "남음";
            var elTarget = elTargetList[CLASSNAME.PAST]; //김태곤 교수님께 질문: 초기화를 안해줬더니 due<today여도 future 섹션에 들어가는 이유...
            
            if(due < today) {
                explain = "지남";
                eltarget = elTargetList[CLASSNAME.PAST];
            }else if(due === today) {
                elTarget = elTargetList[CLASSNAME.TODAY];
            }else {
                elTarget = elTargetList[CLASSNAME.FUTURE];
            }
            
            elTarget.innerHTML += _makeInnerHTML.call(this, todoInfo, dayDiff, explain);
        }
    };
    
    function TodoManager () {
        //DB의 todo 테이블에 의존하는 컬럼 네임 (엄밀하게는 서버의 todoEntity객체 field명)
        this.FIELD_NAME = {
            TID: "tid",
            ASSIGNER_ID: "assigner_id",
            PARTY_ID: "pid",
            PARTY_NAME: "pName",
            TITLE: "title",
            CONTENTS: "contents",
            DUEDATE: "dueDate",
            LAST_EDITER_ID: "last_editer_id"
        };
        
        this.appendList = appendList.bind(this);
                
       
    };
    
    TodoManager.prototype.appendList = this.appendList;
    
    TodoManager.prototype.getData = function () {
        return this.data;
    };
    
    TodoManager.prototype.setData = function(data) {
        this.data = data;
    };
    
    TodoManager.prototype.addData = function (data) {
        this.data.push(data);
        //dueDate별로 다시 정렬하기
    };
    
    TodoManager.prototype.getFieldName = function () {
        return this.FIELD_NAME;
    };

    function _makeInnerHTML (todoInfo, dayDiff, explain) {
        var field = this.FIELD_NAME;
        return LIST_TEMPLATE.replace("<%=tid%>", todoInfo[field.TID]).replace("<%=title%>", todoInfo[field.TITLE]).replace("<%=date%>", dayDiff).replace("<%=explain%>", explain); //pNAME넘어와야 함
        
    };
    
    function _bind(context, method) {
        return function() {
            return method.apply(context, arguments);
        };
    };

    Date.prototype.diffDays = function (date) {
        var timeDiff = Math.abs(this.getTime() - date.getTime());
        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
        return diffDays;
    };
    
    return TodoManager;
})();