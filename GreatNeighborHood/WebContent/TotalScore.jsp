<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Score</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href = "css/TotalScoreStyle.css" rel="stylesheet" type="text/css">
</head>

<body>

<nav class="navbar navbar-fixed-top bg-primary">
<div class="container-fluid">
	<a href = "http://localhost:3000/project/index.html#/user/<c:out value="${userId}"/>">
		<h4 class="projectName">GreatNeighborhood</h4>
	</a>
	</div>
</nav>


<div class="container-fluid top-blank">

 	<div class="col-xs-8"> 
		<div class="panel panel-danger">
			<h4 class ="Scoretext">Population Score : ${populationScore}</h4> 
			<a href="displaypopulation?populationId=<c:out value="${populationId}"/>"
				 class="btn btn-info btn-lg active" role="button" aria-pressed="true">Detail Information</a>
		</div>
		
		<div class="panel panel-success">
			<h4 class ="Scoretext">Household Score : ${householdScore}</h4> 
			<a href="displayHouseHold?householdId=<c:out value="${householdId}"/>"
			class="btn btn-info btn-lg active" role="button" aria-pressed="true">Detail Information</a>
		</div>
		
		<div class="panel panel-warning">
			<h4 class ="Scoretext">Hospital Score : 0</h4> 
			<a href="hospital?AddressId=<c:out value="${AddressId}"/>"
			class="btn btn-info btn-lg active" role="button" aria-pressed="true">Detail Information</a>
		</div>
	</div>	
	
	

	 <div class="col-xs-4"> 
		<h2 class ="Scoretext">Total Score</h2>
		<h2 class ="Scoretext">${TotalScore}</h2>
	</div>

</div>	
<div class="container-fluid">
		<b class ="alter">${messages.success}</b>
</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>