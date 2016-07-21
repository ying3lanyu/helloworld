<?php

	require_once "empService.class.php";
	
	$empService=new EmpService();

	if(!empty($_REQUEST['flag'])){
		$flag=$_REQUEST['flag'];
		if($flag=="del"){
			$id=$_GET['id'];
			if($empService->delEmpById($id)==1){
				header("Location:ok.php");
				exist();
			}else{
				header("Location:error.php");
				exist();
			}
		}elseif($flag=="addemp"){
			$name=$_POST['name'];
			$grade=$_POST['grade'];
			$email=$_POST['email'];
			$salary=$_POST['salary'];

			$res=$empService->addEmp($name,$grade,$email,$salary);
			if($res==1){
				header("Location:ok.php");
				exist();	
			}else{
				header("Location:error.php");
				exist();	
			}
		}elseif($flag=="updemp"){
			$id=$_POST['id'];
			$name=$_POST['name'];
			$grade=$_POST['grade'];
			$email=$_POST['email'];
			$salary=$_POST['salary'];

			$res=$empService->updEmp($id,$name,$grade,$email,$salary);
			if($res==1){
				header("Location:ok.php");
				exist();	
			}else{
				header("Location:error.php");
				exist();	
			}
		}
		
	}


?>