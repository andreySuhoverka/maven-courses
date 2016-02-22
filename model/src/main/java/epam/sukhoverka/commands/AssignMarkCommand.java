package epam.sukhoverka.commands;



import epam.sukhoverka.dao.StudentDao;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.manager.PageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AssignMarkCommand implements ActionCommand, AjaxCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageManager.MAIN_PAGE_PATH;
        String login = request.getParameter("login");
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        int mark = Integer.valueOf(request.getParameter("mark"));
        StudentDao studentDao = new StudentDao();
        try{
            studentDao.assignMarkForStudent(courseId, login, mark);
        } catch (DBSystemException e) {
            e.printStackTrace();
            page = PageManager.ERROR_PAGE_PATH;
        }
        return page;
    }
}
