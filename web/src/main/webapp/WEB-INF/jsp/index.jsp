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
        <i class="stackexchange icon"></i>Questions
    </a>
    <a class="item" href="${path}/users">
        <i class="users basic icon"></i>Users
    </a>
    <a class="item" href="<c:url value="/j_spring_security_logout"/>">
        <i class="sign out icon"></i>Log Out
    </a>

    <a class="item" href="${path}/questions/ask">
        <i class="question icon"></i>Ask Questions
    </a>
</div>

<div class="ui piled segment">
    <p>
    <h3>ALL QUESTIONS&ensp;${questionCount}</h3>
    </p>
    <div class="ui horizontal icon divider">
        <i class="circular stackexchange icon"></i>
    </div>

    <c:forEach items="${questions}" var="qs" varStatus="loop">

        <table class="ui basic small table">
            <tbody>
            <tr>
                <td rowspan="2" class="wide one" align="center">
                    <h2>${qs.answerCount}</h2><br/>
                    <small>answers</small>
                </td>
                <td rowspan="2" class="wide one" align="center">
                    <h2>${qs.votes}</h2><br/>
                    <small>votes</small>
                </td>
                <td colspan="2" class="wide sixteen">
                    <h3><a href="#">${qs.title}</a></h3>
                </td>
            </tr>
            <tr>
                <td class="wide eight">
                    <c:forEach items="${qs.tags}" var="tag">
                        <a href="${path}/questions/tagged/${tag.name}" title="${fn:substring(tag.desc, 0, 50)}&hellip;" class="ui teal label">${tag.name}</a>&ensp;
                    </c:forEach>
                </td>
                <td>
                    <small>
                        asked <a href="${path}/user/${qs.user.id}">${qs.user.name}</a>
                        <fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm"
                                        value="${qs.created}"/>
                    </small>
                </td>
            </tr>
            </tbody>
        </table>

        <c:if test="${not loop.last}">
            <div class="ui section divider"></div>
        </c:if>

    </c:forEach>

    <div class="ui horizontal icon divider">
        <i class="circular stackexchange icon"></i>
    </div>

    <div class="ui borderless pagination menu">

        <fmt:formatNumber var="pages" type="number" pattern="#"
                          value="${(questionCount / 10) + 0.5}"/>

        <c:forEach var="i" begin="1" end="${pages}" step="1">
            <a class="item" href="${path}/questions?page=${i}">${i}</a>
        </c:forEach>
    </div>

</div>

<br/><br/><br/>
<br/><br/><br/>

</body>