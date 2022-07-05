<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<title>Place Order - aarria.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Fallsbuy Ecommerce Private Limited">
<meta name="author" content="Amirtharaj">

<link rel="shortcut icon" type="image/png"
	href="/resources/assets/images/only-f.png" />

<link href="resources/assets/checkout/css/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/checkout/css/style.css" rel="stylesheet">
<link href="resources/assets/checkout/css/font-awesome.css"
	rel="stylesheet">

<style>
.Payment-Pag .Payment-tab {
	background: #EDEFF1;
	padding: 30px;
	margin-top: 40px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	-ms-border-radius: 5px;
	border-radius: 5px;
}

.Payment-Pag .Payment-tab .tab-content {
	background: #fff;
	border: 1px solid #ddd;
	border-width: 0px 1px 1px 1px;
	padding: 15px;
}

.Payment-Pag .Payment-tab .tab-content .order-details {
	font-size: 14px;
}

.Payment-Pag .Payment-tab .tab-content .order-details span {
	color: #c1c1c1;
}

.Payment-Pag .Payment-tab .tab-content .order-details a {
	color: blue;
}

.Payment-Pag .Payment-tab .tab-content .order-details h5 {
	padding: 15px 0 5px 0;
	font-weight: bold;
}

.Payment-Pag .Payment-tab .tab-content .table {
	margin-bottom: 0px;
}

.Payment-Pag .Payment-tab .tab-content .table .total-cost {
	height: 50px;
}

.Payment-Pag .Payment-tab .tab-content .table .total-cost td {
	border: 0px;
}

.Payment-Pag .Payment-tab .tab-content .table a {
	color: #5cb85c;
	text-decoration: underline;
}

.Payment-Pag .Payment-tab .tab-content .table>thead>tr>th {
	border: 1px solid #ddd;
	border-width: 1px 0 1px 0;
}

.Payment-Pag .Payment-tab .tab-content .re-login {
	width: 50%;
	margin: 50px auto;
}

.Payment-Pag .Payment-tab .tab-content .re-login .input-group .input-group-addon
	{
	border-radius: 6px 0px 0px 6px;
	position: relative;
	z-index: 3;
}

.Payment-Pag .Payment-tab .tab-content .re-login .input-group .form-control
	{
	border-radius: 0px 6px 6px 0px;
}

@media screen and (max-width: 767px) {
	.re-login {
		width: 100% !important;
	}
}

#mobile {
	font-size: 16px;
}

.btn-success {
	font-weight: normal;
}

#password {
	font-size: 16px;
}

.input-group-addon {
	border-radius: 4px 0px 0px 4px;
}

#checkout .modal-dialog {
	width: 60%;
}

#checkout hr {
	margin: 10px;
}

#checkout .right-border {
	border-right: 1px solid #ccc;
	padding-right: 15px;
}

#checkout .order-details {
	font-size: 14px;
}

#checkout .order-details span {
	color: #c1c1c1;
}

#checkout .order-details a {
	color: #5cb85c;
}

#checkout .order-details h5 {
	padding: 0px 0 5px 0;
	font-weight: bold;
}

@media screen and (max-width: 767px) {
	.re-login {
		width: 100% !important;
	}
	#checkout .modal-dialog {
		width: 90%;
		margin: 30px auto 0 auto;
	}
	#checkout .modal-dialog .right-border {
		border-bottom: 1px solid #ccc;
		padding-right: 15px;
		margin-bottom: 15px;
	}
}
</style>
</head>

