/**
 * Created by dahye on 2015. 4. 17..
 */
ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};
ubuntudo.dataChangedEvent = new CustomEvent("dataChanged");

window.addEventListener("load", function () {
	'use strict';

	var util = ubuntudo.utility;
	var oDataManager = new ubuntudo.ui.DataManager();
	var oTodoManager = new ubuntudo.ui.TodoManager();
	var elList = document.querySelectorAll(".ns_personal section ul");
	var elLightBox = document.querySelector(".light_box");
  
        
	var elCompleteBtnList;
	var oDetailModal;
	var oModalManager;
	// modal창 중 detailModal을 선택해서 ModalManager에 넣는것 같은데, 이런 형태면 Modal을 관리하는 인터페이스 같은 객체가 있어야할 뜻..

    /*todo data 서버에 요청하여 받아오기*/
	util.ajax({
		"method": "GET",
		"uri": "/personal/todo",
		"param": null,
		"callback": oDataManager.setData
	});

    /*todo data에 변화가 있을 때 list를 다시 그리고, 다시 그려진 각 리스트에 이벤트 새롭게 등록.*/
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
    var oTodoAddModal = new ubuntudo.ui.TodoAddModal();
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
       oTodoManager.add(ev, oDataManager);
	   oTodoAddModalManager.hideModal(ev);
	});
});

//달력 관련 jquery (datepicker)
$(function() {
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