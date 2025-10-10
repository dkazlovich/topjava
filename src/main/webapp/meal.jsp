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

<p>DateTime:
    <input type="datetime-local">
</p>
<p>Description:
    <input type="text">
</p>
<p>Calories:
    <input type="text">
</p>
<input type="button" value="Save" />
<button onclick="window.history.back()" type="button">Cancel</button>
</body>
</html>
