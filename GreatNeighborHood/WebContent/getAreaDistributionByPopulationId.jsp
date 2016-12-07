<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Get AreaDistribution By populationId</title>
</head>
<body>
 <div class="container theme-showcase" role="main">
<form class="inMiddle" action="getAreaDistributionByPopulationId" method="post">
    <h1 class="blue-color">Search for AreaDistribution by PopulationId</h1>
    <p>
        <label for="populationId">PopulationId</label>
        <input class="form-control" id="PopulationId" name="PopulationId" value="${fn:escapeXml(param.PopulationId)}">
    </p>
    <p>
		<input type="submit" class="btn btn-lg btn-primary">

	</p>
</form>


<div class="inMiddle" id="AreaDistributionCreate"><a href="AreaDistributionCreate.jsp">Create AreaDistribution</a></div>


<h1 class="inMiddle">Matching AreaDistribution</h1>
<table border="1" class="inMiddle">
    <tr>
        <th class="panel panel-primary">PopulationId</th>
        <th class="panel panel-primary">Urban</th>
        <th>Urbancluster</th>
        <th>Rural</th>
        
        <th></th>
        
    </tr>
    <c:forEach items="${AreaDistribution}" var="AreaDistribution">
        <tr>
         	<td><c:out value="${AreaDistribution.getPopulationId()}"/></td>
            <td><c:out value="${AreaDistribution.getUrban()}"/></td>
            <td><c:out value="${AreaDistribution.getUrbancluster()}"/></td>
            <td><c:out value="${AreaDistribution.getRural()}"/></td>
            
            <td>
                <a href="AreaDistributionDelete?PopulationId=<c:out value="${AreaDistribution.getPopulationId()}"/>">Delete</a>
            </td>
            <td><a href="AreaDistributionUpdateUrban?PopulationId=<c:out value="${AreaDistribution.getPopulationId()}"/>">Update</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
 <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
