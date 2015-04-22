/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
    var oTodoManager = new ubuntudo.ui.todoManager();
    var elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    var oDetailModal = new ubuntudo.ui.detailModal(oTodoManager.getData(), oTodoManager.getFieldName());
    var oModalManager = new ubuntudo.ui.modalManager(oDetailModal);
    
    oTodoManager.appendList("/personal");
    
    [].forEach.call(elList, function(element) {
        element.addEventListener("click", function(ev) {
        oModalManager.showModal(ev);
    })});
    
    elLightBox.addEventListener("click", function(e) {
        oModalManager.hideModal();
    }); 
    
    
    
    //submit 버튼 누르면 투두 추가 요청 - 리팩토링 필요
    var submitBtn = document.querySelector(".add_todo .submit_btn");
    submitBtn.addEventListener('click', function(e) {
        event.preventDefault();

        var form = document.querySelector(".add_todo");
        //var pid = form.querySelector(".add_todo select").value;
        var pid = -1;
        var date = document.getElementsByName("date")[0].value;
        if(date === "만기기한이 없습니다.") {
            date = "";
        }
        var title = document.getElementsByName("title")[0].value;
        if(title === ""){
            return "제목을 채워야 합니다.";
        }
        var contents = document.getElementsByName("contents")[0].value;

        var param = "pid=" + pid + "&date=" + date + "&title=" + title + "&contents=" + contents;

        var callback = function (result) {
            oTodoManager.setData(result);
            oModalManager.appendList();
            return true;
        }
        
//        if(ubuntudo.utility.ajax( "POST", "/personal", param, callback)) {
//            document.getElementById("add_wrap").style.display = "block";
//        }
    });
    
});

