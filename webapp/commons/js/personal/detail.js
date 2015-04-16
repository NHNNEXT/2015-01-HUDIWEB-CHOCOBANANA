var DMM = (function() {
    var DetailModalManager = function (sModalName, sTitleName, sDetailName) {
        this.elModal = document.querySelector(sModalName);
        this.elTitle = this.elModal.querySelector(sTitleName);
        this.elDetail = this.elModal.querySelector(sDetailName);
    };

    DetailModalManager.prototype.modalShow = function(ev, sTargetName, sChildName) {
        var elTarget = ev.target;
        var id;
        
        /*아래 제목처럼 주석처리한 부분은 나중에 private 함수로 뽑을 부분임*/
        //Target찾기
        while(elTarget.className !== sTargetName ) {
            elTarget = elTarget.parentElement;
        }
        
        //children찾기
        for(var i = 0; i < elTarget.childElementCount; i++) {
            if(elTarget.children[i].className === sChildName) {
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
        
        //모달창 보이기
        this.elModal.style.display = "block";
    };
    
    DetailModalManager.prototype.modalHide = function(ev) {
        this.elModal.style.display = "none";
    };
    
    return DetailModalManager;
})();

//service code
(function(){
    var elList = document.querySelector(".past");
    var elLightBox = document.querySelector(".light_box");
    var oDMM = new DMM(".detail_modal", ".title", ".detail_wrapper");
    
    elList.addEventListener("click", function(e) {
        oDMM.modalShow(e, "todo", "tid");
    });
    
    elLightBox.addEventListener("click", function(e) {
        oDMM.modalHide(e);
    }); 
})();


