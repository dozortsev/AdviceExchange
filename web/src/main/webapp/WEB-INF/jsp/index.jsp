<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>
    User
</title>

${user.id} <br/>
${user.name} <br/>
${user.age} <br/>
${user.email} <br/>
${user.password} <br/>

<a href="<c:url value="/j_spring_security_logout" />">Logout</a>