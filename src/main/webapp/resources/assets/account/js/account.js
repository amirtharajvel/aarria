$(document).ready(function() {

	$('#bank_account_modal').on('show.bs.modal', function () {
	    $('.modal .modal-body').css('overflow-y', 'auto'); 
	    $('.modal .modal-body').css('max-height', $(window).height() * 0.7);
	});
	
	showbankaccount();
	updateemail();
});

function updateemail() {
	$("#update_email_link").click(function() {
		$("#update_email_modal").modal('show');
	});
}

function showbankaccount() {
	$("#bank_accounts_link").click(function() {
		$("#bank_account_modal").modal('show');
	});
}
