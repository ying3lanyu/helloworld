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
		    url: "Jsonone",    //�����url��ַ
		    dataType: "json",   //���ظ�ʽΪjson
		    async: true, //�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
		    data: { "id": "value" },    //����ֵ
		    type: "GET",   //����ʽ
		    beforeSend: function() {
		        //����ǰ�Ĵ���
		    },
		    success: function(req) {
		        //����ɹ�ʱ����
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
		        //������ɵĴ���
		    },
		    error: function(err) {
		        //���������
		        
		    }
		});
    }