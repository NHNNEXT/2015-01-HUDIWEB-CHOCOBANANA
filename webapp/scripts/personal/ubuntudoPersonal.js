/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};
ubuntudo.dataChangedEvent = new CustomEvent("dataChanged");

function showSelectedParty () {
	var selectList = document.getElementById("select_party_list");
    var selected = document.getElementById("selected_party_name");
   	var partyName = selectList.value;
    selected.innerHTML = partyName;
    selected.pid = selectList.pid;
} 

window.addEventListener("load", function () {
	'use strict';      
	// modal창 중 detailModal을 선택해서 ModalManager에 넣는것 같은데, 이런 형태면 Modal을 관리하는 인터페이스 같은 객체가 있어야할 뜻..

    /*todo data 서버에 요청하여 받아오기*/
	var util = ubuntudo.utility;
	var oDataManager = new ubuntudo.ui.DataManager();
	util.ajax({
		"method": "GET",
		"uri": "/personal/todo",
		"param": null,
		"callback": oDataManager.setData
	});

	/*user가 가입한 파티 리스트 서버에 요청하여 받아오기*/
	var oTodoAddModal = new ubuntudo.ui.TodoAddModal(); 	
	util.ajax({
		"method": "GET",
		"uri": "/party",
		"param": null,
		"callback": oTodoAddModal.setPartyList
	});

    /*todo data에 변화가 있을 때 list를 다시 그리고, 다시 그려진 각 리스트에 이벤트 새롭게 등록.*/
	var oTodoManager = new ubuntudo.ui.TodoManager();
	var elCompleteBtnList;
	var oDetailModal;
	var oModalManager;
	window.addEventListener("dataChanged", function () {
		/*
		 *@private
		 */
		function _addEvent(element) {
			element.addEventListener("click", _complete);
		}

		function _removeEvent(element) {
			element.removeEventListener("click", _complete);
		}

		function _complete(ev) {
			oTodoManager.complete(ev, oDataManager);
		}

		var data = oDataManager.getData();
		var fieldName = oDataManager.getFieldName();
		
        oDetailModal = new ubuntudo.ui.DetailModal(data, fieldName);
		oModalManager = new ubuntudo.ui.ModalManager(oDetailModal);
        
        // todo data리스트 다시 그리기
        oTodoManager.appendList(data, fieldName); 
		
        // 각 완료 버튼에 이벤트 다시 걸기
        if (elCompleteBtnList !== undefined) {
			[].forEach.call(elCompleteBtnList, _removeEvent);
		}
		elCompleteBtnList = document.getElementsByClassName('complete_btn');
		[].forEach.call(elCompleteBtnList, _addEvent);
	});

    /*detail modal관련 이벤트 등록*/
    var elList = document.querySelectorAll(".ns_personal section ul");
	var elLightBox = document.querySelector(".light_box");
	[].forEach.call(elList, function (element) {
		element.addEventListener("click", function (ev) {
			oModalManager.showModal(ev);
		});
	});
	elLightBox.addEventListener("click", function (ev) {
		oModalManager.hideModal(ev);
	});
    
    /*add todo modal관련 이벤트 등록*/
    var elAddTodoBtn = document.querySelector(".todo_add_btn");
    var elCancelBtn = document.querySelector(".cancel_btn");
    //oTodoAddModal은 위에서 만들어 둠
    var oTodoAddModalManager = new ubuntudo.ui.ModalManager(oTodoAddModal);
    
    elAddTodoBtn.addEventListener("click", function(ev){
        oTodoAddModalManager.showModal(ev);
    });

    elCancelBtn.addEventListener("click", function(ev){
	   oTodoAddModalManager.hideModal(ev);
    });
    
	/* submit 버튼 누르면 투두 추가 */
	var elSubmitBtn = document.querySelector(".add_todo .submit_btn");
	elSubmitBtn.addEventListener('click', function (ev) {
       oTodoManager.add(ev, oDataManager, oTodoAddModal);
	   oTodoAddModalManager.hideModal(ev);
	});
    
    /*길드 검색 이벤트 등록*/
    var elSearchResultList =  document.querySelector(".search_result_list");
    var elSearchInput = document.getElementById("global-header").querySelector(".search_input");
    var oSearchManager = new ubuntudo.ui.SearchManager(elSearchResultList, elSearchInput);
    elSearchInput.addEventListener("keyup", function(ev) {
        oSearchManager.autoComplete(ev);
    })
});

//달력 관련 jquery (datepicker)
$(function() {
	'use strict';

    var myDatepicker = $("#datepicker");
    myDatepicker.datepicker({ 
        firstDay: 0,
        minDate: 0,
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        dateFormat: "yy-mm-dd"
    });

    $("#ui-datepicker-div").addClass("ui-datepicker-default");

    myDatepicker.click(function() {
       $("#ui-datepicker-div").removeClass("ui-datepicker-default");
    });
});

var editBtn = document.querySelector(".btn_wrapper .edit_btn");
editBtn.addEventListener('click', function(e) {
	'use strict';
    e.preventDefault();
    e.stopPropagation(); 

    var util = ubuntudo.utility;
    var oDataManager = new ubuntudo.ui.DataManager();
    var oTodoManager = new ubuntudo.ui.TodoManager();
    var elList = document.querySelectorAll("section ul");
    var elLightBox = document.querySelector(".light_box");
    var oDetailModal;
    var oModalManager;

    var tid = document.querySelector(".detail_wrapper .tid").textContent;
    var title_edit = document.getElementById('title_edit').innerHTML;
    var note_edit = document.getElementById('note_edit').value;
    var due_date_edit = document.getElementById('due_date_edit').innerHTML;
    

    var param = "tid=" + tid + "&title_edit=" + title_edit + "&note_edit=" + note_edit + "&due_date_edit=" + due_date_edit;

    util.ajax({
        "method": "PUT", 
        "uri": "/todo", 
        "param" : param, 
        "callback" : oDataManager.addData
    });
    document.getElementsByClassName("detail_modal")[0].style.display = "none";
}); 

var deleteTodoBtn = document.querySelector(".btn_wrapper .delete_btn");
deleteTodoBtn.addEventListener('click', function(e) {
	'use strict';
	e.preventDefault();
	e.stopPropagation(); 
	
	var util = ubuntudo.utility;
	var oDataManager = new ubuntudo.ui.DataManager();
	
	var tid = document.querySelector(".detail_wrapper .tid").textContent;
		
	util.ajax({
		"method": "DELETE", 
		"uri": "/todo/" + tid, 
		"param" : null, 
		"callback" : oDataManager.addData
	});
	document.getElementsByClassName("detail_modal")[0].style.display = "none";
}); 