<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>User</title>
</head>

<body class="login-body">

<div class="ui inverted segment">
    <h3 class="ui left tiny floated header">
        <a class="ui purple label">
            ${user.name}
        </a>
    </h3>

    <h3 class="ui right tiny floated header">
        <a class="ui small blue submit button"
           href="<c:url value="/j_spring_security_logout"/>">
            Logout
        </a>
    </h3>
</div>

<div class="ui piled segment">

    <h2 class="ui header">
        <h3 class="ui left floated header">
            All Questions
        </h3>

        <h3 class="ui right floated header">
            6<br/><small>questions</small>
        </h3>
    </h2>

    <c:forEach items="${map}" var="entry">

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
                <td rowspan="2">Votes<br/>${entry.key.votes}</td>
                <td rowspan="2" colspan="2">
                    <b>${entry.key.name}</b>
                    <br/>
                    <small>${entry.key.content}</small>
                </td>
            </tr>
            <tr>
            </tr>
            <tr>
                <td>Answers<br/>${entry.value}</td>
                <td>
                    <c:forEach items="${entry.key.tags}" var="tag">
                        <a class="ui teal label">${tag.name}</a>&thinsp;
                    </c:forEach>
                </td>
                <td>
                    Dozortsev Anton<br/>
                    <small>
                        <fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm"
                                        value="${entry.key.created}"/>
                    </small>
                </td>
            </tr>
            </tbody>
        </table>
        <hr/>
    </c:forEach>
    <br/><br/><br/><br/>

    <c:forEach var="i" begin="0" end="6" step="2">
        <a href="${path}/questions?page=${i}">${i + 1}</a>&emsp;
    </c:forEach>

</div>
</body>