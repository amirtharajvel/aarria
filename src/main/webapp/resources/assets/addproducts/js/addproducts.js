var IMAGE_WIDTH = 50;
var IMAGE_HEIGHT = 50;
var FILE_SIZE = 100;

$(document).ready(function() {
	addcolor();
	measure();
	size();
	removesize();
	submit();
});

function calculateprice() {
	$.ajax({
		url : "/priceCalculator",
		method : 'POST',
		data : getCalculatePriceData(),
		success : function(data) {
			$("#calculate_price_result").html(data);
		}
	});
}

function getCalculatePriceData() {
	
	var data = {
			originalPrice : $("#originalPrice").val(),
			weight : $("#weight").val(),
			profitToBeAdded : $("#profitToBeAdded").val()
	}
	
	return data;
}

function addcolor() {
	$(document)
			.on(
					'click',
					'.color_btn',
					function(event) {
						var rgb = $(event.target).css('backgroundColor');
						var title = $(event.target).attr('title');
						// var strip = rgb2hex(rgb).replace("#", ""); when hex
						// was used instead of names
						var strip = title;

						if (isIdExists(strip)) {
							return;
						}
						addColorRefiner(strip);
						$("#selected_colors")
								.append(
										'<button id="'
												+ strip
												+ '" onclick="removeButton(\''
												+ strip
												+ '\')" title="'
												+ title
												+ '" style="border:1px solid gold;margin-left:2px;background-color:'
												+ rgb
												+ '" type="button" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>');
					});

}

function addColorRefiner(strip) {
	var opt = new Option("Sample", strip);
	$("#colors").append(opt);
	opt.setAttribute("selected", "selected");
}

function isIdExists(id) {
	if ($("#" + id).length) {
		return true;
	} else {
		return false;
	}
}

function resetFileInput(id) {
	var input = $("#" + id);
	input = input.val('').clone(true);
}

function resetFileInputClass(cls) {
	var input = $("." + cls);
	input = input.val('').clone(true);
}

function removeButton(id) {
	isIdExists(id);
	$("#" + id).remove();

	$("#colors[value='" + id + "']").each(function() {
		$(this).remove();
	});
}

function clearSelectedColors() {
	$("#selected_colors").html('');
}

function rgb2hex(rgb) {
	if (rgb.search("rgb") == -1) {
		return rgb;
	} else {
		rgb = rgb.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)(?:,\s*(\d+))?\)$/);
		function hex(x) {
			return ("0" + parseInt(x).toString(16)).slice(-2);
		}
		return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	}
}

function addColorRow() {

	var colorCount = parseInt($("#color_counter").val());
	colorCount = ++colorCount;

	var newRow = '<tr id="colorDto['
			+ colorCount
			+ '].colorMainImageTR"><td><input name="colorDto['
			+ colorCount
			+ '].colorMainImage" onchange="validateFile(\'colorDto'
			+ colorCount
			+ 'colorMainImage\')" id="colorDto'
			+ colorCount
			+ 'colorMainImage" accept="image/png, image/jpeg" type="file" /></td>'
			+ '<td><input name="colorDto['
			+ colorCount
			+ '].colorSubImages" accept="image/png, image/jpeg" onchange="validateFile(\'colorDto'
			+ colorCount + 'colorSubImage\')" id="colorDto' + colorCount
			+ 'colorSubImage"	type="file" multiple /></td>'
			+ '<td><a href="#" onclick="deleteRow(\'colorDto[' + colorCount
			+ '].colorMainImageTR\')">Delete</a></td></tr>';

	$("#color_counter").val(colorCount);

	$("#color_images_table").append(newRow);
}

function validateFile(id) {
	var files = document.getElementById(id).files;

	var _URL = window.URL || window.webkitURL;

	for (i = 0; i < files.length; i++) {

		var file, img;

		if ((file = files[i])) {
			img = new Image();
			img.onload = function() {
				if (this.width < IMAGE_WIDTH || this.height < IMAGE_HEIGHT) {
					alert("Please upload a minimum of " + IMAGE_WIDTH + " * "
							+ IMAGE_HEIGHT + " dimension image");
					resetFileInput(id);
					return;
				}

				if (file.size < FILE_SIZE) {
					alert("File	size must be atleast " + FILE_SIZE + " KB - "
							+ files[i].name + " has only " + files[i].size
							+ " KB ");

					resetFileInput(id);
				}
			};
			img.src = _URL.createObjectURL(file);
		}

	}
}

function getImgSize(imgSrc) {
	var newImg = new Image();
	newImg.src = imgSrc;
	var height = newImg.height;
	var width = newImg.width;
	p = $(newImg).ready(function() {
		return {
			width : newImg.width,
			height : newImg.height
		};
	});
	alert(p[0]['width'] + " " + p[0]['height']);
}

function deleteRow(id) {
	var row = document.getElementById(id);
	row.parentNode.removeChild(row);
}

function measure() {
	$('#measure_unit_select').on('change', function() {

		$("#measure_unit_text").css("color", "");

		if (this.value === "Select") {
			emptyUnits();
			return;
		}

		if (this.value === "ADD_NEW") {
			$("#newMeasurementUnit").css("display", "block");
			emptyUnits();
			return;
		} else {
			$("#newMeasurementUnit").css("display", "none");
			$("#newMeasurementUnit").val("");
		}
		$("#measure_means_fill").html(this.value);
		$("#measure_means_fill_2").html(this.value);
	});
}

function emptyUnits() {
	$("#measure_means_fill").html("");
	$("#measure_means_fill_2").html("");
}

