<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<title>${member.name}</title>

<body>
<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide two"></td>
        <td class="wide twelve">

            <jsp:include page="header.jsp"/>

            <div class="ui piled segment">
                <p>
                <h3>${member.name}</h3>
                </p>

                <div class="ui horizontal icon divider">
                    <i class="circular signal icon"></i>
                </div>


                    <%-- Tags --%>

                <div class="ui pointing secondary demo menu">
                    <a class="active red item" data-tab="first">
                        Activity
                    </a>
                    <a class="blue item" data-tab="second">
                        Questions ${fn:length(questions)}
                    </a>
                    <a class="teal item" data-tab="third">
                        Answers ${fn:length(answers)}
                    </a>
                </div>


                    <%-- Activity --%>

                <div class="ui active tab" data-tab="first">
                    <table class="ui basic table">
                        <tbody>
                        <c:forEach items="${activity}" var="act">

                            <c:set var="isQuestion" value="${act.type eq 'QUESTION'}"/>
                            <tr>
                                <td class="wide one">
                                    ${act.votes}
                                </td>
                                <td class="wide fifteen">
                                    <a href="${path}/question/${isQuestion ? act.id : act.question.id}">
                                            ${isQuestion ? act.title : act.question.title}
                                    </a>
                                    <fmt:formatDate type="both" value="${act.created}"
                                                    pattern="yyyy-MM-dd / HH:mm"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


                    <%-- Questions --%>

                <div class="ui tab" data-tab="second">
                    <table class="ui basic table">
                        <tbody>
                        <c:forEach items="${questions}" var="qs">
                            <tr>
                                <td>${qs.votes}</td>
                                <td><a href="${path}/question/${qs.id}">${qs.title}</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


                    <%-- Answers --%>

                <div class="ui tab" data-tab="third">
                    <table class="ui basic table">
                        <tbody>
                        <c:forEach items="${answers}" var="asw">
                            <tr>
                                <td class="wide one">${asw.votes}</td>
                                <td class="wide fifteen">
                                    <a href="${path}/question/${asw.question.id}">${asw.question.title}</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </td>
        <td class="wide two"></td>
    </tr>
    </tbody>
</table>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>
