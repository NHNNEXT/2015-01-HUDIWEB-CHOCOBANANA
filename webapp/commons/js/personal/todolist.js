(function () {
	window.addEventListener("load", loadList, false);
})(); 

function loadList() {	
	var listClassNames = [".past", ".today", ".future"];
		
	var data;
    /*
    var url = "/list.do"; //여기 서버에서 세션에서 사용자 id를 불러와서 list를 json으로 return 
	var request = new XMLHttpRequest();

	request.open("GET" , url , true);
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	request.send(null);
		
	request.onreadystatechange = function() {
		// 응답이 도착했고, 정상적인 응답이라면, 응답데이터를 파싱하기 시작한다. 
		if(request.readyState === 4 && request.status === 200) {
			data = request.responseText;
			data = JSON.parse(data);
			addTodoList(listClassNames, data);
		}
	}


	*/
	//ajax요청을 모듈로 빼자

	//test
	data = [
			  {
			    "tid" : "1",
			    "assigner_id" : "1",
			    "pid" : "-1",
			    "pName": "개인",
			    "title" : "자구알 3주차 숙제",
			    "contents" : "",
			    "duedate" : "2015.05.15",
			    "last_editer_id" : "1"
			  },
			  {
				    "tid" : "1",
				    "assigner_id" : "1",
				    "pid" : "-1",
				    "pName": "자구알",
				    "title" : "자구알 3주차 숙제",
				    "contents" : "",
				    "duedate" : "2015.04.08",
				    "last_editer_id" : "1"
			  },
			  {
			    "tid" : "1",
			    "assigner_id" : "1",
			    "pid" : "-1",
			    "pName": "개인",
			    "title" : "HCI 3주차 숙제",
			    "contents" : "",
			    "duedate" : "2015.04.07",
			    "last_editer_id" : "1"
			 }
		  ];
	addTodoList(listClassNames, data);
}

function addTodoList(listClassNames, data) {
	var listTemplate = '<li class="todo"><span class="tid"><%=tid%></span><div class="party_pavicon"><%=partyFirstAlphabet%></div><h2><%=title%></h2><p><%=date%>일 <%=explain%></p><a class="complete_btn"></a></li>';
	var listArray = [];
	var today = new Date();
	var length = Object.keys(listClassNames).length;
	
	for (var i=0; i < length; i++) {
		listArray[i] = document.querySelector(listClassNames[i]);
		listArray[i].innerHTML = "";
		console.log(listArray[i].innerHTML);
	}

	for(var i = 0; i < data.length; i++) {
		var list = data[i];
		var due = new Date(list["duedate"]);
		var explain = "남음";
		var dayDiff = 0;
		if(due < today) {
			explain = "지남";
			dayDiff = today.diffDays(due);
			listArray[0].innerHTML += makeInnerHTML(listTemplate, list, dayDiff, explain);
		}else if(due > today) {
			dayDiff = due.diffDays(today);
			listArray[2].innerHTML += makeInnerHTML(listTemplate, list, dayDiff, explain);
		}else {
			listArray[1].innerHTML += makeInnerHTML(listTemplate, list, dayDiff, explain);
		}
	}
	
}

function makeInnerHTML(listTemplate, list, dayDiff, explain){
	 return listTemplate.replace("<%=tid%>", list["tid"]).replace("<%=partyFirstAlphabet%>", list["pName"].substring(0,1)).replace("<%=title%>", list["title"]).replace("<%=date%>", dayDiff).replace("<%=explain%>", explain);
}

Date.prototype.diffDays = function (date) {
	var timeDiff = Math.abs(this.getTime() - date.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	return diffDays;
}

