package epam.sukhoverka.dao;

import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
    private final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private static Logger logger = Logger.getLogger(LoginDAO.class);

    public boolean userExists(String login, String password) throws DBSystemException {

        boolean userExists = false;
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement("SELECT * FROM users WHERE LOGIN = ? AND PASSWORD = ?");
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    rs = ps.executeQuery();
                    userExists = rs.next();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DBSystemException("error while reading db", e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBSystemException("prepare statement isn't created", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("connection error, can't connect to database", e);
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBSystemException("error when closing connection", e);
            }
        }
        return userExists;
    }

}
