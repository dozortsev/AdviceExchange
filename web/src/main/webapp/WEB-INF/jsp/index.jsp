<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>User</title>
</head>

<body class="login-body">

<jsp:include page="header.jsp"/>

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
                    <h3><a href="${path}/question/${qs.id}">${qs.title}</a></h3>
                </td>
            </tr>
            <tr>
                <td class="wide eight">
                    <div class="ui teal circular labels">
                        <c:forEach items="${qs.tags}" var="tag">
                            <a href="${path}/questions/tagged/${tag.name}"
                               title="${tag.desc}" class="ui label">
                                <i class="tag icon"></i><small>&nbsp;${tag.name}&nbsp;</small>
                            </a>&ensp;
                        </c:forEach>
                    </div>
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

</body>

<jsp:include page="footer.jsp"/>