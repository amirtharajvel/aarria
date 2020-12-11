$(document).ready(function() {
	onsubmit();
});

function onsubmit() {
	$('#verification_form').submit(function(evt) {
		evt.preventDefault();
		verifyMobile();
	});
}

function sendVerificationCode() {
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
}

function verifyMobile() {
	$.ajax({
		url : '/verifyMobileOnForgotPassword',
		method : 'GET',
		data : getMobileVerifyInput(),
		success : function(data) {
			if (data === "success") {
				alert(" success");

			} else if (data === "verificationcodesent") {

			} else if (data === "usernotfound") {
				alert("user not found");
			} else if (data === "malfunctioned") {
				window.location.href = "home";
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
