<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order id - ${orderIdPrimaryKey}</title>
<link href="/resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<style>
h3 {
	color: red;
}

tr {
	line-height: 2em;
}

#form td {
	padding: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<div id="order_details">
			<table class="table-striped">
				<tr>
					<td width="50%">Order Status</td>
					<td width="50%">${order.status}&nbsp;&nbsp;<a target="_blank"
						href="/printorderinvoice?orderId=${order.orderId}">Print Courier
							Invoice</a></td>
				</tr>
				<tr>
					<td width="50%">Order No</td>
					<td width="50%">${order.id}</td>
				</tr>
				<tr>
					<td width="50%">Order ID</td>
					<td width="50%">${order.orderId}</td>
				</tr>
				<tr>
					<td width="50%">Order Date</td>
					<td width="50%">${order.orderedDate}</td>
				</tr>
				<tr>
					<td width="50%">User Mobile</td>
					<td width="50%">${order.mobile}</td>
				</tr>
				<tr>
					<td width="50%">Shipping Date</td>
					<td width="50%">${order.delivery.shippingDate}</td>
				</tr>
				<c:if
					test="${order.status == 'OPEN' || order.status == 'BEING_PACKED' || order.status == 'DISPATCHED'}">
					<tr>
						<td width="50%">Expected Delivery Date</td>
						<td width="50%">${order.delivery.expectedDeliveryDate}</td>
					</tr>
				</c:if>
				<c:if
					test="${order.status != 'OPEN' || order.status != 'BEING_PACKED' || order.status != 'DISPATCHED'}">
					<tr>
						<td width="50%">Delivered Date</td>
						<td width="50%">${order.delivery.deliveredDate}</td>
					</tr>
				</c:if>
				<tr>
					<td width="50%">Shipping Address</td>
					<td width="50%">${order.delivery.shippingAddress}</td>
				</tr>
				<tr>
					<td width="50%">Total Amount</td>
					<td width="50%">${order.totalOrderAmount}</td>
				</tr>
				<tr>
					<td width="50%">Payment mode</td>
					<td width="50%"><c:if test="${order.isCashOnDelivery == true}">Cash On Delivery</c:if>
						<c:if test="${order.isCashOnDelivery == false}">NON COD</c:if> <input
						type="hidden" id="isCashOnDeliveryHidden"
						value="${order.isCashOnDelivery}" /></td>
				</tr>
				<tr>
					<td width="50%">Catalogue</td>
					<td width="50%">${order.catalogue}</td>
				</tr>
				<c:if test="${order.showRefundButton == true}">
					<tr>
						<td width="50%">Refund</td>
						<td width="50%"><button
								onclick="showRefundModal('${order.status}')" type="button"
								class="btn btn-success">Refund</button></td>
					</tr>
				</c:if>
				<c:if test="${order.returnCashMethod != null }">
					<tr>
						<td width="50%">Refund To</td>
						<td width="50%">${order.returnCashMethod}<c:if
								test="${order.returnCashMethod != null && order.returnCashMethod == 'BANK_ACCOUNT' && order.selectedBankAccountForRefund != null}"> - ${order.selectedBankAccountForRefund}</c:if>
						</td>
					</tr>
				</c:if>
				<c:if test="${order.showArrangeReturnButton == true}">
					<tr>
						<td width="50%">Arrange Return</td>
						<td width="50%">
							<button type="button"
								onclick="arrangeReturn(${order.id }, ${item.itemId })"
								id="remove_item_button" class="btn btn-success">Arrange
								Return</button>
						</td>
					</tr>
				</c:if>
				<c:if test="${order.isReturnPickupArranged == true}">
					<tr>
						<td width="50%">Approve Return</td>
						<td width="50%">
							<button type="button"
								onclick="approveReturn(${order.id }, ${item.itemId })"
								id="remove_item_button" class="btn btn-success">Approve
								Return</button>
						</td>
					</tr>
				</c:if>
				<c:if
					test="${order.status != 'OPEN' && order.status != 'BEING_PACKED'}">

					<tr>
						<td width="50%">Tracking ID</td>
						<td width="50%">${order.delivery.trackingId}</td>
					</tr>
					<tr>
						<td width="50%">Shipper</td>
						<td width="50%">${order.delivery.shipper}</td>
					</tr>
					<tr>
						<td width="50%">Shipping Charge</td>
						<td width="50%">&#8377; ${order.delivery.shippingCharge}</td>
					</tr>
				</c:if>

				<c:if test="${order.showReturnPickup == true}">

					<tr>
						<td width="50%">Return Tracking Id</td>
						<td width="50%">${order.delivery.returnTrackingId}</td>
					</tr>
					<tr>
						<td width="50%">Return Courier</td>
						<td width="50%">${order.delivery.returnCourierServiceName}</td>
					</tr>
					<tr>
						<td width="50%">Return Shipping Charge</td>
						<td width="50%">&#8377;
							${order.delivery.returnShippingCharge}</td>
					</tr>

				</c:if>
			</table>
			<form action="/updateOrder" id="updateOrderForm" method="post">
				<table id="form"
					style="border: 2px solid gray; width: 100%; margin-top: 20px; margin-bottom: 20px;"
					class="table-bordered">

					<tr>
						<td width="50%">New Status</td>
						<td width="50%"><select name="orderStatus" id="orderStatus">
								<c:forEach items="${order.orderStatusEnum}" var="orderStatus">
									<option value="${orderStatus}"
										${orderStatus == order.status ? 'selected="selected"' : '' }>${orderStatus}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%">Shipper</td>
						<td width="50%"><select name="shipper">
								<c:forEach items="${order.shipper}" var="shipper">
									<li><a href="#">${shipper}</a></li>
									<option value="${shipper}"
										${shipper == order.delivery.shipper ? 'selected="selected"' : '' }>${shipper}</option>
								</c:forEach>
						</select></td>
					</tr>
					<c:if test="${order.status == 'BEING_PACKED'}">
						<tr>
							<td>Shipping Charge</td>
							<td><input type="text" name="shippingCharge"
								required="required" value="${ order.delivery.shippingCharge}" /></td>
						</tr>
						<tr>
							<td width="50%">Tracking Name</td>
							<td width="50%"><input type="text" name="trackingName"
								value="${ order.delivery.trackingName}" /></td>
						</tr>
						<tr>
							<td>Tracking ID</td>
							<td><input type="text" name="trackingId" required="required"
								value="${order.delivery.trackingId}" /></td>
						</tr>
					</c:if>
					<tr>
						<td>Cashback method</td>
						<td><div id="order_return_cash_method">
								<p>
									<input type="radio" name="returnCashMethod" value="0">
									Add cash back to the Wallet (Immediate)<br> <input
										type="radio" name="returnCashMethod" value="1"> Revert
									Cash to my bank Account (takes 4-5 business days)<br>
								</p>
							</div></td>
					</tr>

					<tr>
						<td width="50%">Select Bank Account</td>
						<td width="50%"><c:if test="${order.bankAccounts != null }">
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
																	value="${account.identifier }">

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
							</c:if></td>
					</tr>
					<tr>
						<td>&nbsp;<input type="hidden" value="${order.id}"
							name="orderId" /></td>
						<td><input type="submit" value="Update" /></td>
					</tr>
				</table>
			</form>
			<br />
			<table class="table">
				<thead>
					<tr>
						<th>Image</th>
						<th>Quantity</th>
						<th>Size</th>
						<th>Weight</th>
						<th>Outside Price</th>
						<th>Original Price</th>
						<th>Unit Sold Price</th>
						<th>Total Sold Price</th>
						<th>Status</th>
						<th>Cash Return Method</th>
						<th>Remove</th>

					</tr>
				</thead>
				<c:set var="i" value="0" scope="page" />
				<c:forEach items="${order.items}" var="item" varStatus="loop">
					<c:if test="${item.status == 'OPEN'}">
						<c:set var="i" value="${i + 1}" scope="page" />
					</c:if>
					<tr>
						<td><img onclick="showPictures('${item.product.pid}')"
							src="${item.product.image}" height="150" width="100" /> <input
							type="hidden" id="${item.product.pid}"
							value="${item.product.additionalImages}" /></td>
						<td><h3>
								<strong>${item.quantity}</strong>
							</h3></td>
						<td><h3>
								<strong>${item.size}</strong>
							</h3></td>
						<td>${item.product.weight }</td>
						<td>${item.actualPrice}</td>
						<td>${item.originalPrice}</td>
						<td>${item.unitSoldPrice}</td>
						<td>${item.totalSoldPrice}</td>
						<td>${item.status}</td>
						<td>${item.returnCashMethod}</td>
						<td>
							<button type="button"
								onclick="removeItem(${order.id }, ${item.itemId })"
								id="remove_item_button" class="btn btn-success">Remove
								Item</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" value="${i}" id="how_many_open_items" />
		</div>
	</div>
