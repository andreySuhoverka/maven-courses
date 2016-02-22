package epam.sukhoverka.dao.jdbc;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactoryC3P0 implements ConnectionFactory {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.jdbc_config");


    private ComboPooledDataSource cpds;
    private static ConnectionFactoryC3P0 dataSource;
    //private final static ResourceBundle props = ResourceBundle.getBundle("resources.connectionPool");

    private ConnectionFactoryC3P0() throws PropertyVetoException {
        initDataSource();
    }

    public static ConnectionFactoryC3P0 getInstance() throws Exception {
        if (dataSource == null) {
            dataSource = new ConnectionFactoryC3P0();
        }
        return dataSource;
    }

    public void initDataSource() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");
        cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521");
        cpds.setUser("andrei");
        cpds.setPassword("andrei");
        cpds.setAutoCommitOnClose(true);
    }

    public Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }


}
