<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Review</title>
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">

<style>
.navbar-default {
	background-color: #323a3d;
	padding: 1%;
}

.btn-success {
	background-color: #fcf7f7;
	border-color: #ddd;
	color: #323A3D;
}

.btn-success:hover, .btn-default:hover {
	color: #323A3D;
	background-color: #f5e4e4;
	border-color: #ddd;
}
</style>
</head>

<body style="padding-top: 7%;">
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/"><img src="resources/assets/images/logo.png"
					height="40" width="150"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container">
		<div style="width: 100%;">
			<div style="width: 40%; margin: 0 auto;">

				<c:if test="${isUserAlreadyReviewed != null}">
					<h3>Thank you. You have already posted comment for this
						product. redirecting in 2 seconds..</h3>
					<script>
						window.setTimeout(function() {
							window.location = "/";
						}, 2000);
					</script>
				</c:if>

				<c:if test="${reviewSaved != null}">
					<h3>Thank you. Your comment is submitted for review.
						redirecting in 2 seconds..</h3>
					<script>
						window.setTimeout(function() {
							window.location = "/";
						}, 2000);
					</script>

				</c:if>
				<c:if test="${reviewSaved == null && isUserAlreadyReviewed == null}">
					<div>
						<form action="/submitreview" method="post">
							<div class="form-group">
								<div id="big"></div>
							</div>
							<div class="form-group">
								<label for="name">Name</label> <input type="text"
									class="form-control" name="name" id="name" required="required">
							</div>
							<div class="form-group">
								<label for="title">Title</label> <input type="text"
									class="form-control" name="title" id="title"
									required="required">
							</div>
							<div class="form-group">
								<label for="comment">Comment</label>
								<textarea minlength="10" class="form-control" rows="5"
									id="comment" name="review" maxlength="100" required="required"></textarea>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success">Submit</button>
							</div>
							<input type="hidden" value="${pid}" name="pid" /> <input
								type="hidden" name="score" id="score" />
						</form>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/products/js/jquery.raty.js"></script>
	<script>
		$('#big')
				.raty(
						{
							cancel : true,
							cancelOff : 'resources/assets/writereview/images/cancel-off-big.png',
							cancelOn : 'resources/assets/writereview/images/cancel-on-big.png',
							half : true,
							size : 24,
							starHalf : 'resources/assets/writereview/images/star-half-big.png',
							starOff : 'resources/assets/writereview/images/star-off-big.png',
							starOn : 'resources/assets/writereview/images/star-on-big.png',
							click : function(score, evt) {
								$("#score").val(score);
							}
						});

		//$('#star').raty('score');
	</script>
</body>
</html>