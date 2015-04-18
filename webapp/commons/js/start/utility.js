/**
 * Created by jjungmac on 2015. 4. 5..
 * Edited by dahye on 2015. 4. 18 (add 'findIndex' function)
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


ubunntudo.utility.findIndex = (function (object, key, value) {
    var index = 0;
    var length = Object.keys(object).length;
    for(var index = 0; index < length; index++) {
        if(object[key] === value) {
            break;
        }
    }
    return index;
})();
    
