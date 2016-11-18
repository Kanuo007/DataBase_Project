<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a AgeDistribution</title>
</head>
<body>
<h1>Create AgeDistribution</h1>
<form action="ageDistributionCreate" method="post">
    <p>
        <label for="PopulationId">PopulationId</label>
        <input id="PopulationId" name="PopulationId" value="">
    </p>
    <p>
        <label for="LessThan5">LessThan5</label>
        <input id="LessThan5" name="LessThan5" value="">
    </p>
    <p>
        <label for="from5To17">from5To17</label>
        <input id="from5To17" name="from5To17" value="">
    </p>
    <p>
        <label for="from18To24">from18To24</label>
        <input id="from18To24" name="from18To24" value="">
    </p>
    <p>
        <label for="from25To44">from25To44</label>
        <input id="from25To44" name="from25To44" value="">
    </p>
    <p>
        <label for="from45To64">from45To64</label>
        <input id="from45To64" name="from45To64" value="">
    </p>
    <p>
        <label for="MoreThan65">MoreThan65</label>
        <input id="MoreThan65" name="MoreThan65" value="">
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