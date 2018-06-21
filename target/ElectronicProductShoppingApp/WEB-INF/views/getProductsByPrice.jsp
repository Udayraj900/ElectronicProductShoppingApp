<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products by Price</title>
<script type="text/javascript">
function validatePrice(){
	
	var price = document.getElementById("productPrice").value;
    if (price == "" || price == null) {
        alert("Please enter the price");
        return false;
    }
    
}
function isNumber(evnt) {
    evt = (evnt) ? evnt : window.event;
    var charCode = (evnt.which) ? evnt.which : evnt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

</script>
</head>
<body>
<h1>View Products by Price</h1>
<form action="getProductByPriceRange" method="POST"  onsubmit=" return validatePrice()">
<table>
<tr>
<td>Enter the price(the products > this price range will be displayed)</td>
<td><input type="text" id="productPrice" name="productPrice" onkeypress=" return isNumber(event)"/></td>
</tr>
<tr>
<td><input type="button" value="Back" onclick="history.back()"></td>
<td><input type="submit" value="Get Products">
</tr>
</table>
</form>
<div id ="productsByPrice">
	<table border="1px">
			<tr>
				<th>ProductId</th>
				<th>ProductName</th>
				<th>Category</th>
				<th>Price</th>
				<th>Available Stock</th>
				<th>Remarks</th>
			</tr>

			<c:forEach items='${productsByPrice}' var="product">
				<tr>
					<td>${product.productId}</td>
					<td>${product.productName}</td>
					<td>${product.productCategory}</td>
					<td>${product.price}</td>
					<td>${product.currentStockNumbers}</td>
					<td>${product.remarks}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>