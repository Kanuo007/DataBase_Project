<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Gender Distribution By AddressId</title>
</head>
<body>
	<form action="findgenderdistribution" method="post">
		<h1>Find Gender Distribution By AddressId</h1>
		<p>
			<label for="addressid">AddressId</label>
			<input id="addressid" name="addressid" value="${fn:escapeXml(param.addressid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching GenderDistribution</h1>
        <table border="1">
            <tr>
                <th>PopulationId</th>
                <th>AddressId</th>
                <th>Total</th>
                <th>Male</th>
                <th>Female</th>
                <th>Delete GenderDistribution</th>
                <th>Update GenderDistribution</th>
            </tr>
            <tr>
                <td><c:out value="${genderdistribution.getPopulationId()}" /></td>
                <td><c:out value="${genderdistribution.getAddressId()}" /></td>
                <td><c:out value="${genderdistribution.getTotal()}" /></td>
                <td><c:out value="${genderdistribution.getMale()}" /></td>
                <td><c:out value="${genderdistribution.getFemale()}" /></td>
                <td><a href="deletegenderdistribution?addressid=<c:out value="${genderdistribution.getAddressId()}"/>">Delete</a></td>
                <td><a href="updategenderdistribution?addressid=<c:out value="${genderdistribution.getAddressId()}"/>">Update</a></td>
            </tr>
       </table>
</body>
</html>