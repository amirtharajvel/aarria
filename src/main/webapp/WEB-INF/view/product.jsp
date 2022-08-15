<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${product.name}&nbsp;at&nbsp;aarria.com</title>

<meta name="description"
	content="aarria.com Online Shopping India - Women's wear">
<meta name="keywords"
	content="aarria.com, Online Shopping, online shopping India, sarees for women latest design, latest design, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, good quality products, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="aarria">

<link rel="shortcut icon" type="image/png"
	href="/resources/assets/images/only-f.png" />

<link href="/resources/assets/product/css/product.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="/resources/assets/product/css/xzoom.css" media="all" />
<link type="text/css" rel="stylesheet" media="all"
	href="/resources/assets/product/css/magnific-popup.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="/resources/assets/product/css/lightslider.min.css" />

<link href="/resources/assets/product/css/jquery.raty.css"
	rel="stylesheet">

<style>
div#preload {
	display: none;
}

.easy-autocomplete {
	width: 100% !important;
}
</style>

<style>
ul {
	list-style: none outside none;
	padding-left: 0;
	margin: 0;
}

.content-slider li {
	text-align: center;
}

.content-slider h3 {
	margin: 0;
	font-size: 14px;
	padding-top: 10px;
	font-weight: normal;
	color: #333;
}

.content-slider h4 {
	color: #B12704 !important;
	font-size: 18px;
	padding-top: 3px;
	font-weight: normal;
	font-family:
}

.demo {
	width: 95%;
	margin: auto;
	background-color: white;
}

.lSPager {
	display: none;
}

.content-slider img {
	width: 100%;
	height: 150px;
}

img:hover {
	cursor: pointer;
}

.clone left h3 a {
    font-color: #007185;
}

</style>


