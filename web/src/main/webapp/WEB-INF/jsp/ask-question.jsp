<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title><fmt:message key="ask.qs"/></title>

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

                <h3><fmt:message key="aq.lbl.title"/></h3>

                <form:form action="${path}/questions/create" method="POST" modelAttribute="ask">

                    <div class="ui form">
                        <div class="field">
                            <input type="text" name="title" required="true" autofocus="true"
                                   placeholder="<fmt:message key="aq.input.title.ph"/>">
                        </div>
                    </div>

                    <p>
                        <i class="circular bold link icon" data-variation="small inverted" data-title="Bold"
                           data-position="top center" data-content="**This is bold**">
                        </i>
                        <i class="circular italic link icon" data-variation="small inverted" data-title="Italic"
                           data-position="top center" data-content="*This is italic*">
                        </i>
                        <i class="circular url link icon" data-variation="small inverted" data-title="Link"
                           data-position="top center" data-content="[Google](http://www.google.com/)">
                        </i>
                        <i class="circular quote left link icon" data-variation="small inverted" data-title="Blockquote"
                           data-position="top center" data-content="> Lorem ipsum dolor sit amet.">
                        </i>
                        <i class="circular text height link icon" data-variation="small inverted" data-title="Header"
                           data-position="top center" data-content="# Header1<br>## Header2<br>### Header3">
                        </i>
                        <i class="circular code link icon" data-variation="small inverted" data-title="Code"
                           data-position="top center" data-content="`git pull`">
                        </i>
                        <i class="circular list link icon" data-variation="small inverted" data-title="Bulleted List"
                           data-position="top center" data-content="* item1<br> * item2">
                        </i>
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
                                    <fmt:message key="aq.lbl.tags.msg"/>
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
                            <fmt:message key="tags"/>
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
                               value="<fmt:message key="qs.btn.post.qs"/>"/>
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
<script src="${path}/js/semantic.min.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>