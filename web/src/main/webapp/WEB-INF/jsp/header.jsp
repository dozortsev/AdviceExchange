<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<div class="ui secondary menu">
    <a class="item" href="${path}/user/${user.id}" title="${user.name}">
        +&ensp;<i class="user icon"></i>
    </a>
    <a class="item" href="${path}/questions">
        <i class="leaf icon"></i><fmt:message key="questions"/>
    </a>
    <a class="item" href="${path}/users">
        <i class="users basic icon"></i><fmt:message key="users"/>
    </a>
    <a class="item" href="<c:url value="/j_spring_security_logout"/>">
        <i class="url basic icon"></i><fmt:message key="logout"/>
    </a>
    <a class="item right" style="background-color: #faea9f;"
       href="${path}/questions/ask">
        <b><i class="idea basic icon"></i><fmt:message key="ask.qs"/></b>
    </a>
</div>

