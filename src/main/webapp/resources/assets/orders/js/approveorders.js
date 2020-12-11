$(document).ready(function() {
	pills();

	$('#return_div a').click(function(e) {
		$('#orderItemId').val($('#orderItemId_hidden').val());
		$("#myModal").modal('show');
	});

	cancelOrder();
});

function cancelOrder() {
	$('.launchConfirm').on('click', function(e) {
		$("#cancelOrderModal").modal('show');
	});
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
