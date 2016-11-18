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
    <title>Get Diversity By populationId</title>
</head>
<body>
 <div class="container theme-showcase" role="main">
<form class="inMiddle" action="getDiversityByPopulationId" method="post">
    <h1 class="blue-color">Search for Diversity by PopulationId</h1>
    <p>
        <label for="populationId">PopulationId</label>
        <input class="form-control" id="PopulationId" name="PopulationId" value="${fn:escapeXml(param.PopulationId)}">
    </p>
    <p>
		<input type="submit" class="btn btn-lg btn-primary">

	</p>
</form>


<div class="inMiddle" id="DiversityCreate"><a href="DiversityCreate.jsp">Create Diversity</a></div>


<h1 class="inMiddle">Matching Diversity</h1>
<table border="1" class="inMiddle">
    <tr>
        <th class="panel panel-primary">PopulationId</th>
        <th class="panel panel-primary">hispanic</th>
        <th>white</th>
        <th>black</th>
        <th>aian</th>
        <th>asian</th>
        <th>NHOPI</th>
        <th>SOR</th>
       
        
    </tr>
    <c:forEach items="${Diversity}" var="Diversity">
        <tr>
        	<td><c:out value="${Diversity.getPopulationId()}"/></td>
            <td><c:out value="${Diversity.getHispanic()}"/></td>
            <td><c:out value="${Diversity.getWhite()}"/></td>
            <td><c:out value="${Diversity.getBlack()}"/></td>
            <td><c:out value="${Diversity.getAian()}"/></td>
            <td><c:out value="${Diversity.getAsian()}"/></td>
            <td><c:out value="${Diversity.getNHOPI()}"/></td>
            <td><c:out value="${Diversity.getSOR()}"/></td>
            <td>
                <a href="DiversityDelete?PopulationId=<c:out value="${Diversity.getPopulationId()}"/>">Delete</a>
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
