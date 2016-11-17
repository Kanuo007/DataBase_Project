<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create an EducationDistribution</title>
</head>
<body>
	<h1>Create an EducationDistribution</h1>
	<form action="createeducationdistribution" method="post">
		<p>
			<label for="addressid">AddressId</label>
			<input id="addressid" name="addressid" value="">
		</p>
		<p>
			<label for="total">Total</label>
			<input id="total" name="total" value="">
		</p>
		<p>
			<label for="nothighschool">NotHighSchool</label>
			<input id="nothighschool" name="nothighschool" value="">
		</p>
		<p>
			<label for="college">College</label>
			<input id="college" name="college" value="">
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