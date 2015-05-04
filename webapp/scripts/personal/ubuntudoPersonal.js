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
	var elList = document.querySelectorAll("section ul");
	var elLightBox = document.querySelector(".light_box");
	var elCompleteBtnList;
	var oDetailModal;
	var oModalManager;
	// modal창 중 detailModal을 선택해서 ModalManager에 넣는것 같은데, 이런 형태면 Modal을 관리하는 인터페이스 같은 객체가 있어야할 뜻..

	util.ajax({
		"method": "GET",
		"uri": "/personal/todo",
		"param": null,
		"callback": oDataManager.setData
	});

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
		oTodoManager.appendList(data, fieldName);
		if (elCompleteBtnList !== undefined) {
			[].forEach.call(elCompleteBtnList, _removeEvent);
		}
		elCompleteBtnList = document.getElementsByClassName('complete_btn');
		[].forEach.call(elCompleteBtnList, _addEvent);
	});

	[].forEach.call(elList, function (element) {
		element.addEventListener("click", function (ev) {
			oModalManager.showModal(ev);
		});
	});

	elLightBox.addEventListener("click", function () {
		oModalManager.hideModal();
	});

	//submit 버튼 누르면 투두 추가 요청 - 리팩토링 필요
	var submitBtn = document.querySelector(".add_todo .submit_btn");
	submitBtn.addEventListener('click', function (e) {
		e.preventDefault();
		e.stopPropagation();

		//var form = document.querySelector(".add_todo");
		//var pid = form.querySelector(".add_todo select").value;
		var pid = -1;
		var date = document.getElementsByName("date")[0].value;
		if (date === "만기기한이 없습니다.") {
			date = new Date();
			//date = date.toISOString().slice(0,10).replace(/-/g,"-"); 이러면 27일인데 26일이 나옴. 이유를 찾아보자.
			date = date.yyyymmdd();
		}
		var title = document.getElementsByName("title")[0].value;
		if (title === "") {
			return "제목을 채워야 합니다.";
		}
		var contents = document.getElementsByName("contents")[0].value;

		var param = "pid=" + pid + "&dueDate=" + date + "&title=" + title + "&contents=" + contents;

		util.ajax({
			"method": "POST",
			"uri": "/personal/todo",
			"param": param,
			"callback": oDataManager.addData
		});

		document.getElementById("add_wrap").style.display = "none";
	});
});



