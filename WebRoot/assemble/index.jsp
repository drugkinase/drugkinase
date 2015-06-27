<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sensitivity</title>
<link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon">
<script type="text/javascript" src="../style/js/jquery.js"></script
<!-- <link href="../style/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<script src="../style/js/d3.min.js"></script>
<style>

body {
  font: 10px sans-serif;
  text-align: center;
}
.axis .grid-line{
    stroke: black;
    shape-rendering: crispEdges;
    stroke-opacity: .2;
}
.axis path,
.axis line {
  fill: none;
  stroke: #000;
  shape-rendering: crispEdges;
}

div.tooltip {   
  position: absolute;           
  text-align: left;           
  width: 120px;                  
  height: 40px;                 
  padding: 2px;             
  font: 12px sans-serif;        
  background: lightsteelblue;   
  border: 0px;      
  border-radius: 8px;           
  pointer-events: none;         
}

.label {
	font: 18px sans-serif;
	font-weight:bold
}
</style>

<script>
 	$(function ($) {
	    $('#iframe1', parent.document).text()
	});
	$(document).ready( function () {
		//var dataId = "data/data_" + parent.curId + "_IC50.tsv";
	});
</script>

</head>
<body>
<p id="p"></p>
<script type="text/javascript">
var margin = {top: 20, right: 20, bottom: 40, left: 50},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

//tooltip div
var div = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 0);
//set scales
var x = d3.scale.linear()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

//set color
var color = d3.scale.category10();

//set axis
var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

//create svg
var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


//load data
d3.tsv("data/data_" + parent.curId + "_Assemble.tsv", function(error, data) {
  if (error) throw error;

  data.forEach(function(d) {
    d.height = +d.height;
    d.rank = +d.rank;
  });

//set domain
  x.domain(d3.extent(data, function(d) { return d.rank; })).nice();
  y.domain(d3.extent(data, function(d) { return d.height; })).nice();

//plot x axis
  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
    .append("text")
      .attr("class", "label")
      .attr("x", width / 2 + 80)
      .attr("y", 35)
      .style("text-anchor", "end")
      .text("ranked by sensitivity");

//plot y axis
  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("class", "label")
      .attr("transform", "rotate(-90)")
      .attr("x", -width / 5 + 30)
      .attr("y", -40)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("IC50 (micromolar)")
      
d3.selectAll("g.y g.tick")
            .append("line")
                .classed("grid-line", true)
                .attr("x1", 0)
                .attr("y1", 0)
                .attr("x2", width)
                .attr("y2", 0);
//plot dots
  svg.selectAll(".dot")
      .data(data)
    .enter().append("circle")
      .attr("class", "dot")
      .attr("r", 3.5)
      .attr("cx", function(d) { return x(d.rank); })
      .attr("cy", function(d) { return y(d.height); })
      .style("fill", function(d) { return setColor(d.height); })
      .on("mouseover", function(d) { 
            div.transition()        
                .duration(200)      
                .style("opacity", .9)
            div .html("Name: " + d.name + "<br/>Rank: "  + d.rank + "<br/>Score: " + d.height)
            	.style("left", (d3.event.pageX + 20) + "px")     
                .style("top", (d3.event.pageY + 5) + "px");  
            })
        .on("mouseout", function(d) {       
            div.transition()        
                .duration(500)      
                .style("opacity", 0);   
        });
//set color
  function setColor(value) {
  	if(value > 0)
  		return "red"
  	else
  		return "blue"
  }
  
//legend
//  var legend = svg.selectAll(".legend")
//      .data(color.domain())
//    .enter().append("g")
//      .attr("class", "legend")
//      .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
      
//  legend.append("rect")
//      .attr("x", width - 18)
//      .attr("width", 18)
//      .attr("height", 18)
//      .style("fill", color);

//  legend.append("text")
//      .attr("x", width - 24)
//      .attr("y", 9)
//      .attr("dy", ".35em")
//      .style("text-anchor", "end")
//      .text(function(d) { return d; });

});
</script>
<div class="container">

</div>
</body>
</html>