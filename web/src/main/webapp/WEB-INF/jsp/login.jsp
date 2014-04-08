<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>
        <fmt:message key="login.title"/>
    </title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>

<body class="login-body">

<c:if test="${ not empty error }">

    <div class="ui black small message">
        <i class="close icon"></i>

        <div class="header">
            You must register before you can do that!
        </div>
        Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>

<div class="ui piled segment">

    <!-- Tabs -->

    <div class="ui pointing secondary demo menu">
        <a class="active red item" data-tab="first">
            Log in
        </a>
        <a class="blue item" data-tab="second">
            Sign in
        </a>
    </div>


    <!-- Login panel -->

    <form action="<c:url value="/j_spring_security_check" />" method="POST">

        <div class="ui active tab segment" data-tab="first">
            <div class="ui form segment">
                <div class="field">
                    <label><fmt:message key="login.lbl.login"/></label>

                    <div class="ui small left labeled icon input">
                        <input type="text" name="j_username">
                        <i class="mail icon"></i>
                    </div>
                </div>
                <div class="field">
                    <label><fmt:message key="login.lbl.pwd"/></label>

                    <div class="ui small left labeled icon input">
                        <input type="password" name="j_password">
                        <i class="lock icon"></i>
                    </div>
                </div>
                <input class="ui red submit small button" type="submit" value="Login"/>
            </div>
        </div>
    </form>


    <!-- Signin panel -->

    <form:form action="${pageContext.request.contextPath}/createAccount"
               method="POST"
               modelAttribute="newUser">

        <div class="ui tab segment" data-tab="second">
            <div class="ui form segment">
                <div class="two fields">
                    <div class="field">
                        <label>Name</label><input name="name" type="text">
                    </div>
                    <div class="field">
                        <label>Age</label><input name="age" type="text">
                    </div>
                </div>
                <div class="field">
                    <div class="field">
                        <label>About me</label><input name="aboutMe" type="text">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>Location</label><input name="location" type="text">
                    </div>
                    <div class="field">
                        <label>Site</label><input name="site" type="text">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>Email</label><input name="email" type="text">
                    </div>
                    <div class="field">
                        <label>Password</label><input name="password" type="password">
                    </div>
                </div>

                <input class="ui small blue submit button" type="submit" value="Create"/>
            </div>
        </div>
    </form:form>
</div>
</body>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<script src="${pageContext.request.contextPath}/js/semantic.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>