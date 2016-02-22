package epam.sukhoverka.dao;

import epam.sukhoverka.dao.extractor.Extractor;
import epam.sukhoverka.dao.extractor.TeacherExtractor;
import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.model.Teacher;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class TeacherDao extends AbstractDao<Teacher> {

    private final ResourceBundle sqlBundle = ResourceBundle.getBundle("properties.sql");
    private static Logger logger = Logger.getLogger(TeacherDao.class);

    public Teacher createTeacherByLogin(String login) throws NoSuchEntityException, DBSystemException {
        Extractor<Teacher> teacherExtractor = new TeacherExtractor();
        String sql = sqlBundle.getString("CREATE_TEACHER_BY_LOGIN");
        Teacher teacher = getModelByLogin(login, sql, teacherExtractor);
        return teacher;
    }

    public void signUpTeacher(String login, String shortName, String name, String surName, String telephone, String password) throws DBSystemException {

        String insertIntoUsersSql = sqlBundle.getString("INSERT_INTO_USERS");
        String insertIntoStudentsSql = sqlBundle.getString("INSERT_INTO_TEACHERS");
        String userRole = "teacher";
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
                ps2.setString(2, shortName);
                ps2.setString(3, name);
                ps2.setString(4, surName);
                ps2.setString(5, telephone);

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
}
