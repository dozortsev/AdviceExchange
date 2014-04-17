<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>Question</title>

    <link rel="stylesheet" href="${path}/css/md-style.css"/>
</head>

<body class="login-body" onload="foo()">

<div class="ui piled segment">


    <table class="ui basic table">
        <thead>
        <tr>
            <th class="ui header" colspan="3">${question.name}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td rowspan="2"><h1>${question.votes}</h1></td>
            <td colspan="2">

                <%-- raw --%>

                <div id="question-content">${question.content}</div>

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
            <td>Asked&ensp;<fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm" value="${question.created}"/>
                <a href="#"><b>${question.user.name}</b></a>
            </td>
        </tr>
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

                                        <a class="author"><b>${cm.user.name}</b></a>
                                    <a href="#" class="remove icon"></a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <form class="ui reply form">
                        <div class="field">
                            <textarea></textarea>
                            <div class="ui button small red submit labeled">
                                Add
                            </div>
                        </div>
                    </form>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="ui section divider"></div>

    <table class="ui basic table">
        <thead>
        <tr>
            <th class="ui header" colspan="3">${fn:length(answers)}&ensp;Answer</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="asw" items="${answers}">
            <tr>
                <td rowspan="2"><h1>${asw.votes}</h1></td>
                <td colspan="2">
                    ${asw.content}
                </td>
            </tr>
            <tr>
                <td></td>
                <td>Answered&ensp;<fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm" value="${asw.created}"/>
                    <a href="#"><b>${asw.user.name}</b></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${path}/questions"
       class="ui small red submit button">
        Questions
    </a>

</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>