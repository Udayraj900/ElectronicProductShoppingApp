<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>ADD PRODUCT</title>

<script type="text/javascript">

$(document).ready(function() {
	
$('#productId').blur(function() {
	$("#error").html("");
	var productId=$(this).val();
	
	if(productId!=''){
	$.ajax({
        type:"GET",
        url : "getProductById?product="+productId,
        async: true,
        dataType: 'json',
        success : function(response) {
        	if(response!=null && response!=''){
        	$.each(response, function(index, item) {
        		$('#productName').val(item.productName);
        		$('#productCategory').val(item.productCategory);
        		$('#price').val(item.price);
        		$('#currentStockNumbers').val(item.currentStockNumbers);
        		$('#remarks').val(item.remarks);
        	});
        }else{
        	$('#productName').val('');
    		$('#productCategory').val('');
    		$('#price').val('');
    		$('#currentStockNumbers').val('');
    		$('#remarks').val('');
        	$("#error").html("Product with the given id doesn't exist");
        	
        }
        },
        error: function() {
            alert('Error occured');
        }
    });
}
});

	function isNumber(evnt) {
	    evt = (evnt) ? evnt : window.event;
	    var charCode = (evnt.which) ? evnt.which : evnt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        return false;
	    }
	    return true;
	}

});
</script>
</head>
<body>
<h1>Update An Existing Product</h1>
<form:form action="updateExistingProduct" method="POST" commandName="product" id = "updateProductForm" > 
		<table>
		 	<tr>
				<td>Product Id:</td>
				<td><form:input path="productId" id="productId" /></td>
			</tr>
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
			<tr>
				<td><input type="button" value="Back" onclick="history.back()"></td>
				<td><input type="submit" value="Update Product" /></td>
				
			</tr>
		</table>
	</form:form>
	<div id="error" style="color: red"></div>
	<div id ="msg" style="color: Green">
	<h2>${msg}</h2>
	</div>
</body>
</html>