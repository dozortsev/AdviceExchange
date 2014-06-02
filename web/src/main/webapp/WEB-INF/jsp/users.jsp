<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<head>
    <title>
        User / ${user.name}
    </title>
</head>
<body>

<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide two"></td>
        <td class="wide twelve">

            <jsp:include page="header.jsp"/>

            <div class="ui piled segment">
                <p>
                <h3>USERS&ensp;${userCount}</h3>

                <form:form action="${path}/users" method="GET">

                    <div class="ui action input">
                        <div class="ui icon input">
                            <input type="text" placeholder="Search..." name="name" autofocus="true">
                            <i class="search icon"></i>
                        </div>
                    </div>

                </form:form>
                </p>
                <div class="ui horizontal icon divider">
                    <i class="circular users basic icon"></i>
                </div>

                <c:if test="${fn:length(userSet) eq 0}">
                    <h4 class="ui inverted red block header center aligned">
                        <fmt:message key="index.msg.search.err"/>
                    </h4>
                </c:if>

                <%-- Users --%>

                <main class="ui four column grid items">
                    <c:forEach items="${userSet}" var="user">
                        <section class="column">
                            <div class="item">
                                <div class="content">
                                    <a href="${path}/user/${user.id}">
                                            ${user.name}
                                    </a>
                                        ${user.reputation}
                                    <small>
                                        <fmt:formatDate type="both" value="${user.joined}"
                                                        pattern="yyyy-MM-dd / HH:mm"/>
                                    </small>
                                    <small>
                                        <c:if test="${ not empty user.site }">
                                            ${user.site}
                                        </c:if>
                                    </small>
                                </div>
                            </div>
                        </section>
                    </c:forEach>
                </main>

                <c:if test="${userCount >= 36}">

                    <div class="ui horizontal icon divider">
                        <i class="circular users basic icon"></i>
                    </div>

                    <div class="ui borderless pagination menu">

                        <fmt:formatNumber var="pages" type="number" pattern="#"
                                          value="${(userCount / 36) + 0.5}"
                                />

                        <c:forEach var="i" begin="1" end="${pages}" step="1">
                            <c:url var="showUsers" value="/users">

                                <c:param name="page" value="${i}"/>

                                <c:if test="${fn:length(name) > 0}">
                                    <c:param name="name" value="${name}"/>
                                </c:if>
                            </c:url>
                            <a class="item" href="${showUsers}">${i}</a>&emsp;
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </td>
        <td class="wide two"></td>
    </tr>
    </tbody>
</table>
</body>

<jsp:include page="footer.jsp"/>

