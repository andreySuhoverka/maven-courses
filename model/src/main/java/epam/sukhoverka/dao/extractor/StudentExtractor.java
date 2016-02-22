package epam.sukhoverka.dao.extractor;


import epam.sukhoverka.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentExtractor extends Extractor<Student> {

    @Override
    public Student extractOne(ResultSet rs) throws SQLException {
        String login = rs.getString("login");
        String name = rs.getString("name");
        String surName = rs.getString("surName");
        String telephone = rs.getString("telephone");
        return new Student(login, name, surName, telephone);
    }
}