</body>
<script src="/resources/assets/common/js/jquery-2.1.4.min.js"></script>
<script src="/resources/assets/common/js/bootstrap.min.js"></script>
<script src="/resources/assets/vieworderdetails/js/vieworderdetails.js"></script>

<div id="picture_modal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" id="modal_content">
			<div class="modal-body">
				<div id="picture"></div>
			</div>
		</div>
	</div>
</div>

<div id="removeItemModal" class="modal fade">
	<form method="post" action="/removeItemFromOrder">
		<input type="hidden" name="orderId" id="cancel_order_hidden_orderId" />
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
								<textarea minlength="10" name="comments" rows="3" cols="50">Cancel before dispatch</textarea>
							</div>
						</div>
					</div>
					<div id="return_cash_method_div">
						<p>
							<input type="radio" name="returnCashMethod" value="0">
							Add cash back to the Wallet (Immediate)<br> <input
								type="radio" name="returnCashMethod" value="1"> Revert
							Cash to my bank Account (takes 4-5 business days)<br>
						</p>
						<p class="text-warning">
							<small>Your request will be processed shortly.</small>
						</p>
					</div>
				</div>
				<div class="modal-footer">

					<input type="hidden" name="orderIdPrimaryKey"
						id="orderIdPrimaryKey" /> <input type="hidden" name="itemId"
						id="itemId" /> <input type="hidden" name="fromPage" id="fromPage"
						value="vieworderdetailsasadmin/${order.id}" />
					<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
					<button type="submit" id="confirm_cancel_order"
						class="btn btn-success">Remove Item</button>


				</div>
			</div>
		</div>
		<input type="hidden" id="orderItemId" name="orderItemId" />
	</form>
