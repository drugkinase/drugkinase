<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*, java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>DrugKinase</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="drug, kinase, chemical, compound, protein" />
	
	<link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon">
	<link href="./style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="./style/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="./style/css/1272.css" rel="stylesheet">
	
	<script type="text/javascript" src="./style/js/jquery.js"></script
	<script type="text/javascript" src="./style/js/application.js"></script>
	<script>
		function setValue() {
			var type = document.getElementsByName("protein-drug").value;
			var searchValue;
			if (document.getElementByName('protein-drug').checked) {
			  	searchValue = document.getElementByName('search').value;
			}
			return searchValue;
		}
	</script>
	
</head>
<body style="padding-bottom: 131px;">

<no-conversion-pixels></no-conversion-pixels>

<header class="">
	<div class="navbar navbar-fedora navbar-fixed-top bs-docs-nav   is-not-signed-in is-at-top" id="navbar" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" data-target=".navbar-header-collapse" data-toggle="collapse" type="button">
					<span class="sr-only">
						Toggle navigation
					</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			 
				<a class="navbar-brand header-logo" href="#">
					<img src="./images/logo.png">
				</a>
				<div class="collapse navbar-collapse navbar-header-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li> <a class="btn btn-primary pull-right btn-lg" href="index.jsp"> Home </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="download.jsp" target=""> Download </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="contactUs.jsp" target=""> Contact Us </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="help.jsp" target=""> Help </a> </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>

<div class="view-school">
	<div class="devise-layout fedora-page">
		<div class="container">
			<div class="col-lg-6 col-lg-offset-3 content">
				<h1> DrugKinase Analysis </h1>
				<div class="row">
					<div class="col-xs-10 col-xs-offset-1 col-md-8 col-md-offset-2">
						<form class="new_user" id="new_user" action="simple_query" accept-charset="UTF-8" method="post">
							<input name="utf8" type="hidden" value="✓">
							<div class="form-group" style="width: auto;">
								<input autofocus="autofocus" class="form-control input-hg" placeholder="UniProt ID or PubChem ID" type="text" value="" name="search">
							</div>
							<div class="form-group" style="width: auto; text-align: center;">
								<input type="radio" checked="checked" name="protein-drug" value="protID" /> 
								UniProt ID(eg.<a href="simple_query?protein-drug=protID&search=O00141">O00141</a>)
								&nbsp;&nbsp;
								<input type="radio" name="protein-drug" value="chemID" /> 
								PubChem ID(eg.<a href="simple_query?protein-drug=chemID&search=1401">1401</a>)
								&nbsp;&nbsp;
								<input type="radio" name="protein-drug" value="assemble" /> 
								Assemble(eg.<a href="simple_query?protein-drug=assemble&search=45270897,644241">45270897,644241</a>)
							</div>
							<input type="submit" name="commit" value="search" class="btn btn-primary btn-hg btn-block login-button">
							<br> <br>
							<h5 class="text-center">
								<a class="" href="#">Any questions?</a>
							</h5>
						</form>

					</div>
				</div>
			</div>
		</div>
		<div class="container" style="text-align: center;">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="row-fluid">
							<div class="span6" style="float: left; width: 48%;">
								<dl>
									<dt style="font-size: 18px;">
										What is DrugKinase?
									</dt>
									<dd style="text-align: left;">
										Enter through the narrow gate; for the gate is wide and the road is easy that leads to destruction, 
										and there are many who take it. For the gate is narrow and the road is hard that leads to life, 
										and there are few wh o find it.
									</dd>
								</dl>
							</div>
							<div class="span6" style="float: right; width: 48%;">
								<dl>
									<dt style="font-size: 18px;">
										How to use DrugKinase?
									</dt>
									<dd style="text-align: left;">
										Love is patient; love is kind; love is not envious pr boastful or arrogant or rude. It does not insist on its own way; 
										it is not irritable or re sentful; it does not rejoice in wrongdoing, but rejoices in the truth. I t bears all things, 
										hopes all things, endures all things. Love never end s.
									</dd>
								</dl>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<footer class="bottom-menu bottom-menu-inverse">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
				<p> ©Fudan University </p>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
				<ul class="list-unstyled">
					<li> <a href="#"> Designed by Admis </a> </li>
					<li> <a href="#"> Privacy Policy </a> </li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
			</div>
		</div>
	</div>
</footer>

</body>
</html>