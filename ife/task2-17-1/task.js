/*数据格式演示
var aqiSourceData = {
  "北京": {
    "2016-01-01": 10,
    "2016-01-02": 10,
    "2016-01-03": 10,
    "2016-01-04": 10
  }
};
*/
var $ = function(id){return document.getElementById(id);};
var aqiChartWrap = document.getElementsByClassName('aqi-chart-wrap')[0];
var selectCity = $("city-select");
var formTime = $("form-gra-time");
var colorList = new Array("#4CC74C","#AFDB00","#FFAA00","#F55345","#CD0C0D","#83272C");
// var selectTime = document.getElementsByName("gra-time");
console.log(colorList[1]);
function getColor(item){
      if(item<35){return colorList[0];}
      else if(item<75){return colorList[1];}
      else if(item<115){return colorList[2];}
      else if(item<150){return colorList[3];}
      else if(item<250){return colorList[4];}
      else{return colorList[5];}
    };

// 以下两个函数用于随机模拟生成测试数据
function getDateStr(dat) {
  var y = dat.getFullYear();
  var m = dat.getMonth() + 1;
  m = m < 10 ? '0' + m : m;
  var d = dat.getDate();
  d = d < 10 ? '0' + d : d;
  return y + '-' + m + '-' + d;
}
function randomBuildData(seed) {
  var returnData = {};
  var dat = new Date("2016-01-01");
  var datStr = ''
  for (var i = 1; i < 92; i++) {//获得92天的天数和数据
    datStr = getDateStr(dat);
    returnData[datStr] = Math.ceil(Math.random() * seed);
    dat.setDate(dat.getDate() + 1);
  }
  return returnData;
}

var aqiSourceData = {
  "北京": randomBuildData(500),//传入符合实际情况的seed，生成对象
  "上海": randomBuildData(300),
  "广州": randomBuildData(200),
  "深圳": randomBuildData(100),
  "成都": randomBuildData(300),
  "西安": randomBuildData(500),
  "福州": randomBuildData(100),
  "厦门": randomBuildData(100),
  "沈阳": randomBuildData(500)
};
// 用于渲染图表的数据
var chartData = {};

// 记录当前页面的表单选项
var pageState = {
  nowSelectCity: "",
  nowGraTime: "day"
}
/**
 * 渲染图表
 */
function renderChart() {
  initAqiChartData()
  var color = '',text = '';
  for (var item in chartData) {
    //color = '#' + Math.floor(Math.random() * 0xFFFFFF).toString(16);
    color = getColor(chartData[item]);
    text += '<div title="'+item+":"+chartData[item]+'"style="height:'+chartData[item]+'px; background-color:'+color+'"></div>';
}
  aqiChartWrap.innerHTML = text;
}
/**
 * 日、周、月的radio事件点击时的处理函数
 */
function graTimeChange(time) {
  // 设置对应数据 
  pageState.nowGraTime = time;
  // 调用图表渲染函数
  // if(pageState.nowGraTime == "week"){
  //   alert("weekchange");
  // }else if(pageState.nowGraTime == "month"){
  //   alert("changetomonth");
  // }else{
  //   alert("error!");
  // }
  renderChart();
}

/**
 * select发生变化时的处理函数
 */
function citySelectChange(targetCity) {
  // 设置对应数据
  pageState.nowSelectCity = targetCity;
  // 调用图表渲染函数
  renderChart();
}

/**
 * 初始化日、周、月的radio事件，当点击时，调用函数graTimeChange
 */
function initGraTimeForm() {
  formTime.addEventListener("change",function(eve){
    graTimeChange(eve.target.value);
  });
}

/**
 * 初始化城市Select下拉选择框中的选项
 */
function initCitySelector() {
  var html = "";
  // 读取aqiSourceData中的城市，然后设置id为city-select的下拉列表中的选项
  for(var city in aqiSourceData){
    html += "<option>"+city+"</option>";
  }
  selectCity.innerHTML += html;
  // 给select设置事件，当选项发生变化时调用函数citySelectChange
  selectCity.addEventListener("change",function(eve){
    var targetCity = eve.target.options[eve.target.selectedIndex].innerHTML;
    citySelectChange(targetCity);
  });

}

/**
 * 初始化图表需要的数据格式
 */
function initAqiChartData() {
  // 将原始的源数据处理成图表需要的数据格式
  // 处理好的数据存到 chartData 中
  timeAndNum = aqiSourceData[pageState.nowSelectCity];
  if (pageState.nowGraTime == "day") {
    chartData = timeAndNum;
  }
  if (pageState.nowGraTime == "week") {
    chartData = {};
    var numSum = 0,daySum = 0,week = 0;
    for (item in timeAndNum) {
      numSum += timeAndNum[item];
      daySum++;
      if((new Date(item).getDay()) == 6){
        week++;
        chartData["第"+week+"周"] = Math.ceil(numSum/daySum);
        numSum = 0;
        daySum = 0;
      }      
    }
    if(daySum != 0){
      week++;
      chartData["第"+week+"周"] = Math.ceil(numSum/daySum);
    }
  }
  if (pageState.nowGraTime == "month") {
    chartData = {};
    var numSum = 0,daySum = 0,month =0;
    for(item in timeAndNum){
      numSum += timeAndNum[item];
      daySum ++;
      if((new Date(item).getMonth()) !== month){
        month++;
        chartData["第"+month+"月"] = Math.ceil(numSum/daySum);
        numSum = 0;
        daySum = 0;
      } 
    }
    if (daySum != 0) {
      month++;
      chartData["第"+month+"月"] = Math.ceil(numSum/daySum);
    }
  }
}

/**
 * 初始化函数
 */
function init() {
  initGraTimeForm()
  initCitySelector();
  initAqiChartData();
}

init();