<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Education Distribution By AddressId</title>
</head>
<body>
	<form action="findeducationdistribution" method="post">
		<h1>Find Education Distribution By AddressId</h1>
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
	<h1>Matching EducationDistribution</h1>
        <table border="1">
            <tr>
                <th>PopulationId</th>
                <th>AddressId</th>
                <th>Total</th>
                <th>Not High School</th>
                <th>College</th>
                <th>Delete EducationDistribution</th>
                <th>Update EducationDistribution</th>
            </tr>
            <tr>
                <td><c:out value="${educationdistribution.getPopulationId()}" /></td>
                <td><c:out value="${educationdistribution.getAddressId()}" /></td>
                <td><c:out value="${educationdistribution.getTotal()}" /></td>
                <td><c:out value="${educationdistribution.getNotHighSchool()}" /></td>
                <td><c:out value="${educationdistribution.getCollege()}" /></td>
                <td><a href="deleteeducationdistribution?addressid=<c:out value="${educationdistribution.getAddressId()}"/>">Delete</a></td>
                <td><a href="updateeducationdistribution?addressid=<c:out value="${educationdistribution.getAddressId()}"/>">Update</a></td>
            </tr>
       </table>
</body>
</html>