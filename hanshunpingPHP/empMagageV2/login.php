<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>login demo</title>
</head>
<body>
	<h1>用户管理系统</h1>
	<form action="loginProcess.php" method="post">
		<label>用户ID</label><input type="text" name="id_admin"><br>
		<label>密&nbsp&nbsp码</label><input type="password" name="password"><br>
		<input type="submit" name="用户登录">
	</form>
<?php
	if(!empty($_GET['errorno'])){
		$errorno=$_GET['errorno'];
		if ($errorno==1){
			echo "<br><font color='red'>illegal!!!</font>";
		}
	}
?>
</body>
</html>