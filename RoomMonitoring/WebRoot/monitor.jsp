<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%
String u=(String)request.getSession().getAttribute("username");
if(u==null){
response.sendRedirect("index.jsp");
}
 %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'MyJsp.jsp' starting page</title>
    <meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/monitor.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">

  </head>
  
  <body>
  	<div class="aboutus" id="aboutus">
	  	<div class="container">
	  		<div class="logo">
	  			<a href="moniter.jsp"><img src="images/logo.png" alt=""></a>
	  		</div>
	  		<div>
	  			<div id="myCarousel" class="carousel slide" style="height:350px;">
	   			<!-- 轮播（Carousel）指标 --> 
	   			<!-- 轮播（Carousel）项目 -->
	   				<div class="carousel-inner">
	      				<div class="item active">
	         				<div class="slide-content">
								<h2>Welcome to system!</h2>
								<p>You can monitor your computer room by use this system without coming to <br>the real room frequently,which make monitor a easy thing!</p>
								<a href="">Contact us</a>
							</div>
	      				</div>
	      				<div class="item">
	         				<div class="slide-content">
								<h2>Welcome to system!</h2>
								<p>You can monitor your computer room by use this system without coming to <br>the real room frequently,which make monitor a easy thing!</p>
								<a href="">Contact us</a>
							</div>
	      				</div>
	      				<div class="item">
	         				<div class="slide-content">
								<h2>Welcome to system!</h2>
								<p>You can monitor your computer room by use this system without coming to <br>the real room frequently,which make monitor a easy thing!<br></p>
								<a href="">Contact us</a>
							</div>
	      				</div>
	   				</div>
	   				<ol class="carousel-indicators" style="margin-top:3em;">
	      				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      				<li data-target="#myCarousel" data-slide-to="1"></li>
	     				<li data-target="#myCarousel" data-slide-to="2"></li>
	   				</ol>  
				</div> 
	  		
	  		</div>
	  	</div>
	</div>
    <div class="nav">
		<ul>
			<li><a href="#myStatus" class="scroll">STATUS</a></li>
			<li><a href="test.jsp" class="scroll">DATA</a></li>
			<li class="active-li"><a href="monitor.jsp">HOME</a></li>
			<li><a href="movie.jsp" class="scroll">MOVIE</a></li>
			<li><a href="analyse.jsp" class="scroll">ANALYSE</a></li>
		</ul>
	</div>	
	<div class="section section-1">
		<h1>About <font>Us</font></h1>
		<div class="details">
			<h3>What's different?</h3>
			<p>Our details and description are listed here!<br>
				用物联网的技术思想创新探索“互联网+”的解决方案和面向物联网技术与应用的智慧服务系统（Smart Service System ,简称3S），创新研究和设计物联网智慧服务系统的关键技术及其应用，主要包括：物联网技术体系架构、新型传感器件、智能信息感知节点、多终端协同控制器、用户智能控制终端、异构融合与多网协同、SDN与智能服务组网、用户服务平台与大数据处理、智能信息处理、CPS技术与智能信息系统、面向用户的软件定义服务、智慧服务与行业应用技术、物联网技术与应用标准、面向用户服务的信息安全。
			</p>
		</div>
	</div>
	<a name="myStatus"></a>
	<div class="section section-2">
		<h1>Present <font>Status</font></h1>
		<div class="container">
			<div class="col-md-2">	
				<div class="skill-grid">
					<div class="circle" id="circles-1"></div>									
					<h3><a href="#">Temperature</a></h3>	
				</div>								  
			</div>
			<div class="col-md-2">	
				<div class="skill-grid">
					<div class="circle" id="circles-2"></div>									
					<h3><a href="#">Humidity</a></h3>	
				</div>								  
			</div>
			<div class="col-md-2">	
				<div class="skill-grid">
					<div class="circle" id="circles-3"></div>									
					<h3><a href="#">Smog</a></h3>	
				</div>								  
			</div>
			<div class="col-md-2">	
				<div class="skill-grid">
					<div class="circle" id="circles-4"></div>									
					<h3><a href="#">Power</a></h3>	
				</div>								  
			</div>
			<div class="col-md-2">	
				<div class="skill-grid">
					<div class="circle" id="circles-5"></div>									
					<h3><a href="#">Waterlog</a></h3>	
				</div>								  
			</div>
		</div>
	</div>
	<script type='text/javascript' src="js/jquery-1.11.3.min.js"></script>
	<script type='text/javascript' src="js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/monitor.js"></script>	
	<script>
		test(); 
//		setInterval(test,30000);
	</script> 
 <footer>
    <img src="images/iet2.png"><br>
      <p>&copy Design by maoxiaoxuan,2016|All Rights Reserved <a href="http://letlab.bupt.cn">LET LAB</a></p>
  </footer>
  </body>
</html>
