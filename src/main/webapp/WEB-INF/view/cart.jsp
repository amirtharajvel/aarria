<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
<head></head>
<body>
	<div id="items">
		<c:forEach items=""></c:forEach>
	</div>
	<div id="proceed">
		<form action="checkout">
			<input type="submit" value="Proceed to Checkout" />
		</form>
	</div>

	<script src="resources/assets/cart/js/jquery-2.1.4.js"></script>
	<script src="resources/assets/cart/js/cart.js"></script>

	<script>
		$(document).ready(function() {
			displayCart();
		});
	</script>

</body>
</html>