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
	
	<script type="text/javascript" charset="utf8" src="./style/bootstrap/js/bootstrap.js"></script>
	
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
						<li> <a class="fedora-navbar-link navbar-link" href="index.jsp"> Home </a> </li>
						<li> <a class="btn btn-primary pull-right btn-lg" href="download.jsp" target=""> Download </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="contactUs.jsp" target=""> Contact Us </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="help.jsp" target=""> Help </a> </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>
<div class="view-school">
	<div class="devise-layout fedora-page" style="padding-bottom: 0px;">
		<div class="container" style="height: auto;">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<dl>
							<dt>CPI download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/cpi.txt" target="_blank"></a></dt>
								<dd>CPI conclude.....</dd>
								<hr>
							<dt>CCI download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/cci.txt" target="_blank"></a></dt>
								<dd>CCI conclude......</dd>
								<hr>
							<dt>PPI download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/ppi.txt" target="_blank"></a></dt>
								<dd>PPI conclude......</dd>
								<hr>
							<dt>Drug sensitivity download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/drug_sensitivity.txt" target="_blank"></a></dt>
								<dd>Drug sensitivity is....../dd>
								<hr>
							<dt>CCLE download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/ccle.txt" target="_blank"></a></dt>
								<dd>CCLE is ....../dd>
								<hr>
							<dt>GDSC download &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/gdsc.txt" target="_blank"></a></dt>
								<dd>GDSC is ....../dd>
								<hr>
							<dt>All chemicals &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/chemical_detail.txt" target="_blank"></a></dt>
								<dd>All chemicals conclude..../dd>
								<hr>
							<dt>All proteins &nbsp;&nbsp;&nbsp;&nbsp;<a class="icon-download-alt" href="data/database_file/protein_detail.txt" target="_blank"></a></dt>
								<dd>All proteins conclude..../dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- *********************************************************************************************************** -->
</div>
<footer class="bottom-menu bottom-menu-inverse">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
				<p> ©Fudan University </p>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
				<ul class="list-unstyled">
					<li> <a href="#"> Designed by Songying </a> </li>
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