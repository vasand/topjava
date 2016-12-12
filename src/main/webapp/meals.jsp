<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 17.11.16
  Time: 12.11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Meal </title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 5px 5px;
            height: 27px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<a href="index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Meal list</h1>

<table class="tg">
    <tr>
        <th width="20">ID</th>
        <th width="120">Date</th>
        <th width="200">Description</th>
        <th width="50">Calories</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <tr>
            <td>${meal.id}</td>
            <td>${meal.date} ${meal.time}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="<c:url value='/meals/edit?id=${meal.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/meals/remove?id=${meal.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<h2>Add / Edit a User</h2>

<c:url var="addAction" value="/meals/add"/>

<form action="${addAction}" commandName="meal" method="post">
    <table>
        <c:if test="${!empty curMeal.description}">
            <tr>
                <td>ID</td>
                <td>
                    <input type="text" name="id" value="${curMeal.id}" readonly="true" size="8" disabled="true"/>
                    <input type="hidden" name="id" value="${curMeal.id}">
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Date-time</td>
            <td><input type="datetime-local" value="${curMeal.dateTime}" name="dateTime"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="${curMeal.description}"/></td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="number" name="calories" value="${curMeal.calories}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="${!empty curMeal.description ? 'Save':'Add'}"/></td>
        </tr>
    </table>
</form>
</body>
</html>
