package epam.sukhoverka.commands;

import epam.sukhoverka.dao.CourseDao;
import epam.sukhoverka.dao.RatingDao;
import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.dao.TeacherDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;
import epam.sukhoverka.model.Student;
import epam.sukhoverka.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class DisplayStudentsRating implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageManager.STUDENTS_RATING_PAGE_PATH;
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        String courseStatus = request.getParameter("courseStatus");
        RatingDao ratingDao = new RatingDao();
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        TeacherDao teacherDao = new TeacherDao();
        Map loginMarkMap = null;
        try{
            Course course = courseDao.createCourseById(courseId);
            request.setAttribute("course", course);

            Teacher courseTeacher = teacherDao.createTeacherByLogin(course.getCourseAuthorLogin());
            request.setAttribute("courseTeacher", courseTeacher);


            loginMarkMap = ratingDao.getStudentRatingByCourseId(courseId);
            request.setAttribute("ratingMap", loginMarkMap);
            List<Student> students = studentDao.getAllStudentsByCourseIdAndStatus(courseId, courseStatus);
            request.setAttribute("students", students);
        }catch (DBSystemException e){
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
