package epam.sukhoverka.manager;

import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 6/22/14
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
public enum MessageManager {
    LOGIN_ERROR_MESSAGE,
    WRONG_URL;

    private final ResourceBundle bundle = ResourceBundle.getBundle("properties.messages");
    public String getMessage(){
        return bundle.getString(this.name());
    }




}
