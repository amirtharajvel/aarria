<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Thank you for your order at fallsbuy.com. Welcome to the
	world of quality.</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="fallsbuy">

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
	<div class="jumbotron">

		<div class="panel panel-default">
			<div class="panel-body">
				<c:if test="${sessionScope.order != null}">
					<div style="color: #5cb85c;">
						<h3>Thank you for shopping with us.</h3>
					</div>
					<div>
						Order Number : <span style="font-weight: normal; font-size: 15px;">${sessionScope.order.orderNo }</span>
						<br /> Estimated Delivery : <span
							style="font-weight: normal; font-size: 15px;">
							<%-- ${sessionScope.order.estimatedDeilvery } --%> <strong>Within 24
							Hours </strong> (Only in Bengaluru). We'll contact you shortly.
						</span>
						<h5>
							<a id="goto_myorders">Review or edit your order</a>
						</h5>
					</div>
				</c:if>
				<c:if test="${sessionScope.order == null}">
					<script>
						window.location = "/";
					</script>
				</c:if>
			</div>
		</div>
	</div>
</div>

<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
<script src="resources/assets/common/js/bootstrap.min.js"></script>
<script src="resources/assets/checkout/js/jquery.bootstrap.wizard.js"></script>
<script src="resources/assets/checkout/js/checkout.js"></script>

<script>
	window.onbeforeunload = function(event) {
		removeAttribute('order');
		return null;
	};
	$(document).ready(function() {

		function removeAttribute(attribute) {
			$.ajax({
				url : '/removeAttribute',
				contentType : 'text/plain',
				method : 'GET',
				data : 'attribute=' + attribute,
				success : function() {
					window.location = 'myorders';
				}
			});
		}

		$('#goto_myorders').click(function() {
			removeAttribute("order");
			window.location = "/myorders";
			return false;
		});
	});
</script>
</html>