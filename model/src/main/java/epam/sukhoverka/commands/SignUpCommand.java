package epam.sukhoverka.commands;

import epam.sukhoverka.business_logic.NameMaker;
import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.dao.TeacherDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String shortName = NameMaker.createShortName(name);
        String surname = request.getParameter("surname");
        String telephone = request.getParameter("telephone");
        boolean isTeacher = "on".equals(request.getParameter("isTeacher")) ? true : false;
        String password = request.getParameter("password");

        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        String page = null;
        try {
            if(isTeacher){
                teacherDao.signUpTeacher(login,shortName, name, surname, telephone, password);

            }else{
                studentDao.signUpStudent(login, name, surname, telephone, password);
            }
            page = PageManager.LOGIN_PAGE_PATH;
        } catch (DBSystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
