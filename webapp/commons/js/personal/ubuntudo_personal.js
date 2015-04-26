/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

window.addEventListener("load", function () {
    var util = ubuntudo.utility;
    var fecho = util.echo;
    var oTodoManager = new ubuntudo.ui.todoManager();

    util.ajax({
        "method": "GET",
        "uri": "/personal/todo",
        "param": null,
        "callback": oTodoManager.appendList,
        "context": oTodoManager
    });  
    
    var elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    
    
    //이 아래부터는 data가 오고 난 후부터 해야하는데... async라 데이터가 언제 올지 ㅠㅠ => appendList에서 이벤트를 발생시키자!
    var oDetailModal = new ubuntudo.ui.detailModal(oTodoManager.getData(), oTodoManager.getFieldName());
    var oModalManager = new ubuntudo.ui.modalManager(oDetailModal);
        

    //oTodoManager.appendList();
    
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
            date = null;
        }
        var title = document.getElementsByName("title")[0].value;
        if(title === ""){
            return "제목을 채워야 합니다.";
        }
        var contents = document.getElementsByName("contents")[0].value;

        var param = "pid=" + pid + "&dueDate=" + date + "&title=" + title + "&contents=" + contents;

        var callback = function (result){
            var newResult = oTodoManager.addData(result);
            oTodoManager.appendList(newResult);
        }
        
        ubuntudo.utility.ajax({
            "method": "POST", 
            "uri": "/personal", 
            "param" : param, 
            "callback" : callback
        });
        
        
        document.getElementById("add_wrap").style.display = "none";
        e.stopPropagation(); 
    });
    
});

