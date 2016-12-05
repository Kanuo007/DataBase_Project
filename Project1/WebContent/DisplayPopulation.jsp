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
	<form action="displaypopulation" method="post">
		<h1>Display Population Details By PopulationId</h1>
		<p>
			<label for="populationId">PopulationId</label>
			<input id="populationId" name="populationId" value="${fn:escapeXml(param.populationId)}">
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
        		<th> Overall Score</th>
        	</tr>
        	<tr>
        		<td><c:out value="${diversity.getScore()*0.3+0.3*agedistribution.getScore()+0.4*educationdistribution.getScore()}" /></td>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        		<th>Total # of People</th>
        	</tr>
        	<tr>
        		<td><c:out value="${population.getTotal()}" /></td>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
            <tr>
                <th>Diversity Details</th>
            </tr>
            <tr>
            	<th>Score</th>
                <th>Hispanic</th>
                <th>White</th>
                <th>Black</th>
                <th>Asian</th>
                <th>American Indian and Alaska Native alone</th>
                <th>Native Hawaiian and Other Pacific Islander</th>
                <th>Other</th>
            </tr>
            <tr>
                <td><c:out value="${diversity.getScore()}" /></td>
                <td><c:out value="${diversity.getHispanic()}" /></td>
                <td><c:out value="${diversity.getWhite()}" /></td>
                <td><c:out value="${diversity.getBlack()}" /></td>
                <td><c:out value="${diversity.getAsian()}" /></td>
                <td><c:out value="${diversity.getAian()}" /></td>
                <td><c:out value="${diversity.getNHOPI()}" /></td>
                <td><c:out value="${diversity.getSOR()}" /></td>
            </tr>
            <tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
            <tr>
				<th>Age Distribution Details</th>
			</tr>
			<tr>
            	<th>Score</th>
                <th>LessThan5</th>
                <th>from5To17</th>
                <th>from18To24</th>
                <th>from25To44</th>
                <th>from45To64</th>
                <th>MoreThan65</th>
            </tr>
            <tr>
            	<td><c:out value="${agedistribution.getScore()}" /></td>
            	<td><c:out value="${agedistribution.getLessThan5()}" /></td>
            	<td><c:out value="${agedistribution.getFrom5To17()}" /></td>
            	<td><c:out value="${agedistribution.getFrom18To24()}" /></td>
            	<td><c:out value="${agedistribution.getFrom25To44()}" /></td>
            	<td><c:out value="${agedistribution.getFrom45To64()}" /></td>
            	<td><c:out value="${agedistribution.getMoreThan65()}" /></td>
            </tr>
            <tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
        	</tr>
        	<tr>
			<tr>
                <th>Education Distribution Details</th>
            </tr>
            <tr>
            	<th>Score</th>
                <th>notHighSchool</th>
                <th>HighSchool</th>
                <th>College</th>
            </tr>
            <tr>
            	<td><c:out value="${educationdistribution.getScore()}" /></td>
            	<td><c:out value="${educationdistribution.getNotHighSchool()}" /></td>
            	<td><c:out value="${educationdistribution.getHighSchool()}" /></td>
            	<td><c:out value="${educationdistribution.getCollege()}" /></td>
            </tr>
       </table>
</body>
</html>