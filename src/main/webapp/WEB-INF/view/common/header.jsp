<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row margin-0">
	<div class="col-xs-5 col-sm-3 col-md-2 padding-left0">
		<a href="/"><img alt="Fallsbuy.com"
			src="/resources/assets/images/logo.png"></a>
	</div>
	<div class="col-xs-7 col-sm-3 col-md-2 header-right text-right mobile-menu">

		<c:if test="${sessionScope.userId == null}">
			<a style="color: black;" href="/register">New User?</a>
		</c:if>

		<c:if test="${sessionScope.userId != null}">
			<span style="color: black;"> ${sessionScope.greeting} | <a
				style="color: black;" href="/myaccount">Account</a>
			</span>
		</c:if>

		<c:if test="${sessionScope.userId != null}">
			<span style="color: black;"> | </span>
			<a href="/logout" style="color: black;"> Logout </a>
		</c:if>
		<c:if test="${sessionScope.userId == null}">
			<span style="color: black;"> | </span>
			<a href="/login" style="color: black;">Login</a>
		</c:if>
		<a href="#"><i class="fa fa-cart-plus" data-toggle="modal"
			data-target="#myModal2" aria-hidden="true"></i></a>
	</div>
	<div class="col-xs-12 col-sm-6 col-md-8">
		<div class="input-group" style="width: 100%;">
			<input id="example-ajax-post" style="width: 100%;"
				placeholder="Search" />
		</div>
		<nav class="navbar navbar-default">
	      <div class="container padding-0">
	        
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>	          
	        </div>
	    
	        
	        <div class="collapse navbar-collapse padding-0" id="navbar-collapse-1" style="font-size:16px">
	          <ul class="nav navbar-nav">
				<li><a href="/products/cat/4?page=1&sort=Price:%20Low%20To%20High">All Women</a></li>
				<li><a href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High">Sarees</a></li>
				<li><a href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High">Kurtis</a></li>
				<li><a href="/products/cat/4003?page=1&sort=Price:%20Low%20To%20High">Dress	Materials</a></li>
				<!-- <li><a href="/products/cat/402?page=1&sort=Price:%20Low%20To%20High">Western</a></li> -->
				<!-- <li><a href="/products/cat/4?sort=Price:%20Low%20To%20High&child_cat=4&ref=-1&page=0">Women</a></li> -->
			  </ul>
	        </div>
	      </div>
	    </nav>		
	</div>
	<div class="col-xs-6 col-sm-3 col-md-2 header-right text-right desktop-menu">

		<c:if test="${sessionScope.userId == null}">
			<a style="color: black;" href="/register">New User?</a>
		</c:if>

		<c:if test="${sessionScope.userId != null}">
			<span style="color: black;"> ${sessionScope.greeting} | <a
				style="color: black;" href="/myaccount">Account</a>
			</span>
		</c:if>

		<c:if test="${sessionScope.userId != null}">
			<span style="color: black;"> | </span>
			<a href="/logout" style="color: black;"> Logout </a>
		</c:if>
		<c:if test="${sessionScope.userId == null}">
			<span style="color: black;"> | </span>
			<a href="/login" style="color: black;">Login</a>
		</c:if>
		<a href="#"><i class="fa fa-cart-plus" data-toggle="modal"
			data-target="#myModal2" aria-hidden="true"></i></a>
	</div>
</div>
