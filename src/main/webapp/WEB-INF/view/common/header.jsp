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
	    
	        
	        <div class="collapse navbar-collapse js-navbar-collapse">
            					<ul class="nav navbar-nav">
            						<li class="dropdown mega-dropdown"><a href="#"
            							class="dropdown-toggle" data-toggle="dropdown">All Women <span
            								class="caret"></span></a>
            							<ul class="dropdown-menu mega-dropdown-menu">
            								<li class="col-sm-2">
            									<ul>
            										<li class="dropdown-header"><a href="products?cat=201"
            											style="color: #62bd7f;">Top</a></li>
            										<li><a href="products?cat=2001">T-Shirt</a></li>
            										<li><a href="products?cat=2002">T-Shirt F/S</a></li>
            										<li><a href="products?cat=2003">T-Shirt H/S</a></li>
            										<li><a href="products?cat=2004">Top F/S</a></li>
            										<li><a href="products?cat=2005">Tunic</a></li>
            										<li><a href="products?cat=2006">Dress</a></li>
            										<li><a href="products?cat=2007">Polo</a></li>
            									</ul>
            								</li>
            							</ul>
            						</li>
            						<li class="dropdown mega-dropdown">
            						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Kurtis
            						    <span class="caret"></span></a>
                                        <ul class="dropdown-menu mega-dropdown-menu">
                                            <li class="col-sm-2">
                                                <ul>
                                                    <li class="dropdown-header"><a href="products?cat=201"
                                                        style="color: #62bd7f;">Party Wear</a></li>
                                                    <li><a href="products?cat=2001">Regular Wear</a></li>
                                                    <li><a href="products?cat=2002">Party Wear</a></li>

                                                </ul>
                                            </li>
                                        </ul>
                                    </li>

                                    <li class="dropdown mega-dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Sarees <span class="caret"></span></a>
                                        <ul class="dropdown-menu mega-dropdown-menu">
                                            <li class="col-sm-2">
                                                <ul>
                                                    <!--<li class="dropdown-header"><a href="products?cat=201"
                                                        style="color: #62bd7f;">Party Wear</a></li>-->
                                                    <li><a href="products?cat=2001">Regular Wear</a></li>
                                                    <li><a href="products?cat=2002">Party Wear</a></li>

                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="dropdown mega-dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dress Materials <span class="caret"></span></a>
                                        <ul class="dropdown-menu mega-dropdown-menu">
                                            <li class="col-sm-2">
                                                <ul>
                                                    <!--<li class="dropdown-header"><a href="products?cat=201"
                                                        style="color: #62bd7f;">Party Wear</a></li>-->
                                                    <li><a href="products?cat=2001">Regular Wear</a></li>
                                                    <li><a href="products?cat=2002">Party Wear</a></li>

                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
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
