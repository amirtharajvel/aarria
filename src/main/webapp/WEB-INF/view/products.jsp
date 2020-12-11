<%@page import="org.apache.commons.lang3.StringUtils"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ page
	import="java.util.*, com.aarria.retail.web.dto.response.*, org.apache.commons.lang3.ArrayUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta charset="utf-8">
<title>aarria.com - Women's Wear</title>

<meta name="description" content="aarria.com: Women's wear">
<meta name="keywords"
	content="aarria.com, Online Shopping, online shopping India, sarees for women latest design, latest design, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="aarria">

<link rel="shortcut icon" type="image/png"
	href="/resources/assets/images/only-f.png" />

<link href="/resources/assets/products/css/products.css"
	rel="stylesheet">

<style>
.checkbox input[type="checkbox"], .checkbox-inline input[type="checkbox"],
	.radio input[type="radio"], .radio-inline input[type="radio"] {
	position: inherit;
}
</style>

<style>
.easy-autocomplete {
	width: 100% !important;
}
</style>

</head>
<body>
	<header class="header-div">
		<jsp:include page="common/header.jsp" />
	</header>

	<div class="container container-div products-div">

		<!-- <div style="margin-top: 2%;">
			<div style="float: left;">
				<a href="/checkout?target=FALLS200"> <img
					src="/resources/assets/images/200-50-products.jpg" />
				</a>
			</div>
			<div style="margin-left: 10%; float: right;">
				<a href="/checkout?target=BIGTREAT"> <img
					src="/resources/assets/images/coupon-code.gif" />
				</a>
			</div>
		</div> -->

		<div class="row" style="background-color: white; color: black;">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div
					style="margin: 0 auto; width: 100%; padding-left: 25%; color: white;">
					<div>

						<span class="blink" style="color: #303A3D; font-size: 16px;">
							<!-- <!-- <span
							style="color: #323a3d; font-size: 22px; font-weight: bolder;">Why
								fallsbuy.com? </span> <br /> <br /> --> <!-- <span
							class="glyphicon glyphicon-heart"></span> &nbsp; <span
							style="color: #5cb85c; font-weight: bold;">Weekends : </span>
							FREE same day delivery <br /> <span
							class="glyphicon glyphicon-heart"></span> &nbsp; <span
							style="color: #5cb85c; font-weight: bold;">Weekends : </span>
							FREE Try and Buy <br /> <span class="glyphicon glyphicon-heart"></span>
							&nbsp; <span style="color: #5cb85c; font-weight: bold;">Weekends
								: </span> FREE shipping and cash on delivery - No minimum order<br />
							<br /> <span style="font-size: 18px; color: #5cb85c;"> -->



						</span> <br />
						<!-- <button class="btn btn-success" type="button"
							style="margin-top: 1%; margin-left: 25%; brackground-color: #9400D3;"
							onclick="window.location='/products/cat/4002?page=1&sort=Price: Low To High'">SHOP
							NOW</button> -->
					</div>
				</div>
			</div>
		</div>

		<div class="row breadcrumb_row">

			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb" id="breadcrumb_category">
					<li><a href="/">Home</a></li>
					<c:forEach begin="0"
						end="${fn:length(childCategories.breadcrumps)}" var="breadcrumb"
						items="${childCategories.breadcrumps}">
						<li><a href="/products/cat/${breadcrumb.id}?page=1">${breadcrumb.name}</a></li>
					</c:forEach>
				</ol>
			</div>
		</div>
		<div class="row">

			<div class="col-xs-12 col-sm-4 col-md-3">
				<div class="filter-div">
					<div class="row margin-0">
						<div class="col-xs-6 col-sm-6 col-md-6 padding-0">
							<h3>Filters</h3>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 padding-0 text-right">
							<a
								href="/products/cat/${categoryId}?page=1&sort=Price: Low To High"
								id="clear_all_anchor">Clear All</a>
						</div>
					</div>

					<!-- start accordion -->
					<div class="panel-group" id="accordion">
						<div class="panel panel-default template">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse"
										href="#collapseThree"> Categories </a>
								</h4>
							</div>

							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body">

									<c:forEach items="${childCategories.children}" var="category">
										<div style="margin-left: 5%;">
											<a
												href="/products/cat/${category.id}?page=1&sort=Price:%20Low%20To%20High">
												${category.name } </a>
										</div>
									</c:forEach>

									<c:if test="${fn:length(childCategories.children) >= 0}">
										<c:forEach items="${parentCategories.children}" var="cat">

											<div
												style="margin-left: 5%;<c:if test='${cat.id == categoryId}'>
												font-weight:bold;
											</c:if>">
												<a
													href="/products/cat/${cat.id}?page=1&sort=Price:%20Low%20To%20High">
													${cat.name} </a>
											</div>
										</c:forEach>
									</c:if>
								</div>

								<br />
							</div>
						</div>

						<%
							boolean isPriceEntered = false;
							if (request.getQueryString() != null && request.getQueryString().contains("minPrice")) {
								isPriceEntered = true;
							}
							String priceString = request.getQueryString().replaceAll("[&?]minPrice=([^&]$|[^&]*)", "");
							priceString = priceString.replaceAll("[&?]maxPrice=([^&]$|[^&]*)", "").replaceAll("page=([^&]$|[^&]*)",
									"");;
						%>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse"
										href="#collapsePrice"> Price </a>
								</h4>
							</div>

							<div id="collapsePrice"
								class="panel-collapse collapse <%if (isPriceEntered == true) {
				out.println("in");
			}%>">
								<div class="panel-body">

									<input placeholder="&#x20B9;Min" value="${refiners.minPrice }"
										size="6" maxlength="6" id="price_min" type="text" /> <input
										value="${refiners.maxPrice }" type="text"
										placeholder="&#x20B9;Max" maxlength="6" size="6"
										id="price_max" /> <input type="button" class="btn" value="Go"
										onclick="sortByPrice('<%=priceString%>');" />

								</div>
								<br />
							</div>
						</div>

						<%
							String parameters = request.getParameter("attributes");

							String sort = request.getParameter("sort");

							String pageInUrl = request.getParameter("page");

							if (sort == null || sort.trim().equals("")) {
								sort = "Price: Low To High";
							}

							String[] arrayOfAttributeIds = null;
							if (parameters != null) {
								arrayOfAttributeIds = parameters.split(",");
							} else {
								parameters = "";
							}

							parameters = parameters.trim();

							out.println("<input type='hidden' id='request_param' value='" + parameters + "'/>");

							FilterDto refinersDto = (FilterDto) request.getAttribute("refiners");
							if (refinersDto != null) {

								int size = refinersDto.getRefiners() == null ? 0 : refinersDto.getRefiners().size();

								for (int i = 0; i < size; i++) {
									RefinerDto refiner = refinersDto.getRefiners().get(i);

									boolean isAnyCheckboxChecked = false;

									for (AttributeDto att : refiner.getUniqueAttributes()) {
										if (ArrayUtils.contains(arrayOfAttributeIds, att.getId() + "")) {
											isAnyCheckboxChecked = true;
										}
									}
						%>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse"
										href="#collapse<%=i%>"> <%=refiner.getName()%>
									</a>
								</h4>
							</div>

							<div id="collapse<%=i%>"
								class="panel-collapse collapse <%if (isAnyCheckboxChecked == true) {
						out.println("in");
					}%>">
								<div class="panel-body">

									<%
										List<AttributeDto> uniqueAttributes = refiner.getUniqueAttributes();
												if (uniqueAttributes != null) {
													for (int j = 0; j < uniqueAttributes.size(); j++) {

														AttributeDto attrib = uniqueAttributes.get(j);
														Long id = attrib.getId();

														String ids = "";

														if (parameters == null || parameters.trim().equals("")) {
															ids = id + "";
														} else {
															ids = parameters + "," + id + "";
														}

														boolean contains = false;
														if (ArrayUtils.contains(arrayOfAttributeIds, id + "")) {
															contains = true;
															ids = parameters.replace(id + "", "");
														} else {

														}

														if (ids.indexOf(",", ids.length() - 1) >= 0) {
															StringBuilder inter = new StringBuilder(ids);
															inter.setCharAt(ids.length() - 1, ' ');
															ids = inter.toString().trim();
														}

														if (ids.indexOf(",", 0) == 0) {
															StringBuilder inter1 = new StringBuilder(ids);
															inter1.setCharAt(0, ' ');
															ids = inter1.toString().trim();
														}

														if (ids.contains(",,")) {
															ids = ids.replace(",,", ",");
														}
									%>
									<div class="checkbox">

										<%
											String queryStringAttrib = request.getQueryString()
																	.replaceAll("[&?]attributes=([^&]$|[^&]*)", "");
															String queryCheckPage = queryStringAttrib.replaceAll("page=([^&]$|[^&]*)", "");

															if (queryCheckPage != null && !queryCheckPage.startsWith("&")) {
																queryCheckPage = "&" + queryCheckPage;
															}

															String bgColor = "white";
															String color = "#323a3d";
															if (refiner != null && refiner.getName() != null && refiner.getName().equals("Color")) {
																bgColor = attrib.getValue();
																color = attrib.getValue();
															}
										%>

										<input autocomplete="off"
											onchange="window.location.href='/products/cat/${categoryId}?page=1<%=queryCheckPage %>&attributes=<%=ids%>'"
											name="refiner_checkboxes" type="checkbox"
											value="<%=attrib.getId()%>" id="<%=attrib.getId()%>"
											<%if (contains == true) {%> checked="checked" <%}%>>
										<label for="<%=attrib.getId()%>"
											style="width:50%; background-color: <%=bgColor%>;"><a
											style="color:<%=color%>;"
											href="/products/cat/${categoryId}?page=1&attributes=<%=ids%>">
												<%=attrib.getValue()%></label></a>
									</div>
									<%
										}
												}
									%>


								</div>




								<br />
							</div>
						</div>

						<%
							}
							}
						%>


					</div>
					<!-- end accordion -->

				</div>

				<%
					String excludeSoldOut = request.getParameter("excludeSoldOut");
					boolean excludeSoldOutChecked = false;
					if (excludeSoldOut == null || excludeSoldOut.equals("0")) {
						excludeSoldOut = "1";
					} else if (excludeSoldOut.equals("1")) {
						excludeSoldOutChecked = true;
						excludeSoldOut = "0";
					}
					String queryStringSoldOut = request.getQueryString().replaceAll("[&?]excludeSoldOut=([^&]$|[^&]*)", "");
				%>

				<div style="margin-top: 3%;"></div>
				<div style="margin-left: 2%;">
					<input id="exclude_sold_out"
						onchange="window.location='/products/cat/${categoryId}?<%=queryStringSoldOut%>&excludeSoldOut=<%=excludeSoldOut%>'"
						type="checkbox"
						<% if(excludeSoldOutChecked == true) { out.println("checked='checked'"); }%>>&nbsp;
					Exclude Sold Out
				</div>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="row pro-head">
					<div class="col-xs-12 col-sm-4 col-md-4 padding-0">
						<div>
							<span id="category_title"></span>(<span id="product_count">${totalResultCount }<c:if
									test="${empty totalResultCount}">0</c:if></span>) &nbsp;<img
								src="/resources/assets/images/loading.gif" id="loading" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-8 col-md-8 text-right padding-0">

						<%
							String queryString = request.getQueryString().replaceAll("[&?]sort=([^&]$|[^&]*)", "");

							String queryStringPage = queryString.replaceAll("page=([^&]$|[^&]*)", "");
						%>

						<h5>
							Sort By:<a
								href="/products/cat/${categoryId}?page=1&sort=What's New<%=queryStringPage %>"
								id="popular"
								<%if (sort.equals("What's New")) {
				out.println("class='active'");
			}%>>Popular</a><a
								href="/products/cat/${categoryId}?page=1&sort=Price: Low To High<%=queryStringPage %>"
								id="low_to_high"
								<%if (sort.equals("Price: Low To High")) {
				out.println("class='active'");
			}%>>Low
								to High</a><a
								<%if (sort.equals("Price: High To Low")) {
				out.println("class='active'");
			}%>
								href="/products/cat/${categoryId}?page=1&sort=Price: High To Low<%=queryStringPage %>"
								id="high_to_low">High To Low</a>
						</h5>
					</div>
				</div>
				<div class="pro-list">
					<div class="row" id="products_container">

						<c:if test="${fn:length(products) <= 0 }">
							<h4>Sorry.. we can't find the products. We're working on
								refilling our store. Please checkback later.</h4>
						</c:if>


						<c:forEach items="${products}" var="product">
							<div class="col-xs-12 col-sm-6 col-md-4 padding-left0">
								<div class="pro-box">
									<div class="pro-img">
										<a target="_blank" href="/product?id=${product.pid }"> <c:if
												test="${product.isAtleastOneSizeHasStock == true}">
												<img class="img-responsive" src="${product.listingImage }"
													alt="${product.name}">
											</c:if> <c:if test="${product.isAtleastOneSizeHasStock == false}">

												<div class="img__wrapper">
													<img class="img-responsive" src="${product.listingImage }"
														alt="${product.name}"> <a class="sold_out"
														target="_blank" href="/product?id=${product.pid }">Sold
														out</a>
												</div>

											</c:if>
										</a>
									</div>
									<div class="pro-bottom">
										<div class="row margin-0">
											<div style="width: 100%;"
												class="col-xs-7 col-sm-8 col-md-6 padding-0">
												<div class="price_info">
													<span class="price">&#8377; ${product.price } </span>

													<del>&#8377;${product.actualPrice}</del>
													<%
														if (session.getAttribute("isAdmin") != null) {
																Boolean admin = (Boolean) session.getAttribute("isAdmin");
																if (admin == true) {
													%>
													<%-- <button onclick="showUpdatePriceModal(${product.price });">Edit</button> --%>
													<%
														}
															}
													%>
													<%-- <span
														class="actual_price">&#8377;${product.actualPrice}
													</span>&nbsp;&nbsp;  --%>
													<%-- <span class="offer_price">(${product.discount}%OFF)</span> --%>
												</div>
											</div>
											<div style="width: 100%; text-align: left; color: grey;"
												class="col-xs-5 col-sm-6 col-md-6 padding-0 text-right">
												${product.shortName}</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${categoryId == 4}">
			<div class="row"
				style="text-align: right; margin-top: 4%; padding-right: 2%;">
		</c:if>
		<c:if test="${categoryId != 4}">
			<div class="row" style="text-align: center; margin-top: 4%;">
		</c:if>
		<%
			String urlString = request.getQueryString().replaceAll("page=([^&]$|[^&]*)", "");
		%>

		<div>
			<c:forEach var="page" begin="1" end="${totalPageCount}">
				<c:if test="${currentPage == page }">
					<input type="button" value="${page}" class="btn"
						style="background-color: #323A3D; color: white;"
						onclick="gotoPage('<%=urlString%>', ${page});" />

				</c:if>
				<c:if test="${currentPage != page }">
					<input class="btn" type="button" value="${page }"
						style="background-color: #EDEFF1; color: #323A3D;"
						onclick="gotoPage('<%=urlString%>', ${page});" />
				</c:if>
			</c:forEach>

		</div>

	</div>

	</div>
	</div>
	<br>
	<br>
	<footer class="footer-div">
		<jsp:include page="common/footer.jsp" />
	</footer>
	<!-- <div id="treeview10"></div> -->

	<script src="/resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="/resources/assets/common/js/bootstrap.min.js"></script>
	<script src="/resources/assets/products/js/bootstrap-treeview.js"></script>
	<script src="/resources/assets/products/js/jquery.raty.js"></script>
	<script src="/resources/assets/common/js/cart.js"></script>
	<script src="/resources/assets/common/js/jquery.easy-autocomplete.js"></script>

	<script>
		$(window).load(function() {
			$("#onchange_refiner_checkboxes").val(0);
			
			var pathname = window.location.pathname;
			if(pathname.endsWith("4")) {
				//$("#offerModal").modal('show');
				/* var offerPopup = localStorage.getItem('offerPopupShowed');

				if(offerPopup != 'true') {
					
					localStorage.setItem('offerPopupShowed','true');
				}				
 */			}

		});
		
	</script>
	<script>
		$(document).ready(function() {
			$(".eac-square").css("width", "100%");
		});
	</script>

	<script>
		function gotoPage(url, page) {
			
			if(!url.startsWith("&")) {
				url = "&" + url;
			}
			
			window.location = ${categoryId} + "?" + "page="+page + url;
		}
		
		function sortByPrice(str) {
			window.location = ${categoryId} + "?page=1&" + str + "&minPrice=" + $("#price_min").val() + "&maxPrice="+$("#price_max").val();
		}
	</script>
	<script>
		window.addEventListener('popstate', function(event) {

			//history.replaceState({}, 'Title: Google', 'http://www.google.com/');
			//history.replaceState(null, document.title, location.pathname);
			//replaces first element of last element of stack with google.com/gmail so can be used further
			setTimeout(function() {
				//location.replace($("#previous_page").val());
			}, 0);
		}, false);
	</script>
	<script>
	function updatePrice() {
		$.ajax({
			url : "/product/updatePrice",
			method : 'POST',
			contentType : "application/json; charset=utf-8",
			data : getUpdatePriceInput(),
			beforeSend : function() {
				$('#loading').show();
			},
			complete : function() {
				$('#loading').hide();
			},
			success : function(data) {
				renderProducts(data, append);
			}
		});
	}
	
	function getUpdatePriceInput() {
		var input = {
				newPrice : numericCategoryIds,
				productId : numericAttributeIds,
				originalPrice : originalPrice
			};
		
		return input;
	}
	
	function showUpdatePriceModal(currentPrice) {
		$("#updatePriceModal").modal('show');
		$("#current_price_in_edit").html(currentPrice);
	}
	
	function closeConfirm() {
		$("#updatePriceModal").modal('hide');
	}
	</script>

	<!-- hidden fields -->
	<input type="hidden" id="current_cat_id" value='${categoryId}' />
	<input type="hidden" id="child_category_ids" value="" />
	<input type="hidden" id="attribute_ids" value="-1" />
	<input type="hidden" id="products_size" value="0" />
	<a href="#0" class="cd-top">Top</a>
	<input type="hidden" id="onchange_refiner_checkboxes" />
	<input type="hidden" id="previous_page" />
	<input type="hidden" id="previous_href" />

	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true"
		id="updatePriceModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Update Price</h4>
				</div>
				<div class="modal-body">

					Current Price &nbsp; : &nbsp;<label id="current_price_in_edit"></label>
					<br /> <br />New Price <input type="text" value="" /> <br /> <br />Actual
					Price <input type="text" value="" />

				</div>
				<div class="modal-footer">

					<button type="button" onclick="closeConfirm()"
						id="cancel_order_button" class="btn btn-success">Close</button>
					<button type="submit" onclick="placeOrder()"
						id="confirm_place_order_button"
						class="btn btn-success update_new_price">Update</button>

				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true" id="offerModal">
		<div class="modal-dialog modal-sm" style="text-align: center;">
			<div class="modal-content" style="display: inline-block;">
				<div class="modal-header"
					style="background-color: #e83e8c; color: white; display: none;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" style="color: white;" id="myModalLabel">Update
						Price</h4>
				</div>
				<div class="modal-body" style="padding: 0px;">
					<a href="/"><img src="/../resources/assets/images/offer.jpg"></a>
				</div>
				<div class="modal-footer" style="display: none;"></div>
			</div>
		</div>
	</div>
	
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
