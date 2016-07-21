<!DOCTYPE html>
<html>
<head>
	<title>addEmp</title>
	<meta charset="utf-8">
</head>
<body>
	<h1>添加雇员</h1>
	<form action="empProcess.php"method="post">
		<table>
		<tr><td>Name</td><td><input type="text" name="name"></td></tr>
		<tr><td>Grade</td><td><input type="text" name="grade"></td></tr>
		<tr><td>Email</td><td><input type="text" name="email"></td></tr>
		<tr><td>Salary</td><td><input type="text" name="salary"></td></tr>
		<tr>
			<td>
				<input type="hidden" name="flag" value="addemp">
				<input type="submit" value="addEmp">
			</td>
			<td>
				<input type="reset" value="Reset">
			</td>
		</tr>
		</table>
	</form>
		
</body>
</html>