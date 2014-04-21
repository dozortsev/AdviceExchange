<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<head lang="en">

    <meta charset="UTF-8">

    <title>
        User / ${user.name}
    </title>

    <link rel="shortcut icon" href="img/logo.png">

    <link rel="stylesheet" href="css/semantic.css"/>
    <link rel="stylesheet" href="css/style.css"/>

</head>
<body class="login-body">

<div class="ui secondary menu">
    <a class="active item" href="${path}/user/${user.id}">
        <i class="home icon"></i>${user.name}
    </a>
    <a class="item" href="${path}/questions">
        <i class="home icon"></i>Questions
    </a>
    <a class="item" href="${path}/users">
        <i class="users basic icon"></i>Users
    </a>
    <a class="item right" href="<c:url value="/j_spring_security_logout"/>">
        <i class="mail icon"></i>Log Out
    </a>
    <a class="item right" href="${path}/questions/ask">
        <i class="mail icon"></i>Ask Questions
    </a>
</div>

<div class="ui piled segment">

    <p>
        <h3>USERS</h3>
    </p>
    <div class="ui divider"></div>
    <p>
    <form:form action="${path}/users" method="GET">

        <div class="ui action input">
            <div class="ui icon input">
                <input type="text" placeholder="Search..." name="name" autofocus="true">
                <i class="search icon"></i>
            </div>
        </div>

    </form:form>
    </p>

    <%-- Users --%>

    <main class="ui four column grid items">
        <c:forEach items="${users}" var="user">
            <section class="column">
                <div class="item">
                    <div class="content">
                        <a href="${path}/user/${user.id}">
                            ${user.name}
                        </a>
                        ${user.reputation}
                        <small>
                            <fmt:formatDate type="both" value="${user.joined}"
                                            pattern="yyyy-MM-dd / HH:mm"/>
                        </small>
                        <small>
                            <c:if test="${ not empty user.site }">
                                ${user.site}
                            </c:if>
                        </small>
                    </div>
                </div>
            </section>
        </c:forEach>
    </main>

    <div class="ui borderless pagination menu">

        <fmt:formatNumber var="pages" value="${(fn:length(users) != 36 ? 0 : userCount / 36) + 0.5}"
                          scope="page" type="number" pattern="#"/>

        <c:forEach var="i" begin="1" end="${pages}" step="1">
            <a class="item" href="${path}/users?page=${i}">${i}</a>&emsp;
        </c:forEach>
    </div>

</div>

<!-- JS -->

<script src="js/jquery-1.11.0.js"></script>
<script src="js/semantic.js"></script>
<script src="js/main.js"></script>

</body>

