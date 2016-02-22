package epam.sukhoverka.dao.extractor;


import epam.sukhoverka.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseExtractor extends Extractor<Course> {
    @Override
    public Course extractOne(ResultSet rs) throws SQLException {

        int id = rs.getInt("courseId");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String courseAuthorLogin = rs.getString("courseAuthorLogin");
        String startTime = rs.getString("startTime");
        String endTime = rs.getString("endTime");
        String pictureUrl = rs.getString("pictureUrl");
        String authorPictureUrl = rs.getString("authorPictureUrl");
        return new Course(id, title, courseAuthorLogin, description, startTime, endTime, pictureUrl, authorPictureUrl);

    }
}
