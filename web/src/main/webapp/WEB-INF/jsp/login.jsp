<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<head>
    <title>
        <fmt:message key="login.title"/>
    </title>
</head>

<body>

<table class="ui basic table">
    <tbody>
    <tr>
        <td class="wide three"></td>
        <td class="wide ten">

            <c:if test="${not empty message}">

                <div class="ui black small message">
                    <i class="close icon"></i>

                    <div class="ui tiny header">
                            ${message}
                    </div>
                </div>
            </c:if>

            <div class="ui piled segment">

                <!-- Tabs -->

                <div class="ui pointing secondary demo menu">
                    <a class="active red item" data-tab="first">
                        <fmt:message key="login.title"/>
                    </a>
                    <a class="blue item" data-tab="second">
                        <fmt:message key="login.lbl.signup"/>
                    </a>
                </div>


                <!-- Login panel -->

                <form action="<c:url value="/j_spring_security_check"/>" name="form-login" method="POST">

                    <div class="ui active tab segment" data-tab="first">
                        <div class="ui form segment">
                            <div class="field">
                                <label><fmt:message key="login.lbl.email"/></label>

                                <div class="ui small left labeled icon input">
                                    <input type="text" name="j_username" required="true">
                                    <i class="mail icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label><fmt:message key="login.lbl.pwd"/></label>

                                <div class="ui small left labeled icon input">
                                    <input type="password" name="j_password" required="true">
                                    <i class="lock icon"></i>
                                </div>
                            </div>

                            <input class="ui red submit small button" type="submit"
                                   value="<fmt:message key="login.title"/>"/>
                        </div>
                    </div>
                </form>


                <!-- Signin panel -->

                <form:form action="${path}/createAccount" method="POST" modelAttribute="member">

                    <div class="ui tab segment" data-tab="second">
                        <div class="ui form segment">
                            <div class="three fields">
                                <div class="field">
                                    <label><fmt:message key="login.lbl.name"/></label>
                                    <input name="name" type="text" required="true">
                                </div>
                                <div class="field">
                                    <label><fmt:message key="login.lbl.email"/></label>
                                    <input name="email" type="text" required="true">
                                </div>
                                <div class="field">
                                    <label><fmt:message key="login.lbl.pwd"/></label>
                                    <input name="password" type="password" required="true">
                                </div>
                            </div>

                            <div class="ui fluid accordion field">
                                <div class="title">
                                    <i class="icon dropdown"></i>
                                    <fmt:message key="login.lbl.opi"/>
                                </div>
                                <div class="content field">
                                    <div class="three fields">
                                        <div class="field">
                                            <label><fmt:message key="login.lbl.loc"/></label>
                                            <input name="location" type="text">
                                        </div>
                                        <div class="field">
                                            <label><fmt:message key="login.lbl.site"/>
                                            </label><input name="site" type="text">
                                        </div>
                                        <div class="field">
                                            <label><fmt:message key="login.lbl.age"/></label>
                                            <input name="age" type="text">
                                        </div>
                                    </div>
                                    <div class="ui form">
                                        <div class="field">
                                            <label><fmt:message key="login.lbl.aboutMe"/></label>
                                            <textarea name="aboutMe"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input class="ui small blue submit button" type="submit"
                                   value="<fmt:message key="login.btn.nac"/>"/>
                        </div>
                    </div>
                </form:form>
            </div>

        </td>
        <td class="wide three"></td>
    </tr>
    </tbody>
</table>
</body>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
<script src="${pageContext.request.contextPath}/js/semantic.js"></script>
<script src="${pageContext.request.contextPath}/js/showdown.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>