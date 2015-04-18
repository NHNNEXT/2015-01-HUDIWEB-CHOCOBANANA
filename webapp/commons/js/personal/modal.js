/**
 * Created by dahye on 2015. 4. 15
 */
ubuntudo.ui.detailModal = (function() {
    
    // HTML에 의존하는 CLASS 캐싱
	var CLASSNAME = {
		MODAL: "detail_modal",
		TITLE: "title",
		DETAIL: "detail_wrapper",
        TODO: "todo",
        TID: "tid",
        COMPLETE_BTN: "complete_btn"
	};    
    
    function DetailModal () {
        this.elModal = document.querySelector("."+CLASSNAME.MODAL);
        this.elTitle = this.elModal.querySelector("."+CLASSNAME.TITLE);
        this.elDetail = this.elModal.querySelector("."+CLASSNAME.DETAIL);
    }

    DetailModal.prototype.beforeShow = function(ev) {
        var elTarget = ev.target;
        var id;
        /*아래 제목처럼 주석처리한 부분은 나중에 private 함수로 뽑을 부분임*/
        //완료버튼이면 modalShow하지 않도록 방어코드   
        if(elTarget.className === CLASSNAME.COMPLETE_BTN) {
            return false;
        }
        
        //todo찾기
        while(elTarget.className !== CLASSNAME.TODO ) {
            elTarget = elTarget.parentElement;
        }
        
        //tid찾기
        for(var i = 0; i < elTarget.childElementCount; i++) {
            if(elTarget.children[i].className === CLASSNAME.TID) {
                id = elTarget.children[i].innerHTML;
                break;
            }
        }
        
        //findIndex 사용하기 위해 utility 모듈 가져옴
	    var util = ubuntudo.utility;
        
        //tid를 통해 data[i]["tid"] = tid인 i찾기
        //data[i]["contents"], data[i].["pName"], data[i].["title"], data[i].["duedate"] 모달창에 심기
        
        //모달창에 tid 심기 
        for(var i = 0; i < this.elDetail.childElementCount; i++) {
            if(this.elDetail.children[i].className === CLASSNAME.TID) {
                this.elDetail.children[i].innerHTML = id;
                break;
            }
        }
        
        return true;
    };

    return DetailModal;
})();

ubuntudo.ui.modalManager = (function() {
    function ModalManager (oModal) {
        this.modal = oModal;
        this.elModal = oModal.elModal;
    }
    
    ModalManager.prototype.modalShow = function(ev) {
        if(this.modal.beforeShow(ev) === true) {
            this.elModal.style.display = "block";
        }
    }
    
    ModalManager.prototype.modalHide = function() {
        this.elModal.style.display = "none";   
    }
    
    return ModalManager;
})();

