<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
    <spring:form modelAttribute="userFromEditForm" method="post" action="${pageContext.request.contextPath}/editform">
        Новое имя: <spring:input path="name"/>
        Новый E-Mail: <spring:input path="email"/>
        Подтвердите старый Пароль: <spring:password path="pass"/>
        Новый Пароль: <spring:password path="newPass"/>
        <spring:button>Изменить</spring:button>
    </spring:form>

</body>
</html>
