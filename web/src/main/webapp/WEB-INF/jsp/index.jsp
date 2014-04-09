<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<table border="1">
    <tr>
        <td>A. count</td>
        <td>Id</td>
        <td>Name</td>
        <td>Votes</td>
        <td>Created</td>
    </tr>
    <c:forEach items="${map}" var="entry">
    <tr>
        <td>${entry.value}</td>

        <td>${entry.key.id}</td>

        <td>${entry.key.name}</td>
        <td>${entry.key.votes}</td>
        <td><fmt:formatDate type="both" pattern="yyyy-MM-dd / HH:mm" value="${entry.key.created}"/></td>
    </tr>
    </c:forEach>
</table>
<br/>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>