<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>
    User
</title>

${user.id}
${user.name}
${user.age}
${user.email}
${user.password}

<a href="<c:url value="/j_spring_security_logout" />">Logout</a>