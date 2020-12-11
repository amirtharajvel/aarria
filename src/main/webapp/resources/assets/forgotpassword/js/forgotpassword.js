$(document).ready(function() {
	onsubmit();
	disablesubmitbuttons();
});

function disablesubmitbuttons() {
	
	$(".form-horizontal").submit(function(e) {

		$(".login-button").html('Please wait..');
		$(".login-button").attr("disabled", true);
	});
}

function onsubmit() {
	$('#verification_form').submit(function(evt) {
		$("#resend_button").prop('disabled', false);
		evt.preventDefault();
		verifyMobile();
	});

	$('#new_password_form').submit(function(evt) {
		evt.preventDefault();
		changePassword();
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
		url : '/verifyMobileOnForgotPassword',
		method : 'GET',
		data : getMobileVerifyInput(),
		success : function(data) {
			console.log(data);
			if (data === "success") {
				$("#verification_form").hide();
				$("#new_password_form").show();
				$("#resend").hide();
				$("#verification_error_message").hide();
				$("#mobilenoforupdatepassword").val($("#mobileno").val());
			} else if (data === "verificationcodesent") {

			} else if (data === "usernotfound") {
				alert("user not found");
			} else if (data === "malfunctioned") {
				window.location.href = "/";
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

function getUpdatePasswordInput() {
	var input = {
		mobile : $("#mobilenoforupdatepassword").val(),
		newPassword : $("#newPassword").val()
	};
	return input;
}

function changePassword() {
	$.ajax({
		url : '/changePasswordOnForgotPassword',
		method : 'POST',
		data : getUpdatePasswordInput(),
		success : function(data) {
			if (data === "unauthorized") {
				window.location = "home";
			} else if (data === "incorrectoldpassword") {
			} else if (data === "success") {
				$("#new_password_form").hide();
				window.setTimeout(function() {
					window.location = "/";
				}, 2000);
				$("#password_update_success").show();
			} else if (data === "samepassword") {
			}
		}
	});
}
