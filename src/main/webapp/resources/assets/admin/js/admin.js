$(document).ready(function() {
	pills();

});

function pills() {
	$('#rootwizard').bootstrapWizard();
}

function confirmDelete() {
	if (confirm('Are you sure you want to delete?')) {
		return true;
	} else {
		return false;
	}
}

function sendSms() {
	$.ajax({
		url : '/sendSms',
		method : 'GET',
		data : getMobileInput(),
		success : function(data) {
			if (data === "unauthorized") {
				window.location = "/";
			} else {
				$("#sent_otp").html("Sent OTP is " + data);
			}
		}
	});
}

function getMobileInput() {
	var input = {
		mobile : $("#mobile_for_otp").val()
	};
	return input;
}

function getIsMobileRegistedInput() {
	var input = {
		mobile : $("#mobile").val()
	};
	return input;
}

function verifyMobileRegistered() {
	$.ajax({
		url : '/isMobileRegistered',
		method : 'GET',
		data : getIsMobileRegistedInput(),
		success : function(data) {
			$("#isUserRegistered").html(data);
		}
	});
}