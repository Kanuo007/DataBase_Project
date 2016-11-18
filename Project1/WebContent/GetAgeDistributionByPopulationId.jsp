<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
<link href="css/style.css" rel="stylesheet"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Get AgeDistribution By a populationId</title>
</head>
<body>
<form class="inMiddle" action="getAgeDistributionByPopulationId" method="post">
    <h1 class="blue-color">Search for a AgeDistribution by PopulationId</h1>
    <p>
        <label for="populationId">PopulationId</label>
        <input class="form-control" id="populationId" name="populationId" value="${fn:escapeXml(param.populationId)}">
    </p>
    <p>
        <%--<br>1<br>--%>
        <input class="container-fluid" type="submit">
        <%--<br>2<br>--%>
        <%--<br>3<br>--%>
        <%--<span id="successMessage"><b>${messages.success}</b></span>--%>
        <%--<br>4<br>--%>
        <%--${messages.from5To17}--%>
        <%--<br>5<br>--%>
        <%--${messages.LessThan5}--%>

        <%--${messages.ppid}--%>
        <%--${messages.LessThan5}--%>

        <%--<br>6<br>--%>
    </p>
</form>

<br/>
<div class="inMiddle" id="userCreate"><a href="CreateAgeDistribution.jsp">Create AgeDistribution</a></div>
<br/>

<h1 class="inMiddle">Matching AgeDistribution</h1>
<table border="1" class="inMiddle">
    <tr>
        <th class="panel panel-primary">PopulationId</th>
        <th class="panel panel-primary">LessThan5</th>
        <th>from5To17</th>
        <th>from18To24</th>
        <th>from25To44</th>
        <th>from45To64</th>
        <th>MoreThan65</th>
        <th>Delete AgeDistribution</th>
        <th>Update AgeDistribution</th>
    </tr>
    <c:forEach items="${ageDistribution}" var="oneAgeDistribution">
        <tr>
            <td><c:out value="${oneAgeDistribution.getPopulationId()}"/></td>
            <td><c:out value="${oneAgeDistribution.getLessThan5()}"/></td>
            <td><c:out value="${oneAgeDistribution.getFrom5To17()}"/></td>
            <td><c:out value="${oneAgeDistribution.getFrom18To24()}"/></td>
            <td><c:out value="${oneAgeDistribution.getFrom25To44()}"/></td>
            <td><c:out value="${oneAgeDistribution.getFrom45To64()}"/></td>
            <td><c:out value="${oneAgeDistribution.getMoreThan65()}"/></td>
            <td>
                <a href="ageDistributionDelete?populationId=<c:out value="${oneAgeDistribution.getPopulationId()}"/>">Delete</a>
            </td>
            <td><a href="ageDistributionUpdate?populationId=<c:out value="${oneAgeDistribution.getPopulationId()}"/>">Update</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
