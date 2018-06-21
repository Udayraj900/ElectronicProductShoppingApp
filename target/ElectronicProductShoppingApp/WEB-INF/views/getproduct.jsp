<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PRODUCT DETAILS</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function () {
	$('select').change(function() {
		var productId=$(this).val();
		$("#productDetails").html("");
		if(productId!='') {
		$.ajax({
	        type:"GET",
	        url : "getProductById?product="+productId,
	        async: true,
	        dataType: 'json',
	        success : function(response) {
	        	if(response!=null && response!=''){
	        	var innerhtml="<table border='1px'><th>ProductId</th><th>ProductName</th><th>Category</th><th>Price</th><th>Available Stock</th>";
	        	$.each(response, function(index, item) {
	        		innerhtml+="<tr><td>"+item.productId+"</td>";
	            	innerhtml+="<td>"+item.productName+"</td>";
	            	innerhtml+="<td>"+item.productCategory+"</td>";
	            	innerhtml+="<td>"+item.price+"</td>";
	            	innerhtml+="<td>"+item.currentStockNumbers+"</td>";
	        	});
	        	innerhtml+="</tr></table>";
            	$("#productDetails").html(innerhtml);
	        }else{
	        	$("#productDetails").html("<h3>No Product is added to the store!!!!</h3>");
	        }
	        },
	        error: function() {
	            alert('Error!!!!!!');
	        }
	    });
	}
	});
	
});
	
</script>

</head>
<body>
	<h1>PRODUCT DETAILS</h1>
	
		<table>
			<tbody>
				<tr>
					<td>Product: </td>
					<td><select name="product" id="product">
					<option value="-1">  ALL PRODUCT  </option>
							<c:forEach items='${productList}' var="product">
								<option value="${product.productId}">${product.productName}</option>
							</c:forEach>
					</select></td>
			</tbody>
		</table>

<br>
		<div id="productDetails">
		<table border="1px">
			<tr>
				<th>ProductId</th>
				<th>ProductName</th>
				<th>Category</th>
				<th>Price</th>
				<th>Available Stock</th>
			</tr>

			<c:forEach items='${productList}' var="product">
				<tr>
					<td>${product.productId}</td>
					<td>${product.productName}</td>
					<td>${product.productCategory}</td>
					<td>${product.price}</td>
					<td>${product.currentStockNumbers}</td>
				</tr>
			</c:forEach>
		</table>
		
</div>
<br>
<input type="button" value="Back" onclick="history.back()">
</body>
</html>
