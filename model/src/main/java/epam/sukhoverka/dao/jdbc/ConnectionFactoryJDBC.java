package epam.sukhoverka.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionFactoryJDBC implements ConnectionFactory {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.jdbc_config");




    private static ConnectionFactoryJDBC dataSource;
    private static final String url =  resourceBundle.getString("jdbc_url");
    private static final String user = resourceBundle.getString("user");
    private static final String password = resourceBundle.getString("password");


    private ConnectionFactoryJDBC(){}

    public static ConnectionFactoryJDBC getInstance()  {
        if (dataSource == null) {
            dataSource = new ConnectionFactoryJDBC();
        }
        return dataSource;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }


}
