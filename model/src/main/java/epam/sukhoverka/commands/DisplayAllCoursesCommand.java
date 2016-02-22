package epam.sukhoverka.commands;


import epam.sukhoverka.dao.CourseDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DisplayAllCoursesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String startPageString = request.getParameter("startPage");
        // because we actually start to select from db from zero
        int startPage = Integer.valueOf(startPageString) - 1;
        CourseDao courseDao = new CourseDao();
        try{
            List<Course> courses = courseDao.selectAllCoursesByPagination(startPage);
            request.setAttribute("allCourses", courses);
            page = PageManager.All_COURSES_PAGE_PATH;
        } catch (DBSystemException e) {
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
