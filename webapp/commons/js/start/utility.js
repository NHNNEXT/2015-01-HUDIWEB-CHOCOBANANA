/**
 * Created by jjungmac on 2015. 4. 5..
 * Edited by dahye on 2015. 4. 18 ('findIndex', 'ajax', 'echo' function added)
 */


ubuntudo.utility.typeCheck = (function () {
	var typeCheck = function (object) {
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
	}

	return typeCheck;
})();

//ajax로 대체하여 중복제거해야 할듯 - 다혜
ubuntudo.utility.getJSONData = ( function () {
	var getData = function (url, callback) {
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
	}
	return getData;
})();

//ajax로 대체하여 중복제거해야 할듯 - 다혜
ubuntudo.utility.postJSONData = ( function () {
	var postData = function (url, param , callback) {
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
	}

	return postData;
})();

//getJSONData와 postJSONData 중복제거용 - 다혜
ubuntudo.utility.ajax = (function () {
	//var insId = 0;
    var ajax = function (method, uri, param, callback) { 
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
	}

	return ajax;
})(); 
//즉시 실행함수로 하지 않아도 될 것 같다~ 

ubuntudo.utility.echo = (function () {
    function echo (val) {
        return val;
    }
    return echo;
})();


ubuntudo.utility.findIndex = (function () {
    function findIndex(object, key, value) {
        var index = 0;
        var util = ubuntudo.utility;
        var length = Object.keys(object).length;
        for(var index = 0; index < length; index++) {
            if(object[index][key] === value) {
                return index;
            }
        }
    }
    return findIndex;
})();
    
