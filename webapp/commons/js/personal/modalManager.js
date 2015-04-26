/**
 * Created by dahye on 2015. 4. 15
 */
ubuntudo.ui.modalManager = (function() {
    function ModalManager (oModal) {
        this.modal = oModal;
        this.elModal = oModal.elModal;
    }
    
    ModalManager.prototype.showModal = function(ev) {
        if(this.modal.beforeShow(ev) === true) {
            this.elModal.style.display = "block";
        }
    }
    
    ModalManager.prototype.hideModal = function() {
        this.elModal.style.display = "none";   
    }
    
    return ModalManager;
})();
//이거는 즉시실행으로 감싸도 예쁜 것 같다.

ubuntudo.ui.detailModal = (function() {
    // HTML에 의존하는 CLASS 캐싱 _ 이것은 익명함수니까 private으로 쓸 수 있다! 
	var CLASSNAME = {
		MODAL: "detail_modal",
		TITLE: "title",
		DETAIL: "detail_wrapper",
        TODO: "todo",
        TID: "tid",
        PARTY :"party",
        DUEDATE : "due_date",
        CONTENTS : "note",
        COMPLETE_BTN: "complete_btn"
	};    
    
    function DetailModal (todoData, fieldName) {
        this.todoData = todoData;
        this.fieldName = fieldName;
        this.elModal = document.querySelector("."+CLASSNAME.MODAL);
        this.elTitle = this.elModal.querySelector("."+CLASSNAME.TITLE);
        this.elDetail = this.elModal.querySelector("."+CLASSNAME.DETAIL);
    }

    DetailModal.prototype.beforeShow = function(ev) {
        var elTarget = ev.target;
        var id;
        
        /*주석처리한 부분을 모듈로 하여 private 함수로 뽑을 예정*/
        //완료버튼이면 modalShow하지 않도록 방어코드   
        if(elTarget.className === CLASSNAME.COMPLETE_BTN) {
            return false;
        }
        
        //todo찾기
        while(elTarget.className !== CLASSNAME.TODO ) {
            elTarget = elTarget.parentElement;
        }
        
        //tid찾기 - li에다가 data로 심는 게 나을 것 같다.
        for(var i = 0; i < elTarget.childElementCount; i++) {
            if(elTarget.children[i].className === CLASSNAME.TID) {
                id = elTarget.children[i].innerHTML * 1; // 1을 곱한 건 string->int변환하기 위해
                break;
            }
        }
        
        //data[i]["tid"] = id인 인덱스 i 찾기
	    var util = ubuntudo.utility;
        var index = util.findIndex(this.todoData, "tid", id);
        
        //data 불러오기
        var todoInfo = this.todoData[index];
        var field = this.fieldName;
        
        //모달창에 title심기, tid, pName, contents, duedate 심기
        this.elTitle.innerHTML = todoInfo[field.TITLE];
        for(var i = 0; i < this.elDetail.childElementCount; i++) {
            if(this.elDetail.children[i].className === CLASSNAME.TID) {
                this.elDetail.children[i].innerHTML = todoInfo[field.TID];
            }
            else if(this.elDetail.children[i].className === CLASSNAME.PARTY) {
                this.elDetail.children[i].innerHTML = todoInfo[field.PARTY_NAME];
            }
            else if(this.elDetail.children[i].className === CLASSNAME.DUEDATE) {
                this.elDetail.children[i].innerHTML = todoInfo[field.DUEDATE];
            }
            else if(this.elDetail.children[i].className === CLASSNAME.CONTENTS) {   
            	this.elDetail.children[i].value = todoInfo[field.CONTENTS];    
            }
        }
        
        return true;
    };

    return DetailModal;
})();