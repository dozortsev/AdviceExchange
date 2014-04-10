<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%-- Tag Libraries --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%-- Parameters --%>

<c:set var="path" value="${pageContext.request.contextPath}"/>


<%-- Icon --%>

<link rel="shortcut icon" href="${path}/img/logo.png">


<%-- JS --%>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>


<%-- CSS --%>

<link rel="stylesheet" href="${path}/css/semantic.css"/>
<link rel="stylesheet" href="${path}/css/style.css"/>