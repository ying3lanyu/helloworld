<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String u=(String)request.getSession().getAttribute("username");
if(u==null){
 response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'analyse.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/examples.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
	
	<script type='text/javascript' src="js/jquery-1.11.3.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.flot.js"></script>
	

	</script>

  </head>
  
  <body>
  <script type="text/javascript">
  	var res=[];
  	var options={
			series: {
				shadowSize: 0	// Drawing is faster without shadows
			},
			yaxis: {
				min: 20,
				max: 60
			},
			xaxis: {
				min:0,
			}
		};
    function test(){
    	$.ajax({
		    url: "Jsonana",    //请求的url地址
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
		        var obj=req.al;
		        var xtime=[];
		        var ytem=[];
		        res=[];
		        for(var i=99;i>0;i--){
		        	xtime[i]=obj[i]["time"];
		        	xtime[i]=parseInt(xtime[i].substr(11,2)+xtime[i].substr(14,2)+xtime[i].substr(17,2));
		        	ytem[i]=parseInt(obj[i]["temperature"]);
		        	res.push([xtime[i],ytem[i]]);
		        } 
		        //paint(xtime,ytem);
		        console.log(ytem);
		        options["xaxis"]["min"]=res[0][0];
				$.plot($("#placeholder1"), [res], options);
		       /*  var ts=req.one[0]['time'];
		        var tss=ts.substr(11,5);
		       	alert(tss); */
		       	console.log(xtime);
		    },
		    complete: function() {
		        //请求完成的处理
		    },
		    error: function(err) {
		        //请求出错处理
		        
		    }
		});
    }
 //   test();

  </script>
  <div class="nav">
    <ul>
      <li><a href="monitor.jsp">STATUS</a></li>
      <li><a href="test.jsp" class="scroll">DATA</a></li>
      <li><a href="monitor.jsp">HOME</a></li>
      <li><a href="movie.jsp">MOVIE</a></li>
      <li class="active-li"><a href="analyse.jsp">ANALYSE</a></li>
    </ul>
  </div>
    <div style="padding:150px 0 20px 0;">
		<h1>Real-time <font>Temperature</font></h1>
	</div>
	<div id="content">
		<div class="demo-container">
			<div id="placeholder1" class="demo-placeholder"></div>
		</div>

	</div>
	<script type="text/javascript">test();
	setInterval(test,5000);
	</script>
	<footer>
    <img src="images/iet2.png"><br>
      <p>&copy Design by maoxiaoxuan,2016|All Rights Reserved <a href="http://letlab.bupt.cn">LET LAB</a></p>
    </footer>
	
  </body>
</html>
