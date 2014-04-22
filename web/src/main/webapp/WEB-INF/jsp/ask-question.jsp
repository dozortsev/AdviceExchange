<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>
        Ask Question
    </title>
    <link rel="stylesheet" href="${path}/css/md-style.css"/>
</head>

<body class="login-body" onload="mdLivePreview('#raw-content', '#preview-content');">

<div class="ui secondary menu">
    <a class="item" href="${path}/user/${user.id}">
        <i class="home icon"></i>${user.name}
    </a>
    <a class="item" href="${path}/questions">
        <i class="home icon"></i>Questions
    </a>
    <a class="item" href="${path}/users">
        <i class="users basic icon"></i>Users
    </a>
    <a class="item right" href="<c:url value="/j_spring_security_logout"/>">
        <i class="mail icon"></i>Log Out
    </a>
    <a class="item active right" href="${path}/questions/ask">
        <i class="mail icon"></i>Ask Questions
    </a>
</div>

<div class="ui piled segment">

    <form:form action="${path}/questions/create" method="POST" modelAttribute="ask">

        <div class="ui form">
            <div class="field">
                <label>Title</label>
                <input type="text" name="title" required="true">
            </div>
        </div>

        <div class="ui form">
            <div class="field">
                <textarea id="raw-content" name="content" required></textarea>
            </div>
        </div>

        <div class="ui horizontal icon divider">
            <i class="circular magic icon"></i>
        </div>

        <div id="preview-container" class="ui segment">
            <div id="preview-content" class="markdown-body"></div>
        </div>

        <div class="ui form">
            <input class="ui small blue submit button" type="submit" value="Submit"/>
        </div>
    </form:form>
</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

