$(document).ready(function() {
	pills();

	$('#return_').click(function(e) {
		$('#orderItemId').val($('#orderItemId_hidden').val());
		$("#orderIdPrimaryKey").val(orderIdPrimaryKey);
		$("#itemId").val(itemId);
		$("#myModal").modal('show');
	});

	onCancelOrderSubmit();
	onRemoveItemSubmit();
	onAddAccount();
});

function onAddAccount() {
	$("#add_account_button").click(function(e) {

		var action = $("#current_action_for_add_account").val();
		if (action === 'CANCEL_ORDER') {
			submitAddAccount('cancel_order_bank_accounts');
		} else if (action === 'REMOVE_ITEM') {
			submitAddAccount('remove_item_bank_accounts');
		}
	});
}

function submitAddAccount(div) {
	$
			.ajax({
				url : "/addBankAccount",
				method : 'POST',
				data : getBankInput(),
				beforeSend : function() {
				},
				complete : function() {
				},
				success : function(data) {
					$("#bank_account_modal").modal('hide');
					var accounts = data;
					var a_table = "<table style='border: 1px solid gray; padding: 50%; margin-left: 10px;'>";
					for (i = 0; i < accounts.length; i++) {
						var account = accounts[i];
						a_table += "<tr><td><div style='padding: 10px;'><input type='radio' name='selectedAccountForRefund' value='"
								+ account.identifier
								+ "' checked></div></td><td style='padding: 10px;'>"
								+ account.accountNo
								+ "<br />"
								+ account.ifscCode + "</td></tr>";
					}
					a_table += "</table>";

					$("#" + div).html(a_table);
				}
			});
}

function getBankInput() {
	return "accountNo=" + $("#accountNo").val() + "&ifscCode="
			+ $("#ifscCode").val() + "&fromPage=redirect:/myorders";
}

function showReturnScreen(orderId) {
	window.open("/returnorder?orderId=" + orderId, "_blank");
	// $("#returnItemsModal").modal('show');
}

function onCancelOrderSubmit() {

	$("#confirm_cancel_order").html('Cancel Order');
	$("#confirm_cancel_order").attr("disabled", false);

	$("#cancelOrderForm")
			.submit(
					function(evt) {

						var returnCashMethod = $(
								'#cancel_order_cashback_method input[name=returnCashMethod]:checked')
								.val();
						var selectedAccountForRefund = $(
								'#cancel_order_cashback_method input[name=selectedAccountForRefund]:checked')
								.val();

						if (returnCashMethod === '1'
								&& selectedAccountForRefund === undefined) {

							evt.preventDefault();
							$("#current_action_for_add_account").val(
									'CANCEL_ORDER');

							$('#bank_account_modal').modal({
								backdrop : 'static',
								keyboard : false
							})
						} else {
							$("#confirm_cancel_order").html('Please wait..');
							$("#confirm_cancel_order").attr("disabled", true);
						}
					});
}

function onRemoveItemSubmit() {
	$("#submit_return").html('Remove Item');
	$("#submit_return").attr("disabled", false);
	$("#removeItemForm")
			.submit(
					function(evt) {

						var returnCashMethod = $(
								'#remove_item_return_cash_method input[name=returnCashMethod]:checked')
								.val();
						var selectedAccountForRefund = $(
								'#remove_item_bank_accounts input[name=selectedAccountForRefund]:checked')
								.val();
						console.log("returnCashMethod = " + returnCashMethod);
						console.log("selectedAccountForRefund = "
								+ selectedAccountForRefund);

						if (returnCashMethod === '1'
								&& selectedAccountForRefund === undefined) {
							evt.preventDefault();
							$("#current_action_for_add_account").val(
									'REMOVE_ITEM');
							$('#bank_account_modal').modal({
								backdrop : 'static',
								keyboard : false
							})
						} else {
							$("#submit_return").html('Please wait..');
							$("#submit_return").attr("disabled", true);
						}
					});
}

function hidebankaccountsforcancelorder(divId) {
	$("#" + divId).hide();
}

function showbankaccountsforcancelorder(divId) {
	$("#" + divId).css("display", "inline");
}

function showAddBankAccountModal() {
	$("#bank_account_modal").modal('show');
}

function cancelOrder(orderIdPrimaryKey, isCashOnDelivery) {
	if (isCashOnDelivery === true) {
		$("#cancel_order_cashback_method").hide();
	}
	$("#cancel_order_hidden_orderIdPrimaryKey").val(orderIdPrimaryKey);
	$('#cancelOrderModal').modal({
		backdrop : 'static',
		keyboard : false
	})
}

function returnOrder(orderIdPrimaryKey, itemId) {
	$("#orderIdPrimaryKey").val(orderIdPrimaryKey);
	$("#itemId").val(itemId);
	$("#returnOrderModal").modal('show');
}

function removeItemForNonCOD(orderIdPrimaryKey, itemId, price, quantity) {
	var total = parseInt(price) * parseInt(quantity);
	$("#item_price").html(total);

	$("#orderIdPrimaryKey").val(orderIdPrimaryKey);
	$("#itemId").val(itemId);
	$("#removeItemModal").modal('show');
}

function placeReturnRequest() {

	//
	// $('#submit_return').click(function(e) {
	// $.ajax({
	// url : "login",
	// method : 'POST',
	// contentType : "application/json",
	// data : getLoginInput(),
	// success : function(data) {
	//
	// }
	// });
	// });
}

function pills() {
	// $('#rootwizard').bootstrapWizard({
	// 'tabClass' : 'nav nav-pills'
	// });

	$('#rootwizard').bootstrapWizard();

}

function placeReturnRequest(orderItemId) {
	console.log(orderItemId);
	$("#myModal").modal('show');
}

function showReturnBox(id) {
	if ($('#' + id).is(':visible')) {
		$('#' + id).hide();
	} else {
		$('#' + id).show();
	}
}

