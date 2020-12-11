$(document).ready(function() {

});

function onclickofimage(url, id) {

	var selectedOption = $('input[name=homepagecategories]:checked').val();

	if (selectedOption === '0') {
		var newArrivalsCount = parseInt($("#new_arrivals_count").val());

		console.log("appending new arrivals count" + newArrivalsCount);

		if (newArrivalsCount < 9) {
			$("#newarrivals").append(
					"<img height='90' width='70' src='" + url + "'/>");
			$("#newarrivals").append(
					'<input type="hidden" name="newArrivals" value="' + id
							+ '" />');
			$("#new_arrivals_count").val(++newArrivalsCount);
		}
	}

	if (selectedOption === '1') {
		var popularCount = parseInt($("#popular_count").val());

		console.log("appending new arrivals count" + popularCount);

		if (popularCount < 9) {

			$("#popular").append(
					"<img height='90' width='70' src='" + url + "'/>");
			$("#popular")
					.append(
							'<input type="hidden" name="popular" value="' + id
									+ '" />');
			$("#popular_count").val(++popularCount);
		}
	}

	if (selectedOption === '2') {
		var offerCount = parseInt($("#offer_count").val());

		if (offerCount < 9) {
			$("#offers").append(
					"<img height='90' width='70' src='" + url + "'/>");
			$("#offers").append(
					'<input type="hidden" name="offers" value="' + id + '" />');
			$("#offer_count").val(++offerCount);
		}
	}

	if (selectedOption === '3') {
		var bestDealsCount = parseInt($("#best_deals_count").val());

		if (bestDealsCount < 9) {
			$("#bestdeals").append(
					"<img height='90' width='70' src='" + url + "'/>");
			$("#bestdeals").append(
					'<input type="hidden" name="bestDeals" value="' + id
							+ '" />');
			$("#best_deals_count").val(++bestDealsCount);
		}
	}
}