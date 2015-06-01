/**
 * Created by dahye on 2015. 4. 26
 */

"use strict";

/* jshint devel: true */

ubuntudo.ui.DataManager = (function() {
    var FIELD_NAME = {
            TID: "tid",
            ASSIGNER_ID: "assigner_id",
            PARTY_ID: "pid",
            PARTY_NAME: "partyName",
            TITLE: "title",
            CONTENTS: "contents",
            DUEDATE: "dueDate",
            LAST_EDITER_ID: "last_editer_id"
        };

    function DataManager () {
        this.data = {};
        this.setData = setData.bind(this);
        this.getData = getData.bind(this);
        this.addData = addData.bind(this);
        this.removeData = removeData.bind(this);
        this.updateData = updateData.bind(this);
        this.deleteData = deleteData.bind(this);
        this.displayPartyList = displayPartyList.bind(this);
    }

    function setData (datas) {
        this.data = datas;
	    /* jshint ignore:start */
		dispatchEvent(ubuntudo.dataChangedEvent);
	    /* jshint ignore:end */
	}

    function getData () {
        return this.data;
    }

    function addData (data) {
        this.data.push(data);
        //dueDate별로 다시 정렬해야함
	    /* jshint ignore:start */
	    dispatchEvent(ubuntudo.dataChangedEvent);
	    /* jshint ignore:end */
    }

    function removeData (result) {
        if(result.status === "success") {
            var tid = result.tid;
            var index = ubuntudo.utility.findIndex(this.data, "tid", tid);
            this.data.splice(index, 1);
	        /* jshint ignore:start */
	        dispatchEvent(ubuntudo.dataChangedEvent);
	        /* jshint ignore:end */
        }
    }
    
    DataManager.prototype.getFieldName = function() {
        return FIELD_NAME;
    };
    
    function updateData (result) {
    	if(result !== undefined || result !== null) {
	     	var index = ubuntudo.utility.findIndex(this.data, "tid", result[FIELD_NAME.TID]);
	        this.data[index] = result;
		    /* jshint ignore:start */
		    dispatchEvent(ubuntudo.dataChangedEvent);
		    /* jshint ignore:end */
    	}
    }
    
    function deleteData (result) {
     	var index = ubuntudo.utility.findIndex(this.data, "tid", result[FIELD_NAME.TID]);
		this.data.splice(index, 1);
	    /* jshint ignore:start */
	    dispatchEvent(ubuntudo.dataChangedEvent);
	    /* jshint ignore:end */
    }
    
	function displayPartyList(result){
		function PartyIcon(){
		    this.element = document.createElement('div');
		    this.element.className = "party_pavicon";
		}
		
		PartyIcon.prototype.setParent = function(parent){
			parent.appendChild(this.element);
		};
		
		var myParticon = new  PartyIcon();
		myParticon.element.innerHTML = 'My';
		var myPartiIdentifier = document.createElement("INPUT");
		myPartiIdentifier.setAttribute('type', 'hidden');
		myPartiIdentifier.setAttribute('toggleState', 'on');
		myPartiIdentifier.className = -1;
		myParticon.element.appendChild(myPartiIdentifier);
		myParticon.element.addEventListener('click', toggleTodosByParty);
		myParticon.setParent(document.querySelector('#party_icon_list'));
		
		for (var i=0; i<result.length; i++){
			var particon = new  PartyIcon();
			
			particon.element.innerHTML = result[i].partyName.substring(0,2);
			particon.element.setAttribute('data-onColor', 'rgb('+randRGB()+', '+randRGB()+', '+randRGB()+')');
			particon.element.style.backgroundColor = particon.element.getAttribute('data-onColor');
			var partiIdentifier = document.createElement("INPUT");
			partiIdentifier.innerHTML = result[i].pid;
			partiIdentifier.setAttribute('type', 'hidden');
			partiIdentifier.setAttribute('data-toggleState', 'on');
			partiIdentifier.className = result[i].pid;
			particon.element.appendChild(partiIdentifier);
			particon.element.addEventListener('click', toggleTodosByParty);
			particon.setParent(document.querySelector('#party_icon_list'));
		}
		
		colorizePartyIconOnTodo();
	}
	
    function randRGB(){ return rand(0, 255); }
    function rand(min, max){ return Math.round(Math.random() * (max-min)) + min; }
	
	function toggleTodosByParty(e){
		var todoCountForCurrentParty = 0;
		var todos = document.getElementsByClassName('pid');
		var currentTodoId = e.currentTarget.childNodes[1].className;
		
		console.log('toggle party id: ' + currentTodoId); 
		
		for(var todoIdx = 0; todoIdx < todos.length; todoIdx++){
			if(todos[todoIdx].innerHTML === currentTodoId){
				todoCountForCurrentParty++;
				
				if(todos[todoIdx].parentElement.style.display === 'none'){
					e.currentTarget.style.background = e.currentTarget.getAttribute('data-onColor');
					e.currentTarget.setAttribute('data-toggleState', 'on');
					todos[todoIdx].parentElement.style.display = 'block';
				}else{
					e.currentTarget.style.background = "#999999";
					e.currentTarget.setAttribute('data-toggleState', 'off');
					todos[todoIdx].parentElement.style.display = 'none';
				}
			}
		}
		if(todoCountForCurrentParty === 0)
		{alert('this party has no todo.');}
	}
	
	function colorizePartyIconOnTodo(){
		var todos = document.getElementsByClassName('pid');
		var partyList = document.querySelector('#party_icon_list');
		for(var idx = 1; idx < partyList.childElementCount; idx++){
			var currentPartyId = partyList.children[idx].childNodes[1].getAttribute("class");
			for(var jdx = 0; jdx < todos.length; jdx++){
				if(todos[jdx].innerHTML === currentPartyId){
					console.log(todos[jdx].parentNode.childNodes[2]);
					todos[jdx].parentNode.childNodes[2].style.backgroundColor = partyList.children[idx].style.backgroundColor;
				}
			}
		}
		
	}
    return DataManager;
})();
