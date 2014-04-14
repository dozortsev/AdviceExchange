<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>
        Ask Question
    </title>
</head>

<body class="login-body">

<div class="ui piled segment">

    <form:form action="${path}/questions/create" method="POST" modelAttribute="ask">

        <div class="ui form">
            <div class="field">
                <label>Title</label><input type="text" name="name">
            </div>
        </div>

        <div class="ui form">
            <div id="entry-container" class="field">

                <textarea id="user-input" name="content"></textarea>
            </div>
        </div>

        <div id="preview-container">
            <em>Live preview</em>

            <div>
                <div id="wiki-style" class="markdown-body"></div>
            </div>
        </div>

        <input class="ui small blue submit button" type="submit" value="Submit"/>

    </form:form>

</div>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

