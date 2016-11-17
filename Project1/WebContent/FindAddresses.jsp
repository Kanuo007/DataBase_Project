<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Addresses</title>
</head>
<body>
	<form action="findaddresses" method="post">
		<h1>Search for Address by AddressId, or state, or state and county</h1>
		<p>
			<label for="addressId">AddressId</label>
			<input id="addressId" name="addressId" value="${fn:escapeXml(param.addressId)}">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="${fn:escapeXml(param.state)}">
		</p>
		<p>
			<label for="county">County</label>
			<input id="county" name="county" value="${fn:escapeXml(param.county)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Addresses</h1>
        <table border="1">
            <tr>
                <th>AddressId</th>
                <th>State</th>
                <th>County</th>
                <th>Tract</th>
                <th>LandArea</th>
                <th>Population</th>
                <th>Delete Address</th>
                <th>Update Address</th>
            </tr>
            <c:forEach items="${addresses}" var="address" >
                <tr>
                    <td><c:out value="${address.getAddressId()}" /></td>
                    <td><c:out value="${address.getState()}" /></td>
                    <td><c:out value="${address.getCounty()}" /></td>
                    <td><c:out value="${address.getTract()}" /></td>
                    <td><c:out value="${address.getLandArea()}" /></td>
                    <td><a href="population?addressid=<c:out value="${address.getAddressId()}"/>">Population</a></td>
                    <td><a href="deleteaddress?addressid=<c:out value="${address.getAddressId()}"/>">Delete</a></td>
                    <td><a href="updateaddress?addressid=<c:out value="${address.getAddressId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>