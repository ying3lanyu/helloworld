/*获取id的方法*/
var $ = function(id){
	return document.getElementById(id);
};
var numValue = $("get-num");
var numArr = new Array();
var numDiv = $("num-div");
var childDiv = numDiv.childNodes;
var btn = document.querySelectorAll('button');

var leftInB=btn[0];
var leftOutB=btn[1];
var rightInB=btn[2];
var rightOutB=btn[3];
var messB=btn[4];
var bubbleB=btn[5];
var selectB=btn[6];
//var insertB=btn[7];

numArr=[45,60,12,43,30,20,29,33,80,11];

//改变元素数量
function changeElement(){

	leftInB.onclick = function(){
		var num = numValue.value;
		if(legal(num)){
			numArr.unshift(num);
			divCreat();
		}
	};
	rightInB.onclick = function(){
		var num = numValue.value;
		if(legal(num)){
			numArr.push(num);
			divCreat();
		}
	};
	leftOutB.onclick = function(){
		if (numArr.length > 0) {
			numArr.shift();
		}else{
			alert("no data!");
		}
		divCreat();
	}
	rightOutB.onclick = function(){
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

//改变顺序
function changeOrder(){

	messB.onclick=function(){
		numArr.sort(function(){ return 0.7 - Math.random() })
	    //console.log(numArr);
	    divCreat();
	}
	bubbleB.onclick=function(){
		//alert("hi");
		for (var i = 0; i < numArr.length; i++) {
			//console.log(i);
			for (var j = 0; j < numArr.length-i-1; j++) {
				compareNum(j,j+1);
				//setTimeout(divCreat,1000);
				divCreat();
					
			}
			//var j=0;
			// var timer=setInterval(function(i,j){
			// 	console.log(i);
			// 	// if(j<numArr.length-i-1){
			// 	// 	compareNum(j,j+1);
			// 	// 	divCreat();
			// 	// 	j++;
			// 	// }else{
			// 	// 	clearInterval(timer);
			// 	// }
			// },2000);
			
		}
	}
	selectB.onclick=function(){
		for (var i = 0; i < numArr.length-1; i++) {
			//console.log(i);
			var min=i;
			for (var j = i+1; j < numArr.length; j++) {
				if(numArr[j]<numArr[min]){
					min=j;
				}
			}
			compareNum(i,min);
			divCreat();
		}
	}
}

/*验证输入符合规范*/
function legal(num){
	if((!num.match(/^\d+$/g))||num<10||num>100){
		alert("Error:please input correct int number limit to 10~100!");
		return false;
	}else{
		return true;
	}
}

//生产新元素
function divCreat(){
	var html = "";
	if(numArr.length>60){
		alert("Error:can't get more number!")
	}else{
		for (var i = 0; i < numArr.length; i++) {
			html += "<div class='div-bar'><div data-num='"+i+"' style='height: "+(numArr[i]*4)+"px'></div></div>";
		};
		numDiv.innerHTML = html;		
	}

}

//比较大小
function compareNum(a,b){
	var t;
	if(numArr[a]>numArr[b]){
		t=numArr[a];
		numArr[a]=numArr[b];
		numArr[b]=t;
	}
}


//初始化
function init(){
	divCreat();
	changeElement();	
	changeOrder();
}
init();