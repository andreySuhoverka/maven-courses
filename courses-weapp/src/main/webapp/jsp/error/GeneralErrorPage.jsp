<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>error page</title>
    <link rel="stylesheet" href="/css/main1.css">
</head>
<body>
<div class="mainContent">
<bsu:header/>
    <h1>Some error, but don't worry!</h1>
    ${requestScope.errorMessage}
</div>
<bsu:footer/>

</body>
</html>