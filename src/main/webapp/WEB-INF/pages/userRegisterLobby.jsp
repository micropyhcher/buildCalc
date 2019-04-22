<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>

    <form>
        <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
    </form>

    <p>
    <spring:form modelAttribute="userFromRegForm" method="post" action="${pageContext.request.contextPath}/regstandart">
        Ваше имя: <spring:input path="name"/>

        Ваш возраст: <spring:input path="age"/>
        Ваш E-Mail: <spring:input path="email"/>
        Пароль: <spring:password path="pass"/>
        <spring:button>Зарегистрироваться</spring:button>
        <br>
        Зарегистрироваться как:<br>
<%--        <spring:select path="userRole" items="${rolesList}"/><br>--%>
        стандартный пользователь: <spring:radiobutton path="userRole" value="${standartUserRole}"/><br>
        администратор: <spring:radiobutton path="userRole" value="${adminUserRole}"/><br>

    </spring:form>
    <jstl:if test="${ERRORS_LIST.size() > 0}">Ошибка введенных данных:</jstl:if>
    <jstl:forEach items="${ERRORS_LIST}" var="message">
        <li><jstl:out value="${message}"/></li>
    </jstl:forEach>

</body>
</html>
