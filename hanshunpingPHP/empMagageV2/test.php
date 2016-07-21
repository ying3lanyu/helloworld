<?php

	require_once "SqlHelper.class.php";

	$sql="select * from emp limit 0,20";
	$sqlHelper=new SqlHelper();
	$arr=$sqlHelper->execute_dqlArr($sql);
	print_r($arr);

?>