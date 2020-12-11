<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>Orders - aarria.com - Women's Wear</title>
<meta name="author" content="aarria">
<meta name="keywords"
	content="aarria.com, Online Shopping, online shopping India, sarees for women latest design, latest design, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, good quality products, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="aarria">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/account/css/accountreset.css"
	rel="stylesheet">

<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">

<link href="resources/assets/account/css/accountstyle.css"
	rel="stylesheet">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,600'
	rel='stylesheet' type='text/css'>

<title>My Account</title>
</head>
<body style="padding-top: 7%;">

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/"><img src="resources/assets/images/logo.png"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div style="padding-top: 7%;">
		<ul class="cd-pricing">
			<li><header class="cd-pricing-header">
					<h2>Profile</h2>

				</header>

				<div class="cd-pricing-features"
					style="text-align: center; color: gray">
					<ul>
						<li>Mobile: ${user.mobile }</li>
						<c:if test="${user.email == null}">
							<li><a id="update_email_link">Update Email</a></li>
						</c:if>
						<li><c:if test="${user.email != null}">${user.email}</c:if></li>
						<li><a id="bank_accounts_link">Bank Accounts</a></li>

					</ul>
				</div> <!-- .cd-pricing-features --> <footer class="cd-pricing-footer">
					<!-- <a id="edit_profile_div">MORE</a> -->
				</footer> <!-- .cd-pricing-footer --></li>

			<li><header class="cd-pricing-header">
					<h2>Wallet</h2>

					<!-- <div class="cd-price">
					<span>$19.99</span> <span>month</span>
				</div> -->
				</header>

				<div class="wallet">
					<h1>
						<span id="wallet" style="color: gray">Rs.
							${user.walletAmount }</span>
					</h1>
				</div> <footer class="cd-pricing-footer"> </footer></li>

			<li><header class="cd-pricing-header">
					<h2>My Orders</h2>
				</header> <!-- .cd-pricing-header -->

				<div class="cd-pricing-features" style="text-align: center;">
					<ul>
						<li><a href="myorders" style="color: gray">View all
								orders</a></li>
					</ul>
				</div></li>
		</ul>
	</div>

	<div id="update_email_modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4>Your Email</h4>

					<div class="form-group">
						<form action="/updateEmail">
							<input type="email" name="email" /> <input type="submit"
								value="Update" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="bank_account_modal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4>Your Bank Accounts</h4>

					<hr />

					<div class="well">
						<table>
							<tr style="vertical-align: top">
								<td><img style="vertical-align: top"
									src="resources/assets/account/images/warning.png"></td>
								<td>Enter your bank account information to enable refund
									credits for your orders. You are authorizing Fallsbuy to share
									your bank account details with our banking partner to
									facilitate the refund.</td>
							</tr>
						</table>
					</div>
					<form action="addBankAccountFromAccount" method="post">
						<table class="table" style="margin-bottom: 10%;">

							<tr>
								<td>Account No</td>
								<td><input type="text" name="accountNo" required /></td>
							</tr>
							<tr>
								<td>IFSC Code</td>
								<td><input type="text" name="ifscCode" required /> <input
									type="hidden" name="fromPage" value="redirect:/myaccount" /></td>
							</tr>
							<tr>
								<td colspan="2"><button type="submit">Add account</button></td>
							</tr>

						</table>
					</form>

					<c:forEach items="${bankAccounts}" var="account">
						<table style="margin-bottom: 10%;">
							<tr>
								<td>${account.accountNo}<br /> ${account.ifscCode} <br />
									<br />
									<form action="/deleteBankAccount" method="post">

										<input type="hidden" name="accountNo"
											value="${account.accountNo}" /><input type="hidden"
											name="identifier" value="${account.identifier}" /> <input
											type="hidden" name="ifscCode" value="${account.ifscCode}" />
										<input type="hidden" name="fromPage"
											value="redirect:/myaccount" />
										<button type="submit">Delete this account</button>
									</form></td>

							</tr>
						</table>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>

	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/common/js/bootstrap.min.js"></script>
	<script src="resources/assets/account/js/account.js"></script>

</body>
</html>