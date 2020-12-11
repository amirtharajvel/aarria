<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Add Products - Online Shopping: Shop Online for Clothing
	for Women, Men and Kids. Buy hand picked Export Quality Sarees, Kurtis,
	Choli's, Anarkali, Tops, Lahankas, Party Wear, Kids Wear at low price.
	Fallsbuy.com</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Fallsbuy Ecommerce Private Limited">
<meta name="author" content="Fallsbuy">

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/common/style/style.css" rel="stylesheet">
<link href="resources/assets/common/style/font-awesome.css"
	rel="stylesheet">
<link href="resources/assets/common/style/owl.carousel.css"
	rel="stylesheet">
<link href="resources/assets/addproducts/style/addproducts.css"
	rel="stylesheet">
<link href="resources/assets/addproducts/style/sol.css" rel="stylesheet">
</head>
<body>
	<header class="header-div">
		<jsp:include page="common/header.jsp" />
	</header>

	<div class="container-fluid container-div">

		<div class="container">

			<!-- add products starts -->
			<div class="jumbotron" id="body_container">
				<div class="row breadcrumb_row">
					<div class="col-lg-12">
						<ol class="breadcrumb" id="breadcrumb_category">
							<li><a href="home">Home</a></li>
						</ol>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<div class="well fbwell">
							<form:form id="addproducts_form" enctype="multipart/form-data"
								class="form-basic" modelAttribute="model" method="post"
								action="addProduct">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-title-row">
											<h3>Product Details</h3>
										</div>
										<div id="general_error">
											<form:errors class="error" />
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12"
										style="border: 1px solid #94d869; padding: 5px; color: #449652;">
										Price &nbsp; <input type="text" id="originalPrice" /> Weight
										&nbsp; <input type="text" id="weight" /> Profit &nbsp; <input
											type="text" id="profitToBeAdded" /> <input type="button"
											onclick="calculateprice()" value="Get Selling Price" /> <span
											id="calculate_price_result" style="color: #ff5b74;">
									</div>
								</div>
								<!-- <div class="col-lg-6"></div> -->


								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="name" />
										Product Name
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="name" type="text"
											id="name" tabindex="1" />
									</div>
								</div>

								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="originalPrice" />
										Buying Price
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="originalPrice"
											tabindex="2" type="number" step="0.01" name="originalPrice"
											title="buying price - NOT visible to the user" />

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="actualPrice" />
										Market Price
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="actualPrice"
											tabindex="3" type="number" step="0.01" name="actualPrice"
											title="Maximum Retail Price - VISIBLE to the user" />

									</div>
								</div>
								<%-- <div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="price" />
										Selling Price
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="price" tabindex="4"
											type="number" name="price" title="" step="0.01" />
									</div>
								</div> --%>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="profitToBeAdded" />
										Profit
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="profitToBeAdded"
											tabindex="4" type="number" name="profitToBeAdded" title=""
											step="0.01" />

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="catalogue" />
										Catalogue Name or No
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="catalogue" tabindex="4"
											type="text" name="catalogue" title="Catalogue name or number" />

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="igstRate" />
										GST Percentage
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="igstRate" tabindex="4"
											type="number" name="igstRate" title="" step="0.01" />

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="igstCode" />
										GST Code
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="igstCode" tabindex="4"
											type="text" name="igstCode" title="" step="0.01" />

									</div>
								</div>
								<%-- <div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="offerQuantity" />
										Offer Quantity
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="offerQuantity"
											tabindex="4" type="text" name="offerQuantity"
											title="Offer Quantity" />
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="offerPrice" />
										Offer Price
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="offerPrice"
											tabindex="4" type="number" name="offerPrice"
											title="Offer Price" />
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="offerCode" />
										Offer Code
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="offerCode" tabindex="4"
											type="text" name="offerCode" title="Offer code" />
										<table class="table table-sm">
											<tr>
												<th>Offer Code</th>
												<th>Offer Text</th>
												<th>Quantity</th>
											</tr>
											<c:forEach items="${offers}" var="offer">
												<tr>
													<td>${offer.offerCode}</td>
													<td>${offer.offerText}</td>
													<td>${offer.offerQuantity}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="offerText" />
										Offer text
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="offerText" tabindex="4"
											type="text" name="offerText"
											title="Offer text to be displayed in Product Detail" />
									</div>
								</div> --%>
								<%-- <div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="deliveryCharge" />
										Delivery Charge
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="deliveryCharge"
											tabindex="4" type="text" name="deliveryCharge"
											title="Offer text to be displayed in Product Detail" />

									</div>
								</div> --%>
								<%-- <div class="row">
									<div class="col-lg-6 first_row">
										<form:errors class="error" path="price" />
										Stock
									</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="stock" tabindex="5"
											type="number" name="stock" title="Stock in unit"
											required="required" />

									</div>
								</div> --%>
								<div class="row">
									<div class="col-lg-6 first_row">Category</div>
									<div class="col-lg-6">
										<select id="my-select" name="character" multiple="multiple">
											<c:forEach var="item" items="${allcategories}">
												<option value="${item.key}">${item.value}</option>
											</c:forEach>
										</select>
										<form:select path="categories" id="selectedcats"
											name="categories" multiple="multiple">
											<form:options />
										</form:select>
									</div>
								</div>
								<%-- <div class="row">
								<div class="col-lg-6 first_row">
									<span id="measure_unit_text">Measure Unit</span>
								</div>
								<div class="col-lg-6">
									<form:select class="form-control half_width" path="measureUnit"
										id="measure_unit_select" name="measurement">
										<option value="Select">How you measure your product?</option>
										<c:forEach var="item" items="${measureUnits}">
											<option value="${item}">${item}</option>
										</c:forEach>
										<option value="ADD_NEW">Add New</option>
									</form:select>
									<form:input class="form-control half_width"
										id="newMeasurementUnit" type="text" path="newMeasurementUnit"
										onblur="new_unit_added()" />
									<!-- <button type="button" id="newMeasurementUnitAddButton">Add</button> -->
									
								</div>
							</div> --%>
								<%-- <div class="row">
								<div class="col-lg-6 first_row">Minimum Order Quantity</div>
								<div class="col-lg-6">
									<form:input class="form-control" type="number" path="MOQ" />
									&nbsp;&nbsp;<span id="measure_means_fill_2"></span>
								</div>
							</div> --%>
								<div class="row">
									<div class="col-lg-6 first_row">Size(Add NA if no size)</div>
									<div class="col-lg-6">
										<select id="available_sizes" name="sizes_avail"
											multiple="multiple">
											<option value="Select">Select a size</option>
											<c:forEach var="item" items="${allSizes}">
												<option value="${item}">${item}</option>
											</c:forEach>
											<option value="ADD_NEW">Add New</option>
										</select>
										<div id="selected_sizes_div">
											Selected Sizes :
											<form:select class="sizes_selected" path="sizes"
												id="sizes_selected" name="sizes" multiple="true">
											</form:select>

											<input id="add_new_size" type="text" />
											<button onclick="addnewsize()" type="button"
												id="add_new_size_button">Add</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Sizewise Stock(If no size
										mention NA and stock)</div>
									<div class="col-lg-6">
										<table id="_sizewise_stock_table" class="table table-striped">
											<tr>
												<th>Size</th>
												<th>Stock</th>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="sizewiseStock[0].key"></form:input></td>
												<td><form:input class="form-control" type="number"
														path="sizewiseStock[0].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="sizewiseStock[1].key"></form:input></td>
												<td><form:input class="form-control" type="number"
														path="sizewiseStock[1].value"></form:input></td>
											</tr>
										</table>
										<button type="button" class="btn btn-success"
											onclick="add_new_sizewise_stock()"
											id="add_new_sizewise_stock_button">Add New Size and
											Stock</button>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Thumbnail Image</div>
									<div class="col-lg-6">
										<input class="fallsbuy_control" name="listingThumb"
											accept="image/png, image/jpeg"
											onchange="validateFile('listingThumb');" type="file"
											id="listingThumb" /> <a href="#"
											onclick="javascript:resetFileInput('listingThumb');">reset</a>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Images</div>
									<div class="col-lg-6">
										<input class="fallsbuy_control"
											onchange="validateFile('colorSubImages')"
											name="additionalImages" id="colorSubImages"
											accept="image/png, image/jpeg, image/jpg" type="file"
											multiple />
									</div>
								</div>

								<%-- <div class="row">
									<div class="col-lg-6 first_row">CDN Direct Urls</div>
									<div class="col-lg-6">
										<table id="cdn_table" class="table table-striped">
											<tr>
												<th>Urls</th>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="cdnDirectUrls[0]" /></td>

											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="cdnDirectUrls[1]" /></td>

											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="cdnDirectUrls[2]" /></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="cdnDirectUrls[3]" /></td>

											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="cdnDirectUrls[4]" /></td>

											</tr>
										</table>
									</div>
								</div> --%>

								<div class="row">
									<div class="col-lg-6 first_row">Show Discount</div>
									<div class="col-lg-6">
										<form:checkbox class="form-control" path="isShowDiscount"
											name="isShowDiscount" />
									</div>
								</div>

								<div class="row">
									<div class="col-lg-6 first_row">Weight (In Grams)</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="weight" tabindex="6"
											type="number" name="weight" title="Weight in Grams"
											step="0.01" />
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Video</div>
									<div class="col-lg-6">
										<form:input class="form-control" path="video" tabindex="7"
											type="text" name="video" title="Video URL" />
									</div>
								</div>
								<div class="row" style="display: none;">
									<div class="col-lg-6 first_row">Colors</div>
									<div class="col-lg-6">
										<form:select class="form-control" path="colors" id="colors"
											name="colors" multiple="multiple">
											<form:options />
										</form:select>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Selected colors</div>
									<div class="col-lg-6">
										<div id="selected_colors"></div>
										<a href="#" onclick="clearSelectedColors()">Clear all</a>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">Color Palette</div>
									<div class="col-lg-6">
										<div role="group" aria-label="...">
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Red;" title="Red">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Green;" title="Green">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Blue;" title="Blue">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Yellow;" title="Yellow">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Gray;" title="Gray">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Brown;" title="Brown">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Pink;" title="Pink">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Lavender;" title="Lavender">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Lime;" title="Lime">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Magenta;" title="Magenta">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Gold;" title="Gold">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Ivory;" title="Ivory">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Linen;" title="Linen">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Olive;" title="Olive">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Purple;" title="Purple">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Violet;" title="Violet">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: White;" title="White">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Black;" title="Black">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Orange;" title="Orange">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: HotPink;" title="HotPink">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: DeepPink;" title="DeepPink">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: LightSalmon;" title="LightSalmon">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: RosyBrown;" title="RosyBrown">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Aqua;" title="Aqua">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: LightCyan;" title="LightCyan">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: SkyBlue;" title="SkyBlue">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: Beige;" title="Beige">&nbsp;&nbsp;&nbsp;&nbsp;</button>
											<button type="button" class="btn btn-default color_btn"
												style="background-color: LightGray;" title="LightGray">&nbsp;&nbsp;&nbsp;&nbsp;</button>

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-6 first_row">Description</div>
									<div class="col-lg-6">
										<table id="desc_table" class="table table-striped">
											<tr>
												<th>Title</th>
												<th>Description</th>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[0].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[0].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[1].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[1].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[2].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[2].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[3].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[3].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[4].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[4].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[5].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[5].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[6].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[6].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[7].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[7].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="description[8].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="description[8].value"></form:input></td>
											</tr>
										</table>
										<button type="button" class="btn btn-success"
											onclick="add_new_desc()" id="add_new_descption_button">Add
											New Description</button>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-6 first_row">Shipping And Returns</div>
									<div class="col-lg-6">
										<table id="ship_and_returns_table" class="table table-striped">
											<tr>
												<th>Title</th>
												<th>Description</th>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[0].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[0].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[1].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[1].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[2].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="shippingAndReturns[2].value"></form:input></td>
											</tr>
										</table>
										<button type="button" class="btn btn-success"
											onclick="add_new_ship_and_returns()"
											id="add_new_ship_and_returns_row_button">Add New
											Shipping And Returns Description</button>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-6 first_row">Searcheable Attributes</div>
									<div class="col-lg-6">
										<table id="searchable_attribute_table"
											class="table table-striped">
											<tr>
												<th>Attribute</th>
												<th>Value</th>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[0].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[0].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[1].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[1].value"></form:input></td>
											</tr>
											<tr>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[2].key"></form:input></td>
												<td><form:input class="form-control" type="text"
														path="searchableAttributes[2].value"></form:input></td>
											</tr>
										</table>
										<button type="button" class="btn btn-success"
											onclick="add_new_searchable_attribute()"
											id="add_new_searchable_attribute_button">Add New
											Attribute</button>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 first_row">&nbsp;</div>
									<div class="col-lg-6">
										<button class="btn btn-success" type="submit">Add
											Product</button>
									</div>
								</div>

							</form:form>
						</div>
					</div>
				</div>
			</div>
			<!-- add products ends -->
		</div>
	</div>
	<br>
	<br>
	<footer class="footer-div">
		<jsp:include page="common/footer.jsp" />
	</footer>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/common/js/owl.carousel.js"></script>

	<script>
		$('.owl-carousel').owlCarousel({
			loop : false,
			margin : 10,
			responsiveClass : true,
			responsive : {
				0 : {
					items : 1,
					nav : true
				},
				768 : {
					items : 3,
					nav : true
				},
				1000 : {
					items : 4,
					nav : true,
					loop : false
				}
			}
		});
		$(document).ready(function() {
			/*common files*/
			$(function() {
				/* $("header").load("resources/assets/common/header.html");
				$("footer").load("resources/assets/common/footer.html"); */
			});

		});
	</script>
	<script src="resources/assets/addproducts/js/addproducts.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#my-select').searchableOptionList({
				maxHeight : '250px'
			});
		});
	</script>

	<script src="resources/assets/addproducts/js/sol.js"></script>
	<input type="hidden" id="_sizewise_stock_row_count" value="2" />
	<input type="hidden" id="_searchable_attribute_row_count" value="3" />
	<input type="hidden" id="_ship_and_returns_row_count" value="3" />
	<input type="hidden" id="_desc_row_count" value="9" />
</body>
</html>
