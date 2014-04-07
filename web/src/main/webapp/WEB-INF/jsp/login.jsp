<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title>
        <fmt:message key="login.title"/>
    </title>

    <link rel="shortcut icon" href="img/logo.png">
</head>

<link rel="stylesheet" href="css/semantic.css" type="text/css"/>

<c:if test="${not empty error}">
    <div>
        Your login attempt was not successful, try again.<br/>

        Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
    <br/><br/>
</c:if>

<form action="<c:url value="/j_spring_security_check" />" method="POST">
    <table>
        <tr>
            <td>
                Email
            </td>
            <td>
                <input type="text" name="j_username"/>
            </td>
        </tr>
        <tr>
            <td>
                Password
            </td>
            <td>
                <input type="password" name="j_password"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input name="submit" type="submit" value="submit"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/LogUp">
                    Create account
                </a>
            </td>
        </tr>
    </table>
</form>