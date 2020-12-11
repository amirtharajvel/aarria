$(document).ready(function() {
	onSubmitRules();
	onRefund();
});

function showRefundModal(status) {

	if (status == 'DISPATCHED' || status == 'REFUND_INITIATED'
			|| status == 'DELIVERED') {

		var refundableAmount = parseInt($("#total_refundable_amount").val());
		if (Number(refundableAmount) <= 0) {
			alert("Sorry.. you cannot make a zero rupee refund");
			return;
		}

		$('#refundModal').modal('show');
	} else {
		if (status === 'REFUNDED') {
			alert("Already refunded");
		} else {
			alert("You cannot refund before dispatch!");
		}
	}
}

function onRefund() {
	$("#refund_form")
			.submit(
					function(evt) {
						var refundableAmount = parseInt($(
								"#total_refundable_amount").val());
						var actualAmountEntered = parseInt($(
								"#totalAmountToBeRefundedForOrder").val());

						if (Number(actualAmountEntered) > Number(refundableAmount)) {
							alert("Why are you entering more amount than the actual?");
							evt.preventDefault();
						}

						var returnCash = $("#return_cash_method_for_order")
								.val();
						var refundReceiptNumber = $("#refundReceiptNumber")
								.val().length;
						if (returnCash === 'BANK_ACCOUNT'
								&& Number(refundReceiptNumber) < 5) {
							alert("Please make sure you enter the receipt number");
							evt.preventDefault();
						}
					});
}

function showPictures(pid) {
	console.log("pid " + pid);
	var pics = $("#" + pid).val();
	console.log("pics " + pics);
	var arr = pics.split(",");
	var images = "";
	for (i = 0; i < arr.length; i++) {
		images += "<img src='" + arr[i] + "' />";
	}

	$("#picture").html(images);
	$('#picture_modal').modal('show');
}

function arrangeReturn(orderIdPrimaryKey, itemId) {
	$('#arrangeReturnModal').modal('show');
}

function approveReturn(orderIdPrimaryKey, itemId) {
	$('#approveReturnModal').modal('show');
}

function removeItem(orderIdPrimaryKey, itemId) {

	var itemsOpen = $("#how_many_open_items").val();

	var currentOrderStatus = $("#order_status_hidden").val();

	if (currentOrderStatus === 'REFUNDED') {
		alert("Order life cycle is over. You cannot do anything other than just view?!");
		return;
	}

	if (itemsOpen === '1') {
		alert("Please cancel the order.. as only one item left");
		return;
	}

	var isCashOnDeliveryHidden = $("#isCashOnDeliveryHidden").val();
	if (isCashOnDeliveryHidden === 'true') {
		$("#return_cash_method_div").hide();
	}

	$("#orderIdPrimaryKey").val(orderIdPrimaryKey);
	$("#itemId").val(itemId);
	$("#fromPage").val("vieworderdetailsasadmin/" + orderIdPrimaryKey);
	$('#removeItemModal').modal('show');
}

// All the rules combination for this page goes here
function onSubmitRules() {
	$("#updateOrderForm")
			.submit(
					function(evt) {

						var orderStatus = $('#orderStatus option:selected')
								.text();

						var currentOrderStatus = $("#order_status_hidden")
								.val();

						if (currentOrderStatus === 'REFUNDED') {
							alert("Order life cycle is over. You cannot do anything other than just view?!");
							evt.preventDefault();
						}

						if (currentOrderStatus === 'REFUND_INITIATED') {
							alert("You can only refund through refund button");
							evt.preventDefault();
						}

						if (orderStatus === 'CANCELLED_WITHIN_30_MINS') {
							alert("Hey.....You chose wrong status. You cannot cancel with in 30 mins. You've to choose CANCELLED_WHILE_PACKAGING");
							evt.preventDefault();
						}

						if (orderStatus === 'CANCELLED_WHILE_PACKAGING') {

							var isReturnCashSelected = false;

							$(
									'#order_return_cash_method input[name="returnCashMethod"]')
									.each(
											function() {
												if ($(this).is(':checked')) {
													isReturnCashSelected = $(
															this).val();
												}
											});

							var isCashOnDeliveryHidden = $(
									"#isCashOnDeliveryHidden").val();

							if (isReturnCashSelected === false
									&& isCashOnDeliveryHidden === 'false') {
								alert("Hey.....Please select a return cash back method ");
								evt.preventDefault();
							}

							var bankAccounts = $("#bank_accounts_length").val();
							console.log("isReturnCashSelected "
									+ isReturnCashSelected + ", bankAccounts "
									+ bankAccounts);

							if (bankAccounts === '0'
									&& isReturnCashSelected === '1') {
								alert("Please call and ask the user to enter a bank account.. then proceed with refund. A bank account must be there to deposit into bank account. Or ask the user to credit in Wallet.");
								evt.preventDefault();
							}
						}
					});

}