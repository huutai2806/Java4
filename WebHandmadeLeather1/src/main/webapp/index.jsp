<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đồ da thủ công - Trang chủ</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<div id="logo">
			<p>HANDCRATZ</p>
		</div>
		<nav id="hnav">
			<ul class="menu">
				<li><a href="Collection" target="_blank">COLLECTION</a></li>
				<li><a href="#">ABOUT</a></li>
				<li><a href="#">BLOG</a></li>
				<li><a href="#">CONTACT</a></li>
				<li><a href="Signup">SIGN IN/SIGN UP</a></li>
			</ul>
		</nav>
		<div>
			<form method="post" action="#">
				<input type="text" name="txtSearch"
					placeholder="What are you looking for?" size="30" /> <input
					type="button" name="btSerch" value="SEARCH" onclick="#" />
			</form>
		</div>
	</header>
	<main>
		<div id="banner">
			<img class="banner" src="Images/do thu cong.jpg">
		</div>
		<div class="banner2">
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																										<p class="p1">BE AUTHENTIC</p>
			<p class="p2">Authemticity can't be fake</p>

			<button class="shopnow">SHOP NOW</button>
		</div>
		<section class="trending">
			<h2>TRENDING PRODUCTS</h2>
			<p class="intro">Technology increasingly dominates the fashion
				industry. However, traditional crafts bring delicate and
				sophisticated beauty and aesthetic and humanistic values that
				designers, want to keep.</p>

			<c:forEach items="${trendlistp}" var="o">
				<div class="product">
					<img src="${o.productImg}" alt="Product Image">

					<p>${o.productName}<br />
						<span>${o.productPrice}</span>
					</p>
					<button>Add to cart</button>
				</div>
			</c:forEach>
			<hr />
		</section>

		<section class="popular">
			<h2>POPULAR PRODUCTS</h2>
			<p class="intro">Handmade leather products are popular and widely
				used. In which, men prefer to use shoes, men's wallets or watch
				bands and women prefer women's bags and shoes. Our popular products
				are diverse and high quality with various colors and leather
				materials.</p>
			<c:forEach items="${popularlistp}" var="p">
				<div class="product">
					<img src="${p.productImg}" alt="Giày da thủ công">
					<p>${p.productName}<br />
						<span>${p.productPrice}</span>
					</p>
					<button>Add to cart</button>
				</div>
			</c:forEach>
			<hr>
		</section>

		<section class="blog">
			<h2>LASTED FROM THE BLOGS</h2>
			<p>We have detailed and quality articles about leather materials
				and handmade leather products from product quality experts,
				professional fashion models and smart users.</p>
			<section class="blog">
				<article>
					<p>
						<span>Brief about leather material</span><br />
						<span>20/4/2021</span><br />
						<span>Genuine leather is considered one of the most durable
							natural materials. Handmade or industrial leather products are
							softer and more beautiful than imitation leather.</span><br />
						<span>read more</span>
				</article>
				<article>
					<p>
						<span>Brief about leather material</span><br />
						<span>20/4/2021</span><br />
						<span>Genuine leather is considered one of the most durable
							natural materials. Handmade or industrial leather products are
							softer and more beautiful than imitation leather.</span><br />
						<span>read more</span>
				</article>
				<article>
					<p>
						<span>Brief about leather material</span><br />
						<span>20/4/2021</span><br />
						<span>Genuine leather is considered one of the most durable
							natural materials. Handmade or industrial leather products are
							softer and more beautiful than imitation leather.</span><br />
						<span>read more</span>
				</article>
			</section>
		</section>
		<div class="signup">
			<label class="label-signup">Sign up for free updates, lastest
				news and special offers</label>
			<form method="post" action="#">
				<input type="text" name="name" placeholder="Your name" size="30" />
				<input type="text" name="email" placeholder="Your email" size="30" />
				<input type="submit" name="submit" value="Sign Up" />
			</form>
		</div>
	</main>
	<footer>
		<div id="extend-f">
			<nav class="footnav1">
				<h3>CUSTOMER SERVICES</h3>
				<ul>
					<li><a href="#">Contact</a></li>
					<li><a href="#">Return</a></li>
					<li><a href="#">Site map</a></li>
					<li><a href="#">Gift Vouchers</a></li>
				</ul>
			</nav>
			<nav class="footnav2">
				<h3>INFORMATIONS</h3>
				<ul>
					<li><a href="#">About me</a></li>
					<li><a href="#">Delivery information</a></li>
					<li><a href="#">Private policy</a></li>
				</ul>
			</nav>
			<nav class="footnav3">
				<h3>SOCIAL NETWORK LINK</h3>
				<ul>
					<li><a href="#">Facebook</a></li>
					<li><a href="#">Google +</a></li>
					<li><a href="#">Twitter</a></li>
				</ul>
			</nav>
		</div>
		<div id="root-f">
			<h4>2023&copy;Design by Ngan</h4>
		</div>
	</footer>
</body>
</html>