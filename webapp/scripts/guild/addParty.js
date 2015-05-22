/*
 * ubuntudo = {};
 * ubuntudo.utility = {};
 */

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
	var newParty = document.createElement("div");
	var newPartyName = document.createElement("div");
	var text = document.createTextNode(partyName);
  
	// css 추가
	newParty.className = "party_list";
	newPartyName.className = "party_name";
	
	// 파티 목록에 파티 추가
	document.getElementsByClassName("added_party")[0].appendChild(newParty);
	newParty.appendChild(newPartyName);
	newPartyName.appendChild(text);
	
	 /* pid, p_name, party_leader_id도 함께 추가해야함 */
	/*
	 * pid++
	 * p_name = partyName
	 * party_leader = 현재 로그인한 유저
	 * 
	 */
	
	originalPartyAddBox();
}

function originalPartyAddBox() {
	document.getElementsByClassName("input_partyName")[0].value = "";
	document.getElementById("party_add_box_btn").style.display = "block";
	document.getElementById("partyName_input_box_btn").style.display = "none";
}