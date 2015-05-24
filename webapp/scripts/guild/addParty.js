ubuntudo = {};
ubuntudo.ui = {};
ubuntudo.utility = {};

// 파티 추가하기 박스를 눌렀을 때
document.getElementById("party_add_box_btn").addEventListener("click", inputPartyName);

//파티 추가하기 박스에서, 추가 버튼을 눌렀을 때
document.getElementById("inputPartyName_add_btn").addEventListener("click", addParty);

//파티 추가하기 박스에서, 취소 버튼을 눌렀을 때
document.getElementById("inputPartyName_cancel_btn").addEventListener("click", originalPartyAddBox);

function inputPartyName(){
	document.getElementById("party_add_box_btn").style.display = "none";
	document.getElementById("partyName_input_box_btn").style.display = "block";	
}

function addParty(){
	var partyName = document.getElementsByClassName("input_partyName")[0].value;
	
	var href = window.location.href;
    var gid = href.substr(href.lastIndexOf("/")+1);
    
	var util = ubuntudo.utility;
	    
	    util.ajax({
	        "method": "POST",
	        "uri": "/party",
	        "param": "gid="+gid+"&partyName="+ partyName,
	        "callback": addPartyCallback
	    });
	
	   
	function addPartyCallback(result) {
		if (result.status === "success"){
			var newParty = document.createElement("div");
			var newPartyName = document.createElement("a");
			var text = document.createTextNode(partyName);
			//var pid = guildInfoData["result"].parties[i].pid;
			
			// css 추가
			newParty.className = "party_list";
			newPartyName.className = "party_name";
			
			// a태그에 href 추가 
			//newPartyName.setAttribute("href", "/party/"+pid);
			
			// 파티 목록에 파티 추가
			document.getElementsByClassName("added_party")[0].appendChild(newParty);
			newParty.appendChild(newPartyName);
			newPartyName.appendChild(text);
			
			// 길드 헤더에 파티수 추가 (프론트)
			var presentPartyNum = Number(document.getElementsByClassName("partyNum")[0].innerHTML.split(" ")[1]);
			presentPartyNum = presentPartyNum + 1;
			document.getElementsByClassName("partyNum")[0].innerHTML = "파티 " + presentPartyNum;
			
			originalPartyAddBox();
		
		}
		else {
			alert("파티 생성에 실패했습니다. 다시 만들어주세요.");
		}
	}
}

// 다시 원래 모양으로 partyAddBox가 돌아옴 
function originalPartyAddBox() {
	document.getElementsByClassName("input_partyName")[0].value = "";
	document.getElementById("party_add_box_btn").style.display = "block";
	document.getElementById("partyName_input_box_btn").style.display = "none";
}