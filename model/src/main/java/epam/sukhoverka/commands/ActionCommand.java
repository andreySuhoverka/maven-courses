package epam.sukhoverka.commands;


import epam.sukhoverka.manager.PageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface ActionCommand {
    String NO_RESULT_PAGE = PageManager.LOGIN_PAGE_PATH; ; // изменить на нужное
//    ActionCommand NO_ACTION = (request, response) -> NO_RESULT_PAGE;

    String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException;
}
