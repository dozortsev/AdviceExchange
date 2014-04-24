<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<head lang="en">

    <title>
        User / ${user.name}
    </title>

</head>
<body class="login-body">

<div class="ui secondary menu">
    <a class="active item" href="${path}/user/${user.id}">
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
    <a class="item right" href="${path}/questions/ask">
        <i class="mail icon"></i>Ask Questions
    </a>
</div>

<div class="ui piled segment">

    <p>
        <h3>USERS&ensp;${userSetTotalCount}</h3>
    </p>
    <div class="ui divider"></div>
    <p>
    <form:form action="${path}/users" method="GET">

        <div class="ui action input">
            <div class="ui icon input">
                <input type="text" placeholder="Search..." name="name" autofocus="true">
                <i class="search icon"></i>
            </div>
        </div>

    </form:form>
    </p>

    <%-- Users --%>

    <main class="ui four column grid items">
        <c:forEach items="${userLimitedSet}" var="user">
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

    <c:if test="${userSetTotalCount >= 36}">
        <div class="ui borderless pagination menu">

            <fmt:formatNumber var="pages" type="number" pattern="#"
                              value="${(userSetTotalCount / 36) + 0.5}"
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

</body>

