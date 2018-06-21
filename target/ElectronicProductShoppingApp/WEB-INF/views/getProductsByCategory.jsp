<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Products by category</title>

<script type="text/javascript">
$(document).ready(function() {
	$('select').change(function() {
	$("#error").html("");
	var categoryName = document.getElementById("category").value;
	alert(categoryName);
	if(categoryName != " " || categoryName != null){
		document.getElementById("getButton").disabled = false;
		//document.getElementById("productsByCategory").style.visibility = "visible";
		
	}else{
		$("#error").html("No Product is avaliable.");
	}
	});	
});
</script>
</head>
<body>
	<h1>View Products by category</h1>
	<form name="categoryForm" method="POST"
		action="getProductListByCategory">
		<table>
			<tbody>
				<tr>
					<td>Product Category:</td>
					<td><select name="category" id="category">
							<option value="">--Select Product ---</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category}">${category}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="button" value="Back" onclick="history.back()"></td>
					<td><input type="submit" id="getButton" value="Get Products" disabled="disabled"/></td>

				</tr>
			</tbody>
		</table>

	</form>
	<div id="error" style="color: RED">
	</div>
	<div id="productsByCategory" style="visibility: visible;">
		<table border="1px">
			<tr>
				<th>ProductId</th>
				<th>ProductName</th>
				<th>Price</th>
				<th>Available Stock</th>
				<th>Remarks</th>
			</tr>

			<c:forEach items='${productsByCategory}' var="product">
				<tr>
					<td>${product.productId}</td>
					<td>${product.productName}</td>
					<td>${product.price}</td>
					<td>${product.currentStockNumbers}</td>
					<td>${product.remarks}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>