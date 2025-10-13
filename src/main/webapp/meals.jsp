<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="ru">
<head>
    <title>Meal list</title>
    <meta charset="UTF-8">
    <style>
        table {
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #999;
            padding: 8px;
            text-align: left;
        }

        .excess-true {
            color: red;
        }

        .excess-false {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meal.jsp">Add meal</a>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${mealsTo}">
        <tr class="${meal.excess ? 'excess-true' : 'excess-false'}">
            <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>