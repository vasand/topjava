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

<form action="<c:url value="/meals"/>" method="POST">
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

<h1>Add / Edit a Meal</h1>
    table>
    <tr>
        <td><input type="submit" value="Add" name="Add"/></td>
        <td><input type="submit" value="Edit" name="Edit"/></td>
        <td><input type="submit" value="Delete" name="Delete"/></td>
    </tr>
    </table>
    <a href="<c:url value='/meals/add?id=${meal.id}'/>">add</a>
</form>
<%--
<c:url var="addAction" value="/meals/add"/>

<form:form action="${addAction}" commandName="user">
    <input type="hidden" name = "page" value="${page}"/>
    <input type="hidden" name = "userName" value="${userName}"/>
    <table>
        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="admin">
                    <spring:message text="Admin"/>
                </form:label>
            </td>
            <td>
                <form:select path="admin">
                    <form:option value="false">false</form:option>
                    <form:option value="true">true</form:option>
                </form:select>
            </td>
        </tr>
        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="createDate">
                        <spring:message text="Create date"/>
                    </form:label>
                </td>
                <td>
                    <form:input type = "date" path="createDate" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="createDate"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Save"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
--%>
</body>
</html>
