/**
 * Created by dahye on 2015. 4. 26
 */
ubuntudo.ui.dataManager = (function() {
    var FIELD_NAME = {
            TID: "tid",
            ASSIGNER_ID: "assigner_id",
            PARTY_ID: "pid",
            PARTY_NAME: "pName",
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
    }
    
    function setData (datas) {
        this.data = datas;
		window.dispatchEvent(dataChangedEvent);
	}
    
    function getData () {
        return this.data;
    }
    
    function addData (data) {
        this.data.push(data);
        //dueDate별로 다시 정렬해야함
        window.dispatchEvent(dataChangedEvent);
    };
    
    function removeData (result) {
        if(result.status === "success") {
            var tid = result.tid;
            var index = ubuntudo.utility.findIndex(this.data, "tid", tid);
            this.data.splice(index, 1);
            window.dispatchEvent(dataChangedEvent);
        }
    }
    
    DataManager.prototype.getFieldName = function() {
        return FIELD_NAME;
    }   
    
    return DataManager;
})();