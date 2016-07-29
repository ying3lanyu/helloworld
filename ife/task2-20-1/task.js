// 基于任务18进行升级
// 将新元素输入框从input改为textarea
// 允许一次批量输入多个内容，格式可以为数字、中文、英文等，
// 可以通过用回车，逗号（全角半角），顿号，空格（全角半角、Tab等均可）等符号作为不同内容的间隔
// 增加一个查询文本输入框，和一个查询按钮，当点击查询时，
// 将查询词在各个元素内容中做模糊匹配，将匹配到的内容进行特殊标识，如文字颜色等。
// 举例，内容中有abcd，查询词为ab或bc，则该内容需要标识
/*获取id的方法*/
var $ = function(id){
	return document.getElementById(id);
};

var numDiv = $("num-div");
var totalText = "";
var keyWord = "";
var totalArr = new Array();
var childDiv = numDiv.childNodes;

/*验证输入符合规范*/
function legal(num){
	if(!num.match(/^(\w|[\u4E00-\u9FA5]|(\s+)|\r|\,|\，)*$/)){
		alert("Error:please input correct text！");
		return false;
	}else{
		return true;
	}
}
function divCreat(Arr){
	var html = "";
	for (var i = 0; i < Arr.length; i++) {
		html += "<div data-num='"+i+"'>"+Arr[i]+"</div>";
	};
	numDiv.innerHTML = html;
}
//function 
	
function init(){

	$("right-in").onclick = function(){
		totalText +=  $("get-text").value+" ";
		if(legal(totalText)){
			totalArr = totalText.split(/\s+|\r|\,+|\，+/).filter(function(e){
				if(e!=null&&e.length>0){
					return true;
				}else{
					return false;
				}
			})
			divCreat(totalArr);
		}
	};

	numDiv.onmouseover = function(){
		for(var item in childDiv){
			childDiv[item].onclick = function(){
				totalArr.splice(this.dataset.num,1);
				divCreat(totalArr);
			}
		}
	};

	$("query-button").onclick = function(){
		keyWord=$("key-word").value;
		var presentArr = new Array();
		presentArr = (keyWord.length==0)?totalArr:totalArr.map(function(e){
				e = e.replace(new RegExp(keyWord,"g"),"<span class='selected'>"+keyWord+"</span>");
				return e;
			});
		divCreat(presentArr);
	};
	
}

init();