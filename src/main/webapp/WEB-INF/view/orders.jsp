<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Approve Orders</title>
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />


<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="resources/assets/orders/css/datatables.css"
	rel="stylesheet">
<link href="resources/assets/orders/css/approveorders.css"
	rel="stylesheet">
<style>
.btn-success {
	background-color: #fcf7f7;
	border-color: #ddd;
	color: #323A3D;
}

.btn-success:hover, .btn-default:hover {
	color: #323A3D;
	background-color: #f5e4e4;
	border-color: #ddd;
}
</style>
</head>

<body style="padding-top: 7%;">
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/"><img src="resources/assets/images/logo.png"
					height="40" width="150"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container">
		<div id="rootwizard">
			<ul style="padding: 10px;">
				<li><a href="#open_orders" data-toggle="tab">Open</a></li>
				<li><a href="#being_packed_orders" data-toggle="tab">Being
						Packed</a></li>
				<li><a href="#dispatched_orders" data-toggle="tab">Dispatched
				</a></li>
				<li><a href="#delivered_orders" data-toggle="tab">Delivered
				</a></li>
				<li><a href="#return_pickup_requested_orders" data-toggle="tab">Return
						Pickup Request</a></li>
				<li><a href="#to_be_refunded_orders" data-toggle="tab">To
						Be Refunded </a></li>
				<li><a href="#cancelled_and_refunded_orders" data-toggle="tab">Cancelled
						and Refunded</a></li>
				<li><a href="#cod_unconfirmed_orders" data-toggle="tab">COD
						Order attempt Failure</a></li>
				<li><a href="#non_cod_unconfirmed_orders" data-toggle="tab">NON-COD
						Payment Failure</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane" id="open_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">

								<table id="openOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="being_packed_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">


								<table id="beingPackedOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="dispatched_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">


								<table id="dispatchedOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="delivered_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">

								<table id="deliveredOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>
				<div class="tab-pane" id="to_be_refunded_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">

								<table class="table">
									<tr>
										<th>Order no</th>
										<th>ordered date</th>
										<th>Total Order Amount</th>
										<th>Order Status</th>
									</tr>
									<c:forEach items="${refunds}" var="refund">
										<tr
											onclick="window.open('vieworderdetailsasadmin/${refund.orderIdPrimaryKey}' ,'_blank')">
											<td>${ refund.orderNo}</td>
											<td>${ refund.orderedDate}</td>
											<td>${ refund.totalAmount}</td>
											<td>${ refund.orderStatus}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>

				<div class="tab-pane" id="return_pickup_requested_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">


								<table id="toBeRefundedOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>

				<div class="tab-pane" id="cancelled_and_refunded_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">


								<table id="cancelledOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>


				<div class="tab-pane" id="cod_unconfirmed_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">


								<table id="codUnconfirmedOrders" class="display" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>


				<div class="tab-pane" id="non_cod_unconfirmed_orders">
					<!--  accordion content starts -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#${order.orderId}" aria-expanded="true"
									aria-controls="collapseOne"> ${order.orderId}</a>
							</h4>
						</div>
						<div id="${order.orderId}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">

								<table id="nonCodUnconfirmedOrders" class="display"
									cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Order Id</th>
											<th>Status</th>
											<th>Ordered Date</th>
										</tr>
									</thead>
								</table>

							</div>
						</div>
					</div>
					<!--  accordion content ends -->
				</div>

			</div>
		</div>
	</div>



	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/orders/js/approveorders.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/common/js/pills.js"></script>
	<script src="resources/assets/orders/js/datatables.js"></script>
	<!-- <script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script> -->
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var openOrdersTable = $('#openOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {

														d.status = new Number(0); //OPEN ORDERS
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#openOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											openOrdersTable.$('tr.selected')
													.removeClass('selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var beingPackedOrdersTable = $('#beingPackedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {

														d.status = new Number(1); //BEING PACKED ORDERS
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#beingPackedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											beingPackedOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var dispatchedOrdersTable = $('#dispatchedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {

														d.status = new Number(2); //DISPATCHED
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#dispatchedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											dispatchedOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var deliveredOrdersTable = $('#deliveredOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(3); //DELIVERED
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#deliveredOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											deliveredOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var returnPickupRequestedTable = $(
									'#returnPickupRequestedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(7); //RETURNED
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#returnPickupRequestedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											returnPickupRequestedTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var toBeRefundedOrdersTable = $(
									'#toBeRefundedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(6); //RETURN_PICKUP_REQUESTED
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#toBeRefundedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											toBeRefundedOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var cancelledOrdersTable = $('#cancelledOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(4); //CANCELLED_WITHIN_30_MINS and CANCELLED_WHILE_PACKAGING (code in server side)
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#cancelledOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											cancelledOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var codUnconfirmedOrdersTable = $(
									'#codUnconfirmedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(
																11); //CANCELLED_WITHIN_30_MINS and CANCELLED_WHILE_PACKAGING (code in server side)
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#codUnconfirmedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											codUnconfirmedOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

							var nonCodUnconfirmedOrdersTable = $(
									'#nonCodUnconfirmedOrders')
									.DataTable(
											{
												"processing" : true,
												"serverSide" : true,
												"columns" : [ // NOTE: you need to specify names of fields as ids for the columns
												{
													"data" : "id"
												}, {
													"data" : "orderId"
												}, {
													"data" : "status"
												}, {
													"data" : "orderedDate"
												} ],
												"ajax" : {
													"url" : "approveordersdata",
													"type" : "POST",
													"contentType" : "application/json; charset=utf-8",
													"data" : function(d) {
														d.status = new Number(
																10); //CANCELLED_WITHIN_30_MINS and CANCELLED_WHILE_PACKAGING (code in server side)
														return JSON
																.stringify(d); // NOTE: you also need to stringify POST payload
													}
												}
											});

							$('#nonCodUnconfirmedOrders tbody').on(
									'click',
									'tr',
									function() {
										var id = $(this).find('td:eq(0)')
												.text();
										console.log("id is" + id);

										if ($(this).hasClass('selected')) {
											$(this).removeClass('selected');
										} else {
											nonCodUnconfirmedOrdersTable.$(
													'tr.selected').removeClass(
													'selected');
											$(this).addClass('selected');
										}

										window.open("vieworderdetailsasadmin/"
												+ id, "_blank");
									});

						});
	</script>
</body>
</html>