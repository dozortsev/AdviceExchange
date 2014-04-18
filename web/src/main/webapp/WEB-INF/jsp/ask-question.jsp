<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>
        Ask Question
    </title>
    <link rel="stylesheet" href="${path}/css/md-style.css"/>
</head>

<body class="login-body" onload="mdLivePreview('#content-answer', '#preview-content');">

<div class="ui piled segment">

    <form:form action="${path}/questions/create" method="POST" modelAttribute="ask">

        <div class="ui form">
            <div class="field">
                <label>Title</label><input type="text" name="name">
            </div>
        </div>

        <div class="ui form">
            <div class="field">
                <textarea id="content-answer" name="content"></textarea>
            </div>
        </div>

        <div id="preview-content" class="markdown-body"></div>

        <input class="ui small blue submit button" type="submit" value="Submit"/>
    </form:form>

    <input class="ui small red button" type="submit"
           value="Cancel" onclick="history.back();"/>

</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

