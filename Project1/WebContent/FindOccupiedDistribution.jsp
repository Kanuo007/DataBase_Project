<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find an OccupiedDistribution</title>
</head>
<body>
	<form action="findOccupiedDistribution" method="post">
		<h1>Search for a OccupiedDistribution by HouseHoldId</h1>
		<p>
			<label for="householdId">HouseHoldId</label>
			<input id="householdId" name="householdId" value="${fn:escapeXml(param.householdId)}">
		</p>
		<p>
			<label for="totalOccupiedUnitFrom">TotalOccupiedUnitFrom</label>
			<input id="totalOccupiedUnitFrom" name="totalOccupiedUnitFrom" value="${fn:escapeXml(param.totalOccupiedUnitFrom)}">
		</p>
		<p>
			<label for="totalOccupiedUnitTo">totalOccupiedUnitTo</label>
			<input id="totalOccupiedUnitTo" name="totalOccupiedUnitTo" value="${fn:escapeXml(param.totalOccupiedUnitTo)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<!-- <div id="occupiedDistributioncreate"><a href="occupiedDistributioncreate">Create HouseHold</a></div> -->
	<br/>
	<h1>Matching OccupiedDistribution</h1>
        <table border="1">
            <tr>
                <th>HouseholdId</th>
                <th>TotalOccupiedUnit</th>
                <th>TotalVacantUnit</th>
                <th>RenterOccupiedUnit</th>
                <th>OwnerOccupiedUnit</th>
            </tr>
            <c:forEach items="${occupiedDistributions}" var="occupiedDistribution" >
                <tr>
                    <td><c:out value="${occupiedDistribution.getHousedhold().getHouseholdId()}" /></td>
                    <td><c:out value="${occupiedDistribution.getTotalOccupiedUnit()}" /></td>
                    <td><c:out value="${occupiedDistribution.getTotalVacantUnit()}" /></td>
                   <td><c:out value="${occupiedDistribution.getRenterOccupiedUnit()}" /></td>
                    <td><c:out value="${occupiedDistribution.getOwnerOccupiedUnit()}" /></td>
                </tr>
                </c:forEach>
       </table>
</body>
</html>




