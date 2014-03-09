<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>
    User
</title>

${user.id} <br/>
${user.name} <br/>
${user.age} <br/>
${user.email} <br/>
${user.password} <br/>

<form:form action="${pageContext.request.contextPath}/questions" method="GET">

    Tags: <input name="tags" type="text"/>

    <input type="submit" value="Find"/>
</form:form>
<br/>
<c:if test="${not empty questions}">
    <table>
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
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>