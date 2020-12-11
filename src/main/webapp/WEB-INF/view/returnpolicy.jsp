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

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/common/style/style.css" rel="stylesheet">
<link href="resources/assets/common/style/font-awesome.css"
	rel="stylesheet">
<style>
body {
	font-family: "Source Sans Pro", sans-serif !important;
}

body, h1, h2, h3, h4, h5, h6, p, span, div, ul, li, ol, a {
	color: black;
}

a:focus {
	outline: none;
}

#size_container {
	border-top: 1px solid #e5e0e0;
	margin-top: 3%;
	padding-top: 3%;
	width: 100%;
}

#add_to_cart {
	margin-top: 3%;
	padding-top: 3%;
	width: 100%;
}

.add_to_cart {
	width: 100%;
}

#quantity_container {
	margin-top: 3%;
}

.btn-group .btn+.btn, .btn-group .btn+.btn-group, .btn-group .btn-group+.btn,
	.btn-group .btn-group+.btn-group {
	margin-left: 2px;
}

#actual_price {
	text-decoration: line-through
}

#discount {
	color: #62bd7f;
}

body {
	font-family: "Arial";
}

#sizechart_link {
	color: black;
	text-decoration: underline;
	padding-left: 5%;
}

.well {
	background-color: white;
}

ul {
	padding-left: 3%;
}

.panel-default>.panel-heading {
	color: #323A3D;
	background-color: #EDEFF1;
	box-shadow: 0 0 10px #9ecaed;
}

.padding-left0 {
	padding-left: 2%;
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

				<h4>Returns Policy</h4>
				<br />
				<div class="panel-group" id="accordion"
					style="padding-top: 0px; margin-top: 0px;" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">

						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne"> What is your policy on
									returns? </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<p>
									It's <strong>Try and Buy.</strong> So you can return
									immediately on the spot if you do not like the product. You can
									still return 7 days after the purchase.
								</p>
							</div>
						</div>
					</div>

					<!-- <div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree"
									aria-expanded="false" aria-controls="collapseThree"> Will I
									get free shipping if I return part of my order? </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								Yes, you will. We'll be happy to give you a full refund when you
								return your order. <br /> <br /> <strong> But, for
									some reasons we may not be able to arrange pick up service. In
									that case you have to send the items to us on your own. You've
									to bear the return shipping cost for those cases. We'll give
									full refund for the returned items.</strong>
							</div>
						</div>
					</div> -->

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingEleven">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseEleven"
									aria-expanded="false" aria-controls="collapseEleven"> How
									do I return an order? </a>
							</h4>
						</div>
						<div id="collapseEleven" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingEleven">
							<div class="panel-body">
								You can return entire or part of an order within 7 days of
								delivery.<br /> <br />

								<ul>
									<li><a href="login">Login</a> to your account</li>
									<li>Select <strong>Account</strong> from <a href="/">home</a>
										page.
									<li>In My Account page select <strong>View all
											orders</strong>
									</li>
									<li>In My orders page select <strong>Past Orders</strong>
										tab.
									</li>
									<li>Click <strong>Return item(s)</strong> button to return
										items.
									</li>
									<li>We'll arrange you a pick up service to pick your
										items.</li>
								</ul>
								<br /> Or <a href="aboutus#contact_us">Call us</a> and we'll
								book a return for you. <br /> <br />
								<!--  <strong> Note:
									In case our pick up service is not available to your location
									you have to send the items to us on your own. You've to bear
									the return shipping cost for those cases.</strong> -->
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwo">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo"> Will you
									accept a return if I've discarded the packaging? </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">Yes. When scheduling a return,
								please wrap the returned products before handing them over to
								our courier partner.</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingFive">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFive"
									aria-expanded="false" aria-controls="collapseFive"> Can I
									exchange my purchase for another product? </a>
							</h4>
						</div>
						<div id="collapseFive" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFive">
							<div class="panel-body">We're sorry, we do not offer this
								facility at the moment. You can always return the product and
								use your refund to make new purchases on aarria.com</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingFour">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseFour"
									aria-expanded="false" aria-controls="collapseFour"> Can I
									place multiple return requests for an order? </a>
							</h4>
						</div>
						<div id="collapseFour" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingFour">
							<div class="panel-body">We're sorry. You can place only one
								return request for an order. You have to return all the items
								you wish to return in a single return order.</div>
						</div>
					</div>

					<div id="refunds">
						<h4>
							<br />Refunds
						</h4>
						<br />
					</div>


					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingSix">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseSix"
									aria-expanded="false" aria-controls="collapseSix"> If I pay
									for my order by debit card/credit card/netbanking how long it
									will take to process my refund? </a>
							</h4>
						</div>
						<div id="collapseSix" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingSix">
							<div class="panel-body">It takes four to five business days
								to receive the returned item and perform a quality check. We
								will then take two business days to issue the refund. Your bank
								or card issuer may take a further two to 7 business days to
								process the transaction. Get in touch with them to know more.</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingSeven">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseSeven"
									aria-expanded="false" aria-controls="collapseSeven"> I paid
									for my order with Cash On Delivery. How is my refund processed?
								</a>
							</h4>
						</div>
						<div id="collapseSeven" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingSeven">
							<div class="panel-body">You'll get instantly through any
								available payment method.</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTen">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTen"
									aria-expanded="false" aria-controls="collapseTen"> I have
									other questions about returns and refunds. To whom I can call?</a>
							</h4>
						</div>
						<div id="collapseTen" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTen">
							<div class="panel-body">We're here to answer your
								questions. Call us on these numbers +91 9901411006, +91
								8553797479</div>
						</div>
					</div>

					<div id="cancellation">
						<h4>
							<br />Cancellation
						</h4>
						<br />
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingEight">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseEight"
									aria-expanded="false" aria-controls="collapseEight"> Can I
									cancel the order any time? </a>
							</h4>
						</div>
						<div id="collapseEight" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingEight">
							<div class="panel-body">
								<ul>
									<li>You can cancel the entire/part of the order for 30
										minutes after it is placed.</li>
									<li>After 30 minutes, if the order is not dispatched still
										you can call us and cancel the order.</li>
									<li>You cannot cancel the order if it is dispatched, you
										can only return it after you receive.</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingNine">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#collapseNine"
									aria-expanded="false" aria-controls="collapseNine"> What
									should I do if the product is delivered by you is damaged or
									defective? </a>
							</h4>
						</div>
						<div id="collapseNine" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingNine">
							<div class="panel-body">No worries. You can return it
								immediately or you can place a return request.</div>
						</div>
					</div>


					<div id="shipping">
						<br /> <br />
						<h4>Shipping</h4>
						<br />

						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwelve">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwelve"
										aria-expanded="false" aria-controls="collapseTwelve"> When
										will I receive my order? </a>
								</h4>
							</div>
							<div id="collapseTwelve" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwelve">
								<div class="panel-body">You'll get it with in 24 hours.</div>
							</div>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingThirteen">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThirteen"
										aria-expanded="false" aria-controls="collapseThirteen">
										Will I be charged for delivery? </a>
								</h4>
							</div>
							<div id="collapseThirteen" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingThirteen">
								<div class="panel-body">No. We are providing free delivery
									for all orders.</div>
							</div>
						</div>

					</div>
				</div>
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
