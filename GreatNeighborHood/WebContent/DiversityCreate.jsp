<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Create a User</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
	<h1>Create Diversity</h1>
	</div>
	<form action="DiversityCreate" method="post">
		<p>
			<h2><label for="PopulationId">PopulationId</label></h2>
			<input id="PopulationId" name="PopulationId" value="">
		</p>
		<p>
			<h2><label for="hispanic">hispanic</label></h2>
			<input id="hispanic" name="hispanic" value="">
		</p>
		<p>
			<h2><label for="white">white</label></h2>
			<input id="white" name="white" value="">
		</p>
		<p>
			<h2><label for="black">black</label></h2>
			<input id="black" name="black" value="">
		</p>
		<p>
			<h2><label for="aian">aian</label></h2>
			<input id="aian" name="aian" value="">
		</p>
		<p>
			<h2><label for="asian">asian</label></h2>
			<input id="asian" name="asian" value="">
		</p>
		<p>
			<h2><label for="NHOPI">NHOPI</label></h2>
			<input id="NHOPI" name="NHOPI" value="">
		</p>
		<p>
			<h2><label for="SOR">SOR</label></h2>
			<input id="SOR" name="SOR" value="">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
		</p>
	</form>
	<p>
		<div class="alert alert-success" role="alert">
		<span id="successMessage"><b>${messages.success}</b></span>
		</div>
	</p>
	
	</div>
	
	<!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>