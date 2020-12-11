$(document).ready(function() {
	onSubmit();
	onAddAccount();
	onReturnCashSelected();
});

function onReturnCashSelected() {

	$('#return_cash_method input[type=radio][name=returnCashMethod]').change(
			function() {
				if (this.value === '1') {
					$("#return_order_bank_accounts").show();
				} else {
					$("#return_order_bank_accounts").hide();
				}
			});
}

function enableDifferentLocation() {
	if ($("#different_location_checkbox").is(':checked') === true) {
		$("#pickupAddressTextArea").prop("disabled", false);
		$("#pickupAddressTextArea").focus();

		$('input[name=pickupAddress]:checked').each(function() {
			$(this).prop('checked', false);
		});

	} else {
		$("#pickupAddressTextArea").prop("disabled", true);
	}
}

function getBankInput() {
	return "accountNo=" + $("#accountNo").val() + "&ifscCode="
			+ $("#ifscCode").val() + "&fromPage=redirect:/myorders";
}

function isItemSelected() {
	var isSelected = false;
	$('input[name=itemsToReturn]:checked').each(function() {
		// $(this).prop('checked', false);
		isSelected = true;
	});
	return isSelected;
}

function onSubmit() {
	$("#return_order_form")
			.submit(
					function(evt) {

						if (isItemSelected() === false) {
							alert("Please select atleast one item to return");
							evt.preventDefault();
							return;
						}

						var pickupLocation = $(
								'input[name=pickupAddress]:checked').val();

						var differentPickupLocationCount = $(
								"#pickupAddressTextArea").val().length;

						if (pickupLocation == undefined
								&& differentPickupLocationCount <= 10) {
							// alert("Please enter a pick up location");
							$("#pickupAddressTextArea").prop('required', true);
							$("#pickupAddressTextArea").prop("disabled", false);
							$("#pickupAddressTextArea").focus();
							evt.preventDefault();
						}

						var returnCashMethod = $(
								'input[name=returnCashMethod]:checked').val();

						var bankAccount = $("#bank_accounts").val();

						if (bankAccount === 'true' && returnCashMethod === '1') {
							$("#bank_account_modal").modal('show');
							evt.preventDefault();
						}
					});
}

function onAddAccount() {
	$("#add_account_button").click(function(e) {
		submitAddAccount('return_order_bank_accounts');
	});
}

function showBankDialog() {
	$("#bank_account_modal").modal('show');
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
					$("#bank_accounts").val("false");
				}
			});
}

function hidebankaccountsforcancelorder(divId) {
	$("#" + divId).hide();
}

function showbankaccountsforcancelorder(divId) {
	$("#" + divId).css("display", "inline");
}
