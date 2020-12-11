<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin - fallsbuy.com</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/admin/css/admin.css" rel="stylesheet">
<style>
#isUserRegistered {
	margin: 10px 0px;
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
		<div id="rootwizard">
			<ul style="padding: 10px;">
				<li><a href="#update_image" data-toggle="tab">Update
						Product Images</a></li>
				<li><a href="#delete_orders" data-toggle="tab">Remove
						Products</a></li>
				<li><a href="#open_orders" data-toggle="tab">Order details</a></li>
				<li><a href="#past_orders" data-toggle="tab">Send OTP</a></li>
				<li><a href="#cancelled_orders" data-toggle="tab">Cancel
						Refund</a></li>
				<li><a href="#wallet_details" data-toggle="tab">Wallet
						Balance</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane" id="update_image">

					<c:if test="${isProductNotPresent == true}">
						<div style="color: red; margin: 10px 0px;">Sorry! Product
							does not exists with that id!</div>
					</c:if>
					<form action="/getProductImages">
						<div style="margin: 10px 0px;">
							Enter Product Id <input type="text" name="id" /> <input
								type="submit" value="Get Product Images" />
						</div>
					</form>
					<div style="margin: 20px 0px;">
						<form action="/addAdditionalImages" method="post"
							enctype="multipart/form-data">

							<table>
								<tr>
									<td><input name="additionalImages"
										accept="image/png, image/jpeg" type="file" multiple /> <input
										name="productId" value="${productId}" type="hidden" /></td>
									<td><input type="submit" value="Upload Images" /></td>
								</tr>
							</table>

						</form>
					</div>
					<div id="product_images">
						<table>

							<c:forEach items="${images.images}" var="image">
								<tr>
									<td><img src="${image.preview}" height="100" width="70"
										style="margin-top: 10px;" /></td>
									<td><form id="deleteForm"
											action="/deleteProductAdditionalImage">
											<input name="productId" value="${productId}" type="hidden" />
											<input name="productAdditionalImageId" value="${image.id}"
												type="hidden" /> <input type="submit"
												value="Delete this image" onclick="return confirmDelete()" />
										</form></td>
									<td style="padding-left: 200px;"><form
											action="addPreviewAndSmallImage" method="post"
											enctype="multipart/form-data">
											<input name="imageType"
												value="<%=com.aha.core.util.Enum.ImageType.PREVIEW%>"
												type="hidden" /> <input name="productId"
												value="${productId}" type="hidden" /> <input name="imageId"
												value="${image.id}" type="hidden" /> <input
												name="previewOrSmallImage" accept="image/png, image/jpeg"
												type="file" multiple /> <input type="submit"
												value="Upload Preview Image" />
										</form></td>

									<td><form action="addPreviewAndSmallImage" method="post"
											enctype="multipart/form-data">
											<input name="imageType"
												value="<%=com.aha.core.util.Enum.ImageType.SMALL%>"
												type="hidden" /> <input name="productId"
												value="${productId}" type="hidden" /> <input name="imageId"
												value="${image.id}" type="hidden" /> <input
												name="previewOrSmallImage" accept="image/png, image/jpeg"
												type="file" multiple /> <input type="submit"
												value="Upload Small Image" />
										</form></td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>

				<div class="tab-pane" id="delete_orders">
					<form action="getorderdetails">
						<c:if test="${soldoutProducts != null }">
							<c:forEach var="dto" items="${soldoutProducts}">
								${dto.productId}
							</c:forEach>
						</c:if>
					</form>
					<div id="isUserRegistered"></div>
					<c:if test="${orders != null }">
						<c:forEach var="order" items="${orders }">
							<a href="vieworderdetailsasadmin/${order.orderIdPrimaryKey}"
								target="_blank">${order.orderNo}</a>
							<br />
						</c:forEach>
					</c:if>
				</div>
				<div class="tab-pane" id="open_orders">
					<form action="getorderdetails">
						Mobile <input type="text" name="mobile" id="mobile" /> <br /> <br />Order
						no <input type="text" name="orderno" /> <br /> <br /> <input
							type="submit" value="Fetch Order Details" /> <input
							type="button" onclick="verifyMobileRegistered()"
							value="Verify Mobile Registered" />
					</form>
					<div id="isUserRegistered"></div>
					<c:if test="${orders != null }">
						<c:forEach var="order" items="${orders }">
							<a href="vieworderdetailsasadmin/${order.orderIdPrimaryKey}"
								target="_blank">${order.orderNo}</a>
							<br />
						</c:forEach>
					</c:if>
				</div>
				<div class="tab-pane" id="past_orders">
					Mobile <input type="text" id="mobile_for_otp" /> <input
						type="button" onclick="sendSms()" value="Send OTP" />
					<div id="sent_otp"></div>
				</div>
				<div class="tab-pane" id="cancelled_orders">
					<table class="table">
						<tr>
							<th>Order no</th>
							<th>ordered date</th>
							<th>Total Order Amount</th>
							<th>Order Status</th>
						</tr>
						<c:forEach items="${refunds}" var="refund">
							<tr
								onclick="window.open('vieworderdetailsasadmin/${refund.orderIdPrimaryKey}' ,'_blank')">
								<td>${ refund.orderNo}</td>
								<td>${ refund.orderedDate}</td>
								<td>${ refund.totalAmount}</td>
								<td>${ refund.orderStatus}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="tab-pane" id="wallet_details">
					<c:if test="${wallets != null }">
						<table class="table">
							<tr>
								<th>Mobile no</th>
								<th>ordered date</th>
								<th>Balance</th>
							</tr>
							<c:forEach var="wallet" items="${wallets }">
								<tr>
									<td>${wallet.mobileNo }</td>
									<td>${wallet.depositedDate }</td>
									<td>${wallet.walletAmount }</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<c:if test="${wallets == null }">No user has Wallet balance</c:if>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/admin/js/admin.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/common/js/pills.js"></script>
</body>
</html>