</div>

<div id="refundModal" class="modal fade">
	<form method="post" id="refund_form" action="/refund">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirm Refund</h4>
				</div>
				<div class="modal-body">
					<h3>Total amount to be refunded: ${order.totalRefundableAmount }</h3>
					<h5>Return Cash to : ${order.returnCashMethod }</h5>
					<hr />
					<br /> Enter the amount to refund : <input type="text"
						name="totalAmountToBeRefundedForOrder"
						id="totalAmountToBeRefundedForOrder"
						value="${ order.totalRefundableAmount}"> <br /> Enter the
					receipt number <input type="text" id="refundReceiptNumber"
						name="refundReceiptNumber" value="NA" /> <input type="hidden"
						name="orderIdPrimaryKey" value="${order.id }" /> <input
						type="hidden" id="total_refundable_amount"
						value="${order.totalRefundableAmount }" /> <input type="hidden"
						id="return_cash_method_for_order"
						value="${order.returnCashMethod }" />
					<button type="submit">Confirm Refund</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="arrangeReturnModal" class="modal fade">
	<form method="post" id="refund_form" action="/arrangeReturn">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirm return</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="orderIdPrimaryKey" value="${order.id }" />
					<br /> Enter the tracking id : <input type="text"
						name="returnTrackingId" /> <br /> <br /> Enter the return
					courier service name : <input type="text"
						name="returnCourierServiceName" /> <br /> <br /> Enter the
					return shipping charge : <input type="number"
						name="returnShippingCharge" /> <br /> <br /> Enter the return
					shipping date and time : <input type="text"
						name="returnPickupDateAndTime" /> <br /> <br /> Enter the
					return shipping date and time : <input type="text"
						name="returnPickupAddress" /> <br /> <br /> <input
						type="checkbox" name="isReturnShippingNotAvailable" value="true">Shipping
					Not available<br /> <br />
					<button type="submit">Arrange Return</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="approveReturnModal" class="modal fade">
	<form method="post" id="refund_form" action="/approveReturn">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirm return</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="orderIdPrimaryKey" value="${order.id }" />
					<br /> Enter the tracking id : <input type="text"
						name="returnTrackingId" value="${order.delivery.returnTrackingId}" />
					<br /> <br /> Enter the return courier service name : <input
						type="text" name="returnCourierServiceName"
						value="${order.delivery.returnCourierServiceName}" /> <br /> <br />
					Enter the return shipping charge : <input type="number"
						name="returnShippingCharge"
						value="${order.delivery.returnShippingCharge}" /> <br /> <br />
					<button type="submit">Approve Return</button>
				</div>
			</div>
		</div>
	</form>
</div>

<input type="hidden" id="order_status_hidden" value="${order.status}" />
<input type="hidden" id="bank_accounts_length"
	value="${fn:length(order.bankAccounts)}" />

</html>
