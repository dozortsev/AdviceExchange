<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>
        Question
    </title>
    <link rel="stylesheet" href="${path}/css/md-style.css"/>

    <script src="${path}/js/showdown.js"></script>
</head>

<c:set var="isAdmin" value="false"/>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <c:set var="isAdmin" value="true"/>
</sec:authorize>

<body onload="mdRawConvector('#question-raw-content', '#question-preview');
              mdLivePreview('#raw-content', '#preview-content');

      <c:forEach items="${answers}" var="asw">
              mdRawConvector('#asw-raw-content-${asw.id}', '#asw-preview-${asw.id}');
      </c:forEach>
">

<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide two"></td>
        <td class="wide twelve">

        <jsp:include page="header.jsp"/>

        <div class="ui piled segment">
        <p>
        <h3>${question.title}</h3>
        <p>
        <table class="ui basic small table">
            <tbody>
            <tr>
                <td class="wide one" align="center" style="vertical-align: text-top;">
                    <h2>${question.votes}</h2>
                    <br/>
                    <small>votes</small>
                </td>
                <td colspan="2">

                    <%-- raw --%>

                    <div id="question-raw-content" class="hide">${question.content}</div>

                    <%-- preview --%>

                    <div id="question-preview" class="markdown-body"></div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="wide eight" colspan="3">
                    <div class="ui teal small labels">
                        <c:forEach items="${question.tags}" var="tag">
                            <a href="${path}/questions/tagged/${tag.name}"
                               title="${tag.desc}" class="ui label">
                                <i class="tag icon"></i>&nbsp;${tag.name}&nbsp;
                            </a>&ensp;
                        </c:forEach>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="wide seven">
                    <c:if test="${isAdmin || question.user.id eq user.id }">
                        <a href="${path}/question/delete/${question.id}">delete</a>
                        &ensp;|&ensp;
                        <a href="#">edit</a>
                    </c:if>
                </td>
                <td class="wide eight" align="left">
                    <small>
                        asked&ensp;
                        <a href="${path}/user/${question.user.id}">
                            <b>${question.user.name}</b>
                        </a>
                        <fmt:formatDate type="both" value="${question.created}"
                                        pattern="yyyy-MM-dd / HH:mm"/>
                    </small>
                </td>
            </tr>
            </tbody>
        </table>


        <%-- Commets --%>

        <h4>Comments</h4>

        <div class="ui horizontal icon divider">
            <i class="circular chat icon"></i>
        </div>

        <table class="ui basic small table">
            <tbody>
            <c:forEach var="cm" items="${comments}">
                <tr>
                    <td class="wide one">
                    </td>
                    <td colspan="2" class="wide fifteen">
                        <small>
                                ${cm.content} - <a href="${path}/user/${cm.user.id}">${cm.user.name}</a>
                            <fmt:formatDate type="both" value="${cm.created}"
                                            pattern="yyyy-MM-dd / HH:mm"/>

                            <c:if test="${isAdmin || (cm.user.id eq user.id)}">
                                <a href="#">delete</a>
                            </c:if>
                        </small>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td class="wide one"></td>
                <td class="wide twelve">
                    <div class="ui fluid input">
                        <input type="text" placeholder="Add comments...">
                    </div>
                </td>
                <td class="wide two">
                    <a class="ui small red button">Add Comment</a>
                </td>
            </tr>
            </tbody>
        </table>

        <%-- Answers --%>

        <div class="ui section divider"></div>

        <p>
            <c:choose>
            <c:when test="${question.answerCount > 0}">
        <h4>${question.answerCount}&ensp;Answers</h4>
        </c:when>

        <c:otherwise>
            <h4>Not answered yet</h4>
        </c:otherwise>
        </c:choose>
        <p>

        <div class="ui horizontal icon divider">
            <i class="circular flag icon"></i>
        </div>

        <table class="ui basic small table">
            <tbody>
            <c:forEach var="asw" items="${answers}">
                <tr>
                    <td class="wide one" align="center" style="vertical-align: text-top;">
                        <h2>${asw.votes}</h2><br/>
                        <small>votes</small>
                    </td>
                    <td colspan="2">

                            <%-- raw --%>
                        <div id="asw-raw-content-${asw.id}" class="hide">${asw.content}</div>

                            <%-- preview --%>
                        <div id="asw-preview-${asw.id}" class="markdown-body"></div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="wide seven">
                        <c:if test="${isAdmin || asw.user.id eq user.id}">
                            <a href="${path}/answer/delete/${asw.id}">delete</a>
                            &ensp;|&ensp;
                            <a href="#">edit</a>
                        </c:if>
                    </td>
                    <td class="wide eight" align="left">
                        <small>
                            answered <a href="${path}/user/${asw.user.id}">${asw.user.name}</a>

                            <fmt:formatDate type="both" value="${asw.created}"
                                            pattern="yyyy-MM-dd / HH:mm"/>
                        </small>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Pos new Answer -->

        <c:if test="${!(user.id eq question.user.id)}">

            <form:form action="${path}/answer/create" method="POST" modelAttribute="answer">
                <p>
                <h4>Your answer</h4>

                <p>

                <div class="ui form">
                    <div class="field">
                        <textarea id="raw-content" name="aswContent" required></textarea>
                    </div>
                </div>

                <div class="ui horizontal icon divider">
                    <i class="circular magic icon"></i>
                </div>

                <div id="preview-container" class="ui segment">
                    <div id="preview-content" class="markdown-body"></div>
                </div>

                <div class="ui form">
                    <input class="ui small red submit button" type="submit" value="Post Your Answer"/>
                </div>
            </form:form>

            <div class="ui segment">
                <p>
                    Browse other questions tagged:&thinsp;
                    <c:forEach items="${question.tags}" var="tag">
                        <a href="${path}/questions/tagged/${tag.name}"
                           title="${tag.desc}" class="ui teal small label">
                            <i class="tag icon"></i>&nbsp;${tag.name}&nbsp;
                        </a>
                    </c:forEach>
                    or <a href="${path}/questions/ask">ask your own questions</a>.
                </p>
            </div>

        </c:if>

        </div>

        </td>
        <td class="wide three"></td>
    </tr>
    </tbody>
</table>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>