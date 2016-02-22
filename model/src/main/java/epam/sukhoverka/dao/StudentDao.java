package epam.sukhoverka.dao;

import epam.sukhoverka.dao.extractor.Extractor;
import epam.sukhoverka.dao.extractor.StudentExtractor;
import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.model.Student;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StudentDao extends AbstractDao<Student> {
    private final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private final ResourceBundle sqlBundle = ResourceBundle.getBundle("properties.sql");
    private static Logger logger = Logger.getLogger(StudentDao.class);

    //todo change
    public void signUpStudent(String login, String name, String surName, String telephone, String password) throws DBSystemException {

        String insertIntoUsersSql = sqlBundle.getString("INSERT_INTO_USERS");
        String insertIntoStudentsSql = sqlBundle.getString("INSERT_INTO_STUDENTS");
        String userRole = "student";
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            try {
                ps1 = dbConnection.prepareStatement(insertIntoUsersSql);
                ps1.setString(1, login);
                ps1.setString(2, password);
                ps1.setString(3, userRole);

                ps2 = dbConnection.prepareStatement(insertIntoStudentsSql);
                ps2.setString(1, login);
                ps2.setString(2, telephone);
                ps2.setString(3, name);
                ps2.setString(4, surName);

                logger.debug(ps1.unwrap(PreparedStatement.class).toString());
                try {
                    ps1.executeUpdate();
                    ps2.executeUpdate();
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

    }


    public List<Student> getAllStudentsByCourseIdAndStatus(int courseId, String status) throws DBSystemException {
        Connection dbConnection = null;
        List<Student> students = new ArrayList();
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("SELECT_STUDENTS_BY_COURSE_ID_AND_STATUS"));
                ps.setInt(1, courseId);
                ps.setString(2, status);
                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    rs = ps.executeQuery();
                    StudentDao studentDao = new StudentDao();
                    while (rs.next() == true) {
                        String login = rs.getString("login");

                        Student student = studentDao.createStudentByLogin(login);
                        students.add(student);
                    }

                } catch (SQLException e) {
                    throw new DBSystemException("error while reading db", e);
                } catch (NoSuchEntityException e) {
                    throw new DBSystemException("no such student with this login", e);
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
        return students;
    }

    public void enrollStudentOnCourse(String login, int courseId) throws DBSystemException {
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("INSERT_STUDENT_IN_COURSE_ATTENDEES"));
                ps.setInt(1, courseId);
                ps.setString(2, login);
                ps.setString(3, "current");
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    ps.executeUpdate();
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
    }

    public void unEnrollStudentFromCourse(String login, int courseId) throws DBSystemException {
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("DELETE_STUDENT_FROM_COURSE_ATTENDEES"));
                ps.setInt(1, courseId);
                ps.setString(2, login);
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    ps.executeUpdate();
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

    }

    public void assignMarkForStudent(int courseId, String login, int mark) throws DBSystemException {
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                if (isStudentMarkExists(courseId, login)) {
                    ps = dbConnection.prepareStatement(sqlBundle.getString("ASSIGN_MARK_TO_STUDENT_UPDATE"));
                    ps.setInt(1, mark);
                    ps.setInt(2, courseId);
                    ps.setString(3, login);
                } else {
                    ps = dbConnection.prepareStatement(sqlBundle.getString("ASSIGN_MARK_TO_STUDENT_INSERT"));
                    ps.setInt(1, courseId);
                    ps.setString(2, login);
                    ps.setInt(3, mark);

                }

                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    ps.executeUpdate();
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


    }

    public boolean isStudentMarkExists(int courseId, String login) throws DBSystemException {
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(sqlBundle.getString("STUDENT_MARK_EXISTS"));
                ps.setInt(1, courseId);
                ps.setString(2, login);
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                ResultSet rs = null;
                try {
                    rs = ps.executeQuery();
                    return rs.next();
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


    }

    public Student createStudentByLogin(String login) throws NoSuchEntityException, DBSystemException {
        Extractor<Student> studentExtractor = new StudentExtractor();
        String sql = sqlBundle.getString("CREATE_STUDENT_BY_LOGIN");
        Student student = getModelByLogin(login, sql, studentExtractor);
        return student;
    }


}





