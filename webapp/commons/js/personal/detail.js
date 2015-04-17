/**
 * Created by dahye on 2015. 4. 15
 */
ubuntudo.ui.modalManager = (function() {
    var ModalManger = function (oModal) {
        this.modal = oModal;
        this.elModal = oModal.elModal;
    }
    
    ModalManger.prototype.modalShow = function(ev) {
        this.modal.beforeShow(ev);
        this.elModal.style.display = "block";
    }
    
    ModalManger.prototype.modalHide = function() {
        this.elModal.style.display = "none";   
    }
    
    return ModalManager;
})();

ubuntudo.ui.detailModal = (function() {
    var DetailModal = function (sModalName, sTitleName, sDetailName, sTargetName, sChildName) {
        this.elModal = document.querySelector(sModalName);
        this.elTitle = oModalManager.elModal.querySelector(sTitleName);
        this.elDetail = oModalManager.elModal.querySelector(sDetailName);
        this.sTargetName = sTargetName;
        this.sChildName = sChildName;
    };

    DetailModalManager.prototype.beforeShow = function(ev) {
        var elTarget = ev.target;
        var id;
        /*아래 제목처럼 주석처리한 부분은 나중에 private 함수로 뽑을 부분임*/
        //완료버튼이면 modalShow하지 않도록 방어코드   
        if(elTarget.className === "complete_btn") {
            return;
        }
        
        //Target찾기
        while(elTarget.className !== this.sTargetName ) {
            elTarget = elTarget.parentElement;
        }
        
        //children찾기
        for(var i = 0; i < elTarget.childElementCount; i++) {
            if(elTarget.children[i].className === this.sChildName) {
                id = elTarget.children[i].innerHTML;
                break;
            }
        }
        //tid를 통해 data[i]["tid"] = tid인 i찾기
        //data[i]["contents"], data[i].["pName"], data[i].["title"], data[i].["duedate"] 모달창에 심기
        
        //모달창에 tid 심기 
        for(var i = 0; i < this.elDetail.childElementCount; i++) {
            if(this.elDetail.children[i].className === sChildName) {
                this.elDetail.children[i].innerHTML = id;
                break;
            }
        }
    };

    return DetailModal;
})();
