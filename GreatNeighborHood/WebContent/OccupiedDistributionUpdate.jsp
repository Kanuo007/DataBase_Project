<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update an OccupiedDistribution</title>
</head>
<body>
	<h1>Update an OccupiedDistribution</h1>
	<form action="occupiedDistributionUpdate" method="post">
	    <p>
			<label for="householdId">HouseHoldId</label>
			<input id="householdId" name="householdId" value="${fn:escapeXml(param.householdId)}">
		</p> 
		<p>
			<label for="RenterOccupiedUnit">New RenterOccupiedUnit</label>
			<input id="RenterOccupiedUnit" name="RenterOccupiedUnit" value="">
		</p>
		<p>
			<label for="TotalOccupiedUnit">New TotalOccupiedUnit</label>
			<input id="TotalOccupiedUnit" name="TotalOccupiedUnit" value="">
		</p>
		<p>
			<label for="TotalVacantUnit">New TotalVacantUnit</label>
			<input id="TotalVacantUnit" name="TotalVacantUnit" value="">
		</p>
		<p>
			<label for="OwnerOccupiedUnit">New OwnerOccupiedUnit</label>
			<input id="OwnerOccupiedUnit" name="OwnerOccupiedUnit" value="">
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

