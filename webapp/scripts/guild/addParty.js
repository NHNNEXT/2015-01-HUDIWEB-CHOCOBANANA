
// 파티 추가하기 버튼을 눌렀을 때
document.getElementById("party_add_btn").addEventListener("click", addParty);


// 사각형을 추가하는 함수
function addParty(){
  var newParty = document.createElement("div");
  
  newParty.className = "party_list";
  
  var randomColor = Math.floor(Math.random()*16777215).toString(16);
    
  // 임의의 색상 넣기
  newParty.style.backgroundColor ='#' + randomColor;
 
  
  // board에 사각형 추가
  document.getElementsByClassName("party_list_wrap")[0].appendChild(newParty);
}