<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
</head>
<body>
	<h1>Sign Up</h1>
	<form method="POST" action="SignUpServlet">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><input type="text" name="gender" /></td>
			</tr>
			<tr>
				<td>PassWord</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><button>Submit</button>
					<input type="reset"></td>
			</tr>
		</table>
	</form>
	<div>
		<h4>Thông tin đăng ký của bạn</h4>
		<h5>${username}</h5>
	</div>
</body>
</html>