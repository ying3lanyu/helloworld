<!DOCTYPE html>
<html>
<head>
	<title>updEmp</title>
<?php
	require_once "empService.class.php";
	require_once "emp.class.php";

	$id=$_GET['id'];

	$empService=new EmpService();
	$arr=$empService->getEmpById($id);

	$emp=new Emp();
	$emp->setId($arr[0][0]);
	$emp->setName($arr[0][1]);
	$emp->setGrade($arr[0][2]);
	$emp->setEmail($arr[0][3]);
	$emp->setSalary($arr[0][4]);
?>
</head>
<body>
	<h1>updteEmp Info id=<?php echo $emp->getId()?></h1>
	<form action="empProcess.php" method="post">
		<table>
			<tr><td></td><td><input type="hidden" name="id" value="<?php echo $emp->getId()?>"></td></tr>
			<tr><td>Name</td><td><input type="text" name="name" value="<?php echo $emp->getName()?>"></td></tr>
			<tr><td>Grade</td><td><input type="text" name="grade" value="<?php echo $emp->getGrade()?>"></td></tr>
			<tr><td>Email</td><td><input type="text" name="email" value="<?php echo $emp->getEmail()?>"></td></tr>
			<tr><td>Salary</td><td><input type="text" name="salary" value="<?php echo $emp->getSalary()?>"></td></tr>
			<tr>
				<td>
					<input type="hidden" name="flag" value="updemp">
					<input type="submit" value="Update">
				</td>
				<td>
					<input type="reset" value="Reset">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