</head>
<body>
	<header class="header-div">
		<jsp:include page="common/header.jsp" />
	</header>
	<div class="container-fluid container-div product-div">
		<div class="row breadcrumb_row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb" id="breadcrumb_category">
					<li><a href="/">Home</a></li>
					<c:forEach begin="0" end="${product.breadcrumbsSize}"
						var="breadcrumb" items="${product.breadcrumbs}">
						<li><a href="/products/cat/${breadcrumb.id}?page=1">${breadcrumb.name}</a></li>
					</c:forEach>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-9">

				<section id="magnific">
					<div class="xzoom-container">
						<div class="row">
							<div class="col-xs-12 col-sm-8 col-md-10 col-xs-push-2">
								<img class="xzoom5" id="xzoom-magnific"
									src="${product.colorImages[0].preview}"
									xoriginal="${product.colorImages[0].original}" />
							</div>
							<div class="col-xs-12 col-sm-4 col-md-2 col-xs-pull-10">
								<div class="xzoom-thumbs" style="border: 0px solid #ccc;">
									<c:if
										test="${product.colorImages != null && fn:length(product.colorImages) > 0 }">
										<c:forEach items="${product.colorImages}" var="img">
											<a href="${img.original}"><img class="xzoom-gallery5"
												height="80" src="${img.small}" xpreview="${img.preview}"
												title="The description goes here"></a>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</section>
				<br> <br>

			</div>

			<!-- second half -->
			<div class="col-xs-12 col-sm-5 col-md-3 details_container">
				<span id="product_title">${product.name}</span>
				<div id="price_container">
					<span id="price_value"><i class="fa fa-inr"></i>${product.price}</span>
					<span
						style="color: #111; font-weight: 700 !important; 13px!important">FREE 1 Day
						Delivery</span>&nbsp;<span style="color: #555">M.R.P:</span> <span
						id="actual_price"><i class="fa fa-inr"></i>${product.actualPrice}</span>&nbsp;&nbsp;
					<br />
					<c:if test="${product.discount != null }">
						You save <span style="color: #B12D24; font-size: 14px;"><i
							class="fa fa-inr"></i>${product.youSaveAmount}</span>
						<span id="discount">(${product.discount}%)</span>
						<c:if test="${product.isAtleastOneSizeHasStock eq true}">
						    <span style="font-size: 18px; color: #007600!important; "> In stock. </span>
						</c:if>

						<c:if test="${product.isAtleastOneSizeHasStock eq false}">
                        						    <span style="font-size: 18px; color: #B12D24!important; "> Out of stock. </span>
                        						</c:if>
					</c:if>
				</div>
				<input type="hidden" id="sizes_length"
					value="${fn:length(product.productStock)}"> <input
					type="hidden" id="is_nosize_product"
					value="${ product.productStock['NA'] != null}" />

				<c:if test="${product.offerPrice != null and product.offerPrice != ''}">
					<div id="offer_container">
						<span class="label label-warning">Offer Price</span> <i class="fa fa-inr"></i>${product.offerPrice}
						<c:if test="${product.offerCode != null and product.offerCode != ''}">
						<span style="color: #b19975;font-size:12px;">Use code: ${product.offerCode}</span>
						</c:if>
					</div>
				</c:if>


				<c:if test="${product.deliveryCharge != null}">
					<div id="delivery_charge_container">
						FREE Try And Buy

					</div>
					<div id="return_container">
                    						Easy Returns

                    					</div>
				</c:if>

				<c:if
					test="${fn:length(product.productStock) > 0 and product.productStock['NA'] == null}">

					<div id="size_container">
						<label for="size" class="control-label input-group">Size</label>
						<div class="btn-group" id="size_selector" data-toggle="buttons">
							<c:forEach var="entry" items="${product.productStock}">
								<label class="btn btn-default"
									onclick="onSizeSelect('${entry.key}',${entry.value})"
									<c:if test="${entry.value == 0}"> style="color:gray;" disabled="disabled"</c:if>>
									<input name="size" value="${entry.key}" type="radio">${entry.key}
								</label>

							</c:forEach>
						</div>
						<c:forEach var="entry" items="${product.productStock}">
							<input type="hidden" value="${entry.value}"
								id="${entry.key}-stock" />
							<input type="hidden" value="" id="${entry.key}-quantity-text" />
						</c:forEach>
						<a href="#" id="pop_a" data-toggle="popover"
							data-content="Please select size"> &nbsp;</a>
						<!--  <a
							href="sizechart#men_casual_top" target="_blank"
							id="sizechart_link">Size Chart</a> -->
						<span id="stock_left"></span>
					</div>
				</c:if>
				<div id="quantity_container">
					<table>
						<tr>
							<td><input type="hidden" value="1" id="quantity_text">
								<input type="button" class="btn btn-default" value="-"
								onclick="decreaseQuantityInProductPage();" /></td>
							<td>&nbsp;</td>
							<td><label id="quantity_wanted" for="quantity"
								class="control-label input-group">1</label></td>
							<td>&nbsp;</td>
							<td><input type="button" class="btn btn-default" value="+"
								onclick="increaseQuantityInProductPage();" /></td>
							<c:if test="${product.productStock['NA'] != null }">
								<td>&nbsp;&nbsp;<span id="stock_left">Only
										${product.productStock['NA']} left </span><input type="hidden"
									value="${product.productStock['NA']}" id="NIL-stock" /></td>

							</c:if>
						</tr>
					</table>
				</div>
				<div id="add_to_cart">
					<button
						onclick="addToCartFromProductPage('${product.pid}',${product.price},'${product.name}','${product.img}','ADDTOCART')"
						type="button" id="add_to_cart_button"
						class="btn btn-success add_to_cart"
						<c:if test="${product.isAtleastOneSizeHasStock == false }">disabled="disabled"</c:if>>ADD
						TO CART</button>
				</div>
				<div id="accordion" class="panel-group">


                    <div class="panel panel-default">
                    						<div class="panel-heading">
                    							<h4 class="panel-title">
                    								<a data-toggle="collapse" data-parent="#accordion"
                    									href="#collapseOffers">Offers</a>
                    							</h4>
                    						</div>
                    						<div id="collapseOffers" class="panel-collapse collapse">
                    							<div class="panel-body">

                    								<table class="table table-sm">
                                                    Get Additional <span style="font-size: 21px;color: #449d44"><strong> FLAT &#8377;50 OFF </strong> </span>- Use code <strong>FALLS200 </strong><hr/>Buy Any 2
                                                    						Get <span style="font-size: 21px; color: #449d44"><strong> FLAT &#8377;200 OFF </strong> </span> - Use code <strong>FALLS200 </strong><hr/> Shop &#8377;2000
                                                    						Get  <span style="font-size: 21px; color: #449d44"><strong>FLAT &#8377;500 OFF </strong> </span> - Use code <strong>BIGTREAT</strong>


                    								</table>
                    							</div>
                    						</div>
                    					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne">Delivery</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">

								<div id="wrapper">
									<li class="input-button">
										<div class="input-wrapper" style="float: left;">
											<!-- <input type="text" class="form-control"
												id="delivery_pincode_text" placeholder="Pincode"
												pattern=".{6,6}" maxlength="6"
												oninput="this.value=this.value.replace(/[^0-9]/g,'');"
												title="Indian pincode is exactly 6 digits"
												required="required" />
											<div id="loader" style="display: none;">
												<img src="loader.gif" />
											</div> -->
										</div>
										<div style="float: left; margin-left: 10px; color: #111">
											<!-- <button type="button" class="btn btn-success" value="search"
												onclick="pincodeCheck()">Check</button> -->
											FREE 1 day Delivery in Bengaluru
										</div>
									</li>
								</div>

								<div style="float: left;">
									<div id="pincode_container" class="well">
										<div id="pincode_status_div"></div>
										<div>
											<span style="font-size: 80%;" class="label label-success"
												id="expected_delivery_date_div"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

                    <div class="panel panel-default">
                    						<div class="panel-heading">
                    							<h4 class="panel-title">
                    								<a data-toggle="collapse" data-parent="#accordion"
                    									href="#collapseThree">Shipping and Returns</a>
                    							</h4>
                    						</div>
                    						<div id="collapseThree" class="panel-collapse collapse">
                    							<div class="panel-body">
                    								<table class="table">
                    									<c:if
                    										test="${fn:length(product.shippingAndReturnsDescriptions) > 0}">
                    										<c:forEach var="attribute"
                    											items="${product.shippingAndReturnsDescriptions}">
                    											<tr>
                    												<td>${attribute.key }</td>
                    												<td><div class="description_value">${attribute.value }</div></td>
                    											</tr>
                    										</c:forEach>
                    									</c:if>
                    								</table>
                    							</div>
                    						</div>
                    					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo">Know Your Wear</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<%-- <div id="rating" data-score='${product.rating}'></div> --%>

								<table class="table table-sm description_table">
									<c:if test="${fn:length(product.descriptions) > 0}">
										<c:forEach var="attribute" items="${product.descriptions}">
											<tr>
												<td>${attribute.key }</td>
												<td><div class="description_value">${attribute.value }</div></td>
											</tr>
										</c:forEach>
									</c:if>

									<c:if test="${fn:length(product.searchableAttributes) > 0}">
										<c:forEach var="sattribute"
											items="${product.searchableAttributes}">
											<tr>
												<td>${sattribute.key }</td>
												<td><div class="description_value">${sattribute.value }</div></td>
											</tr>
										</c:forEach>
									</c:if>

								</table>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseReview">Reviews</a>
							</h4>
						</div>
						<div id="collapseReview" class="panel-collapse collapse">
							<div class="panel-body">
								<div id="write_review_container">
									<a style="color: #2E86C1"
										href="/writereview?pid=${product.pid}">Write Review</a>
								</div>
								<div id="rating" data-score='${product.rating}'>Overall
									Rating &nbsp;&nbsp;</div>

								<c:if test="${fn:length(product.reviews) > 0}">
									<c:forEach var="review" items="${product.reviews}">
										<div class="individual_review_container">
											<div class="individual_review" data-score="${review.score }"></div>
											<strong>${review.name}:</strong> ${review.review }

										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
	</div>

	<div>

		<div class="demo">
			<div>
				<h3>Suggested Products</h3>
				<br />
			</div>
			<div class="item">
				<ul id="content-slider" class="content-slider">
					<c:forEach items="${product.suggestions }" var="suggested">

						<li><a target="_blank" href="/product?id=${suggested.pid }"><img
								style="width: 120px;" src="${suggested.image }" alt="" /></a>
							<h3>
								<a target="_blank" href="/product?id=${suggested.pid }">${suggested.name }</a>
							</h3>
							<h4>&#8377;${suggested.price }</h4></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div style="margin-top: 10%;"></div>
	<footer class="footer-div">
		<jsp:include page="common/footer.jsp" />
	</footer>
	<script src="/resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="/resources/assets/common/js/bootstrap.min.js"></script>
	<!-- 
	<script src="/resources/assets/product/js/product.js"></script>
	<script src="/resources/assets/common/js/cart.js"></script>-->


	<script>
		$(document).ready(function() {
			$(".eac-square").css("width", "100%");
		});
	</script>
	<script type="text/javascript"
		src="/resources/assets/product/js/xzoom.min.js"></script>
	<script type="text/javascript"
		src="/resources/assets/product/js/magnific-popup.js"></script>
	<script src="/resources/assets/product/js/setup.js"></script>
	<script src="/resources/assets/product/js/lightslider.min.js"></script>
	<script src="/resources/assets/products/js/jquery.raty.js"></script>
	<script type="text/javascript">
