<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="web.title.sing-in"/></title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="main">
        <c:if test="${not empty error}">
            <div class="errorblock">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="msg">${message}</div>
        </c:if>
    </div>
    <form action="<c:url value='/sing-in' />" method="post">
        <div class="main">
            <div class="field">
                <label for="username"> <spring:message code="form.sing-in.label.username"/> </label>
                <input type="text" id="username" name="username" value="${username}">
            </div>
            <div class="field">
                <label for="password"><spring:message code="form.sing-in.label.password"/></label>
                <input type="password" id="password" name="password" />
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="submit">
                <input type="submit" name="sinin" value="<spring:message code="form.sing-in.label.submit"/>">
                <input type="button" onclick="location.href='<c:url value='/sing-up'/>';" value="<spring:message code="form.sing-in.label.sing-up"/>" />
            </div>
        </div>
    </form>

</body>
</html>