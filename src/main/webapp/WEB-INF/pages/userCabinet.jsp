<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${userEntered.email}</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
    <body>

        <div>
            <jstl:out value="Вы вошли под именем: ${userEntered.name} | Email : ${userEntered.email}"/>
            <jstl:out value="----${userAllInfo}"/>
        </div>

        <form class="input-group" style="background: #0069d9">
            <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
            <input type="button" value="Редактировать " onClick='location.href="${pageContext.request.contextPath}/editform"'>
            <input type="button" value="Выйти из записи" onClick='location.href="${pageContext.request.contextPath}/unlog"'>
            <input type="button" value="Удалить пользователя" onClick='location.href="${pageContext.request.contextPath}/delform_currentuser"'>
            <input type="button" value="Проба" onClick='location.href="${pageContext.request.contextPath}/unit1page"'>
        </form>

    </body>
</html>
