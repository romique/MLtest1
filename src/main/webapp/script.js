/**
 * Created by Romique on 14.09.2015.
 */
function send(){
    var xhr = new XMLHttpRequest();
    var url = "/rest/simple?values=";
    var values = document.getElementById("values").value;
    var autoAssignKeys = 0;
    url = url + values;
    if (document.getElementById("autoAssignKeys").checked) {
        autoAssignKeys = 1;
    }
    url = url + "&autoAssignKeys=" + autoAssignKeys + "&separator=" + document.getElementById("separator").value;
    xhr.open("GET", url, false);
    xhr.send();
    document.getElementById("result").innerHTML = xhr.response;
}

function sendOanda(){
    var xhr = new XMLHttpRequest();
    var url = "/rest/oanda?values=";
    var values = document.getElementById("values").value;
    var autoAssignKeys = 0;
    url = url + values;
    if (document.getElementById("autoAssignKeys").checked) {
        autoAssignKeys = 1;
    }
    url = url + "&autoAssignKeys=" + autoAssignKeys + "&separator=" + document.getElementById("separator").value;
    xhr.open("GET", url, false);
    xhr.send();
    document.getElementById("result").innerHTML = xhr.response;
}

function getCurrentValue(){
    var xhr = new XMLHttpRequest();
    var url = "/rest/add?instrument=" + document.getElementById("instrument").value;
    xhr.open("GET", url, false);
    xhr.send();
    document.getElementById("result").innerHTML = xhr.response;
}

function getDataMap(){
    var xhr = new XMLHttpRequest();
    var url = "/rest/getdatamap";
    xhr.open("GET", url, false);
    xhr.send();
    document.getElementById("result").innerHTML = xhr.response;
}

function getDivStrategy(){
    var xhr = new XMLHttpRequest();
    var url = "/rest/divstrategy?currency=" + document.getElementById("instrument").value;
    xhr.open("GET", url, false);
    xhr.send();
    document.getElementById("result").innerHTML = xhr.response;
}