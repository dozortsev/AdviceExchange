<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="resources.jsp"%>

<title>${member.name}</title>

<body class="login-body">

<div class="ui piled segment">

    <p>
    <h3>${member.name}</h3>
    </p>
    <div class="ui divider"></div>
</div>

<div class="ui grid">
    <div class="eight wide column">
        <div class="ui vertical fluid menu">
            <div class="header center item">
                Questions
            </div>
            <div class="item">

                <table class="ui basic table">
                    <tbody>
                    <c:forEach items="${questions}" var="qs">
                        <tr>
                            <td>${qs.votes}</td>
                            <td><a href="${path}/question/${qs.id}">${qs.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="eight wide column">
        <div class="ui vertical fluid menu">
            <div class="header center item">
                Answers
            </div>
            <div class="item">
                <table class="ui basic table">
                    <tbody>
                    <c:forEach items="${answers}" var="asw">
                        <tr>
                            <td>${asw.votes}</td>
                            <td>
                                <a href="${path}/question/${asw.question.id}">${asw.question.name}</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>

<script src="${path}/js/jquery-1.11.0.js"></script>
<script src="${path}/js/semantic.js"></script>
<script src="${path}/js/main.js"></script>
