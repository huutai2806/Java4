<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Product</title>
</head>
<body>
	<h1>Insert Product</h1>
	<form method="POST" action="newProductServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Product ID</td>
				<td><input type="text" name="ID" /></td>
			</tr>
			<tr>
				<td>Product Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Product Type</td>
				<td><input type="text" name="type" /></td>
			</tr>
			<tr>
				<td>Product Price</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>Product Image</td>
				<td><input type="file" name="image" value=""></td>
			</tr>
			<tr>
				<td colspan="2"><button>Submit</button>
					<input type="reset"></td>
			</tr>
		</table>
	</form>
	<div>
		<h5>${thongbao}</h5>
	</div>
</body>
</html>