<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/assets/common/style/bootstrap.css"
	rel="stylesheet">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />
<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/common/style/font-awesome.css"
	rel="stylesheet">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Welcome back</title>
<style>
body, html {
	height: 100%;
	background-repeat: no-repeat;
	background-color: white;
	font-family: 'Oxygen', sans-serif;
	color: gray;
}

.main {
	margin-top: 70px;
}

h1.title {
	font-size: 50px;
	font-family: 'Passion One', cursive;
	font-weight: 400;
}

hr {
	width: 10%;
	color: #fff;
}

.form-group {
	margin-bottom: 15px;
}

label {
	margin-bottom: 15px;
}

input, input::-webkit-input-placeholder {
	font-size: 11px;
	padding-top: 3px;
}

.main-login {
	background-color: #fff;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.main-center {
	margin-top: 30px;
	margin: 0 auto;
	max-width: 400px;
	padding: 40px 65px;
	background-color: #FCF7F7;
}

.login-button {
	margin-top: 5px;
}

.login-register {
	font-size: 11px;
	text-align: center;
	color: gray;
}

.input-group {
	width: 100%;
}

#error_message {
	color: #FF4F39;
}

#anchor {
	color: #5cb85c;
}

#verification_error_message {
	color: #FF4F39;
}
</style>
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
			</div>
		</div>
	</div>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/forgotpassword/js/forgotpassword.js"></script>

</body>
</html>