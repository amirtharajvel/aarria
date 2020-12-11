<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>FallsBuy ECommerce Private Limited</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Fallsbuy Ecommerce Private Limited">
<meta name="author" content="Amirtharaj">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />


<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/common/style/style.css" rel="stylesheet">
<link href="resources/assets/common/style/font-awesome.css"
	rel="stylesheet">
<link href="resources/assets/sizechart/style/sizechart.css"
	rel="stylesheet">
</head>
<body>
	<header class="header-div"></header>
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
		<div class="row" id="men_casual_top">

			<h4>Casual Tops and Pants - Men (inches)</h4>
			<br />
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Size</th>
						<th>Neck</th>
						<th>Chest</th>
						<th>Waist</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>XS</td>
						<td>13 - 13.5</td>
						<td>34</td>
						<td>26 - 27</td>
					</tr>
					<tr>
						<td>S</td>
						<td>14 - 14.5</td>
						<td>36</td>
						<td>28 - 30</td>
					</tr>
					<tr>
						<td>M</td>
						<td>15 - 15.5</td>
						<td>38</td>
						<td>31 - 32</td>
					</tr>
					<tr>
						<td>XL</td>
						<td>17</td>
						<td>42</td>
						<td>35 - 36</td>
					</tr>
					<tr>
						<td>2XL</td>
						<td>17.5</td>
						<td>44</td>
						<td>37 - 38</td>
					</tr>
					<tr>
						<td>3XL</td>
						<td>18</td>
						<td>46</td>
						<td>39 - 40</td>
					</tr>
				</tbody>
			</table>
		</div>


		<div class="row" id="men_shirts">

			<h4>Shirts - Men (inches)</h4>
			<br />
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Size</th>
						<th>Neck</th>
						<th>Chest</th>
						<th>Waist</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>XS</td>
						<td>13 - 13.5</td>
						<td>34</td>
						<td>26 - 27</td>
					</tr>
					<tr>
						<td>S</td>
						<td>14 - 14.5</td>
						<td>36</td>
						<td>28 - 30</td>
					</tr>
					<tr>
						<td>M</td>
						<td>15 - 15.5</td>
						<td>38</td>
						<td>31 - 32</td>
					</tr>
					<tr>
						<td>XL</td>
						<td>17</td>
						<td>42</td>
						<td>35 - 36</td>
					</tr>
					<tr>
						<td>2XL</td>
						<td>17.5</td>
						<td>44</td>
						<td>37 - 38</td>
					</tr>
					<tr>
						<td>3XL</td>
						<td>18</td>
						<td>46</td>
						<td>39 - 40</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<br>
	<footer class="footer-div"></footer>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			/*common files*/
			$(function() {
				$("header").load("resources/assets/common/header.html");
				$("footer").load("resources/assets/common/footer.html");
			});

		});
	</script>
</body>
</html>
