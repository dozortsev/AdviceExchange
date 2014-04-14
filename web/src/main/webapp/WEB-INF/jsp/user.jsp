<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<title>${user.name}</title>

<table>
    <tr>
        <td>Questions</td>
    </tr>
    <c:forEach items="${questions}" var="qs">
        <tr>
            <td>${qs.id} ${qs.name} ${qs.user.name}</td>
        </tr>
    </c:forEach>
</table>

<br/><br/><br/>

<table>
    <tr>
        <td>Answers</td>
    </tr>
    <c:forEach items="${answers}" var="asw">
        <tr>
            <td>${asw.id} ${asw.user.name}</td>
        </tr>
    </c:forEach>
</table>
