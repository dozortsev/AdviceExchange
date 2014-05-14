<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title>
        Ask a Question
    </title>
    <link rel="stylesheet" href="${path}/css/md-style.css"/>
</head>

<body onload="mdLivePreview('#raw-content', '#preview-content');">

<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide two"></td>
        <td class="wide twelve">

            <jsp:include page="header.jsp"/>

            <div class="ui piled segment">

                <h2>Title</h2>

                <form:form action="${path}/questions/create" method="POST" modelAttribute="ask">

                    <div class="ui form">
                        <div class="field">
                            <input type="text" name="title" required="true" autofocus="true"
                                   placeholder="What's your human health questions? Be specific.">
                        </div>
                    </div>

                    <p>
                        <i class="circular quote left link icon"></i>
                        <i class="circular strikethrough link icon"></i>
                        <i class="circular text height link icon"></i>
                        <i class="circular globe link icon"></i>
                        <i class="circular bold link icon"></i>
                        <i class="circular italic link icon"></i>
                        <i class="circular underline link icon"></i>
                        <i class="circular comment link icon" data-variation="inverted" data-position="top center" data-content="Hello, I am a pop-up."></i>
                        <i class="circular code link icon"></i>
                        <i class="circular list link icon"></i>
                    </p>

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

                    <div class="ui horizontal icon divider">
                        <i class="circular tags icon"></i>
                    </div>

                    <table class="ui basic table">
                        <tbody>
                        <tr>
                            <td class="wide two">
                                <input id="add-tag" type="button" value="Add tags"
                                       class="ui small blue left submit button"/>
                            </td>
                            <td class="wide fourteen">
                                <div class="ui segment left" id="tag-view">
                                    at least one tag such as [stomachache], max 5 tags
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <input type="hidden" name="tags"/>

                    <%-- Modal --%>

                    <div class="ui small modal">
                        <i class="close icon"></i>
                        <div class="header">
                            Tags
                        </div>
                        <div class="content">
                            <div class="four column doubling ui grid">
                                <c:forEach items="${tags}" var="tag">
                                    <div class="column">
                                        <div class="ui checkbox">
                                            <input type="checkbox" name="tag" value="${tag.name}">
                                            <label>${tag.name}</label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="actions">
                            <div class="ui positive icon button" onclick="getTagNames()">
                                <i class="checkmark icon"></i>
                            </div>
                        </div>
                    </div>

                    <br/><br/>

                    <div class="ui form">
                        <input class="ui small red submit button" type="submit"
                               value="Post Your Question"/>
                    </div>
                </form:form>
            </div>
        </td>
        <td class="wide two"></td>
    </tr>
    </tbody>
</table>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/showdown.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>