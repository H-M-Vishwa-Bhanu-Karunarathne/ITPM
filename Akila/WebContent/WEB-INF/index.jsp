<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CDE IT Solutions</title>

<!-- <link type="text/css" rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath}/assets/css/bootstrap.css" /> --%>
<!-- <link type="text/css" rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath}/assets/css/bootstrap-grid.css.css" /> --%>
<!-- <link type="text/css" rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath}/assets/css/bootstrap-reboot.css" /> --%>
<!-- <link type="text/css" rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath}/assets/css/main.css" /> --%>

<link type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/assets/css/ITPM.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet">

<script>
	$(document).ready(function() {
		$("a[href*='#logo']").click(function(event) {
			event.preventDefault();
			$('html, body').stop().animate({
				scrollTop : $('#wb_logo').offset().top
			}, 600, 'easeOutCirc');
		});
		$("#").popover(
			{
				content : function() {
					return $('#wb_Card3').html();
				},
				html : true,
				placement : 'right',
				template : '<div class="popover Card3" role="tooltip"><div class="arrow"></div><div class="popover-content"></div></div>',
				title : '',
				trigger : 'click'
			});
	});
</script>
</head>
<body>
	<div id="wb_LayoutGrid1">
		<div id="LayoutGrid1">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2">
					<div id="wb_Image1"
						style="display: inline-block; width: 212px; height: 143px; z-index: 0;">
						<img src="${pageContext.request.contextPath}/assets/images/awan.png" id="Image1" alt="">
					</div>
				</div>
				<div class="col-3"></div>
			</div>
		</div>
	</div>
	<div id="wb_logo">
		<div id="logo">
			<div class="row">
				<div class="col-1">
					<div id="wb_logoHeading"
						style="display: inline-block; width: 100%; z-index: 1;">
						<h1 id="logoHeading">complexity measurement tool</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid2">
		<div id="LayoutGrid2">
			<div class="row">
				<div class="col-1">
					<div id="wb_headerMenu"
						style="display: inline-block; width: 100%; z-index: 2; vertical-align: top;">
						<ul id="headerMenu">
							<li class="active" aria-current="page">Home</li>
							<li><a href="./Size,-variables,-and-methods.html">About</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="./coupling.html">Team</a></li>
							<li><a href="./Inheritance.html">Contact</a></li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid3">
		<div id="LayoutGrid3">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2"></div>
			</div>
		</div>
	</div>
	<div id="wb_flowerGrid">
		<div id="flowerGrid">
			<div class="row">
				<div class="col-1">
					<div id="wb_flowerHeading1"
						style="display: inline-block; width: 100%; z-index: 3;">
						<h2 id="flowerHeading1">Select  Topic</h2>
					</div>
					<hr id="Line1" style="display: block; width: 100%; z-index: 4;">
				</div>
			</div>
		</div>
	</div>
	<div id="wb_CardContainer20">
		<div id="CardContainer20">
			<div id="wb_Card2"
				style="display: flex; text-align: center; z-index: 5;">
				<div id="Card2-card-body">
					<div id="Card2-card-item0">Size, variables and methods</div>
					<div id="Card2-card-item1"
						onclick="window.location.href='./Size,-variables,-and-methods.html';return false;">
						<a href="./Size,-variables,-and-methods.html">go</a>
					</div>
					<hr id="Card2-card-item2">
					<hr id="Card2-card-item3">
					<div id="Card2-card-item4">Identifying whether line by line
						code contains the size, variable, and methods. It will help to
						identify the weight allocated to the code.</div>
					<hr id="Card2-card-item5">
				</div>

			</div>
			<div id="wb_Card3"
				style="display: flex; text-align: center; z-index: 6;">
				<div id="Card3-card-body">
					<div id="Card3-card-item0">Control Structure</div>
					<hr id="Card3-card-item1">
					<div id="Card3-card-item2"
						onclick="window.location.href='${pageContext.request.contextPath}/ControlStructure';return false;">
						<a href="${pageContext.request.contextPath}/ControlStructure">go</a>
					</div>
					<hr id="Card3-card-item3">
					<hr id="Card3-card-item4">
					<div id="Card3-card-item5">Number of conditions in the
						control structure and complexity of a program statement using
						values. It will identify the weight due to that particular control
						structure and ways of control structure</div>
					<hr id="Card3-card-item6">
				</div>

			</div>
			<div id="wb_Card59"
				style="display: flex; text-align: center; z-index: 7;">
				<div id="Card59-card-body">
					<div id="Card59-card-item0">Coupling</div>
					<hr id="Card59-card-item1">
					<div id="Card59-card-item2"
						onclick="window.location.href='${pageContext.request.contextPath}/Coupling';return false;">
						<a href="${pageContext.request.contextPath}/Coupling">go</a>
					</div>
					<hr id="Card59-card-item3">
					<hr id="Card59-card-item4">
					<div id="Card59-card-item5">Coupling effectively represents
						how the subsystems can be connected with other subsystems or with
						the outside world</div>
					<hr id="Card59-card-item6">
				</div>

			</div>
			<div id="wb_Card1"
				style="display: flex; text-align: center; z-index: 8;">
				<div id="Card1-card-body">
					<div id="Card1-card-item0">Inheritance</div>
					<hr id="Card1-card-item1">
					<div id="Card1-card-item2"
						onclick="window.location.href='./Inheritance.html';return false;">
						<a href="./Inheritance.html">go</a>
					</div>
					<hr id="Card1-card-item3">
					<hr id="Card1-card-item4">
					<div id="Card1-card-item5">It is a mechanism where you can to
						derive a class from another class for a hierarchy of classes that
						share a set of attributes and methods.</div>
					<hr id="Card1-card-item6">
				</div>

			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid4">
		<div id="LayoutGrid4">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2"></div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid5">
		<div id="LayoutGrid5">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-2"></div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid6">
		<div id="LayoutGrid6">
			<div class="row">
				<div class="col-1">
					<div id="wb_Text1">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>Our
								team </strong></span>
					</div>
					<hr id="Line2" style="display: block; width: 100%; z-index: 10;">
					<div id="wb_Text2">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">D.G
							Akila Udara<br>Karunarathne H.M.V.B<br>W.A.T.T. Fonseka<br>P.G.J
							Nimeshika
						</span>
					</div>
				</div>
				<div class="col-2">
					<div id="wb_Text3">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>Team
								Functions</strong></span>
					</div>
					<hr id="Line3" style="display: block; width: 100%; z-index: 13;">
					<div id="wb_Text4">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">Coupling<br>Inheritence<br>Size,
							variables and methods<br>Control Structure
						</span>
					</div>
				</div>
				<div class="col-3">
					<div id="wb_Text5">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>contract
								us</strong></span>
					</div>
					<hr id="Line4" style="display: block; width: 100%; z-index: 16;">
					<input type="text" id="Editbox1"
						style="display: block; width: 100%; height: 26px; z-index: 17;"
						name="email" value="" spellcheck="false" placeholder="Email">
					<div id="wb_FontAwesomeIcon1"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 18;">
						<a href="./index.html"><div id="FontAwesomeIcon1">
								<i class="fa fa-facebook"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon2"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 19;">
						<a href="./index.html"><div id="FontAwesomeIcon2">
								<i class="fa fa-twitter"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon3"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 20;">
						<a href="./index.html"><div id="FontAwesomeIcon3">
								<i class="fa fa-pinterest"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon4"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 21;">
						<a href="./index.html"><div id="FontAwesomeIcon4">
								<i class="fa fa-instagram"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon5"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 22;">
						<a href="./index.html"><div id="FontAwesomeIcon5">
								<i class="fa fa-youtube"></i>
							</div></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid7">
		<div id="LayoutGrid7">
			<div class="row">
				<div class="col-1">
					<div id="wb_Text6">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">Copyright
							© AWAN Soft Solutions 2020. All right reserved. </span>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/transition.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popover.min.js"></script>
</body>
</html>