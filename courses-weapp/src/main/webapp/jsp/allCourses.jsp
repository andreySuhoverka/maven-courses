<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>all courses</title>
    <link rel="stylesheet" href="/css/main1.css">
</head>
<body>

<c:set var="isStudent" value="${sessionScope.student ne null}" scope="request"/>
<bsu:mainWrap>
    <div class="allCoursesPage">
        <c:forEach var="courseItem" items="${requestScope.allCourses}">
            <bsu:course
                courseId="${courseItem.id}"
                courseTitle="${courseItem.title}"
                authorPictureUrl = "${courseItem.pictureUrl}"
                courseAuthorLogin = "${courseItem.courseAuthorLogin}"
                startTime = "${courseItem.startTime}"
                endTime= "${courseItem.endTime}" />
        </c:forEach>
    </div>
</div>
</bsu:mainWrap>
</body>
</html>