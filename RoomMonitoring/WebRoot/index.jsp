<!DOCTYPE html>
<html>
<head>
<title>A login system for monitor</title>
<!-- For-Mobile-Apps -->
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"/>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //For-Mobile-Apps -->
<!-- Style --> 
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body>
<div class="container">
<h1>MONITOR SYSTEM</h1>
	<div class="logform">
	 <div class="signin">
     <form action="LOGIN" method="post">
	      <input type="text" name="username" class="user" placeholder="UserName" />
	      <input type="password" name="password" class="pass" placeholder="PassWord" />
          <input type="submit" value="LOGIN"/><br><br><br><font>${error}</font>
	 </form>
	 </div>
	</div>
</div>
<div class="footer">
     <p>&copy Design by maoxiaoxuan,2016|All Rights Reserved <a href="http://letlab.bupt.cn">LET LAB</a></p>
</div>
</body>
</html>