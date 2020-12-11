<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<meta name="description"
	content="Fallsbuy.com: Online Shopping India - Buy sarees, tops, kurtis, kids wear, men's wear, party wear - Free Shipping & Cash on Delivery Available.">
<meta name="keywords"
	content="fallsbuy.com, fallsbuy, Online Shopping, online shopping India, India shopping online, anarkali, suits, shirvani, tops, kurti, lahankas, chudidhar,  patiala, silk, sarees, t-shirt, good quality products, frocks, kids wear, women's wear, best price online clothing, best price to buy online, good quality clothes">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Fallsbuy">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/forgotpassword/css/forgotpassword.css"
	rel="stylesheet">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Password Assistance - Create a new password for your Account</title>
</head>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<a href="/"><img src="resources/assets/images/only-f.png"></a>
					<hr />
				</div>
			</div>
			<div class="main-login main-center">

				<c:if test="${userDoesNotExists != null}">
					<div id="error_message">
						${userDoesNotExists} is not present in our database. Please <a
							id="anchor" href="/register">Register. </a>
					</div>

				</c:if>
				<c:if test="${mobile != null}">
					<div id="verification_error_message"></div>
					<div id="resend" style="display: none">
						<button type="button" style="padding: 2px 12px;"
							id="resend_button" class="btn btn-info btn-lg login-button"
							onclick="sendVerificationCode()">Resend</button>
						<br /> <br />
					</div>
					<form:form id="verification_form" accept-charset="utf-8">
						<div class="form-group">
							<label for="mobile" class="cols-sm-2 control-label">Enter
								the OTP sent to ${mobile}</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" id="otpcode" name="code"
										class="form-control numeric-only" title="Enter the OTP"
										pattern=".{4,}" maxlength="4" required="required" /> <input
										type="hidden" id="mobileno" name="mobile" value="${mobile}" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<button type="submit" id="verify_mobile_button"
								class="btn btn-success btn-lg btn-block login-button">Verify</button>
						</div>
					</form:form>
				</c:if>
				<c:if test="${mobile == null}">
					<form class="form-horizontal" method="post" id="register_form"
						action="generateOTP" accept-charset="utf-8">
						<div class="form-group">
							<label for="mobile" class="cols-sm-2 control-label">Mobile</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<input type="text" name="mobile"
										class="form-control numeric-only"
										title="Enter mobile no without country code" pattern=".{10,}"
										maxlength="10" required="required" />
								</div>
							</div>
						</div>

						<div class="form-group ">
							<button type="submit"
								class="btn btn-success btn-lg btn-block login-button">Send
								OTP</button>
						</div>
						<div class="login-register">
							<a href="register" style="color: #5cb85c">Register</a> | <a
								href="login" style="color: #5cb85c">Login</a>
						</div>
					</form>
				</c:if>
				<form class="form-horizontal" method="post" id="new_password_form"
					action="generateOTP" accept-charset="utf-8" style="display: none;">
					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Enter
							the new password</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<input type="password" id="newPassword" class="form-control"
									name="password" pattern=".{3,}" required="required"
									title="Password should be minimum of 3 characters" />
							</div>
						</div>
					</div>

					<div class="form-group ">
						<button type="submit"
							class="btn btn-success btn-lg btn-block login-button">Update
							Password</button>
					</div>
					<div class="login-register">
						<a href="register" style="color: #5cb85c">Register</a> | <a
							href="login" style="color: #5cb85c">Login</a>
					</div>
				</form>
				<div id="password_update_success" style="display: none">
					<h4>Password updated successfully redirecting in 2 seconds..</h4>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/forgotpassword/js/forgotpassword.js"></script>

</body>
</html>