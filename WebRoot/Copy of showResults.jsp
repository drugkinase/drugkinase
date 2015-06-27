<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*, java.sql.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
	
	<link href="./style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="./style/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="./style/css/1272.css" rel="stylesheet">
	<!-- <link href="./style/flat-UI/flat-ui.css" rel="stylesheet">-->
	<!-- <link href="./style/css/buttons.css" rel="stylesheet">-->
	<link href="./style/dataTable/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" charset="utf8" src="./style/dataTable/js/jquery.js"></script>
	<script type="text/javascript" charset="utf8" src="./style/dataTable/js/jquery.dataTables.js"></script>
	
	<!--  
	<script type="text/javascript" src="./style/js/analytics.js"></script>
	<script type="text/javascript" src="./style/js/application.js"></script>
	<script type="text/javascript" src="./style/js/E-v1.js"></script>
	<script type="text/javascript" src="./style/js/nr-632.min.js"></script>
	-->
	<script type="text/javascript">
		window.NREUM||(NREUM={});
		NREUM.info={"beacon":"bam.nr-data.net","errorBeacon":"bam.nr-data.net","licenseKey":"438e27ebc2","applicationID":"6236145","transactionName":"IVlZFhNYCVQDRx5XBBRfRAdORABLFVxeXRJNWFIV","queueTime":3,"applicationTime":67,"agent":"js-agent.newrelic.com/nr-632.min.js"}
	</script>

	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-41402972-1', 'auto');
	  ga('send', 'pageview');
	
	</script>
</head>
<body style="padding-bottom: 131px;">

