<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bsu" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>course info</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="/css/main1.css">

    <script type="text/javascript" src="/js/showStudents.js"></script>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<bsu:mainWrap>

    <div class="info-content">

        <div class="info-content-head">
            <div class="info-content-head-left">
                <h1>${requestScope.course.title}</h1>
                <span class="full-time">From <span>${requestScope.course.startTime}</span> To <span>${requestScope.course.endTime}</span></span>
                <c:if test="${sessionScope.teacher != null and sessionScope.teacher.login eq requestScope.course.courseAuthorLogin}" >
                    <a href="/controller?command=display_students_rating&courseId=${requestScope.course.id}&courseStatus=current" class="show-students-marks-btn">show students marks</a>
                </c:if>

            </div>

            <img src="${requestScope.course.pictureUrl}" alt="course picture"/>
        </div>
        <div class="centerContent">
            <p>${requestScope.course.description}</p>
        </div>


        <div class="sidebar-right">
            <div class="full-info">
                <h2>Instractor</h2>
                <a href="" class="instractor-photo"><img src="${requestScope.course.authorPictureUrl}" alt="photo"></a>
                <span class="instractor-name">${requestScope.courseTeacher.shortName} ${requestScope.courseTeacher.surName}</span>
            </div>
            <c:if test="${sessionScope.teacher != null and sessionScope.teacher.login eq requestScope.course.courseAuthorLogin}" >
                <div class="students-list">
                <h2>Students</h2>
                <a  class="show-students-btn">show students</a>

                <div class="clear"></div>
                <ol id="stud-enum">
                    <c:forEach var="student" items="${requestScope.students}">
                        <li>${student.surName} ${student.name}</li>
                        <div class="stud-enum-assign-div">
                            <form action="/controller" method="post">
                                <input type="hidden" value="assign_mark"/>
                                <input type="hidden" value="${student.login}"/>
                                <input type="hidden" value="${requestScope.course.id}"/>
                                <label for="mark_${student.login}">student mark</label>
                                <input type="text" id="mark_${student.login}" name="mark">
                                <input type="button" class="assign-mark-btn" value="assign" />
                            </form>
                        </div>
                    </c:forEach>
                </ol>
            </div>
            </c:if>
        </div>

    </div>
</bsu:mainWrap>

</body>
</html>