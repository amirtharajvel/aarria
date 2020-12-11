<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>aarria.com - Something went wrong!</title>

<link rel="shortcut icon" type="image/png"
	href="resources/assets/images/only-f.png" />

<link href="/resources/assets/common/style/bootstrap.css"
	rel="stylesheet">
<link href="/resources/assets/checkout/css/checkout.css"
	rel="stylesheet">

</head>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top"
	style="border: 1px solid #DCDCDC;">
<div class="container">
	<div class="navbar-header">
		<a href="/"><img src="/resources/assets/images/logo.png"></a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<c:if test="${isUserLoggedIn.equals('true')}">
			<div style="float: right;">
				<a style="color: wheat;" href="logout">Logout</a>
			</div>
		</c:if>
	</div>
	<!--/.nav-collapse -->
</div>
</nav>
<div class="container">

	<div class="jumbotron" style="min-height: 550px; margin-top: 8%;">
		<h1>Oops...!!!</h1>
		<h2>${errorMsg}</h2>
		<h3>
			Something went wrong. We didn't anticipate this. If you want us to
			know about this, Please <a href="/aboutus#contact_us">write</a> us.
		</h3>
		<a href="/">Go home</a>
	</div>
</div>
</html>