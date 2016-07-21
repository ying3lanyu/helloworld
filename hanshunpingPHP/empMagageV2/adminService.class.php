<?php
	
	require_once 'SqlHelper.class.php';
	class AdminServlet{
		public function checkAdmin($id,$password){

			$sql="select password,name from admin where id=$id";

			$sqlHelper=new SqlHelper();
			//$test=$sqlHelper->test();
			$res=$sqlHelper->execute_dql($sql);

			if($row=$res->fetch_row()){
				if($row[0]==md5($password)){
					return $row[1];
				}
			}

			$res->free();
			$sqlHelper->close_connect();
			return "";
		}
	}

?>