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
	<!-- <link href="./style/flat-UI/flat-ui.css" rel="stylesheet">-->
	<!-- <link href="./style/css/buttons.css" rel="stylesheet">-->
	<link href="./style/dataTable/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" charset="utf8" src="./style/dataTable/js/jquery.js"></script>
	<script type="text/javascript" charset="utf8" src="./style/dataTable/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf8" src="./style/bootstrap/js/bootstrap.js"></script>
	
 	<script type="text/javascript">
		var dataResults = [];
		var string = "<%=session.getAttribute("rets")%>";
		var firstSplit = string.split(";");
		for (var i = 2; i < firstSplit.length; i++) {
			var secondSplit = firstSplit[i].split(",");
			secondSplit[0] = "<a href=similarSearch?ID=" + secondSplit[0] + "\>" + secondSplit[0] + "</a>";
			dataResults.push(secondSplit);
		}

		$(document).ready( function () {
			$('#example').DataTable( {
			    data: dataResults
			} );}
		);
	</script>
	
<%
	String retString = (String)session.getAttribute("rets");
	String[] retArray = retString.split(";");
	String type = (String)session.getAttribute("type");
	String[] score = (String[])session.getAttribute("score");
%>
	<script type="text/javascript">
		$(function ($) {
			var p = $('#iframe').contents().find('#p').text();
			//alert(p);
		});
		var curId = "<%=retArray[1]%>";
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
						<li> <a class="fedora-navbar-link navbar-link" href="#" target=""> Download </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="#" target=""> Contact Us </a> </li>
						<li> <a class="fedora-navbar-link navbar-link" href="#" target=""> Help </a> </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>
<div class="view-school">
<%if(type == "protein") { %>
	<div class="devise-layout fedora-page" style="padding-bottom: 0px;">
		<div class="container" style="height: 600px;">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="tabbable" id="tabs-986545">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#panel-domain" data-toggle="tab">Domain info</a>
								</li>
								<li>
									<a href="#panel-go" data-toggle="tab">GO info</a>
								</li>
								<li>
									<a href="#panel-seq" data-toggle="tab">Sequence info</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-domain">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=100% align="center" height="100%" 
											frameborder="0" scrolling="no" src="./chart/indexPPIDomain.html"></iframe>  
										</div>
									</p>
								</div>
								<div class="tab-pane" id="panel-go">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=100% align="center" height="100%" 
											frameborder="0" scrolling="no" src="./chart/indexPPIGo.html"></iframe>  
										</div>
									</p>
								</div>
								<div class="tab-pane" id="panel-seq">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=100% align="center" height="100%" 
											frameborder="0" scrolling="no" src="./chart/indexPPISeq.html"></iframe>  
										</div>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="devise-layout fedora-page" style="padding-top: 0px;">
		<div class="container">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="page-header">
							<h2> The Similar <% out.print(retArray[0]); %> with <% out.print(retArray[1]); %></h2>
						</div>
						<table id="example" class="display">
						    <thead>
						        <tr>
						            <th><% out.print(retArray[0]); %></th>
						            <th>domain</th>
						            <th>go</th>
						            <th>sequence</th>
						        </tr>
						    </thead>
						    <tbody>

						    </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<%}else { %>
	<div class="devise-layout fedora-page" style="padding-bottom: 0px;">
		<div class="container" style="height: 600px;">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="tabbable" id="tabs-986545">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#panel-score" data-toggle="tab">Score info</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-score">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=100% align="center" height="100%" 
											frameborder="0" scrolling="no" src="./chart/indexCCIScore.html"></iframe>  
										</div>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- *********************************************************************************************************************** -->
	<div class="devise-layout fedora-page" style="padding-top: 0px;">
		<div class="container">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="page-header">
							<h2> The Similar <% out.print(retArray[0]); %> with <% out.print(retArray[1]); %></h2>
						</div>
						<table id="example" class="display">
						    <thead>
						        <tr>
						            <th><% out.print(retArray[0]); %></th>
						            <th>score</th>
						        </tr>
						    </thead>
						    <tbody>

						    </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<%} %>
<!-- *********************************************************************************************************** -->
	<footer class="bottom-menu bottom-menu-inverse">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-4 footer-column">
					<p> ©Songying </p>
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
</div>

</body>
</html>