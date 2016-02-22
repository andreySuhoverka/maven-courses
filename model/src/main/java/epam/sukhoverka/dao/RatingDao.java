package epam.sukhoverka.dao;


import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RatingDao {
    private final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private static Logger logger = Logger.getLogger(RatingDao.class);
    private final ResourceBundle sqlBundle = ResourceBundle.getBundle("properties.sql");

    // the method returns a map<student login, student mark>
    public Map<String,Integer> getStudentRatingByCourseId(int courseId) throws DBSystemException {
        Map<String,Integer> ratingMap = new HashMap();
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("GET_STUDENTS_RATING_BY_COURSE_ID"));
                ps.setInt(1, courseId);

                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    rs = ps.executeQuery();
                    while (rs.next()){
                        String studentLogin = rs.getString("studentLogin");
                        int mark = rs.getInt("mark");
                        ratingMap.put(studentLogin, mark);
                    }
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
        return ratingMap;

    }
}
