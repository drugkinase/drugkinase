<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*, java.sql.*, cn.edu.fudan.admis.database.jsp.*, cn.edu.fudan.admis.database.download.Download" pageEncoding="UTF-8"%>
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
			var count = 0;
			for(var j = 1; j < secondSplit.length; ++j) {
				if(secondSplit[j] == "0" || secondSplit[j] == 0) {
					secondSplit[j] = "-";
					count = count + 1;
				}
			}
			if (count == secondSplit.length - 1) {
				continue;
			}
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
	//get all labels and scores
	String domainLabel = new Jsp().getStringLabel((List<ArrayList<String>>)session.getAttribute("domain"));
	String domainScore = new Jsp().getStringScore((List<ArrayList<String>>)session.getAttribute("domain"));
	String goLabel = new Jsp().getStringLabel((List<ArrayList<String>>)session.getAttribute("go"));
	String goScore = new Jsp().getStringScore((List<ArrayList<String>>)session.getAttribute("go"));
	String seqLabel = new Jsp().getStringLabel((List<ArrayList<String>>)session.getAttribute("seq"));
	String seqScore = new Jsp().getStringScore((List<ArrayList<String>>)session.getAttribute("seq"));
	
	new Download().writePPI(retString);
	String downloadStr = "data/temp/PPI_" + retArray[1] + ".txt";
%>
<script type="text/javascript">
	$(function ($) {
		var p = $('#iframe').contents().find('#p').text();
		//alert(p);
	});
	
	var domainLabel = "<%=domainLabel%>";
	var domainScore = "<%=domainScore%>";
	var goLabel = "<%=goLabel%>";
	var goScore = "<%=goScore%>";
	var seqLabel = "<%=seqLabel%>";
	var seqScore = "<%=seqScore%>";
	
	var labels_domain = domainLabel.split(";");
	var scores_domain = domainScore.split(";");
	var labels_go = goLabel.split(";");
	var scores_go = goScore.split(";");
	var labels_seq = seqLabel.split(";");
	var scores_seq = seqScore.split(";");
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
	<div class="devise-layout fedora-page" style="padding-bottom: 0px;">
		<div class="container" style="height: auto;">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="tabbable" id="tabs-986545">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#panel-domain" data-toggle="tab">Domain Plot</a>
								</li>
								<li>
									<a href="#panel-go" data-toggle="tab">GO Plot</a>
								</li>
								<li>
									<a href="#panel-seq" data-toggle="tab">Sequence Plot</a>
								</li>
								<li>
									<a href="#panel-table" data-toggle="tab">Detailed Table</a>
								</li>
								<li>
									<a href="#panel-download" data-toggle="tab">Download</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-domain">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=52% align="center" height="500px" 
											frameborder="0" scrolling="no" src="./chart/indexDomain.html"></iframe>  
										</div>
									</p>
								</div>
								<div class="tab-pane" id="panel-go">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=52% align="center" height="500px" 
											frameborder="0" scrolling="no" src="./chart/indexGo.html"></iframe>  
										</div>
									</p>
								</div>
								<div class="tab-pane" id="panel-seq">
									<p>
										<div id="iframe1" class="container-fluid" style = "margin-bottom: -150px;">
											<iframe id="iframe" width=52% align="center" height="500px" 
											frameborder="0" scrolling="no" src="./chart/indexSeq.html"></iframe>  
										</div>
									</p>
								</div>
								<div class="tab-pane" id="panel-table">
									<div class="container">
										<div class="container-fluid">
											<div class="row-fluid">
												<div class="span12">
													<div class="page-header">
														<table width="100%">
															<tr>
																<td>
																	<h2> The Similar <% out.print(retArray[0]); %>s with <% out.print(retArray[1]); %> </h2>
																</td>
																<td style="text-align: right; padding-top: 9px; vertical-align: middle;">
																	<a class="icon-download-alt" href="<% out.print(downloadStr); %>" target="_blank">
																	Download&nbsp;&nbsp;&nbsp;&nbsp;</a>
																</td>
															</tr>
														</table>
													</div>
													<table id="example" class="display">
													    <thead>
													        <tr>
													            <th><% out.print(retArray[0]); %></th>
													            <th>Name</th>
													            <th>Domain</th>
													            <th>GO</th>
													            <th>Sequence</th>
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
								<div class="tab-pane" id="panel-download">
									test
								</div>
							</div>
						</div>
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