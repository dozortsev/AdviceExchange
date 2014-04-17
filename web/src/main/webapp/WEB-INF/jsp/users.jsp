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
<body>

<div class="ui red inverted menu">
    <a class="active item">
        <i class="home icon"></i>Home
    </a>
    <a class="item">
        <i class="mail icon"></i>Messages
    </a>
    <a class="item">
        <i class="user icon"></i>Friends
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

    <table class="ui basic table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Joined</th>
            <th>Reputation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>
                    <a href="${path}/user/${user.id}">${user.name}</a>
                </td>
                <td>
                    <fmt:formatDate type="both" value="${user.joined}"
                                    pattern="yyyy-MM-dd / HH:mm"/>
                </td>
                <td>${user.reputation}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


<%-- New

    <c:set var="index" value="4" scope="page"/>

    <c:forEach items="${users}" var="user">

    <div class="ui four connected items">

        <c:set var="index" value="4" scope="page"/>

        <div class="row">
            <div class="item">
                <div class="content">
                    <div class="name">
                            ${user.name}
                    </div>
                    <p class="date">
                        <fmt:formatDate type="both" value="${user.joined}"
                                        pattern="yyyy-MM-dd / HH:mm"/>
                    </p>

                    <p class="description">
                            ${user.email}
                    </p>
                </div>
            </div>
        </div>

        <c:set var="index" value="${index - 1}" scope="page"/>

        <div class="item">
            <div class="content">
                <div class="name">
                        ${user.name}
                </div>
                <p class="date">
                    <fmt:formatDate type="both" value="${user.joined}"
                                    pattern="yyyy-MM-dd / HH:mm"/>
                </p>

                <p class="description">
                        ${user.email}
                </p>
            </div>
        </div>
        </c:forEach>


--%>

    <div class="ui borderless pagination menu">

        <c:set var="setSize" value="${fn:length(users)}"/>

        <c:forEach var="i" begin="1" end="${(setSize != 10 ? 0 : userCount / 10) + 0.5}" step="1">
            <a class="item" href="${path}/users?page=${i}">${i}</a>&emsp;
        </c:forEach>

    </div>


</div>

<!-- JS -->

<script src="js/jquery-1.11.0.js"></script>
<script src="js/semantic.js"></script>
<script src="js/main.js"></script>

</body>

