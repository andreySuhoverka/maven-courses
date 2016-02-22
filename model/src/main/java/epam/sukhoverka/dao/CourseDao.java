package epam.sukhoverka.dao;

import epam.sukhoverka.dao.extractor.CourseExtractor;
import epam.sukhoverka.dao.extractor.Extractor;
import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.model.Course;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CourseDao {

    private final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private final ResourceBundle sqlBundle = ResourceBundle.getBundle("properties.sql");
    private static Logger logger = Logger.getLogger(CourseDao.class);

    //todo: selectUserCoursesByStatus and createCourseById are not in one transaction
    public List<Course> selectUserCoursesByStatus(String login, String status) throws DBSystemException {

        List<Course> courses = new ArrayList();
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                // get ids desired courses
                ps = dbConnection.prepareStatement(sqlBundle.getString("SELECT_USER_COURSES_BY_STATUS"));
                ps.setString(1, login);
                ps.setString(2, status);
                ResultSet rs = null;
                try {
                    logger.debug(ps.unwrap(PreparedStatement.class).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        int courseId = rs.getInt("courseId");
                        Course course = createCourseById(courseId);
                        courses.add(course);
                    }
                } catch (SQLException e) {
                    throw new DBSystemException("error while reading db", e);
                }
            } catch (SQLException e) {
                throw new DBSystemException("prepare statement isn't created", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("connection error", e);
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new DBSystemException("dbConnection exception on close");
                }
            }

        }

        return courses;
    }

    public Course createCourseById(int id) throws DBSystemException {
        Course course = null;
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("SELECT_COURSE_BY_ID"));
                ps.setInt(1, id);
                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    rs = ps.executeQuery();

                    if (rs.next() == true) {
                        Extractor<Course> courseExtractor = new CourseExtractor();
                        course = courseExtractor.extractOne(rs);
                    }

                } catch (SQLException e) {
                    throw new DBSystemException("error while reading db", e);
                }

            } catch (SQLException e) {
                throw new DBSystemException("prepare statement isn't created", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("connection error", e);
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new DBSystemException("dbConnection exception on close");
                }
            }

        }

        return course;

    }

    public List<Course> selectAllCoursesByPagination(int startPage) throws DBSystemException {
        final int COURSES_NUMBER_PER_PAGE = 5;
        List<Course> courses = new ArrayList();
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("SELECT_ALL_COURSES_BY_PAGINATION"));
                ps.setInt(1, startPage * COURSES_NUMBER_PER_PAGE);
                ps.setInt(2, COURSES_NUMBER_PER_PAGE);
                ResultSet rs = null;
                try {
                    logger.debug(ps.unwrap(PreparedStatement.class).toString());
                    rs = ps.executeQuery();
                    Extractor<Course> courseExtractor = new CourseExtractor();
                    courses = courseExtractor.extractAll(rs);
                } catch (SQLException e) {
                    throw new DBSystemException("error while reading db", e);
                }
            } catch (SQLException e) {
                throw new DBSystemException("prepare statement isn't created", e);
            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("connection error", e);
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new DBSystemException("dbConnection exception on close");
                }
            }

        }

        return courses;
    }


}
