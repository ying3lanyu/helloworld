<?php

	require_once "SqlHelper.class.php";

	class EmpService{

		function getEmpById($id){
			$sql="select * from emp where id=$id";
			$sqlHelper=new SqlHelper();
			$arr=$sqlHelper->execute_dqlArr($sql);
			$sqlHelper->close_connect();

			return $arr;
		}
		function getPageCount($pageSize){

			$sql="select count(id) from emp";
			$sqlHelper=new SqlHelper();
			$res=$sqlHelper->execute_dql($sql);

			if($row=$res->fetch_row()){
				$pageCount=ceil($row[0]/$pageSize);
			}

			$res->free();
			$sqlHelper->close_connect();
			return $pageCount;
		}

		function getEmplistByPage($pageNow,$pageSize){
			$sql="select * from emp where id limit ".($pageNow-1)*$pageSize.",$pageSize";
			$sqlHelper=new SqlHelper();
			$res=$sqlHelper->execute_dqlArr($sql);

			return $res;

			//$res->free();
			$sqlHelper->close_connect();
		}

		function getPageList($pageList){

			$sqlHelper=new SqlHelper();

			$sqlAll="select * from emp limit ".($pageList->pageNow-1)*$pageList->pageSize.",".$pageList->pageSize;
			//echo "sql=$sqlAll";
			//exit();
			$sqlCount="select count(id) from emp";
			$sqlHelper->execute_dql_pagelist($sqlAll,$sqlCount,$pageList);

			$sqlHelper->close_connect();

		}

		function delEmpById($id){

			$sql="delete from emp where id=$id";
			$sqlHelper=new SqlHelper();
			
			return $sqlHelper->execute_dml($sql);
		}

		function addEmp($name,$grade,$email,$salary){
			$sql="insert into emp (name,grade,email,salary) values ('$name',$grade,'$email',$salary)";
			$sqlHelper=new SqlHelper();
			$res=$sqlHelper->execute_dml($sql);
			$sqlHelper->close_connect;
			return $res;
		}
		function updEmp($id,$name,$grade,$email,$salary){
			$sql="update emp set name='$name',grade='$grade',email='$email',salary='$salary' where id=$id";
			$sqlHelper=new SqlHelper();
			$res=$sqlHelper->execute_dml($sql);
			$sqlHelper->close_connect;
			return $res;

		}

	}
	

?>