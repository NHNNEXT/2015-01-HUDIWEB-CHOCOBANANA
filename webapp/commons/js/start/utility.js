/**
 * Created by jjungmac on 2015. 4. 5..
 * Edited by dahye on 2015. 4. 18 ('findIndex', 'ajax', 'echo' function added)
 */
(function () {
    ubuntudo.utility.typeCheck = function (object) {
        var rtn = '';
        if (typeof object === 'object') {
            rtn = Object.prototype.toString
                .call(Object)
                .split(' ')[1]
                .replace(']', '');
        } else {
            rtn = typeof object;
        }
        return rtn;
    };

    //ajax로 대체하여 중복제거해야 할듯 - 다혜
    ubuntudo.utility.getJSONData = function (url, callback) {
        var util = ubuntudo.utility;
        var request = new XMLHttpRequest();

        request.open("GET", url, true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send();

        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                var result = request.responseText;
                result = JSON.parse(result);
                if (util.typeCheck(callback) === "function") {
                    callback(result);
                }
            }
        }
    };

    //ajax로 대체하여 중복제거해야 할듯 - 다혜
    ubuntudo.utility.postJSONData = function (url, param , callback) {
        var util = ubuntudo.utility;
        var request = new XMLHttpRequest();

        request.open("POST", url, true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(param);

        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                var result = request.responseText;
                result = JSON.parse(result);
                if(util.typeCheck(callback) === "function") {
                    callback(result);
                }
            }
        }
    };


    //getJSONData와 postJSONData 중복제거용 - 다혜
    ubuntudo.utility.ajax = function (object) { 
        var method = object.method;
        var uri = object.uri;
        var param = object.param;
        var callback = object.callback;
        //var context = object.context;
        //var object = {"method": null, 등등}을 돌면서 object의 value채워넣기
        
        var util = ubuntudo.utility;
        var request = new XMLHttpRequest();
        //this.insId = insId++;
        request.open(method, uri, true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(param);

        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                var result = request.responseText;
                result = JSON.parse(result);
                if(util.typeCheck(callback) === "function") {
                    callback(result);
                }
            }
        }
    };

//    ubuntudo.utility.syncRequest = function (method, uri, param, callback) { 
//        var util = ubuntudo.utility;
//        var request = new XMLHttpRequest();
//        request.open(method, uri, false);
//        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//        request.send(param);
//
//        if (request.readyState === 4 && request.status === 200) {
//          var result = request.responseText;
//          result = JSON.parse(result);
//          if(util.typeCheck(callback) === "function") {
//              return callback(result);
//          }
//        }
//    };

    ubuntudo.utility.echo = function (val) {
        return val;
    };

    ubuntudo.utility.findIndex = function findIndex(object, key, value) {
        var index = 0;
        var util = ubuntudo.utility;
        var length = Object.keys(object).length;
        for(var index = 0; index < length; index++) {
            if(object[index][key] === value) {
                return index;
            }
        }
    };
   
})();
