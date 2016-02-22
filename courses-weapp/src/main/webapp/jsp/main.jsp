
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>my courses</title>
    <link rel="stylesheet" href="/css/main1.css">
    <script type="text/javascript" src="/js/showCoursesWithStatus.js"></script>
</head>
<body>
<bsu:mainWrap>
    <bsu:subHeader/>
    <div class="main-page">
        <div class="currentBlock">
            <c:forEach var="courseItem" items="${sessionScope.currentCourses}">
                <bsu:course
                        courseId="${courseItem.id}"
                        courseTitle="${courseItem.title}"
                        authorPictureUrl = "${courseItem.pictureUrl}"
                        courseAuthorLogin = "${courseItem.courseAuthorLogin}"
                        startTime = "${courseItem.startTime}"
                        endTime= "${courseItem.endTime}" />
            </c:forEach>
        </div>


        <div class="pastBlock">
            <c:forEach var="courseItem" items="${sessionScope.pastCourses}">
                <bsu:course
                        courseId="${courseItem.id}"
                        courseTitle="${courseItem.title}"
                        authorPictureUrl = "${courseItem.pictureUrl}"
                        courseAuthorLogin = "${courseItem.courseAuthorLogin}"
                        startTime = "${courseItem.startTime}"
                        endTime= "${courseItem.endTime}" />
            </c:forEach>
        </div>
        <div class="upcomingBlock">
            <c:forEach var="courseItem" items="${sessionScope.upcomingCourses}">
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