<no-conversion-pixels></no-conversion-pixels>
<script>
    if(typeof ga != 'function') {
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    }
    ga('create', 'UA-44397410-1', 'auto', {'name': 'fedoraTracker'});
    ga('fedoraTracker.send', 'pageview');
    </script>
    
 	<script type="text/javascript">
		var dataResults = [];
		var string = "<%=session.getAttribute("resultset")%>";
		var firstSplit = string.split(";");
		for (var i = 2; i < firstSplit.length; i++) {
			var secondSplit = firstSplit[i].split(",");
			dataResults.push(secondSplit);
		}

		$(document).ready( function () {
			$('#example').DataTable( {
			    data: dataResults
			} );}
		);
	</script>
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
	<div class="devise-layout fedora-page">
		<div class="container">
			<div class="container-fluid">
			<!-- 
				<div class="row-fluid" style="margin-top: 100px; margin-bottom: 100px;">
					<form>
						<table style="text-align: center;"><tr>
							<td style="width: 25%;"></td>
							<td style="width: 20%;">
								<div class="span3">
									<div class="control-group">
										<div class="form-group">
											<input autofocus="autofocus" class="form-control input-hg" placeholder="UniProt ID or PubChem CID" type="text" value="" name="search">
										</div>
									</div>
								</div>
							</td>
							<!--  
							<td style="width: 30%;">
								<div class="form-group" style="width: auto; text-align: center;">
									<input type="radio" checked="checked" name="protein-drug" value="protID" />Protein ID&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="protein-drug" value="chemID" />Chemical ID
								</div>
							</td>
							--
							<td style="width: 10%;">
								<div class="span3">
									<div class="control-group">
										<div class="form-group">
											<input type="submit" value="Go" class="button button-pill button-primary">
										</div>
									</div>
								</div>
							</td>
							<td style="width: 25%;"></td>
						</tr></table>
					</form>
				</div>
				-->
				<div class="row-fluid" style="height: 200px; margin-left: 100px; margin-right: 100px; margin-top: 10px;">
					<div class="span12">
						<div class="hero-unit">
							<h1>
								Hello, world!
							</h1>
							<p>
								And why call ye me Lord, Lord, and do not what I say? If ye know these  things, happy are ye if ye do them. 
								Cleanse that which is within the evil thoughts from within, form the heart, they defy the man. And why sees 
								thou the mote that is in thy brother’s eye, but perceivest not the beam that is in thine own eye? Take head to 
								yourself against insincerity; God knowth your hearts; blessed are the pure in heart, for they shall see God! 
							</p>
							<p>
								<a class="btn btn-primary btn-large" href="#">detail info »</a>
							</p>
						</div>
					</div>
				</div>
						<%
							String retString = (String)session.getAttribute("resultset");
							String[] retArray = retString.split(";");
						%>
	
				<!-- ****************************************************************** -->
				<div class="row-fluid" style="padding-top: 50px;">
					<div class="span12">
						<div class="page-header">
							<h2> The queried results of <% out.print(retArray[1]); %> </h2>
						</div>
						<table id="example" class="display">
						    <thead>
						        <tr>
						            <th><% out.print(retArray[0]); %></th>
						            <th>DTHybrid</th>
						            <th>kbmf</th>
						            <th>minhash</th>
						            <th>RLS</th>
						            <th>svm</th>
						            <th>drugbank</th>
						            <th>stitch</th>
						        </tr>
						    </thead>
						    <tbody>

						    </tbody>
						</table>
					</div>
				</div>
				<!-- ****************************************************************** -->
				<!-- 
				<div class="row-fluid" style="padding-top: 50px;">
					<div class="span12">
						<table width=100%><tr>
							<td width=40%>
								<div class="page-header">
									<h2> The queried results of <% //out.print(retArray[1]); %> </h2>
								</div>
							</td>
							<td width=25%></td>
							<td width=23% style="text-align: right; font-size: 18px;">
								<div class="pagination pagination-centered" style="padding-top: 5px;">
									<c:if test="${param.cur == 1}">  
							        &lt;&lt;&nbsp;&nbsp;
							        &lt;&nbsp;&nbsp; 
								    </c:if>  
								      
								    <c:if test="${param.cur != 1}">  
								        <a href="simple_query?cur=1">&lt;&lt;&nbsp;&nbsp;</a>  
								        <a href="simple_query?cur=${param.cur - 1}">&lt;&nbsp;&nbsp;</a>  
								    </c:if>  
					 				&nbsp;${param.cur} / ${param.totalPage}&nbsp;
								      
								    <c:if test="${param.cur == param.totalPage}">  
								        &gt;&nbsp;&nbsp; 
								        &gt;&gt;&nbsp;&nbsp;
								    </c:if>  
								      
								    <c:if test="${param.cur != param.totalPage}">  
								        <a href="simple_query?cur=${param.cur + 1}">&gt;&nbsp;&nbsp;</a>  
								        <a href="simple_query?cur=${param.totalPage}">&gt;&gt;&nbsp;&nbsp;</a>  
								    </c:if>  
						    	</div>
						    </td>
						    <td style="width: 12%; text-align: right;">
							    <div style="padding-top: 0px;">
							    	<form class="" action="simple_query" method="post" style="margin-bottom: -1px;">
					                    <button type="submit" class="btn" contenteditable="true">Page</button>
										<input type="text" value="1" placeholder="1" size=5 name="cur">
									</form>
							    </div>
						    </td>
					    </tr></table>
						<table class="table table-hover">
							<thead>
								<tr>
									<th width=15%><% //out.print(retArray[0]); %></th>
									<th width=17%>DTHybrid</th>
									<th width=17%>KBMF</th>
									<th width=17%>MINHash</th>
									<th width=17%>RLS</th>
									<th width=17%>SVM</th>
								</tr>
							</thead>
							<%
								//for(int i = 2; i < retArray.length; ++i) {
								//	String[] curRow = retArray[i].split(",");
								//	if (i % 2 == 0){
							%>
							<tbody>
								<tr>
									<% //for(int j = 0; j < curRow.length; ++j) { %>
										//	<td><% //out.print(curRow[j]); %></td>
									<% //}
									//}
									//else{
									%>
									<tr class="info">
									<%  //for(int j = 0; j < curRow.length; ++j) { %>
										//	<td><% //out.print(curRow[j]); %></td>
									<%
										//}
									//}
									%>
									</tr>
							<%
								//}
					 		%>
							</tbody>
						</table>
						<div class="row-fluid" style="text-align: right; font-size: 18px;">
							<table width=100%><tr>
							<td width="65%"></td>
							<td width="23%" style="text-align: right; font-size: 18px;">
							<div class="pagination pagination-centered">
								<c:if test="${param.cur == 1}">  
						        &lt;&lt;&nbsp;&nbsp;
						        &lt;&nbsp;&nbsp; 
							    </c:if>  
							      
							    <c:if test="${param.cur != 1}">  
							        <a href="simple_query?cur=1">&lt;&lt;&nbsp;&nbsp;</a>  
							        <a href="simple_query?cur=${param.cur - 1}">&lt;&nbsp;&nbsp;</a>  
							    </c:if>  
				 				&nbsp;${param.cur} / ${param.totalPage}&nbsp;
							      
							    <c:if test="${param.cur == param.totalPage}">  
							        &gt;&nbsp;&nbsp; 
							        &gt;&gt;&nbsp;&nbsp;
							    </c:if>  
							      
							    <c:if test="${param.cur != param.totalPage}">  
							        <a href="simple_query?cur=${param.cur + 1}">&gt;&nbsp;&nbsp;</a>  
							        <a href="simple_query?cur=${param.totalPage}">&gt;&gt;&nbsp;&nbsp;</a>  
							    </c:if>  
						    </div></td>
						    <td width="12%" style="text-align: right;">
						    <div style="padding-top: 0px;">
						    	<form class="" action="simple_query" method="post" style="margin-bottom: -5px;">
				                    <button type="submit" class="btn" contenteditable="true">Page</button>
									<input type="text" value="1" placeholder="1" size=5 name="cur">
								</form>
						    </div></td>
						    </tr></table>
						</div>
					</div>
				</div>   -->
				
			</div>
		</div>
	</div>
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