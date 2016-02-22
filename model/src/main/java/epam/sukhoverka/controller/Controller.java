package epam.sukhoverka.controller;

import epam.sukhoverka.commands.ActionCommand;
import epam.sukhoverka.commands.AjaxCommand;
import epam.sukhoverka.commands.util.ActionFactory;
import epam.sukhoverka.exception.user.NoSuchCommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processRequest(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }
    private static int count = 0;
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        String page = null;


        ActionFactory factory = new ActionFactory();
        ActionCommand command = null;
        try {
            command = factory.defineCommand(req);
        } catch (NoSuchCommandException e) {
            e.printStackTrace();
        }
        page = command.execute(req, resp);
        if(command instanceof AjaxCommand){
            //log message here about successful ajax request processing
        }else {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);

        }







    }


}
