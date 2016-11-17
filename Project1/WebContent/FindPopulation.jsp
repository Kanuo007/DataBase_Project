<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Population</title>
</head>
<body>
	<form action="findpopulation" method="post">
		<h1>Find Population By AddressId</h1>
		<p>
			<label for="addressId">AddressId</label>
			<input id="addressId" name="addressId" value="${fn:escapeXml(param.addressId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Population</h1>
        <table border="1">
            <tr>
                <th>PopulationId</th>
                <th>AddressId</th>
                <th>Total</th>
                <th>Delete Population</th>
                <th>Update Population</th>
            </tr>
            <tr>
                <td><c:out value="${population.getPopulationId()}" /></td>
                <td><c:out value="${population.getAddressId()}" /></td>
                <td><c:out value="${population.getTotal()}" /></td>
                <td><a href="deletepopulation?addressid=<c:out value="${population.getAddressId()}"/>">Delete</a></td>
                <td><a href="updatepopulation?addressid=<c:out value="${population.getAddressId()}"/>">Update</a></td>
            </tr>
       </table>
</body>
</html>