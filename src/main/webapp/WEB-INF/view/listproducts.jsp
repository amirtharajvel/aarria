<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Fallsbuy.com - A Trusted Online Clothing Portal for
	Women, Men and Kids. Buy Sarees, Kurtis, Choli's, Anarkali, Tops,
	Lahankas, Party Wear, Kids Wear at low price with Export quality. No
	fake discounts. Only good quality hand picked products.</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Good quality products at low price, low cost good quality products in India, good quality clothes">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, sarees for women latest design, latest design, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/products/css/products.css" rel="stylesheet">

</head>
<body>
	<header class="header-div">
		<jsp:include page="common/header.jsp" />
	</header>

	<div class="container container-div products-div">
		<div class="row breadcrumb_row">

			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<ol class="breadcrumb" id="breadcrumb_category">
					<li><a href="/">Home</a></li>
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
							<a href="#" id="clear_all_anchor">Clear All</a>
						</div>
					</div>

					<!-- start accordion -->
					<div class="panel-group" id="accordion">
						<div class="panel panel-default template">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThree"> Categories
									</a>
								</h4>
							</div>

							<div id="collapseThree" class="panel-collapse collapse">
								<div class="panel-body">
									<div style="margin-left: 5%;">
										<a href="#">Kurtis</a>
									</div>
									<div style="margin-left: 5%;">
										<a href="#">Sarees</a>
									</div>
									<div style="margin-left: 5%;">
										<a href="#">Dress Materials</a>
									</div>
								</div>
								<br />
							</div>
						</div>
					</div>
					<!-- end accordion -->


				</div>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="row pro-head">
					<div class="col-xs-12 col-sm-4 col-md-4 padding-0">
						<h4>
							<span id="category_title"></span>(<span id="product_count">0</span>)
						</h4>
					</div>
					<div class="col-xs-12 col-sm-8 col-md-8 text-right padding-0">
						<h5>
							Sort By:<a href="" id="popular">Popular</a><a href=""
								id="low_to_high">Low to High</a><a href="" id="high_to_low">High
								to Low</a>
						</h5>
					</div>
				</div>
				<div class="pro-list">
					<div class="row" id="products_container"></div>
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
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/products/js/bootstrap-treeview.js"></script>
	<script src="resources/assets/products/js/jquery.raty.js"></script>
	<script src="resources/assets/products/js/products.js"></script>
	<script src="resources/assets/common/js/cart.js"></script>

	<script src="resources/assets/common/js/jquery.easy-autocomplete.js"></script>
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
	<script>
		$(window).load(function() {
			$("#onchange_refiner_checkboxes").val(0);
			
		});
	</script>
	<script>
		$(document).ready(function() {
			$(".eac-square").css("width", "100%");
		});
	</script>
	<script>
		window.addEventListener('popstate', function(event) {
			//window.location.href = $("#previous_page").val();
			//history.replaceState({}, 'Title: Google', 'http://www.google.com/');
			history.replaceState(null, document.title, location.pathname);
			//replaces first element of last element of stack with google.com/gmail so can be used further
			setTimeout(function() {
				location.replace($("#previous_page").val());
			}, 0);
		}, false);
	</script>

	<!-- hidden fields -->
	<input type="hidden" id="current_cat_id"
		value='<%=request.getParameter("cat")%>' />
	<input type="hidden" id="child_category_ids" value="" />
	<input type="hidden" id="attribute_ids" value="-1" />
	<input type="hidden" id="products_size" value="0" />
	<a href="#0" class="cd-top">Top</a>
	<input type="hidden" id="onchange_refiner_checkboxes" />
	<input type="hidden" id="previous_page" />
	<input type="hidden" id="previous_href" />
</body>
</html>
