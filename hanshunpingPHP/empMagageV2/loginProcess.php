<?php

	require_once 'adminService.class.php';

	$id_admin=$_POST['id_admin'];
	$password=$_POST['password'];
	$adminService=new AdminServlet();
	//echo $id_admin,$password;
	if($name=$adminService->checkAdmin($id_admin,$password)){
		header('Location:empMain.php?name=$name');
		exit();
	}else{
		header("Location:login.php?errorno=1");
		exit();
	}
?>