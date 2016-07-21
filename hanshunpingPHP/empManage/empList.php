<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>List</title>
	<style type="text/css">
		.table-style{
			width:600px;
			border:1px solid black;
			border-collapse: collapse;
			text-align: center;
		}
		.table-style td,.table-style th{
			border: 1px solid black;
		}
	</style>
</head>
<body>
	<h1>雇员信息列表</h1>
<?php

	$pageSize=3;//一页几条数据
	$rowCount=0;//一共几条数据
	$pageNow=1;//目前在第几页
	$pageCount=0;//一共有几页

	if(!empty($_GET['pageNow'])){
		$pageNow=$_GET['pageNow'];
	}

	$mysqli=new MySQLi("localhost","root","123521","test");
	if($mysqli->connect_error){
		die("wrong!".$mysqli->connect_error);
	}

	$sqlcount="select count(id) from emp";
	$rescount=$mysqli->Query($sqlcount);
	if($rowcount=$rescount->fetch_row()){
		$rowCount=$rowcount[0];
	}
	$pageCount=ceil($rowCount/$pageSize);
	//echo $pageCount;
	$sql="select * from emp where id limit ".($pageNow-1)*$pageSize.",$pageSize";


	$res=$mysqli->Query($sql);
	echo "<table class='table-style'>";
	echo "<tr><th>id</th><th>name</th><th>grade</th><th>email</th><th>salary</th><th>删除用户</th><th>修改用户</th>";
	while($row=$res->fetch_row()){
		echo "<tr>";
		foreach ($row as $key => $value) {
				echo "<td>$value</td>";
		}
		echo "<td><a href='#'>删除用户</a></td><td><a href='#'>修改用户</a></td></tr>";
	}

	echo "<br>";

	// for($i=1;$i<=$pageCount;$i++){
	// 	echo "<a href='empList.php?pageNow=$i'>$i</a>&nbsp";
	// }
	if($pageNow>1){
		$prePage=$pageNow-1;
		echo "<a href='empList.php?pageNow=$prePage'>上一页</a>&nbsp";
	}
	if($pageNow<5){
		for($i=1;$i<=10;$i++){
			echo "<a href='empList.php?pageNow=$i'>$i</a>&nbsp";
		}
	}elseif($pageNow>($pageCount-5)){
		for($i=($pageCount-10);$i<=$pageCount;$i++){
			echo "<a href='empList.php?pageNow=$i'>$i</a>&nbsp";
		}
	}else{
		for($i=($pageNow-5);$i<=($pageNow+5);$i++){
			echo "<a href='empList.php?pageNow=$i'>$i</a>&nbsp";
		}
	}
	if($pageNow<$pageCount){
		$nextPage=$pageNow+1;
		echo "<a href='empList.php?pageNow=$nextPage'>上一页</a>&nbsp";
	}
	echo "当前页&nbsp$pageNow/共有&nbsp$pageCount&nbsp页";
	$res->free();
	$mysqli->close();
?>
</body>
	<form action="empList.php">
		<input type="text" name="pageNow">
		<input type="submit" value="GO">
	</form>
</html>
