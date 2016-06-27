<%@ page language="java" import="java.util.*,user.model.*" pageEncoding="ISO-8859-1"%>
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
 <!-- welcome&nbsp;<%=u %> -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type='text/javascript' src="js/jquery-1.11.3.min.js"></script>
	<script type='text/javascript' src="js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="js/main.js"></script>
  </head>
  
  <body>
  <script>
//	var pageNow=1;
    function test(pageNow){
    	$.ajax({
		    url: "Jsonpage",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    data: {pageNow:pageNow },    //参数值
		    type: "GET",   //请求方式
		    beforeSend: function() {
		        //请求前的处理
		    },
		    success: function(req) {
		        //请求成功时处理
		    console.log(req);	
		    var obj=req.al;
		    var pageCount=req.pageCount;
 			//$("#maintable tbody tr").remove();
 			var cols=["id","time","temperature","humidity","smog","power","waterlog"];
 			var infonomal=[0,0,30,30,30,30,0];
 			//alert(cols.length);
 			$("#maintable tbody").html("");
            	for (var i = 0; i < obj.length; i++) {
            		var row = $("#content").clone();
                	var html="<tr>";
                	for(var j=0;j<cols.length;j++){
                		html+="<td>";
                    	html+=obj[i][cols[j]];
                    	html+="</td>";
              	  }
               	 html+="</tr>";
               	 $("#maintable").append(html);
                       /*  row.find("#ID").text(obj[i]["id"]);
                        row.find("#Time").text(obj[i]["time"]);
                        row.find("#Temperature").text(obj[i]["temperature"]);
                        row.find("#Humidity").text(obj[i]["humidity"]);
                        row.find("#Smog").text(obj[i]["smog"]);
                        row.find("#Power").text(obj[i]["power"]);
                        row.find("#Waterlog").text(obj[i]["waterlog"]);
                        row.appendTo("#maintable"); */        
            	} 	
            	$("#pagecount").empty();
            	var page="";
            	if(pageCount<=20){
            		if(pageNow!=1){
            		page+="<li><a onclick=test("+(pageNow-1)+");>&laquo;</a></li>";
            		} 
            	for(var m=1;m<=pageCount;m++){
  					page+="<li id=page"+m+"><a onclick=test("+m+");>"+m+"</a></li>";
 				}
  				if(pageNow!=pageCount){
     				page+="<li><a onclick=test("+(pageNow+1)+");>&raquo;</a></li>"; 
  				}   
            	}else{
            		if(pageNow!=1){
            		page+="<li><a onclick=test("+(pageNow-1)+");>&laquo;</a></li>";
            		} 
            		for(var m=pageNow;m<=pageNow+19&&m<=pageCount;m++){
  							page+="<li id=page"+m+"><a onclick=test("+m+");>"+m+"</a></li>";
 						}  
 					if(pageNow!=pageCount){
 						page+="<li><a onclick=test("+(pageNow+1)+");>&raquo;</a></li>";        			    		
            		}         	
     			}  
            	$("#pagecount").append(page);
            	$("#page"+pageNow+"").addClass("active");
		    },
		    complete: function() {
		        //请求完成的处理
		    },
		    error: function(err) {
		        //请求出错处理
		        
		    }
		});
    }
    function jump(){
    	var jpage=parseInt($("#jumpto")[0].value);
    	//alert(jpage);
    	test(jpage);
    }
	</script> 
  <div class="nav">
    <ul>
      <li><a href="monitor.jsp">STATUS</a></li>
      <li class="active-li"><a href="test.jsp" class="scroll">DATA</a></li>
      <li><a href="monitor.jsp">HOME</a></li>
      <li><a href="movie.jsp">MOVIE</a></li>
      <li><a href="analyse.jsp">ANALYSE</a></li>
    </ul>
  </div>
  <div style="padding-top:150px;">
    <h1>History <font>Data</font></h1>
  </div> 
  <div class="data-box">
  <table  id="maintable" class="table table-hover" width="100%" layoutH="138">
      <thead>
        <tr>
            <th width="80">ID</th>
              <th width="190">TIME</th>
              <th width="120">TEMPERATURE</th>
              <th width="100">HUMIDITY</th>
              <th width="150">SMOG</th>
              <th width="80">POWER</th>
              <th width="80">WATERLOG</th>
            </tr>
         </thead>
         <tbody>
         </tbody> 
        </table>
        <script>test(1);</script>
        <div style="height:60px;">
          <div style="float:left;height:60px;">
            <ul class="pagination pagination-lg"  id="pagecount"></ul>
          </div>
          <div style="width:200px;height:60px;float:right;margin-top:30px;" >
            Page <input type="text" style="width:40px;"id="jumpto">&nbsp
            <button type="button" class="btn btn-default btn-sm" onclick=jump();>GO</button>
          </div>
        
        </div>
  </div> 
        <footer>
    <img src="images/iet2.png"><br>
      <p>&copy Design by maoxiaoxuan,2016|All Rights Reserved <a href="http://letlab.bupt.cn">LET LAB</a></p>
  </footer>
  </body>
</html>
