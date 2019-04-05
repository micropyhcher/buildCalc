<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>
    <form>
        <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
    </form>
    <p>

    <spring:form modelAttribute="userFromLogForm" method="post" action="${pageContext.request.contextPath}/logform">
        E-Mail: <spring:input path="email"/>
        Пароль: <spring:password path="pass"/>
        <spring:button>Войти</spring:button>
    </spring:form>

        <jstl:if test="${errorsList.size() > 0}">Ошибка введенных данных:</jstl:if>
        <jstl:forEach items="${errorsList}" var="mesage">
            <li><jstl:out value="${mesage}"/></li>
        </jstl:forEach>

    <%--<jstl:out value="${userFromLoginForm}"/>--%>
</body>
</html>
