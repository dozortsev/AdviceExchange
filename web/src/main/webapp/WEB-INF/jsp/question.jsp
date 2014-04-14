<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>Question</title>

    <link rel="stylesheet" href="${path}/css/md-style.css"/>

</head>

<body class="login-body">

<div class="ui piled segment">

    ${question.votes}<h1>${question.name}</h1>${question.created}<br/>${question.user.name}<br/>

    <div id="question-content" class="markdown-body">${question.content}</div>
    <br/><br/><br/>

    <c:forEach var="tag" items="${question.tags}">
        <a class="ui teal label">${tag.name}</a>&thinsp;
    </c:forEach>

    <br/>
    <hr/>
    <br/>

    <h1>Answers</h1>

    <c:forEach items="${answers}" var="asw">

        ${asw.votes}&emsp;

        <div id="preview-container">
            <div>
                <div id="answer-content" class="markdown-body">
                        ${asw.content}
                </div>
            </div>
        </div>

        ${asw.created}
        <br/>
        ${asw.user.name}
        <br/>
    </c:forEach>
    <br/>
    <hr/>
    <br/>

    <h1>Comments</h1>
    <c:forEach items="${comments}" var="cm">
        <i>${cm.content}</i><br/><br/>
    </c:forEach>

    <br/><br/><br/>

</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>