function fillSuggestions(id) {
	$
			.ajax({
				url : "/listproducts/" + parseInt(id),
				method : 'GET',
				beforeSend : function() {
					$('#loading').show();
				},
				complete : function() {
					$('#loading').hide();
				},
				success : function(all) {

					var data = JSON.parse(all);
					if (data != null && data.length > 0) {
						console.log("There are " + data.length + " products");
						var suggested = "";

						for (i = 0; i < data.length; i++) {
							var product = data[i];
							suggested += '<li><img src="'
									+ product.image
									+ '" alt=""><h3>Product Name</h3><h4>500 - 600</h4></li><li>';
						}

						$("#content-slider").html(suggested);

					} else {
						console.log("There are no suggestions..");
					}
				}
			});
}
