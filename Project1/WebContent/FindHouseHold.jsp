<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findHouseHold" method="post">
		<h1>Search for a HouseHold by HouseHoldId Or MedianHouseValue</h1>
		<p>
			<label for="householdId">HouseHoldId</label>
			<input id="householdId" name="householdId" value="${fn:escapeXml(param.householdId)}">
		</p>
		<p>
			<label for="medianHouseValueFrom">MedianHouseValueFrom</label>
			<input id="medianHouseValueFrom" name="medianHouseValueFrom" value="${fn:escapeXml(param.medianHouseValueFrom)}">
		</p>
		<p>
			<label for="medianHouseValueTo">MedianHouseValueTo</label>
			<input id="medianHouseValueTo" name="medianHouseValueTo" value="${fn:escapeXml(param.medianHouseValueTo)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="houseHoldCreate"><a href="houseHoldCreate">Create HouseHold</a></div>
	<br/>
	<h1>Matching HouseHold</h1>
        <table border="1">
            <tr>
                <th>HouseholdId</th>
                <th>AddressId</th>
                <th>TotalIncomeOfHouseholds</th>
                <th>AVGIncomeOfHouseholds</th>
                <th>NumOfHouseholdsLackFacilities</th>
                <th>TotalNumOfHouseholds</th>
                <th>TotalHouseValue</th>
                <th>MedianHouseValue</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${households}" var="household" >
            <tr>
                <td><c:out value="${household.getHouseholdId()}" /></td>
                <td><c:out value="${household.getAddress().getAddressId()}" /></td>
                <td><c:out value="${household.getTotalIncomeOfHouseholds()}" /></td>
                <td><c:out value="${household.getAVGIncomeOfHouseholds()}" /></td>
                <td><c:out value="${household.getNumOfHouseholdsLackFacilities()}" /></td>
                <td><c:out value="${household.getTotalNumOfHouseholds()}" /></td>
                <td><c:out value="${household.getTotalHouseValue()}" /></td>
                <td><c:out value="${household.getMedianHouseValue()}" /></td>
                <td><a href="houseHoldUpdate?householdId=<c:out value="${household.getHouseholdId()}"/>">Edit</a></td>
                <td><a href="houseHoldDelete?householdId=<c:out value="${household.getHouseholdId()}"/>">Delete</a></td>
             </tr>
             </c:forEach>
       </table>
</body>
</html>

