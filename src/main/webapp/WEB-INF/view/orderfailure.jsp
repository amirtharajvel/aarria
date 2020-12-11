<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Order failed - We're sorry.</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/checkout/css/checkout.css" rel="stylesheet">
<style>
a {
	cursor: pointer;
}
</style>
</head>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
<div class="container">
	<div class="navbar-header">
		<a href="/"><img src="resources/assets/images/logo.png"
			height="40" width="150"></a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<c:if test="${isUserLoggedIn.equals('true')}">
			<div style="float: right;">
				<a style="color: wheat;" href="logout">Logout</a>
			</div>
		</c:if>
	</div>
	<!--/.nav-collapse -->
</div>
</nav>

<div class="container">
	<div class="jumbotron" style="min-height: 550px;">
		<h3>We're sorry, but your current order could not be processed.</h3>
		<h4>${sessionScope.errorMessage}</h4>
		<a id="contact_us">Contact us</a> <br /> <a id="go_home">Go home</a>
		<br /> <a id="goto_cart">Go to cart</a><br /> <a id="goto_orders">Go
			to orders</a>

		<c:if test="${sessionScope.errorMessage == null}">
			<script>
				window.location = "/";
			</script>
		</c:if>
	</div>
</div>

<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
<script src="resources/assets/common/js/bootstrap.min.js"></script>
<script src="resources/assets/checkout/js/jquery.bootstrap.wizard.js"></script>

<script>
	window.onbeforeunload = function(event) {
		removeAttribute('errorMessage', "/");
		return null;
	};

	$(document).ready(function() {

		function removeAttribute(attribute, loc) {
			$.ajax({
				url : '/removeAttribute',
				contentType : 'text/plain',
				method : 'GET',
				data : 'attribute=' + attribute,
				success : function() {
					window.location = loc;
				}
			});
		}

		$('#goto_orders').click(function() {
			removeAttribute("errorMessage", "/myorders");
			window.location = "/myorders";
			return false;
		});

		$('#goto_cart').click(function() {
			removeAttribute("errorMessage", "/checkout");
			window.location = "/checkout";
			return false;
		});

		$('#contact_us').click(function() {
			removeAttribute("errorMessage", "/aboutus#contact_us");
			window.location = "/aboutus#contact_us";
			return false;
		});

		$('#go_home').click(function() {
			removeAttribute("errorMessage", "/");
			window.location = "/";
			return false;
		});
	});
</script>

</html>