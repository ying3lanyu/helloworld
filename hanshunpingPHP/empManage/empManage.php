<?php

	$id_admin=$_POST['id_admin'];
	$password=$_POST['password'];

	$mysqli=new MySQLi("localhost","root","123521","test");
	if($mysqli->connect_error){
		die("wrong!".$mysqli->connect_error);
	}
	$sql="select password,name from admin where id=$id_admin";

	$res=$mysqli->Query($sql);

	if($row=$res->fetch_row()){
		// foreach ($row as $key => $value) {
		// 	// if($value==md5($password)){
		// 	// 	header('Location:empMain.php');
		// 	// 	exit();
		// 	// }
		// 	echo "-$row[$key]-<br>";
		// }
		if($row[0]==md5($password)){
			$name=$row[1];
			//echo "$name";
			header('Location:empMain.php?name=$name');
			exit();
		}
	}
	header("Location:login.php?errorno=1");
	exit();

	$res->free();
	$mysqli->close();
?>