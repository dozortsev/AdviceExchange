<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>
    Create account
</title>

<form:form action="${pageContext.request.contextPath}/createAccount"
            method="POST"
            modelAttribute="newUser">

    <input type="hidden" name="id"/>
    <input type="text" name="name" placeholder="Name"/><br/>
    <input type="text" name="age" placeholder="Age"/><br/>
    <input type="text" name="aboutMe" placeholder="AboutMe"/><br/>
    <input type="text" name="location" placeholder="Location"/><br/>
    <input type="text" name="site" placeholder="Site"/><br/>
    <input type="text" name="email" placeholder="Email"/><br/>
    <input type="text" name="password" placeholder="Password"/><br/>

    <input type="submit" value="Create"/>

</form:form>