<body>
	<header class="header-div">
		<div style="float: left; padding-left: 2%;">
			<a href="/"><img alt="Fallsbuy.com"
				src="/resources/assets/images/logo.png"></a>
		</div>
		<div style="float: right">
			<c:if test="${isUserLoggedIn.equals('true')}">
				<div style="float: right; padding-right: 25%;">
					<a style="color: black;" href="logout">Logout</a>
				</div>
			</c:if>
		</div>
	</header>
	<div class="container container-div Payment-Pag">
		<div class="Payment-tab">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#ReviewOrder">Review
						Order</a></li>
				<li class="disabled"><a data-toggle="tab"
					class="tab_link
					disabledTab" href="#SecurePayment">Secure
						Payment</a></li>
			</ul>

			<div class="tab-content">
				<div id="ReviewOrder" class="tab-pane fade in active">


					<!-- start user logged in details -->
					<c:if test="${isUserLoggedIn.equals('true')}">
						<div class="Payment-div">
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-8">
									<!-- Use code <strong><span style="color: #ff00a9;">MAR200</span></strong>
									to get Rs.200 discount on each dress -->
									<!-- <div style="color: #323A3D; padding-left: 5%;">
										<img src="/resources/assets/images/heart.gif"><strong>
											Free Delivery on the same day on week ends </strong> <br /> <img
											src="/resources/assets/images/heart.gif"><strong>
											Free Try and Buy </strong>

									</div> -->
								</div>
								<div class="col-xs-12 col-sm-6 col-md-4">
									<div class="order-details">

										<c:if test="${addresses.equals('nil') }">
											<div style="width: 100%; margin-bottom: 10%;">
												<!-- <button id="add_address_button"
												class="btn btn-lg btn-success btn-block" type="button">&nbsp;&nbsp;Add
												a delivery address&nbsp;&nbsp;</button> -->
												<a id="add_address_button">Add a delivery address</a>
											</div>
										</c:if>
										<c:if
											test="${!addresses.equals('nil') && deliverHere != null }">
											<span>Your item(s) will be delivered to this address:</span>
											<h5>${deliverHere.name }</h5>
											<p>${deliverHere.address }</p>
											<p>${deliverHere.landmark }</p>
											<p>${deliverHere.pincode }</p>
											<p>${deliverHere.mobile }</p>
											<a id="change_address">Change delivery address</a>
										</c:if>
										<br /> <br /> <br /> <br />
										<form action="/checkout">
											<div style="width: 100%;">
												<div style="color: #666; font-weight: bold;">
													<c:if test="${couponCodeAppliedMessage != '' }">
														<span style="color: #FFA500; font-weight: bold;">${couponCodeAppliedMessage}</span>
													</c:if>
													&nbsp;&nbsp;
												</div>
												<div style="float: left;">
													<input minlength="3"
														style="width: 100px; border-radius: 4px; font-size: 12px;"
														type="text" id="couponcode" class="form-control"
														type="text" name="couponCode" placeholder="OFFER CODE"
														required="required" autofocus="autofocus"
														title="Enter a coupon code" value="${couponCode}" />

												</div>
												<div style="float: left;">
													<button class="btn btn-success" style="margin-left: 10%;"
														type="submit">Apply</button>
												</div>
											</div>


										</form>


									</div>
								</div>
							</div>

							<div
								style="width: 100%; border-top: 0px solid #ddd; margin-top: 0%;">&nbsp;</div>

							<c:if
								test="${isUserLoggedIn.equals('true') && items == 'noitems'}">
								<div
									style="width: 100%; min-height: 200px; margin-left: 20%; margin-top: 5%;">
									<h3>
										Your shopping cart is empty! <a href="/"
											style="color: #4169e1;">Continue Shopping</a>
									</h3>
								</div>
							</c:if>

							<c:if test="${totalQuantity != null && totalQuantity > 10}">
								<div style="color: red; margin-bottom: 2%; font-size: larger;">
									Sorry.. You can order maximum of 10 items only. Please remove
									the items or reduce the quantity.</div>
							</c:if>
							<c:if test="${message != null && message ne ''}">

								<div style="color: red; margin-bottom: 2%; font-size: larger;">${message}<br />
								</div>
							</c:if>

							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">

									<c:if test="${items != 'noitems' }">

										<div class="table-responsive">
											<table class="table">
												<thead>
													<tr>
														<th></th>
														<th>Name</th>
														<th>Size</th>
														<th style="width: 15%;">Quantity</th>
														<th>Stock</th>
														<th>MRP</th>
														<th>Price</th>
														<th>Offer Price</th>
														<th>Total Price</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${items != null and items != 'noitems'}">
														<c:forEach items="${items}" var="product">
															<tr>
																<td style="width: 100px"><a target="_blank"
																	href="product?id=${product.pid}"><img
																		src="${product.image}" height="100" alt=""></a></td>
																<td><a target="_blank"
																	href="product?id=${product.pid}">${product.name}</a>
																	<c:if test="${product.offerCode != null}">
																	<span style="color:#ff00a9;"><br/> <strong>Use Code: ${product.offerCode}<strong></span>
																	</c:if>
																	</td>
																<td>${product.size}</td>
																<td>
																	<table>
																		<tr>
																			<td><form
																					action="/cart/reduceOneQuantityFromCart"
																					method="post">
																					<input type="submit" value="-"
																						style="margin-right: 10px;" /> <input
																						type="hidden" name="pid" value="${product.pid}" />
																					<input type="hidden" name="size"
																						value="${product.size}" />
																				</form>
																			<td>${product.quantity }</td>
																			<td><form
																					action="/cart/increaseOneQuantityToCart"
																					method="post">
																					<input type="submit" value="+"
																						style="margin-left: 10px;" /> <input
																						type="hidden" name="pid" value="${product.pid}" />
																					<input type="hidden" name="size"
																						value="${product.size}" />
																				</form></td>
																		</tr>
																		<tr>
																			<td colspan="3"><a class="remove_link"
																				style="color: #323a3d; text-decoration: none; font-size: 12px;"
																				href="/cart/removeFromCartFromCheckout/${product.pid}/size/${product.size}">Remove</a></td>
																		</tr>
																	</table>
																</td>
																<td>${product.stock }</td>
																<td>&#8377; ${product.unitPrice}</td>
																<td>&#8377; ${product.sellingPriceBeforeOffer}</td>
																<td><c:if
																		test="${couponCodeAppliedMessage == 'Hurray.. Coupon code applied!!' }">
																		<span
																			style="color: #FFA500; font-weight: bold; margin-left: 2%;">&#8377;
																			${product.amountAfterOfferApplied }</span>
																	</c:if></td>
																<td id="totalunitprice${product.pid}${product.size}">${product.price}</td>
															</tr>
														</c:forEach>
													</c:if>
													<tr class="total-cost">
														<td colspan="7"></td>
														<td><b>Total</b></td>
														<td><b>&#8377;${totalBeforeOffer}</b></td>
													</tr>
													<c:if
														test="${couponCodeAppliedMessage != null and couponCodeAppliedMessage.contains('Hurray..')}">
														<tr style="color: #ff00a9;" class="total-cost">
															<td colspan="7"></td>
															<td><b>Your Savings</b></td>
															<td><strong>&#8377;${savings}</strong></td>
														</tr>
													</c:if>
													<tr class="total-cost">
														<td colspan="7"></td>
														<td><b>Net Total</b></td>
														<td><b>&#8377; <c:if
																	test="${couponCodeAppliedMessage != null and couponCodeAppliedMessage.contains('Hurray..')}">
																	 ${total}
																</c:if> <c:if
																	test="${couponCodeAppliedMessage == null or couponCodeAppliedMessage.contains('Invalid Coupon')}">
																	 ${totalBeforeOffer}

																</c:if> <c:if
																	test="${couponCodeAppliedMessage != null and couponCodeAppliedMessage.contains('Please purchase above')}">

																	${totalBeforeOffer}

																</c:if>

														</b></td>
													</tr>
												</tbody>
											</table>
											<table class="table">
												<tbody>
													<tr>
														<td colspan="4"><b>Payment method :</b>&nbsp;&nbsp;&nbsp;
															<label class="radio-inline"><input type="radio"
																name="returnCashMethod" checked="checked" value="0">Cash
																on delivery</label> <label class="radio-inline"><input
																type="radio" name="returnCashMethod" value="1">Netbanking
																/ Credit/Debit Card / Wallet / UPI </label></td>
														<td class="text-right"><button
																<c:if test="${totalQuantity > 10}"> disabled="disabled" </c:if>
																type="button" onclick="placeOrder()"
																id="place_order_button"
																class="btn btn-success add_to_cart">Place Order</td>
													</tr>
												</tbody>
											</table>
										</div>

									</c:if>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${!isUserLoggedIn.equals('true')}">
						<div class="re-login">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">


									<!-- invalid otp -->
									<c:if test="${invalidOtp.equals('true') }">

										<form class="form-horizontal" method="post"
											action="/verifyMobileOnCheckout" accept-charset="utf-8">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12">

													<div class="input-group input-group-lg"
														style="width: 100%;">

														<div class="error">Invalid One-time-password.</div>
														<div id="resend">
															<button onclick="sendVerificationCode()" type="button"
																class="btn btn-danger resend_button">Resend OTP</button>
															<br /> <br /> <input id="otp"
																style="height: 46px; border-radius: 6px; font-size: 16px;"
																oninput="this.value=this.value.replace(/[^0-9]/g,'');"
																type="text" id="otp" class="form-control"
																value="${enteredOTP}" pattern=".{4,}" maxlength="4"
																required="required" name="otp"
																placeholder="Please enter the OTP sent to ${otpMobile }"
																required="required" autofocus="autofocus"
																title="Enter One-Time-Password sent to ${otpMobile }" />
															<input type="hidden" name="mobile" id="mobile"
																value="${mobile}" />

														</div>

													</div>

												</div>
											</div>

											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- invalid otp -->




									<!-- valid otp -->
									<c:if test="${isValidOTP.equals('true') }">

										<form class="form-horizontal" method="post"
											action="/createNewUserOnCheckout" accept-charset="utf-8">

											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12">

													<div class="input-group input-group-lg"
														style="width: 100%;">

														<div id="resend">
															<label>Set a password</label> <input minlength="3"
																style="height: 46px; border-radius: 6px; font-size: 16px;"
																id="otp" type="password" id="otp" class="form-control"
																value="" name="password"
																placeholder="Type your favourite password"
																required="required" autofocus="autofocus"
																title="Create a Password" /> <input type="hidden"
																name="mobile" id="mobile" value="${mobile}" /> <input
																type="hidden" name="cartItems" id="cart_items" />
														</div>
													</div>
												</div>
											</div>

											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- invalid otp -->


									<!-- send otp -->
									<c:if test="${isSendOTP.equals('true') }">

										<form class="form-horizontal" method="post"
											action="/verifyMobileOnCheckout" accept-charset="utf-8">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12">

													<div class="input-group input-group-lg"
														style="width: 100%;">

														<input
															style="height: 46px; border-radius: 6px; font-size: 16px;"
															id="otp" type="text" id="otp"
															class="form-control numeric-only" name="otp"
															pattern=".{4,}" maxlength="4"
															oninput="this.value=this.value.replace(/[^0-9]/g,'');"
															required="required"
															placeholder="Please enter the OTP sent to ${otpMobile }"
															required="required" autofocus="autofocus"
															title="Enter One-Time-Password sent to ${otpMobile }" />
														<input type="hidden" name="mobile" value="${otpMobile }" />

													</div>

												</div>
											</div>

											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- send otp -->


									<!-- Show mobile number -->
									<c:if test="${isUserLoggedIn.equals('false') }">

										<form class="form-horizontal" method="post"
											action="/loginOnCheckout" accept-charset="utf-8">

											<div class="input-group input-group-lg">
												<span class="input-group-addon" id="sizing-addon1">+91</span>
												<input id="mobile" type="text" name="mobile"
													class="form-control"
													placeholder="Please enter your mobile number"
													required="required" autofocus="autofocus"
													title="Please enter your mobile number" pattern=".{10,10}"
													oninput="this.value=this.value.replace(/[^0-9]/g,'');"
													maxlength="10">
											</div>
											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- End show mobile number -->


									<!-- Show password -->
									<c:if test="${isShowPassword.equals('true') }">

										<form class="form-horizontal" method="post"
											action="/loginOnCheckout" accept-charset="utf-8">

											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12">

													<div class="input-group input-group-lg"
														style="width: 100%;">
														<input style="height: 46px; border-radius: 6px;"
															id="password" type="password" id="password"
															class="form-control" value="" name="password"
															placeholder="Please enter your password"
															required="required" autofocus="autofocus"
															title="Enter your password" /> <input type="hidden"
															name="mobile" value="${mobile}" /> <input type="hidden"
															name="cartItems" id="cart_items" />

													</div>

												</div>
											</div>

											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- End show password -->



									<!-- Incorrect password -->
									<c:if test="${isIncorrectPassword.equals('true') }">

										<form class="form-horizontal" method="post"
											action="/loginOnCheckout" accept-charset="utf-8">

											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12">

													<div class="input-group input-group-lg"
														style="width: 100%;">

														<div id="resend">
															<div
																style="color: red; margin-bottom: 2%; font-size: normal;">
																Incorrect password. Please try again.</div>
															<input style="height: 46px; border-radius: 6px;"
																id="password" type="password" id="password"
																class="form-control" value="" name="password"
																placeholder="Please re-enter the correct password"
																required="required" autofocus="autofocus"
																title="Enter your password" /> <input type="hidden"
																name="mobile" value="${mobile}" /> <input type="hidden"
																name="cartItems" id="cart_items" />

														</div>

														<br /> <a href="forgotpassword" id="forgotpassword"
															style="color: #5cb85c">Forgot Password?</a>

													</div>

												</div>
											</div>

											<br>
											<button
												class="btn btn-lg btn-success btn-block continue_button"
												type="submit">Continue</button>

										</form>
									</c:if>
									<!-- Incorrect password -->


								</div>
							</div>
						</div>
					</c:if>
				</div>
				<div id="SecurePayment" class="tab-pane fade">Oops..!! How
					come you could able to open this?!</div>
			</div>
		</div>

	</div>
	<br>
	<br>
	<footer class="footer-div"></footer>

	<input type="hidden" value="${addresses == 'nil' }"
		id="is_address_not_entered" />

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/checkout/js/jquery.bootstrap.wizard.js"></script>
	<script src="resources/assets/checkout/js/checkout.js"></script>
	<c:if test="${addresses.equals('nil')}">
		<script>
			$(document).ready(function() {
				$("#address_modal").modal('show');
			});
		</script>
	</c:if>
	<c:if test="${!isUserLoggedIn.equals('true')}">
		<script>
			$(document).ready(function() {
				var localCart = JSON.parse(localStorage.getItem("cart"));

				console.log("local cart is " + localCart);

				if (localCart != null) {
					$("#cart_items").val(JSON.stringify(localCart));
					console.log("cart items" + $("#cart_items").val());
				}
			});
		</script>
	</c:if>
	<c:if test="${isUserLoggedIn.equals('true')}">
		<script>
			$(document).ready(function() {
				localStorage.removeItem("cart");
			});
		</script>
	</c:if>
	<c:if test="${paymentStatus == 'success'}">
		<script>
			$(document).ready(function() {
				$('#checkout_tabs a[href="#tab2"]').tab('show');
				$('#secure_payment_tab_link').removeClass('disabledTab');
				$('#review_order_tab_link').addClass('disabledTab');
			});
		</script>
	</c:if>
















	<!-- Modal -->
	<div id="checkout" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">

					<div id="manual_address_entry" style="display: hidden;">
						<div class="row">
							<div class="col-xs-12 col-sm-8 col-md-8 right-border">
								<form class="form-horizontal" id="add_address_form"
									method="POST" action="addAddress">
									<input type="hidden" name="id" id="hidden_current_id" />
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Pincode:</label>
										<div class="col-sm-8 col-md-9">
											<input type="" class="form-control" name="pincode"
												id="pincode" pattern=".{6,6}" maxlength="6"
												oninput="this.value=this.value.replace(/[^0-9]/g,'');"
												title="Indian pincode is exactly 6 digits" required>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Name:</label>
										<div class="col-sm-8 col-md-9">
											<input class="form-control" name="name" id="name" required>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Address:</label>
										<div class="col-sm-8 col-md-9">
											<input class="form-control" minlength="10" name="address"
												id="address" required>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Landmark:</label>
										<div class="col-sm-8 col-md-9">
											<input class="form-control" type="text" name="landmark"
												id="landmark" placeholder="(Optional)">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Email:</label>
										<div class="col-sm-8 col-md-9">
											<input class="form-control" type="email" name="email"
												id="email" required>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4 col-md-3" for="">Mobile:</label>
										<div class="col-sm-8 col-md-9">
											<div class="input-group">
												<span class="input-group-addon">+91</span> <input
													id="mobile_textbox" name="mobile" type="text"
													style="border-radius: 0px 4px 4px 0px;"
													class="form-control" maxlength="10"
													placeholder="Who will receive the order?"
													title="Please enter a mobile number" pattern=".{10,10}"
													oninput="this.value=this.value.replace(/[^0-9]/g,'');">
											</div>
										</div>
									</div>


									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-9">
											<button type="submit" id="add_address_submit"
												class="btn btn-success">Add Address</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-xs-12 col-sm-4 col-md-4">

								<c:if test="${!addresses.equals('nil') }">
									<c:forEach var="address" items="${addresses }">

										<div class="order-details">
											<p class="name_div_ind" style="font-weight: bold;">${address.name }</p>
											<p class="address_div_ind">${address.address }</p>
											<p class="landmark_div_ind">${address.landmark }</p>
											<p class="country_div_ind">${address.country }</p>
											<p class="pincode_div_ind">${address.pincode }</p>
											<p class="mobile_div_ind">${address.mobile }</p>
											<p class="email_div_ind">${address.email }</p>

											<p>
												<%-- <img onclick="deleteAddress(${address.id})"
												class="delete_address"
												src="resources/assets/checkout/images/delete.png"
												height="17" width="17" />  --%>
												<img class="edit_address"
													src="resources/assets/checkout/images/editsvg.png"
													height="21" width="21" /> <input type="hidden"
													class="edit_current_id" name="id" value="${address.id}" />&nbsp;
												<a onclick='deliverHere(${address.id})'>Deliver Here</a>
											</p>
										</div>
										<hr>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true" id="mi-modal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Confirm Order</h4>
				</div>
				<div class="modal-body">
					<p>Confirm order and Proceed</p>
				</div>
				<div class="modal-footer">
					<form action="/placeOrder" id="pay_request_form">
						<input type="hidden" name="paymentMode" id="payment_mode" />
						<button type="button" onclick="closeConfirm()"
							id="cancel_order_button" class="btn btn-success add_to_cart">Close</button>
						<button type="submit" onclick="placeOrder()"
							id="confirm_place_order_button"
							class="btn btn-success add_to_cart">Place Order</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<form accept-charset=utf-8 action="deliverHere" class="deliverHere">
		<input type="hidden" id="current_id" name="id" />
	</form>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-114298247-2"></script>
	<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-114298247-2');
</script>
</body>
</html>
