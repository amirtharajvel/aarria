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
<meta name="author" content="Fallsbuy">
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />


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
	<header class="header-div"></header>
	<div class="container-fluid container-div">

		<div class="container">

			<form:form id="addproducts_form" enctype="multipart/form-data"
				class="form-basic" modelAttribute="model" method="post"
				action="pincodeupload">

				<!-- add products starts -->
				<div class="row">
					<div class="col-lg-6 first_row">Please upload a CSV file</div>
					<div class="col-lg-6">
						<input class="fallsbuy_control" name="csvFile"
							accept="text/comma-separated-values, text/csv, application/csv, application/excel, application/vnd.ms-excel, application/vnd.msexcel, text/anytext"
							type="file" id="csvFile" /> <a href="#"
							onclick="javascript:resetFileInput('csvFile');">reset</a>
					</div>
					<button class="btn btn-success" type="submit">Add Product</button>
				</div>
			</form:form>
			<!-- add products ends -->
		</div>
	</div>
	<br>
	<br>
	<footer class="footer-div"></footer>
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
				$("header").load("resources/assets/common/header.html");
				$("footer").load("resources/assets/common/footer.html");
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

</body>
</html>
