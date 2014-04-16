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

<div class="ui piled segment">

    <form:form action="${path}/users" method="GET">

        <div class="ui action input">
            <div class="ui icon input">
                <input type="text" placeholder="Search..." name="name">
                <i class="search icon"></i>
            </div>

            <input type="submit" class="ui button" value="Find"/>
        </div>

    </form:form>

    <%-- Users --%>

    <table class="ui basic table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Reputation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.reputation}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<!-- JS -->

<script src="js/jquery-1.11.0.js"></script>
<script src="js/semantic.js"></script>
<script src="js/main.js"></script>

</body>

