<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список пользователей</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>

    <form>
        <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
        <input type="button" value="В кабинет" onClick='location.href="${pageContext.request.contextPath}/cabinet"'>
    </form>
    <p>
    <jstl:if test="${!isUserListEmpty}">Список пользователей:</jstl:if>
    <jstl:if test="${isUserListEmpty}">Список пользователей пуст</jstl:if>
    <hr>
    <jstl:forEach items="${userList}" var="userFromList">

        <li><jstl:out value="${userFromList.name} | ${userFromList.email} ${userFromList.role.userRolesEntity} (${userFromList.id} ; ${userFromList.role.userRolesEntity})"/>
        <input type="button" value="Удалить" onclick='location.href="${pageContext.request.contextPath}/deluser/${userFromList.id}"'></li>
    </jstl:forEach>
</body>
</html>
