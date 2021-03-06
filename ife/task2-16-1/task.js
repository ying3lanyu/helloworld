/**
 * aqiData，存储用户输入的空气指数数据
 * 示例格式：
 * aqiData = {
 *    "北京": 90,
 *    "上海": 40
 * };
 */
var $ = function(id){return document.getElementById(id);};
var aqiData = {};
var city = $("aqi-city-input");
var quan = $("aqi-value-input");
var table = $("aqi-table");

String.prototype.trim=function() {

    return this.replace(/(^\s*)|(\s*$)/g,'');
}
/**
 * 从用户输入中获取数据，向aqiData中增加一条数据
 * 然后渲染aqi-list列表，增加新增的数据
 */
function addAqiData() {
	var c = city.value.trim();
	var q = quan.value.trim();
	if(!c.match(/^[a-z|A-Z|\u4E00-\u9FA5]+$/g)){
		alert("Error:please input correct city!");
		return;
	}
	if(!q.match(/^\d+$/g)){
		alert("Error:please input correct int number");
		return;
	}
	aqiData[c] = q;
}

/**
 * 渲染aqi-table表格
 */
function renderAqiList() {
	var dataArr = [];
	var tablehtml = "<tr><td>城市</td><td>空气质量</td><td>操作</td></tr>";
	for(var item in aqiData){
		tablehtml += "<tr><td>"+item+"</td><td>"+aqiData[item]+
		"</td><td><button data-item='"+item+"'>删除</button></td>"
	}
	table.innerHTML = tablehtml;
	
}

/**
 * 点击add-btn时的处理逻辑
 * 获取用户输入，更新数据，并进行页面呈现的更新
 */
function addBtnHandle() {
  addAqiData();
  renderAqiList();
}

/**
 * 点击各个删除按钮的时候的处理逻辑
 * 获取哪个城市数据被删，删除数据，更新表格显示
 */
function delBtnHandle(item) {
  // // do sth.
  delete aqiData[item];
  renderAqiList();
}

function init() {

  // 在这下面给add-btn绑定一个点击事件，点击时触发addBtnHandle函数

  // 想办法给aqi-table中的所有删除按钮绑定事件，触发delBtnHandle函数
  $("add-btn").onclick = function(){
  	addAqiData();
  	renderAqiList();
  };
  table.addEventListener("click", function(eve) {
        if (eve.target && eve.target.nodeName === "BUTTON") {
            delBtnHandle(eve.target.dataset.item);
        }
    })

}


init();
