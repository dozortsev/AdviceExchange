<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<title>
    User
</title>

${user.id} <br/>
${user.name} <br/>
${user.age} <br/>
${user.email} <br/>
${user.password} <br/>
<br/>
User activities:
<br/>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Type</td>
        <td>User Id</td>
        <td>Created</td>
    </tr>
<c:forEach items="${activities}" var="act">
    <tr>
        <td>${act.id}</td>
        <td>${act.type}</td>
        <td>${user.id} ${user.name}</td>
        <td><b>${act.created}</b></td>
    </tr>
</c:forEach>
</table>
<br/>
<form:form action="${pageContext.request.contextPath}/questions" method="GET">

    Tags: <input name="tags" type="text"/>

    <input type="submit" value="Find"/>
</form:form>
<br/>
<c:if test="${not empty questions}">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Votes</td>
            <td>Created</td>
        </tr>
        <c:forEach items="${questions}" var="question">
            <tr>
                <td>${question.id}</td>
                <td>${question.name}</td>
                <td>${question.votes}</td>
                <td>${question.created}</td>
            </tr>
        </c:forEach>
    </table>
    <br/><br/>
</c:if>

<br/>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>