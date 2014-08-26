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

                <table class="table basic">
                    <tr>
                        <td class="wide two">

                            <div class="image">
                                <img src="${path}/img/user.png">
                            </div>
                            <div class="content">
                                <h3>
                                    <c:choose>
                                    <c:when test="${fn:length(member.site) > 1}">
                                        <a href="http://${member.site}">${member.name}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${member.name}
                                    <small>
                                        </c:otherwise>
                                        </c:choose>
                                </h3>
                            </div>
                        </td>

                        <td class="wide fourteen" style="vertical-align: text-top;">
                            <div class="ui floating message">
                                <div class="header">About me</div>
                                ${member.aboutMe}
                            </div>
                        </td>
                    </tr>

                    <%--
                    <jsp:useBean id="now" class="java.util.Date"/>

                    <c:set var="fromYearMember" value="${member.joined.year - now.year}"/>
                    <c:set var="fromMonthMember" value="${member.joined.month - now.month}"/>
                    <c:set var="fromDayMember" value="${member.joined.day - now.day}"/>
                    --%>

                    <tr>
                        <td colspan="2">
                            Age: ${member.age}
                            &emsp;
                            &emsp;
                            Email: <div class="ui input"><input type="email" value="${member.email}" readonly/></div>
                            &emsp;
                            Member form: <fmt:formatDate type="date" value="${member.joined}" pattern="yyyy-MM-dd"/>

                            <%--
                            <c:if test="${fromYearMember != 0}">${fromYearMember} years</c:if>
                            <c:if test="${fromMonthMember != 0}">${fromMonthMember} months</c:if>
                            <c:if test="${fromDayMember != 0}">${fromDayMember} days</c:if>
                            --%>
                        </td>
                    </tr>
                </table>

                <div class="ui horizontal icon divider">
                    <i class="circular signal icon"></i>
                </div>


                    <%-- Tags --%>

                <div class="ui pointing secondary demo menu">
                    <a class="active red item" data-tab="first">
                        Activity
                    </a>
                    <a class="blue item" data-tab="second">
                        <%--<c:set var="countQs" value="${fn:length(questions)}"/>--%>
                        Questions
                    </a>
                    <a class="teal item" data-tab="third">
                        <%--<c:set var="countAsw" value="${fn:length(answers)}"/>--%>
                        Answers
                    </a>
                </div>


                    <%-- Activity --%>

                <div class="ui active tab" data-tab="first">
                    <table class="ui basic table">
                        <tbody>
                        <c:forEach items="${activity}" var="act">
                            <c:set var="isQuestion" value="${act.type eq 'QUESTION'}"/>
                            <tr>
                                <td class="wide two" align="right">
                                    <small>
                                            ${isQuestion ? "<b>asked</b>" : (act.type eq 'ANSWER') ? "answered" : "commented"}
                                    </small>
                                </td>
                                <td class="wide fourteen">
                                    <small>
                                        <a href="${path}/question/${isQuestion ? act.id : act.question.id}">
                                                ${isQuestion ? act.title : act.question.title}
                                        </a>
                                        <fmt:formatDate type="both" value="${act.created}"
                                                        pattern="yyyy-MM-dd / HH:mm"/>
                                    </small>
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
                        <c:forEach items="${activity}" var="qs">
                            <c:if test="${qs.type eq 'QUESTION'}">
                                <tr>
                                    <td class="wide one">${qs.votes}</td>
                                    <td class="wide fifteen">
                                        <a href="${path}/question/${qs.id}">${qs.title}</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


                    <%-- Answers --%>

                <div class="ui tab" data-tab="third">
                    <table class="ui basic table">
                        <tbody>
                        <c:forEach items="${activity}" var="asw">
                            <c:if test="${asw.type eq 'ANSWER'}">
                                <tr>
                                    <td class="wide one">${asw.votes}</td>
                                    <td class="wide fifteen">
                                        <a href="${path}/question/${asw.question.id}">${asw.question.title}</a>
                                    </td>
                                </tr>
                            </c:if>
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
<script src="${path}/js/semantic.min.js"></script>
<script src="${path}/js/main.js"></script>

</body>

<jsp:include page="footer.jsp"/>
