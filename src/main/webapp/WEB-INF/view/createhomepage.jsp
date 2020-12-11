<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>FallsBuy ECommerce Private Limited</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Fallsbuy Ecommerce Private Limited">
<meta name="author" content="Fallsbuy">
<body>

	<div style="width: 100%">

		<div style="float: left; width: 50%;">

			<c:forEach items="${allProductsOrderByAddedDateDesc}" var="item">
				<img
					onclick="onclickofimage('${item.image}','${item.product.id }');"
					src="${item.image}" height="150" width="100" />
			</c:forEach>
		</div>

		<div style="float: left">
			<form action="/createhomepagehtml" method="post">
				<input type="radio" name="homepagecategories" value="0" checked>New
				Arrivals
				<div id="newarrivals" style="width: 100%;"></div>
				<input type="radio" name="homepagecategories" value="1">Popular
				<div id="popular" style="width: 100%;"></div>
				<input type="radio" name="homepagecategories" value="2">Offers
				<div id="offers" style="width: 100%;"></div>
				<input type="radio" name="homepagecategories" value="3">Best
				Deals
				<div id="bestdeals" style="width: 100%;"></div>
				<input type="submit" value="Publish" />
			</form>
		</div>
	</div>

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/createhomepage/js/createhomepage.js"></script>

	<input type="hidden" id="new_arrivals_count" value="0" />
	<input type="hidden" id="popular_count" value="0" />
	<input type="hidden" id="offer_count" value="0" />
	<input type="hidden" id="best_deals_count" value="0" />
</body>