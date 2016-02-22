<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>Bsu Courses</h1>
    <nav>
        <ul>
            <li><a href="/controller?command=display_all_courses&startPage=1">All Courses</a></li>
            <c:choose>
                <c:when test="${sessionScope.student ne null || sessionScope.teacher ne null}" >
                    <li><a href="/controller?command=main_page">My Courses</a></li>
                    <li><a href="/controller?command=sign_out">Sign out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/controller?command=login_page">Sign in</a></li>
                    <li><a href="/controller?command=sign_up_page">Sign up</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>