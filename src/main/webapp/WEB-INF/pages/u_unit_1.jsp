<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Unit1</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>

    <form>
        <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
        <input type="button" value="В кабинет" onClick='location.href="${pageContext.request.contextPath}/cabinet"'>
    </form>

    <spring:form modelAttribute="unit1form" method="post" action="${pageContext.request.contextPath}/unit1page">
        <spring:input path="a"/>
        <spring:input path="b"/>
        <spring:button>Вычислить</spring:button>
    </spring:form>
    <jstl:out value="${unit1list}"/>
    <jstl:out value="${errorsList}"/>
</body>
</html>
