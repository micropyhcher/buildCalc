<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>

<div>
    <jstl:if test="${userEntered_Flag}">Вы вошли под именем: </jstl:if>
    <jstl:out value="${userEntered}"/>

</div>

<img src="${pageContext.request.contextPath}images/Logo.jpg" alt="Фон" style="alignment: center">

    <form class="input-group" style="background: #0069d9">
        <jstl:if test="${userEntered_Role == 'GUEST'}">
            <input type="button" value="Регистрация" onClick='location.href="${pageContext.request.contextPath}/regstandart"'>
            <input type="button" value="Вход на сайт" onClick='location.href="${pageContext.request.contextPath}/logform"'>
        </jstl:if>
        <jstl:if test="${userEntered_Role == 'USER' or userEntered_Role == 'ADMIN'}">
            <input type="button" value="Личный кабинет пользователя" onClick='location.href="${pageContext.request.contextPath}/cabinet"'>
        </jstl:if>
        <jstl:if test="${userEntered_Role == 'ADMIN'}">
            <input type="button" value="Список пользователей" onClick='location.href="${pageContext.request.contextPath}/userlist"'>
        </jstl:if>

    </form>
<br>

</body>
</html>
