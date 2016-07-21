<?php

	class SqlHelper{

		public $mysqli;

		private static $host="localhost";
		private static $user="root";
		private static $password="123521";
		private static $dbname="test";

		public function __construct(){

			//initial task
			$this->mysqli=new MySQLi(self::$host,self::$user,self::$password,self::$dbname);
			if($this->mysqli->connect_error){
				die("unable to connect to $host,$dbname".$this->mysqli->connect_error);
			}
			$this->mysqli->query("set names utf8");
		}

		public function execute_dql($sql){
			$res=$this->mysqli->query($sql) or die("illegal operation!".$this->$mysqli->error);
			return $res;
		}

		public function execute_dqlArr($sql){

			$arr=array();
			$res=$this->mysqli->query($sql) or die("illegal operation!".$this->$mysqli->error);
			$i=0;
			while($row=$res->fetch_row()){
				$arr[$i++]=$row;
			}

			$res->free();
			return $arr;
		}

		public function execute_dql_pagelist($sqlAll,$sqlCount,$pageList){

			$res=$this->execute_dqlArr($sqlAll);
			$res2=$this->execute_dql($sqlCount);

			if($row=$res2->fetch_row()){
				$pageList->pageCount=ceil($row[0]/$pageList->pageSize);
				$pageList->rowCount=$row[0];
			}
			$res2->free();
			$url=$pageList->gotoUrl;
			//navigater part
			$navigater="";
			if(($pageList->pageNow)>1){
				$prePage=$pageList->pageNow-1;
				$navigater="<a href='$url?pageNow=$prePage'>上一页</a>&nbsp";
			}
			if($pageList->pageNow<5){
				for($i=1;$i<=10;$i++){
					$navigater.="<a href='$url?pageNow=$i'>$i</a>&nbsp";
				}
			}elseif($pageList->pageNow>($pageList->pageCount-5)){
				for($i=($pageList->pageCount-9);$i<=$pageList->pageCount;$i++){
					$navigater.="<a href='$url?pageNow=$i'>$i</a>&nbsp";
				}
			}else{
				for($i=($pageList->pageNow-4);$i<=($pageList->pageNow+5);$i++){
					$navigater.="<a href='$url?pageNow=$i'>$i</a>&nbsp";
				}
			}
			if($pageList->pageNow<$pageList->pageCount){
				$nextPage=$pageList->pageNow+1;
				$navigater.="<a href='$url?pageNow=$nextPage'>下一页</a>&nbsp";
			}
			$navigater.="当前页&nbsp$pageList->pageNow/共有&nbsp$pageList->pageCount&nbsp页";

			$pageList->res_array=$res;
			$pageList->navigater=$navigater;

		}

		public function execute_dml($sql){
			$res=$this->mysqli->query($sql) or die("illegal operation!".$this->$mysqli->error);
			if(!$res){
				return 0;
			}else{
				if($this->mysqli->affected_rows>0){
					return 1;
				}else{
					return 2;
				}
			}
		}
		public function close_connect(){
			if(!empty($this->mysqli)){
				$this->mysqli->close();
			}
		}
	}
?>