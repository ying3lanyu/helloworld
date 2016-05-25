/*获取id的方法*/
var $ = function(id){
	return document.getElementById(id);
};
var numValue = $("get-num");
var numArr = new Array();
var numDiv = $("num-div");
var childDiv = numDiv.childNodes;

/*验证输入符合规范*/
function legal(num){
	if(!num.match(/^\d+$/g)){
		alert("Error:please input correct int number");
		return false;
	}else{
		return true;
	}
}
function divCreat(){
	var html = "";
	for (var i = 0; i < numArr.length; i++) {
		html += "<div data-num='"+i+"'>"+numArr[i]+"</div>";
	};
	numDiv.innerHTML = html;
}

function init(){
	$("left-in").onclick = function(){
		var num = numValue.value;
		if(legal(num)){
			numArr.unshift(num);
			divCreat()
		}
	};
	$("right-in").onclick = function(){
		var num = numValue.value;
		if(legal(num)){
			numArr.push(num);
			divCreat()
		}
	};
	$("left-out").onclick = function(){
		if (numArr.length > 0) {
			numArr.shift();
		}else{
			alert("no data!");
		}
		divCreat()
	}
	$("right-out").onclick = function(){
		if(numArr.length > 0){
			numArr.pop();
		}
		else{
			alert("no data to delete!");
		}
		divCreat()
	}
	numDiv.onmouseover = function(){
		for(var item in childDiv){
			childDiv[item].onclick = function(){
				numArr.splice(this.dataset.num,1);
				divCreat();
			}
		}
	};
}
init();