

function getCookie(name){
    var cookie = ' ' + document.cookie;
    var search = ' ' + name + '=';
    var setStr = null;
    var offset = 0;
    var end = 0;
    if (cookie.length > 0){
        offset = cookie.indexOf(search);
        if (offset !== -1){
            offset += search.length;
            end = cookie.indexOf(';',offset);
            if (end === -1) 
                end = cookie.length;
            setStr = unescape(cookie.substring(offset,end));
        }
    }
	if ((setStr != null) && (setStr.charAt(0) === "\"") && (setStr.charAt(setStr.length - 1) === "\"")){
		setStr = setStr.substr(1, setStr.length - 2);
	}
    return (setStr);
};
            
function setCookie(name, value, options) {
    options = options || {};
    var expires = options.expires;
    if (typeof expires === "number" && expires) {
        var d = new Date();
        d.setTime(d.getTime() + expires*1000);
        expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
        options.expires = expires.toUTCString();
    }
    value = encodeURIComponent(value);
    var updatedCookie = name + "=" + value;
    for(var propName in options) {
        updatedCookie += "; " + propName;
        var propValue = options[propName];   
        if (propValue !== true) {
            updatedCookie += "=" + propValue;
        }
    }
    document.cookie = updatedCookie;
};

function Ajax(method, adress, params, headers, respFunc){
    
    if  ((method !== "GET") 
      && (method !== "POST") 
      && (method !== "DELETE")){
                    method = "GET";
    } 
    var xhr = new XMLHttpRequest();
    var paramsString = "";
    
    if ( {}.toString.call(params) === '[object Object]' ) {
        var num = 0;
        for(var key in params) {
            if (num === 0){
                paramsString = "?";
            }else{
                paramsString += "&";
            };
            paramsString += key + "=" + params[key];
            num++;
        };
    };
    
    xhr.open(method, adress + paramsString, true);
    if (typeof respFunc === "function") {
        xhr.onreadystatechange = function() {
            if (this.readyState !== 4) return;
            respFunc(xhr.responseText);
        };
    };
    
    if ( {}.toString.call(headers) === '[object Object]' ) {
        for(var key in headers) {
            xhr.setRequestHeader(key, headers[key]);
        };
    };
    xhr.send("");
    return xhr;
};