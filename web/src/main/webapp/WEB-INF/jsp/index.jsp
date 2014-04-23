<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>User</title>
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
    <a class="item" href="<c:url value="/j_spring_security_logout"/>">
        <i class="mail icon"></i>Log Out
    </a>

    <div class="right menu">
        <div class="item">
            <div class="ui icon input">
                <form action="${path}/questions/tagged" method="GET">
                    <input type="text" placeholder="Search..." name="query">
                    <i class="search link icon"></i>
                </form>
            </div>
        </div>
        <div class="item">
            <a class="item" href="${path}/questions/ask">
                <i class="mail icon"></i>Ask Questions
            </a>
        </div>
    </div>
</div>

<div class="ui piled segment">
    <p>
    <h3>ALL QUESTIONS&ensp;${questionCount}</h3>
    </p>
    <div class="ui horizontal icon divider">
        <i class="circular question icon"></i>
    </div>

    <c:forEach items="${questions}" var="qs">

        <table class="ui basic table">
            <thead>
            <tr>
                <th class="one wide"></th>
                <th class="ten wide"></th>
                <th class="one wide"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td rowspan="2">
                        <h1>${qs.votes}</h1><br/><small>Votes</small>
                </td>
                <td rowspan="2" colspan="2">
                    <b><a href="${path}/question/${qs.id}">
                            ${qs.title}
                    </a></b>
                    <br/>
                    <small>${fn:substring(qs.content, 0, 250)}&hellip;</small>
                </td>
            </tr>
            <tr>
            </tr>
            <tr>
                <td>
                    <h1>${qs.answerCount}</h1><small>Answers</small>
                </td>
                <td>
                    <c:forEach items="${qs.tags}" var="tag">
                        <a class="ui teal label">${tag.name}</a>&ensp;
                    </c:forEach>
                </td>
                <td>
                    <b><a href="${path}/user/${qs.user.id}">${qs.user.name}</a></b><br/>
                    <small>
                        <fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm"
                                        value="${qs.created}"/>
                    </small>
                </td>
            </tr>
            </tbody>
        </table>
        <hr/>
    </c:forEach>

    <div class="ui borderless pagination menu">

        <fmt:formatNumber var="pages" type="number" pattern="#"
                          value="${(questionCount / 10) + 0.5}"/>

        <c:forEach var="i" begin="1" end="${pages}" step="1">
            <a class="item" href="${path}/questions?page=${i}">${i}</a>
        </c:forEach>
    </div>

</div>
</body>