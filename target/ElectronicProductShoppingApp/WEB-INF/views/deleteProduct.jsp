<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DELETE PRODUCT</title>
<script>
	
</script>

</head>
<body>
	<h1>DELETE A PRODUCT</h1>
	<form name="deleteForm" method="POST" action="deleteExistingProduct">
		<table>
			<tbody>
				<tr>
					<td>Product Id:</td>
					<td><select name="product" id="product">
							<option value="">--Select Product ---</option>
							<c:forEach items="${productList}" var="product">
								<option value="${product.productId}">${product.productName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr></tr>
				<tr>
					<td><input type="button" value="Back" onclick="history.back()"></td>
					<td><input type="submit" value="Delete Product" /></td>

				</tr>
			</tbody>
		</table>

	</form>
	<div id="msg" style="color: Green">
		<h2>${msg}</h2>
	</div>
</body>
</html>
