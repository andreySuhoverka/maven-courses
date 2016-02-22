<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Sign in</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/main1.css">
</head>
<body>
<c:if test="${sessionScope.student ne null or sessionScope.teacher ne null}">
    <jsp:forward page="main.jsp"/>
</c:if>

<bsu:mainWrap>
    <div class="registration-login">
        <div class="registration-container-login">
            <h2>Sign in</h2>

            <form method="post" action="/controller">
                <input type="hidden" name="command" value="login"/>

                <c:if test="${requestScope.errorMessage ne null}">
                    <div class="incorrect-login-or-pass">
                        <p>${requestScope.errorMessage}</p>
                    </div>
                    <c:remove var="errorMessage" scope="request" />
                </c:if>
                <p>${errorMessage}</p>
                <div class="form-fow">
                    <label for="login">Login</label>
                    <input type="text" name="login" id="login">
                </div>
                <div class="form-fow">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password">
                </div>

                <div class="form-fow">
                    <button type="submit" class="submitButton">Sign in</button>
                </div>
            </form>
        </div>
        <div class="signInContainer-login">
            <p><span>No Account Yet?</span><br> <a href="/controller?command=sign_up_page">Sign up</a> today.</p>
        </div>
        <div class="clear"></div>
    </div>
</bsu:mainWrap>


</body>
</html>