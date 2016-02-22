package epam.sukhoverka.dao;

import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import epam.sukhoverka.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao extends AbstractDao{
    private final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private static Logger logger = Logger.getLogger(UserDao.class);

    //todo: не в одной транзакции два метода
    public User createUserByLogin(String login) throws DBSystemException {
        User user = null;

        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement("SELECT * FROM users WHERE LOGIN = ?");
                ps.setString(1, login);
                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        // int id = rs.getInt("id");
                        String role = rs.getString("role");

                        if (role.equals("student")) {
                            user = new StudentDao().createStudentByLogin(login);
                            return user;
                        }
                        if (role.equals("teacher")) {
                            user = new TeacherDao().createTeacherByLogin(login);
                            return user;
                        }

                    } else {
                        throw new NoSuchEntityException(login + " is incorrect");
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
            throw new DBSystemException("can not connect to database", e);
        } finally {
            try {
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBSystemException("error when closing database connection", e);
            }
        }
        return user;
    }

    public void signUpUser(String login, String name, String surName, String telephone, String password, boolean isTeacher) throws DBSystemException {

        String insertIntoUsersSql = "INSERT INTO users(login, password, role) VALUES(?,?,?)";
        String userRole = isTeacher ? "teacher" : "student";
        String insertSql = null;
        if(isTeacher){
            insertSql = "INSERT INTO teachers(login,shortName, name, surname, telephone) VALUES(?,?,?,?,?)";
        } else {
            insertSql = "INSERT INTO students(login, telephone, name, surname) VALUES(?,?,?,?)";
        }
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

                ps2 = dbConnection.prepareStatement(insertSql);
                ps2.setString(1,login);
                ps2.setString(2,telephone);
                ps2.setString(3,name);
                ps2.setString(4,surName);

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