function downloadJSAtOnload() {
var element = document.createElement("script");
element.src = "/resources/assets/product/js/product.js";
document.body.appendChild(element);
}
if (window.addEventListener)
window.addEventListener("load", downloadJSAtOnload, false);
else if (window.attachEvent)
window.attachEvent("onload", downloadJSAtOnload);
else window.onload = downloadJSAtOnload;
</script>

	<!-- <script src="/resources/assets/product/js/product.js"></script> -->
	<script src="/resources/assets/common/js/cart.js"></script>

	<script>    	
      $(document).ready(function() {
        $('#content-slider').lightSlider({
            item:8,
            loop:true,
            speed:600,
            responsive : [
                {
                   breakpoint:800,
                    settings: {
                        item:3,
                        slideMove:1,
                        slideMargin:6,
                      }
                },
                {
                    breakpoint:480,
                    settings: {
                        item:2,
                        slideMove:1
                      }
                }
            ]
        });  
      });
      
    </script>

	<!-- <script src="/resources/assets/common/js/jquery.easy-autocomplete.js"></script> -->

	<script>
		$(document).ready(function() {
			$(".eac-square").css("width", "100%");
			
		});
		
		$('#rating').raty({
			  readOnly:  true,
			  score: function () {
		            return $(this).data('score');
		        }
		});

	</script>

	<input type="hidden" id="selected_size" value="NIL" />
	<input type="hidden" id="selected_color" value="NIL" />
	<input type="hidden" id="already_cart_attempted" />
	<input type="hidden" id="category_id" value="" />

	<div id="preload">
		<c:if
			test="${product.colorImages != null && fn:length(product.colorImages) > 0 }">
			<c:forEach items="${product.colorImages}" var="img">
				<img src="${img.original}" />
			</c:forEach>
		</c:if>
	</div>

	<script type="text/javascript">
function downloadJSAtOnload() {
var element = document.createElement("script");
element.src = "/resources/assets/common/js/jquery.easy-autocomplete.js";
document.body.appendChild(element);
}
if (window.addEventListener)
window.addEventListener("load", downloadJSAtOnload, false);
else if (window.attachEvent)
window.attachEvent("onload", downloadJSAtOnload);
else window.onload = downloadJSAtOnload;
</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-114298247-2"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-114298247-2');
</script>

</body>
</html>
