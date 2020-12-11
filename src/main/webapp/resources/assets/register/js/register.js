$(document).ready(function() {
	onsubmit();
	reset();
	disablesubmitbuttons();
});

function disablesubmitbuttons() {
	
	$(".form-horizontal").submit(function(e) {

		$(".login-button").html('Please wait..');
		$(".login-button").attr("disabled", true);
	});
}

function reset() {
	$("#mobile").val('');
	$("#email").val('');
	$("#password").val('');

	setTimeout(function() {
		$('#mobile').focus();
	}, 100);

}

function onsubmit() {
	$('#verification_form').submit(function(evt) {
		$("#resend_button").prop('disabled', false);
		evt.preventDefault();
		verifyMobile();
	});
}

function sendVerificationCode() {
	$("#resend_button").prop('disabled', true);
	$("#resend_button").html('Resending..');
	
	var emailOrPassword = $("#mobileno").val();
	$.ajax({
		url : "/sendVerificationCodeForEmailOrMobile?emailOrMobile="
				+ emailOrPassword,
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
	$("#verification_error_message").html(
			"OTP sent to your mobile once again. Please enter the new OTP");
	$("#resend_button").prop('disabled', true);
	$("#resend_button").html('Resend');
}

function verifyMobile() {
	$.ajax({
		url : '/verifyMobile',
		method : 'GET',
		data : getMobileVerifyInput(),
		success : function(data) {
			if (data === "success") {
				// window.location.href = "myaccount";
				window.location.replace("myaccount")
			} else if (data === "verificationcodesent") {

			} else if (data === "malfunctioned") {
				window.location.href = "home";
			} else if (data === "success") {
				widow.location.href = widow.location.href;
			} else if (data === "failed") {
				var mobile = $("#mobileno").val();

				$("#verification_error_message").html(
						"Incorrect OTP. Please enter the correct OTP ");
				$("#resend").show();

			}
		}
	});
}

function getMobileVerifyInput() {
	var input = {
		mobile : $("#mobileno").val(),
		code : $("#otpcode").val()
	};
	return input;
}
