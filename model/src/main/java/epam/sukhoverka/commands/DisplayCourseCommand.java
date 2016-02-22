package epam.sukhoverka.commands;


import epam.sukhoverka.dao.CourseDao;
import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.dao.TeacherDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;
import epam.sukhoverka.model.Course;
import epam.sukhoverka.model.Student;
import epam.sukhoverka.model.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class DisplayCourseCommand implements ActionCommand {
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = PageManager.COURSE_INFO;
        int courseId = Integer.valueOf(req.getParameter("courseId"));
        CourseDao courseDao = new CourseDao();
        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        try {
            Course course = courseDao.createCourseById(courseId);
            req.setAttribute("course", course);


            Teacher courseTeacher = teacherDao.createTeacherByLogin(course.getCourseAuthorLogin());
            req.setAttribute("courseTeacher", courseTeacher);

            String status = req.getParameter("status");
            List<Student> students = studentDao.getAllStudentsByCourseIdAndStatus(courseId, status);
            req.setAttribute("students", students);

        } catch (DBSystemException e) {
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }

        return page;

    }

}
