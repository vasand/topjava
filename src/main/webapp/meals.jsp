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
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="<c:url value='/edit?id=${meal.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/remove?id=${meal.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<h2>Add / Edit a User</h2>

<c:url var="addAction" value="/meals/add"/>

<form action="${addAction}" commandName="meal">
    <table>
        <c:if test="${!empty meal.description}">
            <tr>
                <td>ID</td>
                <td>
                    <input name="id" readonly="true" size="8" disabled="true"/>
                    <hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Date-time</td>
            <td>
                <input name="dateTime"/>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input name="description"/>
            </td>
        </tr>
        <tr>
            <td>Calories</td>
            <td>
                <input name="calories"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="${!empty meal.description ? Save:Add}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
