<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="web.title.welcome"/></title>
    <spring:url value="/resources/js/WelcomeMessage.js" var="welcomeMessage" />
</head>
<body>
<script type="text/javascript" src=${welcomeMessage}>document.write(getWelcomeMessage());</script>, ${username}! <br>
<form action="<c:url value="/sing-out"/>" id="logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<input type="button" onclick="document.getElementById('logout').submit();" value="<spring:message code="form.welcome.label.sing-out"/>" />
</body>
</html>