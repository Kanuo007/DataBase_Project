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
    <title>Get HouseUnitDistribution By a houseHoldId</title>
</head>
<nav class="navbar navbar-fixed-top navbar-light bg-primary">
    <div class="container-fluid">
        <a ng-click = "model.updateUser()" class="navbar-brand col-m-6 glyphiconPositionRight white-color">
            <span class=" glyphicon glyphicon-ok"></span></a>
        <div class="navbar-header">
            <a class="navbar-brand white-color" href="#">GreatNeighborhood</a>
        </div>
    </div>

</nav>
<body>
<br>
<form class="inMiddle" action="getHouseUnitDistributionByHouseHoldId" method="post">
    <h1 class="blue-color">Search for a HouseUnitDistribution by houseHoldId</h1>
    <p>
        <label for="HouseHoldId">houseHoldId</label>
        <input class="form-control" id="HouseHoldId" name="HouseHoldId" value="${fn:escapeXml(param.HouseHoldId)}">
    </p>
    <p>
        <%--<br>1<br>--%>
        <input class="form-control btn-primary" type="submit">
        <%--<br>2<br>--%>
        <%--<br>3<br>--%>
        <span id="successMessage"><b>${messages.success}</b></span>
        <%--<br>4<br>--%>
            check point
        ${messages.success}
        ${messages.SingleUnit}
            check point
        <%--<br>5<br>--%>
        <%--${messages.LessThan5}--%>

        <%--${messages.ppid}--%>
        <%--${messages.LessThan5}--%>

        <%--<br>6<br>--%>
    </p>
</form>

<%--<button class=" btn btn-primary inMiddle btn-lg" id="userCreate"><a class="white-color" href="CreateAgeDistribution.jsp">Create AgeDistribution</a></button>--%>


<h1 class="inMiddle blue-color">Matching HouseUnitDistribution</h1>

<table border="2" class=" blue-color table">
    <tr>
        <th >HouseHoldId</th>
        <th>SingleUnit</th>
        <th>TwoToNineUnit</th>
        <th>TenMoreUnit</th>
        <th>MobileHome</th>
    </tr>
    <c:forEach items="${houseUnitDistribution}" var="onehouseUnitDistribution">
        <tr>
            <td><c:out value="${onehouseUnitDistribution.getHouseHoldId()}"/></td>
            <td><c:out value="${onehouseUnitDistribution.getSingleUnit()}"/></td>
            <td><c:out value="${onehouseUnitDistribution.getTwoToNineUnit()}"/></td>
            <td><c:out value="${onehouseUnitDistribution.getTenMoreUnit()}"/></td>
            <td><c:out value="${onehouseUnitDistribution.getMobileHome()}"/></td>

        </tr>
    </c:forEach>
</table>

<nav class="navbar navbar-fixed-bottom bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand glyphiconPositionRight white-color">
            <span class="glyphicon glyphicon-user"></span>
        </a>

    </div>
</nav>
</body>
</html>
