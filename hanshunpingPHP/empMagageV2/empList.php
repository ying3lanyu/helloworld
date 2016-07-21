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
	<script type="text/javascript">
		function delConfirm(val){
			return window.confirm("是否要删除id="+val+"的用户？");
		}
	</script>
</head>
<body>
	<h1>雇员信息列表</h1>
<?php
	
	require_once "empService.class.php";
	require_once "pageList.class.php";

	$empService=new EmpService();
	$pageList=new PageList();

	$pageList->pageNow=1;
	$pageList->pageSize=6;
	$pageList->gotoUrl='empList.php';


	if(!empty($_GET['pageNow'])){
		$pageList->pageNow=$_GET['pageNow'];
	}

	$empService->getPageList($pageList);

	echo "<table class='table-style'>";
	echo "<tr><th>id</th><th>name</th><th>grade</th><th>email</th><th>salary</th><th>删除用户</th><th>修改用户</th>";
	for($i=0;$i<$pageList->pageSize;$i++){
		if(!empty($pageList->res_array[$i])){
			$row=$pageList->res_array[$i];
			echo "<tr>";
			foreach ($row as $key => $value) {
				echo "<td>$value</td>";
			}
			$id_select=$row[0];
			echo "<td><a onclick='return delConfirm($id_select)' href='empProcess.php?flag=del&id=$id_select'>删除用户</a></td><td><a href='updEmp.php?id=$id_select'>修改用户</a></td></tr>";
		}
	}

	echo "<br>".$pageList->navigater;
	//printf_r($pageList->res_array);
?>
</body>
	<form action="empList.php">
		<input type="text" name="pageNow">
		<input type="submit" value="GO">
	</form>
</html>
