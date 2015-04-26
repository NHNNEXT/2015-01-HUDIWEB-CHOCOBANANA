/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};
dataChangedEvent = new CustomEvent("dataChanged");

window.addEventListener("load", function () {
    var util = ubuntudo.utility;
    var oDataManager = new ubuntudo.ui.dataManager();
    var oTodoManager = new ubuntudo.ui.todoManager();
    var elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    var oDetailModal;
    var oModalManager;
    
    util.ajax({
        "method": "GET",
        "uri": "/personal/todo",
        "param": null,
        "callback": oDataManager.setData
    });
    
    window.addEventListener("dataChanged", function () {
        var data = oDataManager.getData();
        var fieldName = oDataManager.getFieldName();
        oDetailModal = new ubuntudo.ui.detailModal(data, fieldName);
        oModalManager = new ubuntudo.ui.modalManager(oDetailModal);  
        oTodoManager.appendList(data, fieldName);
    });
    
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
        e.preventDefault();
        e.stopPropagation(); 
        
        var form = document.querySelector(".add_todo");
        //var pid = form.querySelector(".add_todo select").value;
        var pid = -1;
        var date = document.getElementsByName("date")[0].value;
        if(date === "만기기한이 없습니다.") {
            date = new Date();
            //date = date.toISOString().slice(0,10).replace(/-/g,"-"); 이러면 27일인데 26일이 나옴. 이유를 찾아보자.
            date = date.yyyymmdd();
        }
        var title = document.getElementsByName("title")[0].value;
        if(title === ""){
            return "제목을 채워야 합니다.";
        }
        var contents = document.getElementsByName("contents")[0].value;

        var param = "pid=" + pid + "&dueDate=" + date + "&title=" + title + "&contents=" + contents;

        util.ajax({
            "method": "POST", 
            "uri": "/personal", 
            "param" : param, 
            "callback" : oDataManager.addData
        });
        
        document.getElementById("add_wrap").style.display = "none";
    }); 
});



