$(document).ready(function() {
	enableaddAddress();
	disabletab();
	focusmobile();
	rendertab();
	editaddress();
	showedit();
	showdialog();
	// uncheckpaymentmethod();
	onpaymentmodeselected();
	dismisspopoverpaymentmode();
	onaddaddress();
	disablesubmitbuttons();
	onplaceorder();

});

function onplaceorder() {
	$("#confirm_place_order_button").html('Place Order');
	$("#confirm_place_order_button").attr("disabled", false);

	$("#pay_request_form").submit(function(e) {
		$("#confirm_place_order_button").html('Please wait..');
		$("#confirm_place_order_button").attr("disabled", true);
	});
}

function enableaddAddress() {
	$("#add_address_submit").removeAttr('disabled');
}

function onaddaddress() {
	$(".add_address_form").on("submit", function() {
		$("#add_address_submit").attr('disabled', 'disabled');
		return true;
	});
}

function dismisspopoverpaymentmode() {
	$('body').on(
			'click',
			function(e) {
				$('[data-toggle="popover"]').each(
						function() {
							if (!$(this).is(e.target)
									&& $(this).has(e.target).length === 0
									&& $('#pop_a').has(e.target).length === 0) {
								if (e.target.id != 'place_order_button') {
									$(this).popover('hide');
								}
							}
						});
			});
}

function onpaymentmodeselected() {
	$("#payment_mode").val(0);
	$('input:radio[name="returnCashMethod"]').change(function() {
		if ($(this).is(':checked')) {
			$("#payment_mode").val($(this).val());
		}
	});
}

function uncheckpaymentmethod() {
	$("input:radio").attr("checked", false);
}

function showdialog() {

	$("#add_address_button").click(function(e) {
		$("#checkout").modal('show');
	});

	$("#change_address").click(function(e) {
		$("#checkout").modal('show');
	});
}

function deliverHere(id) {
	$("#current_id").val(id);
	$(".deliverHere").submit();
}

function rendertab() {

}

function focusmobile() {

	setTimeout(function() {
		$('#mobile').focus();
	}, 100);
}

function disabletab() {
	$(".disabled").click(function(e) {
		e.preventDefault();
		return false;
	});
}

function sendVerificationCode() {

	$(".resend_button").prop('disabled', true);
	$(".resend_button").html('Resending..');

	var mobile = $("#mobile").val();
	$(".resend_button").prop("placeholder",
			"Enter One-Time-Password sent to " + mobile);

	$.ajax({
		url : "/sendVerificationCodeForEmailOrMobile?emailOrMobile=" + mobile,
		method : 'GET',
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			if (data === "verificationcodesent") {
				showResendSuccessMessage();
			}
		}
	});
}

function showResendSuccessMessage() {
	$(".error").html(
			"OTP sent to your mobile once again. Please enter the new OTP");

	$(".resend_button").prop('disabled', true);
	$(".resend_button").html('Resend');
}

function editaddress() {
	$(".edit_address").click(function(e) {

		var parentDiv = $(this).parents(".order-details");

		var address = parentDiv.find('.address_div_ind').html();
		var mobile = parentDiv.find('.mobile_div_ind').html();
		var landmark = parentDiv.find('.landmark_div_ind').html();
		var pincode = parentDiv.find('.pincode_div_ind').html();
		var name = parentDiv.find('.name_div_ind').html();
		var id = parentDiv.find('.edit_current_id').val();
		var email = parentDiv.find('.email_div_ind').html();

		$("#address").val(address);
		$("#mobile_textbox").val(mobile);
		$("#landmark").val(landmark);
		$("#pincode").val(pincode);
		$("#name").val(name);
		$("#hidden_current_id").val(id);
		$("#email").val(email);

		$('#add_address_form').attr('action', '/updateAddress');

		$("#add_address_submit").text("Update Address");
	});
}

function showedit() {
	$(".individual_address").on("mouseover", function(e) {
		$(this).find('img').show();
	});

	$(".individual_address").on("mouseout", function(e) {
		$(this).find('img').hide();
	});
}

function placeOrder() {

	var addressNotEntered = $("#is_address_not_entered").val();

	if (addressNotEntered === 'true') {
		$("#checkout").modal('show');
		return;
	}

	var isPaymentModeSelected = false;
	if ($('input:radio').is(':checked')) {
		isPaymentModeSelected = true;
	}

	if (!isPaymentModeSelected) {
		$("#pop_a").popover('show');
		return;
	}

	$('#pay_request_form').attr('action',
			'/placeOrder?paymentMode=' + $("#payment_mode").val());

	$('#mi-modal').modal({
		backdrop : 'static',
		keyboard : false
	})

}

function closeConfirm() {
	$("#mi-modal").modal('hide');
}

function deleteAddress(id) {
	console.log("id " + id);
	if (confirm('Are you sure you want to delete?')) {
		console.log("id " + id);
		$('<form action="/deleteAddress/' + id + '"></form>').appendTo('body')
				.submit();
	}
}

function disablesubmitbuttons() {
	$(".form-horizontal").submit(function(e) {

		$(".continue_button").html('Please wait..');
		$(".continue_button").attr("disabled", true);
	});
}