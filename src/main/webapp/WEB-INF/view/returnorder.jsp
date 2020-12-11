<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<title>Return products - Place your returns here. We'll help you
	to make a smooth return process.</title>

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/returnorder/css/returnorder.css"
	rel="stylesheet">

</head>

<body style="padding-top: 7%; padding-bottom: 10%;">
	<form id="return_order_form" method="post" action="placereturnorder">
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

			<c:if test="${order != null }">
				<c:if test="${order.items != null && fn:length(order.items) > 0}">

					<div class="well" style="width: 60%; margin-left: 15%;">
						<table>
							<tr style="vertical-align: middle">
								<td><img style="vertical-align: top"
									src="resources/assets/account/images/warning.png"></td>
								<td style="vertical-align: middle">Your package will be
									picked up by a courier service. Please return the item and
									packaging in its original condition to avoid pickup
									cancellation by courier service. <a href="returnpolicy">More
										details</a>
								</td>
							</tr>
						</table>
					</div>

					<table class="table">
						<tr>
							<th>Check to Return</th>
							<th>Image</th>
							<th>Quantity</th>
							<th>Size</th>
							<th>Unit price</th>
							<th>Total price</th>
						</tr>
						<c:forEach items="${order.items }" var="item">
							<tr>
								<td style="vertical-align: middle"><input type="checkbox"
									name="itemsToReturn" value="${item.id }"> ${item.name }</td>
								<td><img src="${item.image }" height="50" width="40" /></td>
								<td>${item.quantity }</td>
								<td>${item.size }</td>
								<td>${item.unitSoldPrice }</td>
								<td>${item.totalSoldPrice }</td>
							</tr>
						</c:forEach>
					</table>
					<hr />
					<strong>Pick up address </strong>
					<br />
					<div>
						<c:if test="${order.addresses != null }">
							<c:forEach items="${order.addresses}" var="address" varStatus="i">
								<input type="radio" name="pickupAddress"
									value="${address.address}"
									<c:if test="${i.index == 0 }">checked</c:if>> ${address }
							<br />
							</c:forEach>
						</c:if>
						<br /> <input type="checkbox" id="different_location_checkbox"
							onclick="enableDifferentLocation();"> I want to pick up
						at different location <br />
						<textarea minlength="10" name="pickupAddress" rows="3" cols="50"
							id="pickupAddressTextArea" disabled="disabled"></textarea>
					</div>
					<br />
					<div>
						<strong>Pick up date </strong><br /> <select name="pickupDate">
							<c:forEach items="${order.pickupDatesOptions }" var="pickupDate">
								<option value="${pickupDate}">${pickupDate}</option>
							</c:forEach>
						</select>
					</div>
					<br />
					<div>
						<strong>Pick up time</strong> <br /> <select name="pickupTime">
							<option>9-12</option>
							<option>12-3</option>
							<option>3-6</option>
						</select>
					</div>
					<br />
					<div id="return_cash_method">
						<p>
							<input type="radio"
								onclick="hidebankaccountsforcancelorder('cancel_order_bank_accounts')"
								name="returnCashMethod" value="0" checked> Add cash back
							to the Wallet (Immediate)<br> <input type="radio"
								name="returnCashMethod" value="1"
								onclick="showbankaccountsforcancelorder('cancel_order_bank_accounts')">
							Revert Cash to my bank Account (takes 4-5 business days)<br>
						</p>
					</div>
					<br />
					<div id="return_order_bank_accounts" style="display: none;">
						<c:if test="${order.bankAccounts != null }">
							<table>
								<tr>
									<c:forEach items="${order.bankAccounts}" var="account"
										varStatus="i">
										<td>
											<table
												style="border: 1px solid gray; padding: 50%; margin-left: 10px;">
												<tr>
													<td><div style="padding: 10px;">
															<input type="radio" name="selectedAccountForRefund"
																value="${account.identifier }"
																<c:if test="${i.index == 0}">checked</c:if>>
														</div></td>
													<td style="padding: 10px;">${account.accountNo}<br />
														${account.ifscCode}
													</td>
												</tr>
											</table>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:if>
						<c:if test="${order.bankAccounts == null }">
							<a onclick="showBankDialog();" style="cursor: default;"> Add
								bank account</a>
							<br />
						</c:if>
					</div>
					<br />
					<button type="submit" class="btn btn-success">Place return</button>
				</c:if>
			</c:if>
			<c:if test="${order == null && orderPlaced == null}">
				<h3>Oops. Order is not eligible for return.</h3>
			</c:if>

			<c:if test="${orderPlaced != null && orderPlaced == 'success'}">
				<h3>
					<span style="color: #62bd7f;">Done. Once we receive the
						item(s) and check for its conditions we'll process your refund.
						Redirecting in 10 seconds..</span>
				</h3>
				<script type="text/javascript">
					window.setTimeout(function() {
						window.location = "/myorders";
					}, 10000);
				</script>
			</c:if>

		</div>

		<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
		<script src="resources/assets/returnorder/js/returnorder.js"></script>
		<script src="resources/assets/common/js/bootstrap.min.js"></script>
		<input type="hidden" name="orderId" value="${order.orderId}" />
	</form>

	<div id="bank_account_modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4>Your Bank Accounts</h4>

					<hr />

					<div class="well">
						<table>
							<tr style="vertical-align: top">
								<td><img style="vertical-align: top"
									src="resources/assets/account/images/warning.png"></td>
								<td>Enter your bank account information to enable refund
									credits for your orders. You are authorizing Fallsbuy to share
									your bank account details with our banking partner to
									facilitate the refund.</td>
							</tr>
						</table>
					</div>
					<form action="/addBankAccount" method="post">
						<table class="table" style="margin-bottom: 10%;">

							<tr>
								<td>Account No</td>
								<td><input type="text" id="accountNo" name="accountNo" /></td>
							</tr>
							<tr>
								<td>IFSC Code</td>
								<td><input type="text" id="ifscCode" name="ifscCode" /> <input
									type="hidden" name="fromPage" value="redirect:/myorders" /></td>
							</tr>
							<tr>
								<td colspan="2"><button type="button"
										id="add_account_button">Add account</button></td>
							</tr>

						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="${order.bankAccounts == null}"
		id="bank_accounts" />
</body>
</html>