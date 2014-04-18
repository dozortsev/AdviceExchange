<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>Question</title>

    <link rel="stylesheet" href="${path}/css/md-style.css"/>
</head>

<div class="ui secondary menu">
    <a class="active item" href="${path}/questions">
        <i class="home icon"></i>Questions
    </a>
    <a class="item" href="${path}/users">
        <i class="user icon"></i>Users
    </a>
    <a class="item right" href="<c:url value="/j_spring_security_logout"/>">
        <i class="mail icon"></i>Log Out
    </a>
</div>

<body class="login-body"
      onload="mdRawConvector('#question-raw-content', '#question-preview'); mdLivePreview('#raw-content', '#preview-content');">

<div class="ui piled segment">

    <%-- Question --%>

    <table class="ui basic table">
        <thead>
        <tr>
            <th class="ui header" colspan="3">${question.name}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td rowspan="2">
                <h1>${question.votes}</h1>
            </td>
            <td colspan="2">

                <%-- raw --%>

                <div id="question-raw-content">${question.content}</div>

                <%-- preveiw --%>

                <div id="question-preview" class="content-body markdown-body"></div>
            </td>
        </tr>
        <tr>
            <td>
                <c:forEach var="tag" items="${question.tags}">
                    <a class="ui teal label">${tag.name}</a>&thinsp;
                </c:forEach>
            </td>
            <td>
                Asked&ensp;<fmt:formatDate type="both" value="${question.created}"
                                           pattern="yyyy-MM-dd / HH:mm"/>

                <a href="${path}/user/${question.user.id}">
                    <b>${question.user.name}</b>
                </a>
            </td>
        </tr>

        <%-- Comments --%>

        <tr>
            <td></td>
            <td colspan="2">
                <div class="ui comments">
                    <div class="comment">
                        <div class="content">
                            <c:forEach var="cm" items="${comments}">
                                <div class="text">
                                        ${cm.content}
                                    <fmt:formatDate type="both" value="${cm.created}"
                                                    pattern="yyyy-MM-dd / HH:mm"/>

                                    <a class="author"
                                       href="${path}/user/${cm.user.id}">
                                        <b>${cm.user.name}</b>
                                    </a>
                                    <a href="#"><i class="remove icon"></i></a>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="content">
                            <div class="text">
                                <div class="ui action input">
                                    <input type="text" placeholder="Your comments...">
                                    <a href="#"><i class="add icon"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="ui section divider"></div>

    <%-- Answers --%>

    <table class="ui basic table">
        <thead>
        <tr>
            <th class="ui header" colspan="3">
                <c:choose>
                    <c:when test="${question.answerCount > 0}">
                        ${question.answerCount}&ensp;Answers
                    </c:when>
                    <c:otherwise>
                        Not answered yet
                    </c:otherwise>
                </c:choose>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="asw" items="${answers}">
            <tr>
                <td rowspan="2">
                    <h1>${asw.votes}</h1>
                </td>
                <td colspan="2">
                    ${asw.content}
                </td>
            </tr>
            <tr>
                <td></td>
                <td>Answered&ensp;<fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm" value="${asw.created}"/>
                    <a href="${path}/user/${asw.user.id}"><b>${asw.user.name}</b></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%-- Post new answer --%>

    <form:form action="${path}/question/answer/create" method="POST" modelAttribute="answer">
        <p>
        <div class="ui form">
            <p>Your answer</p>

            <div class="field">
                <textarea id="raw-content" name="content"></textarea>
            </div>
        </div>
        </p>
        <div class="ui horizontal icon divider">
            <i class="circular magic icon"></i>
        </div>
        <p>
        <div id="preview-container">
            <div id="preview-content" class="ui segment"></div>
        </div>

        <input class="ui small red submit button" type="submit" value="Post Your Answer"/>
        </p>
    </form:form>

        <div class="ui segment">
            <p>
                Not the answer you're looking for? Browse other questions tagged
                <c:forEach var="tag" items="${question.tags}">
                    <a href="#" class="ui teal small label">${tag.name}</a>&thinsp;
                </c:forEach>
                or <a href="${path}/questions/ask">ask your own questions</a>.
            </p>
        </div>
</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>

</body>