<%@ page
	import="java.util.*, com.aarria.retail.web.dto.response.*, org.apache.commons.lang3.ArrayUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en-US">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="UTF-8" />
<link rel="pingback" href="xmlrpc.html" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>aarria.com - Women's Wear</title>

<meta name="description"
	content="aarria.com: Online Shopping India - Women Branded Wear" />

<link rel="shortcut icon" type="image/png"
	href="/resources/assets/images/only-f.png" />

<link rel='stylesheet' id='style-css'
	href='/resources/assets/index/css/stylebb49.css?ver=5.2.2'
	type='text/css' media='all' />
<link rel='stylesheet' id='jetpack_css-css'
	href='/resources/assets/index/css/jetpackfff2.css?ver=7.5.3'
	type='text/css' media='all' />
<script type="d65d5cc306095d7102ec0419-text/javascript"
	src='/resources/assets/index/js/jquery4a5f.js?ver=1.12.4-wp'></script>
<style>
a, a:link, a:visited, a:focus, a:hover, a:active {
	text-decoration: none;
}
</style>
</head>
<body class="home blog custom-background">

	<nav id="site-navigation" class="main-nav" role="navigation">
		<div id="main-nav-wrapper">
			<div id="logo">
				<a href="/" title="fallsbuy.com" rel="home">
					<div id="logo">
						<img src="/resources/assets/images/logo.png" height="35"
							style="margin-top: 0.5%;" />
					</div>
				</a>
			</div>
			<div class="menu-cool-container">
				<ul id="menu-cool" class="menu">
					<li id="menu-item-976"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-976"><a
						href="/products/cat/4?page=1&sort=Price:%20Low%20To%20High">All
							Women </a></li>
					<li id="menu-item-1001"
						class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1001"><a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High">Sarees</a></li>
					<li id="menu-item-970"
						class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-970"><a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High">Kurtis</a></li>
					<li id="menu-item-971"
						class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-971"><a
						href="/products/cat/4003?page=1&sort=Price:%20Low%20To%20High">Dress
							Materials</a></li>
					<li id="menu-item-972"
						class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-972"><a
						href="/products/cat/402?page=1&sort=Price:%20Low%20To%20High">Western</a></li>
				</ul>
			</div>
			<div style="float: right; padding-right: 2%; margin-top: -1%;">
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

	</nav>
	<div class="clear"></div>
	<div id="wrap">
		<div id="header"></div>
		<div id="post-area">
			<div id="post-1031"
				class="post-1031 post type-post status-publish format-standard has-post-thumbnail hentry category-designs">
				<div class="pinbin-image">
					<a href="/products/cat/4003?page=1&sort=Price:%20Low%20To%20High"><img
						width="300" height="625"
						src="/resources/assets/index/images/17.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/17.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="857"
						data-permalink="https://colorlib.com/pinbin/layout-test/lego/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="lego" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/product?id=2863d58c-f958-4127-b451-1a2ce7111616279"
							rel="category tag">Studio</a>
					</p>
				</div>
			</div>
			<div id="post-1026"
				class="post-1026 post type-post status-publish format-standard has-post-thumbnail hentry category-designs">
				<div class="pinbin-image">
					<a href="/product?id=4d6bda3b-9e63-42db-abb5-fb8cb06f90b9293"><img
						width="300" height="385" height="252"
						src="/resources/assets/index/images/15.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/15.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="849"
						data-permalink="https://colorlib.com/pinbin/layout-test/fotolia_32060553_subscription_monthly_xxl/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL.jpg"
						data-orig-size="1000,843" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;olly - Fotolia&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="Fotolia_32060553_Subscription_Monthly_XXL"
						data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL-300x252.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/product?id=a75964c5-a72e-4fd5-8ca6-0197bfce98c6333"
							rel="category tag">Now in Bengaluru</a>
					</p>
				</div>
				<!-- <div class="pinbin-copy">
					<h2>
						<a class="front-link"
							href="tiled-galleries-powered-jetpack/index.html">Shop
							&#8377;2000 or above get &#8377;500 Off</a>
					</h2>
					<p class="pinbin-date">
						Use code <span style="color: #50C878;">BIGTREAT</span>
					</p>
					<p class="pinbin-link">
						<a href="/checkout?target=BIGTREAT">&rarr;</a>
					</p>
				</div> -->
			</div>
			<div id="post-134"
				class="post-134 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-apps">
				<div class="pinbin-image">
					<a href="/products/cat/4003?page=1&sort=Price: Low To High"><img
						width="300" height="470"
						src="/resources/assets/index/images/16.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/16.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="840"
						data-permalink="https://colorlib.com/pinbin/layout-test/cameraios/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/cameraios.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="cameraios" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/cameraios-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/cameraios.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4003?page=1&sort=Price: Low To High"
							rel="category tag">No questions asked on returns</a>
					</p>
				</div>

			</div>
			<div id="post-555"
				class="post-555 post type-post status-publish format-gallery has-post-thumbnail hentry category-designs category-web-design tag-post-formats post_format-post-format-gallery">
				<div class="pinbin-image">
					<a href="/products/cat/4001?page=1&attributes=1816"><img
						width="300" height="792"
						src="/resources/assets/index/images/18.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/18.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="946"
						data-permalink="https://colorlib.com/pinbin/post-format-test-gallery/galaxy-s-4-product-image-12/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/GALAXY-S-4-Product-Image-12.jpg"
						data-orig-size="1200,1584" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="GALAXY-S-4-Product-Image-12"
						data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/GALAXY-S-4-Product-Image-12-227x300.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/GALAXY-S-4-Product-Image-12-775x1024.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4001?page=1&attributes=1816"
							rel="category tag">Sarees</a>
					</p>
				</div>

			</div>
			<div id="post-358"
				class="post-358 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-apps tag-chattels tag-privation">
				<div class="pinbin-image">
					<a href="/product?id=4ac1bb38-6fd5-4936-834b-a63b656c3b80340"><img
						width="300" height="490"
						src="/resources/assets/index/images/19.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/19.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="835"
						data-permalink="https://colorlib.com/pinbin/readability-test/dribbble/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="dribbble" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/product?id=c190fd93-c6d0-4e31-b6ef-dfcb078c9c23341"
							rel="category tag">Genuine Offers</a>
					</p>
				</div>

			</div>
			<div id="post-149"
				class="post-149 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-graphic-design category-icons">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20High%20To%20Low&attributes=1822"><img
						width="300" height="670"
						src="/resources/assets/index/images/14.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/14.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="933"
						data-permalink="https://colorlib.com/pinbin/comment-test/dribbble2/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/03/dribbble2.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="dribbble2" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/03/dribbble2-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/03/dribbble2.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20High%20To%20Low&attributes=1822"
							rel="category tag">Priya</a>
					</p>
				</div>

			</div>
			<div id="post-168"
				class="post-168 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-graphic-design">
				<div class="pinbin-image">
					<a href="/products/cat/4003?page=1&sort=What's New"><img
						width="300" height="670"
						src="/resources/assets/index/images/20.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/20.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="957"
						data-permalink="https://colorlib.com/pinbin/many-categories/infographic/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2013/03/infographic.png"
						data-orig-size="900,1575" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="infographic" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2013/03/infographic-171x300.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2013/03/infographic-585x1024.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4003?page=1&sort=What's New"
							rel="category tag">Salwars</a>
					</p>
				</div>

			</div>
			<div id="post-152"
				class="post-152 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-icons category-apps">
				<div class="pinbin-image">
					<a href="/product?id=c190fd93-c6d0-4e31-b6ef-dfcb078c9c23341"><img
						width="300" height="392"
						src="/resources/assets/index/images/21.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/21.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="844"
						data-permalink="https://colorlib.com/pinbin/layout-test/dribbble0/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble0.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="dribbble0" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble0-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble0.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="category/designs/index.html" rel="category tag">Feel
							the Quality and Buy</a>
					</p>
				</div>

			</div>
			<div id="post-188"
				class="post-188 post type-post status-publish format-standard has-post-thumbnail hentry category-designs tag-tag1 tag-tag2 tag-tag3">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1822"><img
						width="300" height="562"
						src="/resources/assets/index/images/12.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/12.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="849"
						data-permalink="https://colorlib.com/pinbin/layout-test/fotolia_32060553_subscription_monthly_xxl/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL.jpg"
						data-orig-size="1000,843" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;olly - Fotolia&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="Fotolia_32060553_Subscription_Monthly_XXL"
						data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL-300x252.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/Fotolia_32060553_Subscription_Monthly_XXL.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1822"
							rel="category tag">Priya Paridhi</a>
					</p>
				</div>

			</div>
			<div id="post-128"
				class="post-128 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-web-design">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1839"><img
						width="300" height="570"
						src="/resources/assets/index/images/13.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/13.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="866"
						data-permalink="https://colorlib.com/pinbin/layout-test/webcore/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/webcore.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="webcore" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/webcore-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/webcore.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1839"
							rel="category tag">Sai</a>
					</p>
				</div>

			</div>
			<div id="post-559"
				class="post-559 post type-post status-publish format-aside has-post-thumbnail hentry category-icons category-apps tag-post-formats post_format-post-format-aside">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=52"><img
						width="300" height="454"
						src="/resources/assets/index/images/11.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/11.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="859"
						data-permalink="https://colorlib.com/pinbin/layout-test/radio/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/radio.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="radio" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/radio-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/radio.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=52"
							rel="category tag">Avantika</a>
					</p>
				</div>

			</div>
			<div id="post-562"
				class="post-562 post type-post status-publish format-chat has-post-thumbnail hentry category-designs category-graphic-design tag-post-formats post_format-post-format-chat">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1820"><img
						width="300" height="537"
						src="/resources/assets/index/images/10.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/10.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="857"
						data-permalink="https://colorlib.com/pinbin/layout-test/lego/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="lego" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/lego.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1820"
							rel="category tag">Kalista</a>
					</p>
				</div>

			</div>
			<div id="post-565"
				class="post-565 post type-post status-publish format-link has-post-thumbnail hentry category-designs category-apps tag-design tag-post-formats post_format-post-format-link">
				<div class="pinbin-image">
					<a
						href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1829"><img
						width="300" height="490"
						src="/resources/assets/index/images/9.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/9.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="837"
						data-permalink="https://colorlib.com/pinbin/layout-test/attachment/223/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/223.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="223" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/223-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/223.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4001?page=1&sort=Price:%20Low%20To%20High&attributes=1829"
							rel="category tag">Shreyans Fashions</a>
					</p>
				</div>

			</div>
			<div id="post-674"
				class="post-674 post type-post status-publish format-image has-post-thumbnail hentry category-graphic-design category-icons category-apps tag-post-formats post_format-post-format-image">
				<div class="pinbin-image">
					<a href="/products/cat/4003?page=1&attributes=118"><img
						width="300" height="430"
						src="/resources/assets/index/images/7.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/7.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="860"
						data-permalink="https://colorlib.com/pinbin/layout-test/reminder_dribbble/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/reminder_dribbble.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="reminder_dribbble" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/reminder_dribbble-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/reminder_dribbble.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4003?page=1&attributes=118"
							rel="category tag">Sudriti</a>
					</p>
				</div>

			</div>
			<div id="post-568"
				class="post-568 post type-post status-publish format-image has-post-thumbnail hentry category-icons category-interior category-apps tag-post-formats post_format-post-format-image">
				<div class="pinbin-image">
					<a href="/products/cat/4003?page=1&attributes=148"><img
						width="300" height="490"
						src="/resources/assets/index/images/8.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/8.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="856"
						data-permalink="https://colorlib.com/pinbin/layout-test/ishade2/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ishade2.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="ishade2" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ishade2-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ishade2.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4003?page=1&attributes=148"
							rel="category tag">Suryajyoti</a>
					</p>
				</div>

			</div>
			<div id="post-575"
				class="post-575 post type-post status-publish format-quote has-post-thumbnail hentry category-designs category-interior tag-post-formats post_format-post-format-quote">
				<div class="pinbin-image">
					<a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=1791"><img
						width="300" height="410"
						src="/resources/assets/index/images/6.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/6.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="858"
						data-permalink="https://colorlib.com/pinbin/layout-test/ofc/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ofc.jpg"
						data-orig-size="1482,916" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="ofc" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ofc-300x185.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/ofc-1024x632.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=1791"
							rel="category tag">Kajal style</a>
					</p>
				</div>
			</div>
			<div id="post-579"
				class="post-579 post type-post status-publish format-status has-post-thumbnail hentry category-designs category-icons category-apps tag-post-formats post_format-post-format-status">
				<div class="pinbin-image">
					<a href="/products/cat/4002?page=1&&attributes=99"><img
						width="300" height="422"
						src="/resources/assets/index/images/5.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/5.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="841"
						data-permalink="https://colorlib.com/pinbin/layout-test/categories/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/categories.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="categories" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/categories-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/categories.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a href="/products/cat/4002?page=1&&attributes=99"
							rel="category tag">Omtex</a>
					</p>
				</div>

			</div>
			<div id="post-582"
				class="post-582 post type-post status-publish format-video has-post-thumbnail hentry category-designs category-graphic-design category-icons tag-post-formats post_format-post-format-video">
				<div class="pinbin-image">
					<a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=103"><img
						width="300" height="560" height="240"
						src="/resources/assets/index/images/2.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/2.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="941"
						data-permalink="https://colorlib.com/pinbin/post-format-test-video/e4d8f56cac85d6f9b6f30f0fa7bf2537/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/e4d8f56cac85d6f9b6f30f0fa7bf2537.png"
						data-orig-size="750,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="e4d8f56cac85d6f9b6f30f0fa7bf2537"
						data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/e4d8f56cac85d6f9b6f30f0fa7bf2537-300x240.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2012/06/e4d8f56cac85d6f9b6f30f0fa7bf2537.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=103"
							rel="category tag">Feminista</a>

					</p>
				</div>

			</div>
			<div id="post-587"
				class="post-587 post type-post status-publish format-audio has-post-thumbnail hentry category-designs category-graphic-design tag-post-formats post_format-post-format-audio">
				<div class="pinbin-image">
					<a
						href="/products/cat/4002?page=1&sort=What's New&attributes=61,110"><img
						width="300" src="/resources/assets/index/images/1.jpg"
						height="530"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/1.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="838"
						data-permalink="https://colorlib.com/pinbin/layout-test/amsterdam_open/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/amsterdam_open.jpg"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="amsterdam_open" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/amsterdam_open-300x225.jpg"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/amsterdam_open.jpg" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4002?page=1&sort=What's New&attributes=61,110"
							rel="category tag">Psyna</a>
					</p>
				</div>

			</div>
			<div id="post-167"
				class="post-167 post type-post status-publish format-standard has-post-thumbnail hentry category-graphic-design category-icons category-apps tag-chattels tag-cienaga tag-claycold tag-crushing tag-dinarchy tag-doolie tag-energumen tag-ephialtes tag-eudiometer tag-figuriste tag-habergeon tag-hapless tag-hartshorn tag-hostility-impregnability tag-impropriation tag-knave tag-misinformed tag-moil tag-mornful tag-outlaw tag-pamphjlet tag-pneumatics tag-portly-portreeve tag-precipitancy tag-privation tag-programme tag-psychological tag-puncher tag-ramose tag-renegade tag-retrocede tag-stagnation-unhorsed tag-thunderheaded tag-unculpable tag-withered-brandnew tag-xanthopsia">
				<div class="pinbin-image">
					<a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=93,1788"><img
						width="300" height="800"
						src="/resources/assets/index/images/4.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/4.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="854"
						data-permalink="https://colorlib.com/pinbin/layout-test/inboxx/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/inboxx.png"
						data-orig-size="1040,1990" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="inboxx" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/inboxx-156x300.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/inboxx-535x1024.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=93,1788"
							rel="category tag">100 Miles</a>
					</p>
				</div>

			</div>
			<div id="post-494"
				class="post-494 post type-post status-publish format-standard has-post-thumbnail hentry category-designs category-icons category-apps">
				<div class="pinbin-image">
					<a
						href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=93,1788"><img
						width="300" height="558"
						src="/resources/assets/index/images/3.jpg"
						class="attachment-summary-image size-summary-image wp-post-image"
						alt="" srcset="/resources/assets/index/images/3.jpg"
						sizes="(max-width: 300px) 100vw, 300px" data-attachment-id="845"
						data-permalink="https://colorlib.com/pinbin/layout-test/dribbble1st/"
						data-orig-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble1st.png"
						data-orig-size="800,600" data-comments-opened="1"
						data-image-meta="{&quot;aperture&quot;:&quot;0&quot;,&quot;credit&quot;:&quot;&quot;,&quot;camera&quot;:&quot;&quot;,&quot;caption&quot;:&quot;&quot;,&quot;created_timestamp&quot;:&quot;0&quot;,&quot;copyright&quot;:&quot;&quot;,&quot;focal_length&quot;:&quot;0&quot;,&quot;iso&quot;:&quot;0&quot;,&quot;shutter_speed&quot;:&quot;0&quot;,&quot;title&quot;:&quot;&quot;}"
						data-image-title="dribbble1st" data-image-description=""
						data-medium-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble1st-300x225.png"
						data-large-file="https://colorlib.com/pinbin/wp-content/uploads/sites/8/2008/09/dribbble1st.png" /></a>
				</div>
				<div class="pinbin-category">
					<p>
						<a
							href="/products/cat/4002?page=1&sort=Price:%20Low%20To%20High&attributes=93,1788"
							rel="category tag">100 Miles</a>

					</p>
				</div>

			</div>
		</div>
		<!-- <nav id="nav-below" class="navigation" role="navigation">
			<div class="view-previous">
				<a href="page/2/index.html">&#171; Previous</a>
			</div>
			<div class="view-next"></div>
		</nav> -->
		<div id="footer-area">
			<div id="search-2" class="widget widget_search">
				<div class="widget-copy">
					<form role="search" method="get" id="searchform" class="searchform"
						action="/">
						<div>
							<label class="screen-reader-text" for="s">Search for:</label> <input
								type="text" value="" name="s" id="s" /> <input type="submit"
								id="searchsubmit" value="Leave a comment" />
						</div>
					</form>
				</div>
			</div>
			<div id="recent-posts-2" class="widget widget_recent_entries">
				<div class="widget-copy">
					<h3>Policies</h3>

					<ul>
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
			</div>
			<div id="recent-comments-2" class="widget widget_recent_comments">
				<div class="widget-copy">
					<h3>About Us</h3>
					<ul>
						<li><a href="/aboutus#who_we_are">Company</a></li>
						<li><a href="/aboutus#contact_us">Contact Us</a></li>
						<li><a href="/aboutus#contact_us">Customer Care</a></li>
						<!-- <li><a href="aboutus#privacy_policy">Sell</a></li> -->
					</ul>
				</div>
			</div>
			<div id="categories-2" class="widget widget_categories">
				<div class="widget-copy">
					<h3>Categories</h3>
					<ul>
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
			</div>
		</div>
		<footer class="site-footer">
			<div id="copyright">Copyright � Fallsbuy Ecommerce Private
				Limited 2016</div>
		</footer>
	</div>
	<div style="display: none"></div>


	<script type="d65d5cc306095d7102ec0419-text/javascript"
		src='/resources/assets/index/js/imagesloaded.min55a0.js?ver=3.2.0'></script>
	<script type="d65d5cc306095d7102ec0419-text/javascript"
		src='/resources/assets/index/js/masonry.mind617.js?ver=3.3.2'></script>
	<script type="d65d5cc306095d7102ec0419-text/javascript"
		src='/resources/assets/index/js/functions90c6.js?ver=20130605'></script>

	<script src="/resources/assets/index/js/rocket-loader.min.js"
		data-cf-settings="d65d5cc306095d7102ec0419-|49" defer=""></script>

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-114298247-2"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-114298247-2');
	</script>


</body>

</html>
