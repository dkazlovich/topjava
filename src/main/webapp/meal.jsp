<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal</title>
    <meta charset="UTF-8">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form action="meals?action=${meal.id == null ? 'createMeal' : 'updateMeal'}" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <p>DateTime:
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
    </p>
    <p>Description:
        <input type="text" name="description" value="${meal.description}">
    </p>
    <p>Calories:
        <input type="text" name="calories" value="${meal.calories}">
    </p>
    <input type="submit" value="Save">
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>


</body>
</html>
