<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page pageEncoding="utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Удаление пользователя</title>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
</head>
<body>

    <form>
        <input type="button" value="На главную" onClick='location.href="${pageContext.request.contextPath}/"'>
        <input type="button" value="В кабинет" onClick='location.href="${pageContext.request.contextPath}/cabinet"'>
    </form>

    <spring:form modelAttribute="userFromDeleteForm" method="post" action="${pageContext.request.contextPath}/delform_currentuser">
        Для подтверждения удаления своего аккаунта подтвердите пароль от своей учетной записи: <spring:password path="pass"/>
        <spring:button>Удалить</spring:button>
    </spring:form>

    <jstl:if test="${ERRORS_LIST.size() > 0}">Ошибка введенных данных:</jstl:if>
    <jstl:forEach items="${ERRORS_LIST}" var="message">
        <li><jstl:out value="${message}"/></li>
    </jstl:forEach>

</body>
</html>
