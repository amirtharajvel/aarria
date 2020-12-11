<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Online Shopping: Shop Online for Clothing for Women. Buy
	hand picked brand Sarees, Kurtis, Choli's, Anarkali, Tops, Lahankas,
	Party Wear, Kids Wear at lowest price possible.</title>

<meta name="description"
	content="aarria.com: Online Shopping India - Buy brand sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="aarria.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/aboutus/css/aboutus.css" rel="stylesheet">
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
			<div class="well">
				<div id="who_we_are">
					<!-- <div style="text-align: center;">
						<h2>- Our Mission -</h2>
						<br /> <span style="color: #5cb85c;"> To provide the best
							e-commerce experience possible </span><br />
					</div> -->
					<h4>About us</h4>
					<p>&nbsp;</p>
					<p>aarria.com was founded in 2016 aiming to provide the best
						e-commerce experience possible. We are into clothing for women. We
						strive to improve customer experience by,</p>
					<br />
					<ul>
						<li>Free Try and Buy</li>
						<li>Easy returns</li>
						<li>Free shipping on all orders</li>
						<!-- <li>Video catalogue of the actual product, so that customer
							will not be deceived</li> -->
						<li>Handpicked export quality products</li>
						<li>Hassle free refunds</li>
						<li>Quick responses</li>
						<li>Brand products at lowest price possible</li>
					</ul>
					<p>&nbsp;</p>
					<p>We try to save your bucks in every possible way.</p>
					<br />
					<ul style="list-style: none">
						<li><i class="fa fa-bolt"></i> We buy direct from
							manufacturers whenever possible, we bargain hard to get the best
							price, and then pass the savings on to you.</li>
						<li><i class="fa fa-bolt"></i> We buy in volume to get the
							best prices.</li>
						<li><i class="fa fa-bolt"></i> We keep our margins & costs
							low - because every Rupee we save is a Rupee you save</li>
					</ul>

				</div>
				<p>
				<p>&nbsp;</p>
				<div id="contact_us">
					<p>&nbsp;</p>
					<h4>Contact Us</h4>
					<p>&nbsp;</p>
					<p id="contact">
						We are ready to help you any time. Please email us 24/7 at <strong>customer@fallsbuy.com
						</strong>or reach us on phone at <strong>+91 9901411006 or +91
							8553797479</strong>
					</p>
					<p>&nbsp;</p>
				</div>
				<p>&nbsp;</p>
				<div id="privacy_policy">
					<h4>Privacy Policy & Age Restriction</h4>
					<p>&nbsp;</p>
					<p>Fallsbuy does not direct its websites to children under the
						age of eighteen. We require registered users of the site to be at
						least eighteen years old. If we learn that a user is under
						eighteen years of age, we will promptly delete any personal
						information that the individual has provided to us.</p>
					<p>
						For our detailed privacy policy read on <a href="privacypolicy">Our
							Privacy Policy</a>
					</p>
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
	<script src="/resources/assets/common/js/jquery.easy-autocomplete.js"></script>
	<script>
		var options = {

			url : function(phrase) {
				/* if (phrase.length >= 3) { */
				return "/search/getTerms?query=" + phrase;
				//}
			},

			getValue : function(element) {
				return element.name;
			},

			preparePostData : function(data) {
				data.phrase = $("#example-ajax-post").val();
				return data;
			},

			list : {
				onClickEvent : function() {
					var id = $("#example-ajax-post").getSelectedItemData().id;
					var type = $("#example-ajax-post").getSelectedItemData().type;
					var pid = $("#example-ajax-post").getSelectedItemData().pid;

					//As of now only Products and Categories
					var url = 'products?cat=' + id;
					if (type === 'Product') {
						url = 'product?id=' + pid;
					} else if (type === 'NORESULT') {
						$("#example-ajax-post").val('');
						return;
					}

					console.log("type " + type + " url " + url);

					window.location = url;
				},
				onHideListEvent : function() {
					$("#inputTwo").val("").trigger("change");
				}
			},

			/* template : {
					type : "description",
					fields : {
						description : "realName"
					}
				}, */
			ajaxSettings : {
				dataType : "json",
				method : "POST",
				data : {
					dataType : "json"
				}
			},

			preparePostData : function(data) {
				data.phrase = $("#example-ajax-post").val();
				return data;
			},
			theme : "square",

			// set it so when user types fast the
			requestDelay : 500
		};

		$("#example-ajax-post").easyAutocomplete(options);
	</script>

</body>
</html>
