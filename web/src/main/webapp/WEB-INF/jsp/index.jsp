<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>User</title>
</head>

<body>

<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide two"></td>
        <td class="wide twelve">

            <jsp:include page="header.jsp"/>

            <div class="ui piled segment">
                <p>
                <h3>ALL QUESTIONS&ensp;${questionCount}</h3>

                <form:form action="${path}/questions" method="GET">
                    <div class="ui action input">
                        <div class="ui icon input">
                            <input type="text" placeholder="Search..." name="words" autofocus="true">
                            <i class="search icon"></i>
                        </div>
                    </div>
                </form:form>
                </p>
                <div class="ui horizontal icon divider">
                    <i class="circular stackexchange icon"></i>
                </div>

                <c:if test="${fn:length(questions) eq 0}">
                    <h4 class="ui inverted red block header center aligned">
                        Your search returned no matches.
                    </h4>
                </c:if>

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
                            <td colspan="2">
                                <div class="ui teal labels">
                                    <c:forEach items="${qs.tags}" var="tag">
                                        <a href="${path}/questions/tagged/${tag.name}"
                                           title="${tag.desc}" class="ui small label">
                                            <i class="tag icon"></i>&nbsp;${tag.name}&nbsp;
                                        </a>
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="wide eight"></td>
                            <td>
                                <small>
                                    asked&thinsp;<a href="${path}/user/${qs.user.id}">
                                        <b>${qs.user.name}</b>
                                    </a>
                                    <fmt:formatDate type="both" value="${qs.created}"
                                                    pattern="yyyy-MM-dd / HH:mm"/>
                                </small>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <c:if test="${not loop.last}">
                        <div class="ui section divider"></div>
                    </c:if>
                </c:forEach>

                <c:if test="${fn:length(questions) >= 10}">

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
                </c:if>
            </div>

        </td>
        <td class="wide two"></td>
    </tr>
    </tbody>
</table>
</body>

<jsp:include page="footer.jsp"/>