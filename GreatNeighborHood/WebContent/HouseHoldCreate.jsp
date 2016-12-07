<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a HouseHold</title>
</head>
<body>
	<h1>Create HouseHold</h1>
	<form action="houseHoldCreate" method="post">
		<p>
			<label for="addressId">AddressId</label>
			<input id="addressId" name="addressId" value="">
		</p>
		<p>
			<label for="TotalIncomeOfHouseholds">TotalIncomeOfHouseholds</label>
			<input id="TotalIncomeOfHouseholds" name="TotalIncomeOfHouseholds" value="">
		</p>
		<p>
			<label for="AVGIncomeOfHouseholds">AVGIncomeOfHouseholds</label>
			<input id="AVGIncomeOfHouseholds" name="AVGIncomeOfHouseholds" value="">
		</p>
		<p>
			<label for="NumOfFamilyWithChildUnderSix">NumOfFamilyWithChildUnderSix</label>
			<input id="NumOfFamilyWithChildUnderSix" name="NumOfFamilyWithChildUnderSix" value="">
		</p>
		<p>
			<label for="NumOfFamilyWithOneOrMoreUnder18">NumOfFamilyWithOneOrMoreUnder18</label>
			<input id="NumOfFamilyWithOneOrMoreUnder18" name="NumOfFamilyWithOneOrMoreUnder18" value="">
		</p>
		<p>
			<label for="TotalPersonsInHouseholds">TotalPersonsInHouseholds</label>
			<input id="TotalPersonsInHouseholds" name="TotalPersonsInHouseholds" value="">
		</p>
		<p>
			<label for="NumOfHouseholdsWithoutTeleService">NumOfHouseholdsWithoutTeleService</label>
			<input id="NumOfHouseholdsWithoutTeleService" name="NumOfHouseholdsWithoutTeleService" value="">
		</p>
		<p>
			<label for="NumOfHouseholdsLackFacilities">NumOfHouseholdsLackFacilities</label>
			<input id="NumOfHouseholdsLackFacilities" name="NumOfHouseholdsLackFacilities" value="">
		</p>
		<p>
			<label for="NumOfHouseholdsBuiltAfter2010">NumOfHouseholdsBuiltAfter2010</label>
			<input id="NumOfHouseholdsBuiltAfter2010" name="NumOfHouseholdsBuiltAfter2010" value="">
		</p>
		<p>
			<label for="MedianHouseValue">MedianHouseValue</label>
			<input id="MedianHouseValue" name="MedianHouseValue" value="">
		</p>
		<p>
			<label for="totalHouseValue">TotalHouseValue</label>
			<input id="totalHouseValue" name="totalHouseValue" value="">
		</p>
		<p>
			<label for="NumOfAssistanceFamilies">NumOfAssistanceFamilies</label>
			<input id="NumOfAssistanceFamilies" name="NumOfAssistanceFamilies" value="">
		</p>
		<p>
			<label for="TotalNumOfHouseholds">TotalNumOfHouseholds</label>
			<input id="TotalNumOfHouseholds" name="TotalNumOfHouseholds" value="">
		</p>
		<p>
			<label for="NumOfHouseholdsMovedInAfter2010">NumOfHouseholdsMovedInAfter2010</label>
			<input id="NumOfHouseholdsMovedInAfter2010" name="NumOfHouseholdsMovedInAfter2010" value="">
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


