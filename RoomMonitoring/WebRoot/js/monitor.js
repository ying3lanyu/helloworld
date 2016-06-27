$(function(){
var nav=$(".nav"); 
var win=$(window); 
var sc=$(document);

$(window).scroll(function(){
if($(document).scrollTop()>=$('#aboutus').height()){
nav.addClass("fixed");
//$(".navTmp").fadeIn();

}else{
nav.removeClass("fixed");
//$(".navTmp").fadeOut();
}
}); 
});
function test(){
    	$.ajax({
		    url: "Jsonone",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    data: { "id": "value" },    //参数值
		    type: "GET",   //请求方式
		    beforeSend: function() {
		        //请求前的处理
		    },
		    success: function(req) {
		        //请求成功时处理
		        console.log(req);
		       // alert(req.one[0]["temperature"]);
		        info =[req.one[0]["temperature"],req.one[0]["humidity"],req.one[0]["smog"],req.one[0]["power"],req.one[0]["waterlog"]];
		        var colors = ['#eee', '#5ACF00'];
				var infomax=[70,70,1,100,1];
				var infonormal=[29,40,0,50,0];
				var symbols=['!','!','#','*',''];
				for (var i = 1; i <= 5; i++) {
					var child = document.getElementById('circles-' + i),
					percentage =info[i-1];
					if(info[i-1]>infonormal[i-1]){
						colors=['#eee', '#FF0000'];
					}else{
						colors = ['#eee', '#E83A93'];}	
					Circles.create({
					id:         child.id,
					percentage: percentage/infomax[i-1]*100,
					radius:     90,
					width:      15,
					number:   	percentage,
					text:       '',
					colors:     colors,
					});						
				}  		        
		      //  return info;
		    },
		    complete: function() {
		        //请求完成的处理
		    },
		    error: function(err) {
		        //请求出错处理
		        
		    }
		});
    }