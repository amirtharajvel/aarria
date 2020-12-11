<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<title>Orders - aarria.com - Women's Wear</title>
<meta name="author" content="aarria">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/myorders/css/myorders.css" rel="stylesheet">

</head>

<body style="padding-top: 7%;">
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/"><img src="resources/assets/images/logo.png"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container">
		<div id="rootwizard">
			<ul style="padding: 10px;">
				<li><a href="#open_orders" data-toggle="tab">Orders</a></li>
				<li><a href="#past_orders" data-toggle="tab">Past Orders</a></li>
				<li><a href="#cancelled_orders" data-toggle="tab">Cancelled
						Orders</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane" id="open_orders">
					<!--  accordion content starts -->
					<c:forEach var="order" items="${openOrders}">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#${order.orderId}"
										aria-expanded="true" aria-controls="collapseOne">
										${order.orderId}</a>
								</h4>
							</div>
							<div id="${order.orderId}" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<div style="float: left; width: 70%">
										<c:forEach items="${order.items}" var="item">
											<div>
												<div>
													<table class="order_items_table" width="100%">
														<tr>
															<td width="30%"><a target="_blank"
																class="product_link" href="product?id=${item.pid}"><img
																	height="150" src="${item.image}" /></a></td>

															<td width="30%"><a target="_blank"
																class="product_link" href="product?id=${item.pid}">${item.productName}</a>
																<br />size: ${item.size} <br /> &#8377;
																${item.unitSoldPrice}<br />${item.quantity}<c:if
																	test="${item.quantity == 1}">&nbsp;no</c:if> <c:if
																	test="${item.quantity > 1}">&nbsp;nos</c:if></td>
															<td width="40%"><c:if
																	test="${ order.orderStatus == 'OPEN' && fn:length(order.items) > 1 && item.status == 'OPEN'}">
																	<form method="post" action="removeItemFromOrder">
																		<input type="hidden" name="orderIdPrimaryKey"
																			value="${order.orderIdPrimaryKey }" /> <input
																			type="hidden" name="removedWhile"
																			value="CANCELLED_WHILE_PACKAGING" /> <input
																			type="hidden" name="itemId" value="${item.itemId }" />
																		<input type="hidden" name="fromPage" value="myorders" />

																		<c:if test="${order.isCashOnDelivery}">
																			<button type="submit" id="remove_item_button"
																				class="btn btn-success">Remove Item</button>
																		</c:if>
																		<c:if test="${order.isCashOnDelivery == false}">
																			<button type="button"
																				onclick="removeItemForNonCOD(${order.orderIdPrimaryKey}, ${item.itemId}, ${item.unitSoldPrice},${item.quantity})"
																				class="btn btn-success">Remove Item</button>
																		</c:if>
																	</form>
																</c:if> <c:if test="${item.status != 'OPEN' }">
																	<c:if test="${item.status == 'REFUND_INITIATED' }">
																		<strong> <c:if
																				test="${item.returnCashMethod == '0' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Wallet shortly.
																		</c:if> <c:if test="${item.returnCashMethod == '1' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
																		</strong>
																	</c:if>
																	<c:if test="${item.status == 'REFUNDED' }">
																		<strong> Amount was refunded to your <c:if
																				test="${item.returnCashMethod == '0'}">Wallet</c:if>
																			<c:if test="${item.returnCashMethod == '1'}">bank account</c:if></strong>
																	</c:if>
																</c:if><strong> <c:if
																		test="${item.status == 'CANCELLED_WITHIN_30_MINS' }">You cancelled this item.</c:if>
																	<c:if
																		test="${item.status == 'CANCELLED_WHILE_PACKAGING' }">You cancelled this item while packaging.</c:if></strong>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</c:forEach>
									</div>
									<div style="color: #555555; float: left; width: 30%">

										<c:if test="${ order.orderStatus == 'DISPATCHED'}">
											<button type="button"
												onclick="window.open('${order.delivery.trackingUrl}','_blank')"
												id="track_package_button" class="btn btn-success">Track
												Package</button>
											<br />${order.delivery.trackingNoName} : <strong>${order.delivery.trackingNo}</strong>
										</c:if>
										<c:if test="${ order.orderStatus == 'OPEN'}">

											<button type="button"
												onclick="cancelOrder('${order.orderIdPrimaryKey}',${order.isCashOnDelivery})"
												id="cancel_order_button"
												class="btn btn-success launchConfirm">Cancel Order</button>

										</c:if>
										<br /> <br />
										<c:if test="${order.orderStatus == 'BEING_PACKED'}">
											<strong><span style="color: #62bd7f;">Order
													is being prepared for delivery.</span></strong>
										</c:if>
										<br /> Total : &#8377; <strong>${order.totalOrderPrice}</strong><br />
										<c:if test="${not empty order.walletAmountUsed }">
										Amount to pay: &#8377; <strong>${order.totalOrderPrice - walletAmountUsed}</strong>
											<br />
										</c:if>
										Order Placed: <strong>${order.orderedDate}</strong> <br />Expected
										Delivery : <strong> Within 24 hours (Only in
											Bengaluru) </strong> <br /> Address : <br /> <strong>${order.shippingAddress}</strong>
										<br /> <br /> Payment method:<strong> <c:if
												test="${order.isCashOnDelivery == true}">Cash On Delivery</c:if>
											<c:if test="${order.isCashOnDelivery == false}">Non COD</c:if></strong>
										<br />
										<c:if test="${ order.orderStatus == 'DISPATCHED'}">

											<br />
											Cancellation Status: <p>
												You cannot cancel an item after it is dispatched. You can
												ONLY return after you receive. Please call <a
													href="aboutus#contact_us">customer care</a> for further
												details or read our <a href="/returnpolicy">Return
													Policy</a>
											</p>
										</c:if>
										<c:if test="${ order.orderStatus == 'BEING_PACKED'}">
											<br />
											<br />
											<strong>Cancellation Status: </strong>
											<p>
												Once the item is being prepared for delivery you can still
												call the <a href="aboutus#contact_us">customer care</a> and
												cancel the items. <br /> <strong>Please Note:</strong>
												&nbsp;You cannot cancel after it is dispatched. You can only
												return it after you receive it. For further details read our
												<a href="/returnpolicy">Return Policy.</a>
											</p>
										</c:if>
									</div>

								</div>
							</div>
						</div>
					</c:forEach>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="past_orders">
					<!--  accordion content starts -->
					<c:forEach var="order" items="${deliveredOrders}">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#${order.orderId}"
										aria-expanded="true" aria-controls="collapseOne">
										${order.orderId}</a>
								</h4>
							</div>
							<div id="${order.orderId}" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<div style="float: left; width: 50%">
										<c:forEach items="${order.items}" var="item">
											<table class="order_items_table" width="100%">
												<tr>
													<td width="30%"><img height="150" src="${item.image}" /></td>
													<td width="30%">${item.productName}<br />size:
														${item.size} <br /> &#8377; ${item.unitSoldPrice}<br />${item.quantity}<c:if
															test="${item.quantity == 1}">&nbsp;no</c:if> <c:if
															test="${item.quantity > 1}">&nbsp;nos</c:if></td>
													<td>
														<%-- <c:if
															test="${ item.returnStatus.equals('RETURN_PICKUP_REQUESTED')}">
															Your pick up time is scheduled on  
															</c:if> --%> <c:if test="${item.status != 'OPEN' }">
															<c:if test="${item.status == 'REFUND_INITIATED' }">
																<strong> <c:if
																		test="${item.returnCashMethod == '0' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Wallet shortly.
																		</c:if> <c:if test="${item.returnCashMethod == '1' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
																</strong>
															</c:if>

														</c:if><strong> <c:if
																test="${item.status == 'CANCELLED_WITHIN_30_MINS' }">You cancelled this item.</c:if>
															<c:if
																test="${item.status == 'CANCELLED_WHILE_PACKAGING' }">You cancelled this item while packaging.</c:if></strong>


														<c:if test="${item.status == 'REFUND_INITIATED' }">
															<strong> <c:if
																	test="${order.returnCashMethod == '0' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Wallet.
																		</c:if> <c:if test="${order.returnCashMethod == '1' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
															</strong>
														</c:if> <c:if test="${item.status == 'REFUNDED' }">
															<strong> Amount was refunded to your <c:if
																	test="${order.returnCashMethod == '0'}">Wallet</c:if> <c:if
																	test="${order.returnCashMethod == '1'}">bank account</c:if></strong>
														</c:if>
													</td>
													<c:if
														test="${item.status.equals('RETURN_PICKUP_REQUESTED')}">
														<td><div>
																<strong>You returned this item</strong>
															</div></td>
													</c:if>
												</tr>
											</table>
										</c:forEach>
									</div>
									<div style="float: left; width: 50%">
										<c:if
											test="${order.returnTimeLimit.equals('RETURN_TIME_LIMIT_NOT_EXCEEDED')}">
											<button type="button"
												onclick="showReturnScreen('${order.orderId}')"
												id="return_items_button"
												class="btn btn-success launchConfirm">Return
												item(s)</button>
											<br />
										</c:if>

										<c:if
											test="${order.showReturnPickup == true and order.delivery.isReturnShippingNotAvailable == true}">
											<h4>Please return the items to</h4>
											#304, 57/2, 14th cross, Ejipura, Vivek nagar [po], Bangalore - 560047 <br />
										</c:if>

										<c:if
											test="${order.showReturnPickup == true and order.delivery.isReturnShippingNotAvailable == false and order.isReturnPickupArranged == true}">
											<h4>Return pick up schedule</h4>
											<strong>Pick up date and time:</strong>
											<br /> ${order.pickupTime}
											<br />
											<br />
											<strong>Pick up address: </strong>
											<br />${order.pickupAddress}  <br />
											<br />
											<strong>Pick up courier: </strong>
											<br />${order.delivery.returnCourierServiceName}  <br />
											<br />
											<strong>Pick up tracking Id: </strong>
											<br />${order.delivery.returnTrackingId}  <br />
											<br />
											<strong>Returned Date: </strong>
											<br />${order.returnedDate}  <br />
											<br />
											<strong>Refund Status:</strong>
											<br /> Once we receive the item and
												check for its conditions, we'll process your refund.
											<br />

										</c:if>
										<br /> Total : &#8377; <strong>${order.totalOrderPrice}
										</strong><br />Order Placed: <strong>${order.orderedDate}</strong><br />
										Delivered Date : <strong>${order.deliveredDate}</strong> <br />
										Address : <br /> <strong>${order.shippingAddress}</strong> <br />
										<br />
										<c:if test="${order.orderStatus == 'REFUND_INITIATED'}">
											<br />
										Refund Status:
											<strong> <c:if
													test="${order.returnCashMethod == '0' }">
																			Your amount ${order.totalOrderPrice } will be credited to your Wallet shortly.
																		</c:if> <c:if test="${order.returnCashMethod == '1' }">
																			Your amount ${order.totalOrderPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
											</strong>
										</c:if>
										<c:if test="${order.isReturnPickupRequested == true }">
											You have placed a request to arrange a return for this order. We'll get back to you shortly after arranging the pick up service. <br />
											<strong>Please Note: In case, for any reason if the
												return pick up cannot be arranged please send the items to
												the mentioned address. Please read full <a
												href="returnpolicy">return policy</a> for more details.
											</strong>
										</c:if>
										<c:if test="${order.orderStatus == 'REFUNDED' }">
											<br />
											Refund Status:<br />
											<strong> Amount was refunded to your <c:if
													test="${order.returnCashMethod == '0'}">Wallet</c:if> <c:if
													test="${order.returnCashMethod == '1'}">bank account</c:if></strong>
										</c:if>
										<br /> <br /> <a target="_blank"
											href="viewinvoice?orderId=${order.orderId }">View invoice</a>
									</div>

								</div>
							</div>
						</div>
					</c:forEach>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="cancelled_orders">
					<!--  accordion content starts -->
					<c:forEach var="order" items="${cancelledOrders}">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#${order.orderId}"
										aria-expanded="true" aria-controls="collapseOne">
										${order.orderId}</a>
								</h4>
							</div>
							<div id="${order.orderId}" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<div style="float: left; width: 50%">
										<c:forEach items="${order.items}" var="item">
											<div>
												<div>
													<table class="order_items_table" width="100%">
														<tr>
															<td width="30%"><a target="_blank"
																class="product_link" href="product?id=${item.pid}"><img
																	height="150" src="${item.image}" /></a></td>

															<td width="30%"><a target="_blank"
																class="product_link" href="product?id=${item.pid}">${item.productName}</a>
																<br />size: ${item.size} <br /> &#8377;
																${item.unitSoldPrice}<br />${item.quantity}<c:if
																	test="${item.quantity == 1}">&nbsp;no</c:if> <c:if
																	test="${item.quantity > 1}">&nbsp;nos</c:if></td>
															<td><c:if
																	test="${ order.orderStatus == 'OPEN' && fn:length(order.items) > 1 && item.status == 'OPEN'}">
																	<form method="post" action="removeItemFromOrder">
																		<input type="hidden" name="orderIdPrimaryKey"
																			value="${order.orderIdPrimaryKey }" /> <input
																			type="hidden" name="removedWhile"
																			value="CANCELLED_WHILE_PACKAGING" /> <input
																			type="hidden" name="itemId" value="${item.itemId }" />
																		<input type="hidden" name="fromPage" value="myorders" />

																		<c:if test="${order.isCashOnDelivery}">
																			<button type="submit" id="remove_item_button"
																				class="btn btn-success">Remove Item</button>
																		</c:if>
																		<c:if test="${order.isCashOnDelivery == false}">
																			<button type="button"
																				onclick="removeItemForNonCOD(${order.orderIdPrimaryKey}, ${item.itemId}, ${item.unitSoldPrice},${item.quantity})"
																				class="btn btn-success">Remove Item</button>
																		</c:if>
																	</form>
																</c:if> <c:if test="${item.status != 'OPEN' }">
																	<c:if test="${item.status == 'REFUND_INITIATED' }">
																		<strong> <c:if
																				test="${item.returnCashMethod == '0' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Wallet shortly.
																		</c:if> <c:if test="${item.returnCashMethod == '1' }">
																			Your amount ${item.totalSoldPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
																		</strong>
																	</c:if>
																</c:if></td>
														</tr>
													</table>
												</div>
											</div>
										</c:forEach>
									</div>
									<div style="color: #555555; float: left; width: 50%">

										<br /> <br /> Total : &#8377; <strong>${order.totalOrderPrice}</strong><br />Order
										Placed: <strong>${order.orderedDate}</strong> <br /> Payment
										method:<strong> <c:if
												test="${order.isCashOnDelivery == true}">Cash On Delivery</c:if>
											<c:if test="${order.isCashOnDelivery == false}">Non COD</c:if></strong>
										<c:if test="${order.isCashOnDelivery == false}">
											<c:if test="${order.orderStatus == 'REFUND_INITIATED'}">
												<br />
										Refund Status:
											<strong> <c:if
														test="${order.returnCashMethod == '0' }">
																			Your amount ${order.totalOrderPrice } will be credited to your Wallet shortly.
																		</c:if> <c:if test="${order.returnCashMethod == '1' }">
																			Your amount ${order.totalOrderPrice } will be credited to your Bank account. It'll take 4-5 business days to reflect in your account.
																		</c:if>
												</strong>
											</c:if>
											<c:if test="${order.orderStatus == 'REFUNDED' }">
												<br />
											Refund Status:<br />
												<strong> Amount was refunded to your <c:if
														test="${order.returnCashMethod == '0'}">Wallet</c:if> <c:if
														test="${order.returnCashMethod == '1'}">bank account.</c:if></strong>
											</c:if>
										</c:if>
									</div>

								</div>
							</div>
						</div>
					</c:forEach>
					<!--  accordion content ends -->
				</div>

			</div>
		</div>
	</div>

	<div id="removeItemModal" class="modal fade">
		<form action="/removeItemFromOrder" id="removeItemForm" method="post"
			name="return">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Confirm Remove Item</h4>
					</div>
					<div class="modal-body" style="padding-top: 0px;">
						<h3>
							&#8377;<span id="item_price"></span>
						</h3>
						<div id="remove_item_return_cash_method">
							<p>
								<input type="radio" name="returnCashMethod" value="0"
									onclick="hidebankaccountsforcancelorder('remove_item_bank_accounts')"
									checked> Add cash back to the Wallet (Immediate)<br>
								<input type="radio" name="returnCashMethod" value="1"
									onclick="showbankaccountsforcancelorder('remove_item_bank_accounts')">
								Revert cash to my bank account (takes 4-5 business days)<br>
							</p>
						</div>
						<div id="remove_item_bank_accounts">
							<c:if test="${bankAccounts != null }">
								<table>
									<tr>
										<c:forEach items="${bankAccounts}" var="account" varStatus="i">
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
							<c:if test="${bankAccounts == null }">
								<a onclick="showAddBankAccountModal()" style="cursor: default;">
									Add bank account</a>
							</c:if>
						</div>
						<!-- <p class="text-warning">
							<small>Your request will be processed shortly.</small>
						</p> -->
					</div>
					<div class="modal-footer">
						<input type="hidden" name="orderIdPrimaryKey"
							id="orderIdPrimaryKey" /> <input type="hidden" name="itemId"
							id="itemId" /> <input type="hidden" name="fromPage"
							id="fromPage" value="myorders" />
						<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
						<button type="submit" id="submit_return" class="btn btn-success">Remove
							Item</button>
					</div>
				</div>
			</div>
			<input type="hidden" id="orderItemId" name="orderItemId" />
		</form>
	</div>

	<div id="cancelOrderModal" class="modal fade">
		<form action="cancelOrder" id="cancelOrderForm" method="post"
			name="return">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Confirm Cancellation</h4>
					</div>
					<div class="modal-body">
						<div>
							<p>Please mention the reason for cancellation</p>
							<div class="return_text_area">
								<div>
									<textarea maxlength="100" id="reason_for_cancel_textarea"
										minlength="10" name="comments" rows="2" cols="50"
										required="required"></textarea>
								</div>
							</div>
						</div>
						<div id="cancel_order_cashback_method">
							<p>
								<input type="radio"
									onclick="hidebankaccountsforcancelorder('cancel_order_bank_accounts')"
									name="returnCashMethod" value="0" checked> Add cash
								back to the Wallet (Immediate)<br> <input type="radio"
									name="returnCashMethod" value="1"
									onclick="showbankaccountsforcancelorder('cancel_order_bank_accounts')">
								Revert cash to my bank account (takes 4-5 business days)<br>
							</p>
							<div id="cancel_order_bank_accounts">
								<c:if test="${bankAccounts != null }">
									<table>
										<tr>
											<c:forEach items="${bankAccounts}" var="account"
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
								<c:if test="${bankAccounts == null }">
									<a onclick="showAddBankAccountModal()" style="cursor: default;">
										Add bank account</a>
								</c:if>
							</div>
							<!-- <p class="text-warning">
								<br /> <small>Your request will be processed within 2-3
									business days.</small>
							</p> -->
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="orderIdPrimaryKey"
							id="cancel_order_hidden_orderIdPrimaryKey" /> <input
							type="hidden" name="fromPage" id="fromPage" value="myorders" />

						<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
						<button type="submit" id="confirm_cancel_order"
							class="btn btn-success">Cancel Order</button>
					</div>
				</div>
			</div>
			<input type="hidden" id="orderItemId" name="orderItemId" />
		</form>
	</div>



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
					<form action="/addBankAccount" method="post"
						id="add_bank_account_form">
						<table class="table" style="margin-bottom: 10%;">

							<tr>
								<td>Account No</td>
								<td><input type="text" id="accountNo" name="accountNo"
									required="required" /></td>
							</tr>
							<tr>
								<td>IFSC Code</td>
								<td><input type="text" id="ifscCode" name="ifscCode"
									required="required" /> <input type="hidden" name="fromPage"
									value="redirect:/myorders" /></td>
							</tr>
							<tr>
								<td colspan="2" align="left">

									<button type="button" class="btn btn-success"
										data-dismiss="modal">Close</button> &nbsp;

									<button type="button" class="btn btn-success"
										id="add_account_button">Add account</button>
								</td>
							</tr>

						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="current_action_for_add_account" />

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/myorders/js/myorders.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/common/js/pills.js"></script>
</body>
</html>