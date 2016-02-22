package epam.sukhoverka.dao.extractor;


import epam.sukhoverka.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherExtractor extends Extractor<Teacher> {
    @Override
    public Teacher extractOne(ResultSet rs) throws SQLException {
        String login = rs.getString("login");
        String name = rs.getString("name");
        String shortName = rs.getString("shortName");
        String surName = rs.getString("surName");
        String telephone = rs.getString("telephone");
        Teacher teacher = new Teacher(login,telephone,shortName, name, surName);
        return teacher;
    }
}
