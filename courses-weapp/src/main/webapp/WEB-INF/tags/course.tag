<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="authorPictureUrl" required="true" rtexprvalue="true" %>
<%@ attribute name="courseTitle" required="true" rtexprvalue="true" %>
<%@ attribute name="courseAuthorLogin" required="true" rtexprvalue="true" %>
<%@ attribute name="startTime" required="true" rtexprvalue="true" %>
<%@ attribute name="endTime" required="true" rtexprvalue="true" %>
<%@ attribute name="courseId" required="true" rtexprvalue="true" %>
<div class="courseWrap">
    <article>
        <a href="#"><img src="${authorPictureUrl}" class="authorPicture" alt="${courseTitle}"></a>

        <div class="preview">
            <ul class="meta">
                <li><a href="#">Programming</a></li>
                <li><a href="#">Java</a></li>
            </ul>
            <h2>${courseTitle}</h2>

            <p class="post_info"><a href="#">${courseAuthorLogin}</a> on ${startTime} <a href="#">with
                44 people</a></p>

            <div class="time-line"></div>
            <div class="start-end-time">
                <span class="start-time">${startTime}</span>
                <span class="end-time">${endTime}</span>
            </div>
            <div class="info-unEnroll">
                <a href="/controller?command=display_course&courseId=${courseId}&status=current" class="continue">course
                    info</a>
                <a href="/controller?command=un_enroll_course&courseId=${courseId}&status=current&login=${sessionScope.student.login}" class="continue unEnroll">un-enroll</a>
                <c:if test="${sessionScope.student ne null}" >
                    <a href="controller?command=enroll_course&login=${sessionScope.student.login}&courseId=${courseId}" class="enroll-btn">Enroll in a course</a>
                </c:if>

            </div>
        </div>
        <div class="clear"></div>
    </article>
</div>


