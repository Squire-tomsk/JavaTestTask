<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="web.title.sing-up"/></title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <c:url var="sing_up_url"  value="/sing-up" />
</head>
<body>
    <form:form action="${sing_up_url}" method="post" modelAttribute="singUpForm">
        <div class="main">
            <form:errors path="username" class="errorblock" element="div"/>
            <form:errors path="password" class="errorblock" element="div" />
            <form:errors path="passwordConfirmed" class="errorblock" element="div"/>
        </div>
        </br>
        <div class="main">
            <div class="field">
                <label for="login"><spring:message code="form.sing-up.label.username"/></label>
                <form:input path="username" type="text" id="login"/>
            </div>
            <div class="field">

                <label for="password"><spring:message code="form.sing-up.label.password"/></label>
                <form:input path="password" type="password" id="password"/>
            </div>
            <div class="field">
                <label for="password_confirmation"><spring:message code="form.sing-up.label.passwordсonfirmation"/></label>
                <form:input path="passwordConfirmation" type="password" id="password_confirmation"/>
            </div>
            <div class="submit">
                <input type="submit" value="<spring:message code="form.sing-up.label.submit"/>">
            </div>
        </div>
    </form:form>
</body>
</html>