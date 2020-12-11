<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="resources/assets/register/css/register.css" rel="stylesheet">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Welcome to aarria.com - Enjoy branded products at lowest
	price possible</title>

</head>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<a href="/"><img src="resources/assets/images/logo.png"></a>
					<hr />
				</div>
			</div>
			<div class="main-login main-center">
				<c:if test="${userExists != null}">
					<div id="error_message">
						${userExists} is already registered. Please <a id="anchor"
							href="/login" style="color: blue;">login. </a>
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
					<form:form modelAttribute="form" class="form-horizontal"
						method="post" id="register_form" action="register"
						accept-charset="utf-8">
						<div class="form-group">
							<label for="mobile" class="cols-sm-2 control-label">Mobile</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<form:input path="mobile" autocomplete="off" value=""
										type="text" id="mobile" class="form-control numeric-only"
										title="Enter a 10-digit mobile number without country code"
										pattern=".{10,}" maxlength="10" required="required" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label"> Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<form:input path="email" autocomplete="off"
										class="form-control" id="email" type="email" value=""
										required="required" title="Enter email address" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<form:input type="password" class="form-control" id="password"
										name="password" value="" pattern=".{3,}" required="required"
										title="Password should be Minimum of 3 characters"
										path="password" />
								</div>
							</div>
						</div>

						<div class="form-group ">
							<button type="submit"
								class="btn btn-success btn-lg btn-block login-button">Register</button>
						</div>
						<div class="login-register">
							<a href="login" style="color: #5cb85c">Login</a>
						</div>
					</form:form>
				</c:if>
			</div>
		</div>
	</div>
	<script src="resources/assets/common/js/jquery-2.1.4.min.js"></script>
	<script src="resources/assets/register/js/register.js"></script>

</body>
</html>