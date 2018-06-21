<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ADD PRODUCT</title>

<script>

function isNumber(evnt) {
    evt = (evnt) ? evnt : window.event;
    var charCode = (evnt.which) ? evnt.which : evnt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function validateForm() {
    var name = document.getElementById("productName").value;
    if (name ==""|| name == null) {
           alert("Please enter the product name.");
           return false;
    }
    var category = document.getElementById("productCategory").value;
    if (category =="" || category == null) {
           alert("Please enter the product categeory.");
           return false;
    }
    var price = document.getElementById("price").value;
    if (price == "" || price == null || price == "0.0") {
        alert("Please enter the price");
        return false;
    }
    var stock = document.getElementById("currentStockNumbers").value;
    if (stock == "" || stock == null || stock == "0") {
        alert("Please enter the stock numbers");
        return false;
    }
    var remarks = document.getElementById("remarks").value;
    if (remarks == "" || remarks == null) {
        alert("Please enter the remarks");
        return false;
    }
}
</script>
</head>
<body>
<h1>Add A Product</h1>
<form:form action="saveProduct" method="POST" commandName="product" name = "addProductForm" onsubmit="return validateForm();"> 
		<table>
		 	<!-- <tr>
				<td>Product Id:</td>
				<td><form:input path="productId" id="productId" /></td>
			</tr> -->
			<tr>
				<td>Product Name:</td>
				<td><form:input path="productName" id="productName" ></form:input></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><form:input path="productCategory" id="productCategory"></form:input></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price"  id="price" onkeypress="return isNumber(event)"/></td>
			</tr>
			<tr>
				<td>Stock Number:</td>
				<td><form:input path="currentStockNumbers" id="currentStockNumbers" onkeypress="return isNumber(event)" /></td>
			</tr>
			<tr>
				<td>Remark :</td>
				<td><form:input path="remarks" id="remarks"  /></td>
			</tr>
			<tr></tr>
			<tr>
				<td><input type="button" value="Back" onclick="history.back()"></td>
				<td><input type="submit" value="Add Product" /></td>
				
			</tr>
		</table>
	</form:form>
	<div id = "msg" style="color: Green">
	<h2>${msg}</h2>
	</div>
</body>
</html>