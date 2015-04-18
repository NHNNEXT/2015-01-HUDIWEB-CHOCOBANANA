// jQuery(document).ready(function(){
    
//     var select = $("select#color");
    
//     select.change(function(){
//         var select_name = $(this).children("option:selected").text();
//         $(this).siblings("label").text(select_name);
//     });
// });


// function jsFunction(){
//   var myselect = document.getElementById("selectOpt");
//   alert(myselect.options[myselect.selectedIndex].value);
// }

function showSelectedParty() {
    var partyName = document.getElementById("select_party_list").value;
    document.getElementById("selected_party_name").innerHTML = partyName;
}

function showSelectedMonth() {
    var date = document.getElementsByClassName("select_month_list")[0].value;
    document.getElementById("selected_month").innerHTML = date;
}

function showSelectedDay() {
    var date = document.getElementsByClassName("select_day_list")[0].value;
    document.getElementById("selected_day").innerHTML = date;
}

// <label id="selected_date"></label>
//onchange="showSelectedDate()"