function new_unit_added() {
	var unit = $("#newMeasurementUnit").val();
	$("#measure_means_fill").html(unit);
	$("#measure_means_fill_2").html(unit);
}

function size() {
	$('#available_sizes').on(
			'dblclick',
			function() {

				if (isAlreadyExists(this.value)) {
					return;
				}

				showSelectedDropDown();

				if (this.value === "ADD_NEW") {
					showSelectedSizes();
					showAddNewSize();
					return;
				}

				hideAddNewSize();

				if (this.value === "Select") {
					return;
				}

				showSelectedSizes();
				showSelectedDropDown();
				$("#sizes_selected").append(
						'<option selected="selected">' + this.value
								+ '</option>');

			});
}

function showSelectedSizes() {
	$("#selected_sizes_div").css("display", "inline-block");
}

function showSelectedDropDown() {
	$("#sizes_selected").css("display", "inline-block");
}

function hideSelectedDiv() {
	$("#sizes_selected").css("display", "none");
	$("#selected_sizes_div").css("display", "none");
}

function isAlreadyExists(item) {
	var exists = false;
	$('#sizes_selected option').each(function() {
		if (item.toUpperCase() == this.value.toUpperCase()) {
			exists = true;
		}
	});
	return exists;
}

function addnewsize() {
	var val = $("#add_new_size").val();
	val = val.toUpperCase();

	if (isAlreadyExists(val)) {
		return;
	}
	if (val) {
		$("#sizes_selected").append(
				'<option selected="selected">' + val + '</option>');
	}

}

function showAddNewSize() {
	$("#add_new_size_button").css("display", "inline-block");
	$("#add_new_size").css("display", "inline-block");
}

function hideAddNewSize() {
	$("#add_new_size_button").css("display", "none");
	$("#add_new_size").css("display", "none");
}

function removesize() {
	$('#sizes_selected').dblclick(function() {
		$(this).find('option:selected').remove();

		if (isSelectedSizesEmpty()) {
			hideSelectedDiv();
		}
	});
}

function isSelectedSizesEmpty() {
	if ($('#sizes_selected').has('option').length > 0) {
		return false;
	}

	return true;
}

function displaySizes() {
	$('#sizes_selected option').each(function() {
		alert("display " + this.value);
	});
}

function submit() {

	$("#addproducts_form").submit(function(e) {
		// commenting out measure unit as not making it as mandatory
		// if ($("#measure_unit_select").val() === "Select") {
		// alert("Please select a measurement Unit");
		// $("#measure_unit_text").css("color", "red");
		// return false;
		// }
		return true;
	});
}

function add_new_desc() {
	var count = $("#_desc_row_count").val();

	if (count === undefined) {
		alert("undefined");
		count = Number(9);
	}

	$("#desc_table tr:last")
			.after(
					'<tr id="row_'
							+ count
							+ '"><td><input class="form-control" type="text" name="description['
							+ count
							+ '].key"></input></td>'
							+ '<td align="right"><input class="form-control" type="text" name="description['
							+ count
							+ '].value"></input><button type="button" onclick="remove_desc(\'row_'
							+ count + '\')">Remove</button</td></tr>');

	count = parseInt(count);
	count = count + 1;

	$("#_desc_row_count").val(count);
}

function remove_desc(id) {
	$("#" + id).closest('tr').remove();
}

// searchable attributes
function add_new_searchable_attribute() {
	var count = parseInt($("#_searchable_attribute_row_count").val());
	if (count === undefined) {
		count = 3;
	}
	$("#searchable_attribute_table tr:last")
			.after(
					'<tr id="row_'
							+ count
							+ '"><td><input type="text" class="form-control" name="searchableAttributes['
							+ count
							+ '].key"></input></td>'
							+ '<td align="right"><input type="text" class="form-control" name="searchableAttributes['
							+ count
							+ '].value"></input><button type="button" onclick="remove_searchable_attribute(\'row_'
							+ count + '\')">Remove</button</td></tr>');
	$("#_searchable_attribute_row_count").val(++count);
}

function remove_searchable_attribute(id) {
	$("#" + id).closest('tr').remove();
}

// searcheable attributes
function add_new_sizewise_stock() {
	var count = parseInt($("#_sizewise_stock_row_count").val());
	if (count === undefined) {
		count = 2;
	}

	$("#_sizewise_stock_table tr:last")
			.after(
					'<tr id="sizewiseStock_row_'
							+ count
							+ '"><td><input type="text" class="form-control" name="sizewiseStock['
							+ count
							+ '].key"></input></td>'
							+ '<td align="right"><input type="number" class="form-control" name="sizewiseStock['
							+ count
							+ '].value"></input><button type="button" onclick="remove_searchable_attribute(\'sizewiseStock_row_'
							+ count + '\')">Remove</button</td></tr>');

	$("#_sizewise_stock_row_count").val(++count);
}

function add_new_ship_and_returns() {
	var count = parseInt($("#_ship_and_returns_row_count").val());
	if (count === undefined) {
		count = 3;
	}

	$("#ship_and_returns_table tr:last")
			.after(
					'<tr id="ship_and_returns_row_'
							+ count
							+ '"><td><input type="text" class="form-control" name="shippingAndReturns['
							+ count
							+ '].key"></input></td>'
							+ '<td align="right"><input type="text" class="form-control" name="shippingAndReturns['
							+ count
							+ '].value"></input><button type="button" onclick="remove_searchable_attribute(\'ship_and_returns_row_'
							+ count + '\')">Remove</button</td></tr>');

	$("#_ship_and_returns_row_count").val(++count);
}
