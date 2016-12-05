<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a HouseHold</title>
</head>
<body>
    <div class="container theme-showcase" role="main">

	<form action="displayHouseHold" method="post">
		<h1>Display HouseHold Details By HouseHoldId</h1>
		<p>
			<label for="householdId">HouseHoldId</label>
			<input id="householdId" name="householdId" value="${fn:escapeXml(param.householdId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching HouseHold</h1>
        <table class="table table-striped">
            <tr> 
               <th>Score</th>
            </tr>
            <tr>
               <td><c:out value="${messages.totalScore}" /></td>
            </tr>
            <tr>
                <th>ScoreByHouseHold</th>
                <th>AVGIncomeOfHouseholds</th>
                <th>NumOfHouseholdsLackFacilities</th>
                <th>TotalNumOfHouseholds</th>
                <th>MedianHouseValue</th>
                <th>TotalPersonsInHouseholds</th>
                <th>BuiltAfter2010</th>
            </tr>
            <tr>
           
                <td><c:out value="${messages.houseHoldScore}" /></td>
                <td><c:out value="${household.getAVGIncomeOfHouseholds()}" /></td>
                <td><c:out value="${household.getNumOfHouseholdsLackFacilities()}" /></td>
                <td><c:out value="${household.getTotalNumOfHouseholds()}" /></td>
                <td><c:out value="${household.getMedianHouseValue()}" /></td>
                <td><c:out value="${household.getTotalPersonsInHouseholds()}" /></td>
                <td><c:out value="${household.getNumOfHouseholdsMovedInAfter2010()}" /></td>
             </tr>
             
             <tr>
                <th>ScoreByOccupiedDistribution</th>
                <th>TotalOccupiedUnit</th>
                <th>TotalVacantUnit</th>
                <th>RenterOccupiedUnit</th>
                <th>OwnerOccupiedUnit</th>
                
            </tr>
           
                <tr>
                    <td><c:out value="${messages.occupiedScore}" /></td>
                    <td><c:out value="${occupiedDistribution.getTotalOccupiedUnit()}" /></td>
                    <td><c:out value="${occupiedDistribution.getTotalVacantUnit()}" /></td>
                    <td><c:out value="${occupiedDistribution.getRenterOccupiedUnit()}" /></td>
                    <td><c:out value="${occupiedDistribution.getOwnerOccupiedUnit()}" /></td>
    
                </tr>
                
             <tr>
                <th>ScoreByHouseHoldDistribution</th>
                <th>SingleUnit</th>
                <th>TwoToNineUnits</th>
                <th>MoreThanTenUnits</th>
                <th>MobileHome</th>
                
            </tr>
           
                <tr>
                    <td><c:out value="${messages.distributionScore}" /></td>
                    <td><c:out value="${householdDistribution.getSingleUnit()}" /></td>
                    <td><c:out value="${householdDistribution.getTwoToNineUnit()}" /></td>
                    <td><c:out value="${householdDistribution.getTenMoreUnit()}" /></td>
                    <td><c:out value="${householdDistribution.getMobileHome()}" /></td>
    
                </tr>
               
               
       </table>
    </div>
</body>
</html>

