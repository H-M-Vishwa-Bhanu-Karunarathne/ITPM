<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>CDE IT Solutions</title>
<meta name="generator"
	content="WYSIWYG Web Builder 15 - http://www.wysiwygwebbuilder.com">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/assets/css/ITPM.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/assets/css/Coupling.css"
	rel="stylesheet">

<script>
	$(document).ready(function() {
		$("a[href*='#logo']").click(function(event) {
			event.preventDefault();
			$('html, body').stop().animate({
				scrollTop : $('#wb_logo').offset().top
			}, 600, 'easeOutCirc');
		});
		$("a[href*='#LayoutGrid2']").click(function(event) {
			event.preventDefault();
			$('html, body').stop().animate({
				scrollTop : $('#wb_LayoutGrid2').offset().top
			}, 600, 'easeOutCirc');
		});
		$("#FileUpload1 :file").on('change', function() {
			var input = $(this).parents('.input-group').find(':text');
			input.val($(this).val());
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
							<li><a href="./index.html">Home</a></li>
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
					<hr id="Line1" style="display: block; width: 100%; z-index: 3;">
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid7">
		<div id="LayoutGrid7-overlay"></div>
		<div id="LayoutGrid7">
			<div class="row">
				<div class="col-1">
					<div id="wb_Heading2"
						style="display: inline-block; width: 100%; z-index: 9;">
						<h1 id="Heading2">Control Structure</h1>
					</div>
					<div id="wb_Heading3"
						style="display: inline-block; width: 100%; z-index: 10;">
						<h2 id="Heading3">Mesure complexity</h2>
					</div>
					<form method="post" action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data">
					<div id="wb_LayoutGrid8">
						<div id="LayoutGrid8">
							<div class="row">
								<div class="col-1">
									<div id="wb_Text1">
										<span
											style="color: #FFFFFF; font-family: Arial; font-size: 21px;"><strong>Upload
												your project</strong></span>
									</div>
								</div>
								
								<div class="col-2">
								
									<div id="FileUpload1" class="input-group"
										style="display: table; width: 100%; height: 42px; z-index: 5;">
										
										<input class="form-control" type="text" readonly id="file-upload-path"> 
										<label class="input-group-btn"> 
											
											<input type="file" name="FileUpload1" id="FileUpload1-file" accept=".java,.cpp" 
											style="display: none;"><span class="btn">Browse...</span>
										</label>
									</div>

									</div>
								<div class="col-3">
									<div id="wb_Text2">
										<span
											style="color: #FF0000; font-family: Arial; font-size: 27px;"><strong>(C++/
												Java only)</strong></span>
									</div>
								</div>
								<div class="col-4">
									<input type="submit" id="Button1" name="" value="Upload"
										style="display: inline-block; width: 126px; height: 40px; z-index: 7; cursor: pointer;">
								</div>
							
								<div class="col-5"></div>
							</div>
						</div>
					</div>
					</form>
					<div id="wb_LayoutGrid9">
						<div id="LayoutGrid9">
							<div class="row">
								<div class="col-1"></div>
								<div class="col-2">
									<h4>${msg}</h4>
								</div>
								<div class="col-3"></div>
							</div>
						</div>
					</div>
					<form action="${pageContext.request.contextPath}/Calculate" method="post">
						<div id="wb_LayoutGrid9">
							<div id="LayoutGrid9">
								<div class="row">
									<div class="col-1"></div>
									<div class="col-2">
										<input type="submit" id="Button2" name="" value="Calculate"
											style="display: block; width: 100%;; height: 54px; z-index: 8; cursor: pointer;">
									</div>
									<div class="col-3"></div>
								</div>
							</div>
						</div>
					</form>
					<div id="wb_LayoutGrid10">
						<div id="LayoutGrid10">
							<div class="row">
								<div class="col-1"></div>
								<div class="col-2"></div>
							</div>
						</div>
					</div>
					<table
						style="display: table; width: 100%; height: 176px; z-index: 14;"
						id="Table1">
							<tr>
								<td width="30%" class="cell0 table-header-cell"> Line </td>
								<td class="cell0 table-header-cell"> Nr </td>
								<td class="cell0 table-header-cell"> Nmcms </td>
								<td class="cell0 table-header-cell"> Nmcrms </td>
								<td class="cell0 table-header-cell"> Nrmcrms </td>
								<td class="cell0 table-header-cell"> Nrmcms </td>
								<td class="cell0 table-header-cell"> Nmrgvs </td>
								<td class="cell0 table-header-cell"> Nrmrgvs </td>
								<td class="cell0 table-header-cell"> Line Complexity </td>
							</tr>
							
							<c:forEach items="${compObjs}" var="compObj">
								<tr>
									<td width="30%" class="cell0 table-cell-left-aligned"><pre>${compObj.line}</pre></td>
									<td class="cell0 table-cell">${compObj.nr}</td>	
									<td class="cell0 table-cell">${compObj.nmcms}</td>
									<td class="cell0 table-cell">${compObj.nmcrms}</td>
									<td class="cell0 table-cell">${compObj.nrmcrms}</td>	
									<td class="cell0 table-cell">${compObj.nrmcms}</td>	
									<td class="cell0 table-cell">${compObj.nmrgvs}</td>		
									<td class="cell0 table-cell">${compObj.nrmrgvs}</td>
									<td class="cell0 table-cell">${compObj.lineComplexity}</td>	
													
								</tr>
							</c:forEach>
							
					</table>
					<div id="wb_LayoutGrid11">
						<div id="LayoutGrid11">
							<div class="row">
								<div class="col-1"></div>
								<div class="col-2"></div>
							</div>
						</div>
					</div>
					<div id="wb_LayoutGrid12">
						<div id="LayoutGrid12">
							<div class="row">
								<div class="col-1"></div>
								<div class="col-2"></div>
							</div>
						</div>
					</div>
					<textarea name="TextArea1" id="TextArea1"
						style="display: block; width: 100%;; height: 100px; z-index: 17;"
						rows="3" cols="70" spellcheck="false" placeholder="error log:"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid6">
		<div id="LayoutGrid6">
			<div class="row">
				<div class="col-1">
					<div id="wb_Text3">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>Our
								team </strong></span>
					</div>
					<hr id="Line2" style="display: block; width: 100%; z-index: 24;">
					<div id="wb_Text4">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">D.G
							Akila Udara<br>Karunarathne H.M.V.B<br>W.A.T.T. Fonseka<br>P.G.J
							Nimeshika
						</span>
					</div>
				</div>
				<div class="col-2">
					<div id="wb_Text5">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>Team
								Functions</strong></span>
					</div>
					<hr id="Line3" style="display: block; width: 100%; z-index: 27;">
					<div id="wb_Text6">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">Coupling<br>Inheritence<br>Size,
							variables and methods<br>Control Structure
						</span>
					</div>
				</div>
				<div class="col-3">
					<div id="wb_Text7">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 16px;"><strong>contract
								us</strong></span>
					</div>
					<hr id="Line4" style="display: block; width: 100%; z-index: 30;">
					<input type="text" id="Editbox1"
						style="display: block; width: 100%; height: 26px; z-index: 31;"
						name="email" value="" spellcheck="false" placeholder="Email">
					<div id="wb_FontAwesomeIcon1"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 32;">
						<a href="./index.html"><div id="FontAwesomeIcon1">
								<i class="fa fa-facebook"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon2"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 33;">
						<a href="./index.html"><div id="FontAwesomeIcon2">
								<i class="fa fa-twitter"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon3"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 34;">
						<a href="./index.html"><div id="FontAwesomeIcon3">
								<i class="fa fa-pinterest"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon4"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 35;">
						<a href="./index.html"><div id="FontAwesomeIcon4">
								<i class="fa fa-instagram"></i>
							</div></a>
					</div>
					<div id="wb_FontAwesomeIcon5"
						style="display: inline-block; width: 20px; height: 20px; text-align: center; z-index: 36;">
						<a href="./index.html"><div id="FontAwesomeIcon5">
								<i class="fa fa-youtube"></i>
							</div></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wb_LayoutGrid4">
		<div id="LayoutGrid4">
			<div class="row">
				<div class="col-1">
					<div id="wb_Text8">
						<span style="color: #FFFFFF; font-family: Arial; font-size: 13px;">Copyright
							© AWAN Soft Solutions 2020. All right reserved. </span>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>

<script>
	$( "#FileUpload1-file" ).change(function() {
	   $("#file-upload-path").val($( "#FileUpload1-file" ).val());
	});

</script>
</body>
</html>