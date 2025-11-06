<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
         form {
             display: flex;
             max-width: 1050px;
             flex-direction: row;
             align-items: flex-end;
             gap: 80px;
             font-family: Arial, sans-serif;

             border: 2px solid #ccc;
             border-radius: 8px;
             padding: 20px;
             background-color: #f9f9f9;
         }

        .date-group,
        .time-group {
            display: flex;
            gap: 20px;
            justify-content: space-between;
            flex-wrap: wrap;
        }

        .date-group div,
        .time-group div {
            display: flex;
            flex-direction: column;
            flex: 1;
            min-width: 180px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="date"],
        input[type="time"] {
            padding: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .button-group {
            text-align: right;
        }
        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <form method="get" action="meals">
        <input type="hidden" name="action" value="filter">

        <div class="date-group">
            <div>
                <label for="start-date">From date (inclusive)</label>
                <input type="date" name="from-date" value="<%= request.getParameter("from-date") != null ? request.getParameter("from-date") : "" %>">
            </div>

            <div>
                <label for="end-date">To date (inclusive)</label>
                <input type="date" name="to-date" value="<%= request.getParameter("to-date") != null ? request.getParameter("to-date") : "" %>">
            </div>
        </div>

        <div class="time-group">
            <div>
                <label for="start-time">From time (inclusive)</label>
                <input type="time" name="from-time" value="<%= request.getParameter("from-time") != null ? request.getParameter("from-time") : "" %>">
            </div>

            <div>
                <label for="end-time">To time (exclusive)</label>
                <input type="time" name="to-time" value="<%= request.getParameter("to-time") != null ? request.getParameter("to-time") : "" %>">
            </div>
        </div>

        <div class="button-group">
            <button type="submit">Filter</button>
        </div>
    </form>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>