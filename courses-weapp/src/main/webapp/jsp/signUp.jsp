<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="/css/main1.css">
</head>
<body>

<bsu:mainWrap>
    <div class="registration">
        <div class="registration-container">
            <h2>Sign up</h2>
            <form action="/controller" method="post">
                <input type="hidden" name="command" value="sign_up"/>
                <div class="form-fow">
                    <label for="login">Login</label>
                    <input type="text" name="login" id="login">
                </div>
                <div class="form-fow">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name">
                </div>
                <div class="form-fow">
                    <label for="surname">Surname</label>
                    <input type="text" name="surname" id="surname">
                </div >
                <div class="form-fow">
                    <label for="telephone">Telephone</label>
                    <input type="text" name="telephone" id="telephone">
                </div >
                <div class="form-fow">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password">
                </div>
                <div class="check">
                    <input type="checkbox" name="isTeacher" id="isTeacher">
                    <label for="isTeacher">I'm a teacher</label>

                </div>
                <div class="form-fow">
                    <button type="submit" class="submitButton">Sign Up</button>
                </div>
            </form>
        </div>
        <div class="signInContainer">
            <p><span>Have an Account?</span><br>If you already have a password, please <a href="/controller?command=login_page">sign in.</a></p>
        </div>
        <div class="clear"></div>

    </div>

</bsu:mainWrap>


</body>
</html>