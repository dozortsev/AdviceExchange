<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<div class="ui secondary menu">
    <a class="item" href="${path}/user/${user.id}" title="${user.name}">
        +&ensp;<i class="user icon"></i>
    </a>
    <a class="item" href="${path}/questions">
        <i class="leaf icon"></i>Questions
    </a>
    <a class="item" href="${path}/users">
        <i class="users basic icon"></i>Users
    </a>
    <a class="item right" href="<c:url value="/j_spring_security_logout"/>">
        <i class="off icon"></i>Log Out
    </a>
    <a class="item right" href="${path}/questions/ask">
        <i class="lightbulb icon"></i>Ask Questions
    </a>
</div>

