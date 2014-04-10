<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>${question.name}</title>
</head>

<body class="login-body">

<div class="ui piled segment">

    ${question.votes}<h1>${question.name}</h1>${question.created}<br/><br/>
    <i>${question.content}</i><br/><br/><br/>
    <c:forEach items="${question.tags}" var="tag">
        <a class="ui teal label">${tag.name}</a>&thinsp;
    </c:forEach>
    <br/>
    <hr/>
    <br/>

    <h1>Answers</h1>
    <c:forEach items="${answers}" var="asw">
        ${asw.votes}&emsp;<i>${asw.content}</i>${asw.created}
        <br/><br/>
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

</body>