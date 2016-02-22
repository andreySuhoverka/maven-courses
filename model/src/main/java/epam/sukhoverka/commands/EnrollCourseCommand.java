package epam.sukhoverka.commands;


import epam.sukhoverka.dao.CourseDao;
import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class EnrollCourseCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/jsp/main.jsp";
        String login = request.getParameter("login");
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        try{
            studentDao.enrollStudentOnCourse(login, courseId);
            Course course = courseDao.createCourseById(courseId);
            List<Course> currentCourses = (List<Course>)request.getSession().getAttribute("currentCourses");
            currentCourses.add(course);
        } catch (DBSystemException e) {
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
