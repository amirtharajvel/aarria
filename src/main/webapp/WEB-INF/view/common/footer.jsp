<!-- Modal -->
<div class="modal right fade" id="myModal2" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel2">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2"
					style="text-align: center; color: #323A3D;">Cart</h4>
			</div>
			<div class="modal-body"
				style="padding-left: 0px; padding-right: 0px;">
				<div id="items" style="margin: 15px;">
					<c:forEach items=""></c:forEach>
				</div>
				<div id="proceed" style="display: none">
					<form action="checkout" id="cart_checkout_form">
						<button type="button" class="checkout_button">Checkout</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- modal-content -->

</div>
<!-- modal-dialog -->
<!-- modal -->

<div class="container">
	<div class="row">
		<div>
			<div class="col-xs-12 col-sm-4 col-md-2">
				<ul>
					<li class="dropdown-header">ABOUT US</li>
					<li><a href="/aboutus#who_we_are">Company</a></li>
					<li><a href="/aboutus#contact_us">Contact Us</a></li>
					<li><a href="/aboutus#contact_us">Customer Care</a></li>
					<li><a href="/store">Store Location</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-2">
				<ul>
					<li class="dropdown-header">TOP CATEGORIES</li>
					<!-- <li><a href="products?cat=4001"> Sarees </a></li> -->
					<li><a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High">
							Sarees </a></li>
					<li><a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High">
							Kurtis </a></li>
					<li><a
						href="/products/cat/4003?page=1&sort=Price:%20Low%20To%20High">
							Dress Materials </a></li>
				</ul>
			</div>
			<!-- <div class="col-xs-12 col-sm-4 col-md-2">
			<ul>
				<li class="dropdown-header">INFO</li>
				<li><a href="">FAQ's</a></li>
				<li><a href="">Payment Options</a></li>
				<li><a href="">Sitemap</a></li>
				<li><a href="">Ask a question</a></li>
			</ul>
		</div> -->
			<div class="col-xs-12 col-sm-4 col-md-2">
				<ul>
					<li class="dropdown-header">POLICIES</li>
					<!-- <li><a href="/returnpolicy">Return, Cancellation & Refund
						Policies</a></li> -->
					<li><a href="/returnpolicy">Return Policy</a></li>
					<li><a href="/returnpolicy#refunds">Refunds Policy</a></li>
					<li><a href="/termsofservice">Terms & Conditions</a></li>
					<li><a href="/returnpolicy#cancellation">Cancellation</a></li>
					<li><a href="/returnpolicy#shipping">Shipping Policy</a></li>
					<li><a href="/privacypolicy">Privacy Policy</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<ul>
					<!-- <li class="dropdown-header">SUBSCRIBE</li>
				<li>
					<div class="newsletter-box text-center">
						<h5>Email</h5>
						<button class="btn  bg-gray" type="button">
							Subscribe <i class="fa fa-long-arrow-right"> </i>
						</button>
					</div>
				</li> -->
				</ul>
				<ul class="social">
					<!-- <li><a target="_blank"
						href="https://www.facebook.com/fallsbuy/"> <i
							class=" fa fa-facebook"> &nbsp; </i>
					</a></li>
					<li><a target="_blank" href="https://twitter.com/fallsbuy">
							<i class="fa fa-twitter"> &nbsp; </i>
					</a></li>
					<li><a target="_blank"
						href="https://plus.google.com/b/101725681861219438906/"> <i
							class="fa fa-google-plus"> &nbsp; </i>
					</a></li>
					<li><a target="_blank"
						href="https://in.pinterest.com/fallsbuy/"> <i
							class="fa fa-pinterest"> &nbsp; </i>
					</a></li> -->
					<li><a target="_blank"
						href="https://www.youtube.com/channel/UCoxko1xWXX9V8b9BWBbtCXg">
							<i class="fa fa-youtube"> &nbsp; </i>
					</a></li>
				</ul>
			</div>
		</div>
		<!-- <div style="float: right; vertical-align: center;">
			<div style="float: right; margin-top: 88%; margin-right: 0%;">
				<a href="https://msg91.com/startups/?utm_source=startup-banner"><img
					src="https://msg91.com/images/startups/msg91Badge.png" width="120"
					height="90" title="MSG91 - SMS for Startups" alt="Bulk SMS - MSG91"></a>
			</div>
		</div> -->
	</div>



</div>
<div class="footer-bottom">
	<div class="container">
		<div class="row" style="padding-right: 0%;">
			<div class="col-xs-12 col-sm-8 col-md-8">
				<span>Copyright � Fallsbuy Ecommerce Private Limited 2016</span>

			</div>
			<div class="col-xs-12 col-sm-4 col-md-4 text-right"
				style="padding-right: 0%;">
				<ul class="nav nav-pills payments">
					<li><i class="fa fa-cc-visa"></i></li>
					<li><i class="fa fa-cc-mastercard"></i></li>
					<li><i class="fa fa-cc-amex"></i></li>
					<li><i class="fa fa-cc-paypal"></i></li>
				</ul>
			</div>
		</div>
	</div>
</div>