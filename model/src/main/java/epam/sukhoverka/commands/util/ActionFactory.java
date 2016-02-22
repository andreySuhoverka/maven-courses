package epam.sukhoverka.commands.util;


import epam.sukhoverka.commands.ActionCommand;
import epam.sukhoverka.exception.user.NoSuchCommandException;
import epam.sukhoverka.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) throws NoSuchCommandException {
//        ActionCommand currentCommand = ActionCommand.NO_ACTION;
        ActionCommand currentCommand = null;

        String action = request.getParameter("command");

        if ( action == null || action.isEmpty() ){
            return currentCommand;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException e){
            request.setAttribute("errorMessage", MessageManager.WRONG_URL.getMessage() );
            throw new NoSuchCommandException("current command does not exist! " + action);
        }
        return currentCommand;
    }
}
