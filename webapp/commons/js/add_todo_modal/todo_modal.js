// 투두 추가 버튼 누르면 display block
var addTodoBtn = document.getElementsByClassName("todo_add_btn")[0];
addTodoBtn.addEventListener('click', function(e){
    
    //value 초기화 - 이부분이 beforeShow()에 들어갈 부분
    var elTarget = e.target;
    if(elTarget.className !== "add_todo") {
        elTarget = elTarget.parentElement;
    }
    if(elTarget.className === "add_todo") {
        return;
    }
    var form = document.querySelector(".add_todo");
    form.querySelector("#datepicker").value = "만기기한이 없습니다.";
    form.querySelector(".input_todoName").value = "";
    form.querySelector(".input_todoComment").value ="";
    
	document.getElementById("add_wrap").style.display = "block";
});

// cancel 버튼 누르면 display none
var cancelBtn = document.getElementsByClassName("cancel_btn")[0];
cancelBtn.addEventListener('click', function(e){
	document.getElementById("add_wrap").style.display = "none";
    e.stopPropagation(); //이걸 하지 않으면 다시 diplay값이 block이 된다. -dahye
});

function showSelectedParty() {
    var partyName = document.getElementById("select_party_list").value;
    document.getElementById("selected_party_name").innerHTML = partyName;
}

// 달력 나오는 j query
$(function() {
  var mydatepicker = $("#datepicker");
  mydatepicker.datepicker({ 
    firstDay: 0,
    minDate: 0,
    dayNamesMin: 
        [ "일", "월", "화", "수", "목", "금", "토" ] ,
    dateFormat: "yy-mm-dd"
  });
  
  $("#ui-datepicker-div").addClass("ui-datepicker-default");
  
	mydatepicker.click(function() {
   $("#ui-datepicker-div").removeClass("ui-datepicker-default");
  });
});

