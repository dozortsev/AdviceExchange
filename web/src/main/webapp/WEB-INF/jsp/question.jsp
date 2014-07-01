<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp" %>

<head>
    <title><fmt:message key="questions"/></title>

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
        <h2>${question.title}</h2>
        <p>
        <table class="ui basic small table">
            <tbody>
            <tr>
                <td class="wide one" align="center" style="vertical-align: text-top;">
                    <h2>${question.votes}</h2>
                    <br/>
                    <small><fmt:message key="votes"/></small>
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
                            <a href="${path}/questions/tagged/${tag.name}" class="ui label"
                               data-variation="small inverted" data-title="${tag.name}"
                               data-position="bottom center" data-content="${tag.desc}">
                                <i class="tag icon"></i>
                                &nbsp;${tag.name}&nbsp;
                            </a>
                        </c:forEach>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td class="wide seven">
                    <c:if test="${isAdmin || question.user.id eq user.id }">
                        <a href="${path}/question/delete/${question.id}">
                            <fmt:message key="del"/>
                        </a>
                        &ensp;|&ensp;
                        <a href="#">
                            <fmt:message key="edit"/>
                        </a>
                    </c:if>
                </td>
                <td class="wide eight" align="left">
                    <small>
                        <fmt:message key="asked"/>&thinsp;
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

        <h4><fmt:message key="qs.lbl.comments"/></h4>

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
                                <a href="#"><i class="remove icon"></i></a>
                            </c:if>
                        </small>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td class="wide one"></td>
                <td class="wide twelve">
                    <div class="ui fluid input">
                        <input type="text" placeholder="Your comments...">
                    </div>
                </td>
                <td class="wide two">
                    <div class="ui fluid input">
                        <a class="ui tiny red button">
                            <fmt:message key="qs.btn.add.cm"/>
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <%-- Answers --%>

        <div class="ui section divider"></div>

        <p>
            <c:choose>
            <c:when test="${question.answerCount > 0}">
        <h4>${question.answerCount}&thinsp;<fmt:message key="qs.lbl.answers"/></h4>
        </c:when>

        <c:otherwise>
            <h4><fmt:message key="qs.lbl.answers.msg"/></h4>
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
                        <small><fmt:message key="votes"/></small>
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
                            <a href="${path}/answer/delete/${asw.id}">
                                <fmt:message key="del"/>
                            </a>
                            &ensp;|&ensp;
                            <a href="#">
                                <fmt:message key="edit"/>
                            </a>
                        </c:if>
                    </td>
                    <td class="wide eight" align="left">
                        <small>
                            <fmt:message key="answered"/>&thinsp;
                            <a href="${path}/user/${asw.user.id}">
                                <b>${asw.user.name}</b>
                            </a>
                            <fmt:formatDate type="both" value="${asw.created}"
                                            pattern="yyyy-MM-dd / HH:mm"/>
                        </small>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Post new Answer -->

        <c:if test="${!(user.id eq question.user.id)}">

            <form:form action="${path}/answer/create" method="POST" modelAttribute="answer">
                <p>
                    <h4><fmt:message key="as.lbl.you.asw"/></h4>

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
                    <input class="ui small red submit button" type="submit"
                           value="<fmt:message key="qs.btn.post.asw"/>"/>
                </div>
            </form:form>

            <div class="ui segment">
                <p>
                    <fmt:message key="as.lbl.browse.1"/>&thinsp;
                    <c:forEach items="${question.tags}" var="tag">
                        <a href="${path}/questions/tagged/${tag.name}" class="ui teal small label"
                           data-variation="small inverted" data-title="${tag.name}"
                           data-position="bottom center" data-content="${tag.desc}">
                            <i class="tag icon"></i>
                            &nbsp;${tag.name}&nbsp;
                        </a>
                    </c:forEach>
                    or&thinsp;
                    <a href="${path}/questions/ask">
                        <fmt:message key="as.lbl.browse.2"/>
                    </a>
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
<script src="${path}/js/semantic.min.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>