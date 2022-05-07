<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>aarria - women wear</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Fallsbuy Ecommerce Private Limited">
<meta name="author" content="fallsbuy">

<link href="/resources/assets/products/css/products.css"
	rel="stylesheet">

<style>
body {
	font-family: "Source Sans Pro", sans-serif !important;
}

</style>
</head>
<body>
	<header class="header-div">
		<jsp:include page="common/header.jsp" />
	</header>

	<div class="container container-div product-div">
		<div class="row breadcrumb_row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb" id="breadcrumb_category">
					<li><a href="/">Home</a></li>
					<c:forEach begin="0" end="${product.breadcrumbsSize}"
						var="breadcrumb" items="${product.breadcrumbs}">
						<li><a href="products?cat=${breadcrumb.id}">${breadcrumb.name}</a></li>
					</c:forEach>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="container">
			<h2>Store Location in Bengaluru:</h2> <br/>

			 <h3>aarria,</h3> No 1212, ground floor, <br/>SBM fortune 22nd cross road, <br/>14th Main Rd, 3rd sector,<br/> HSR Layout, Bengaluru, Karnataka 560102<br/><br/><br/>

			<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15555.896926190419!2d77.6386091!3d12.9093777!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xc520440f4ca85ea9!2saarria!5e0!3m2!1sen!2sin!4v1651928998428!5m2!1sen!2sin" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
		</div>

	</div>
	<br>
	<br>
	<footer class="footer-div">
		<jsp:include page="common/footer.jsp" />
	</footer>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>

</body>
</html>
