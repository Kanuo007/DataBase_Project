<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a OccupiedDistribution</title>
</head>
<body>
	<h1>Create OccupiedDistribution</h1>
	<form action="occupiedDistributionCreate" method="post">
		<p>
			<label for="HouseHoldId">HouseHoldId</label>
			<input id="HouseHoldId" name="HouseHoldId" value="">
		</p>
		<p>
			<label for="TotalOccupiedUnit">TotalOccupiedUnit</label>
			<input id="TotalOccupiedUnit" name="TotalOccupiedUnit" value="">
		</p>
		<p>
			<label for="TotalVacantUnit">TotalVacantUnit</label>
			<input id="TotalVacantUnit" name="TotalVacantUnit" value="">
		</p>
		<p>
			<label for="RenterOccupiedUnit">RenterOccupiedUnit</label>
			<input id="RenterOccupiedUnit" name="RenterOccupiedUnit" value="">
		</p>
		<p>
			<label for="OwnerOccupiedUnit">OwnerOccupiedUnit</label>
			<input id="OwnerOccupiedUnit" name="OwnerOccupiedUnit"value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
