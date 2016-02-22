package epam.sukhoverka.commands;

import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UnEnrollCourseCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageManager.MAIN_PAGE_PATH;
        String login = request.getParameter("login");
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        StudentDao studentDao = new StudentDao();
        try{
            studentDao.unEnrollStudentFromCourse(login, courseId);
            List<Course> currentCourses = (List<Course>)request.getSession().getAttribute("currentCourses");
            for(int k = 0; k < currentCourses.size(); k++){
                if(currentCourses.get(k).getId() == courseId){
                    ((List<Course>)request.getSession().getAttribute("currentCourses")).remove(k);
                }
            }
        } catch (DBSystemException e) {
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
