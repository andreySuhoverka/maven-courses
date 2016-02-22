window.onload = function () {
    var pastRef = document.getElementsByClassName("past")[0];
    var currentRef = document.getElementsByClassName("current")[0];
    var upcomingRef = document.getElementsByClassName("upcoming")[0];


    var toShowPastCourses = document.getElementsByClassName("pastBlock")[0];
    var toHidePastCourses = document.getElementsByClassName("pastBlock")[0];

    var toShowCurrentCourses = document.getElementsByClassName("currentBlock")[0];
    var toHideCurrentCourses = document.getElementsByClassName("currentBlock")[0];

    var toShowUpcomingCourses = document.getElementsByClassName("upcomingBlock")[0];
    var toHideUpcomingCourses = document.getElementsByClassName("upcomingBlock")[0];
    pastRef.onclick = function () {
        toHideCurrentCourses.style.display = "none";
        toHideUpcomingCourses.style.display = "none";
        toShowPastCourses.style.display = "block";

    }
    currentRef.onclick = function () {
        toHidePastCourses.style.display = "none";
        toHideUpcomingCourses.style.display = "none";
        toShowCurrentCourses.style.display = "block";

    }
    upcomingRef.onclick = function () {
        toHideCurrentCourses.style.display = "none";
        toHidePastCourses.style.display = "none";
        toShowUpcomingCourses.style.display = "block";
    }
};