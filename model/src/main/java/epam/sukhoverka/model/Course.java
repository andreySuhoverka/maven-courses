package epam.sukhoverka.model;


public class Course {
    private long id;
    private String title;
    private String courseAuthorLogin;
    private String description;
    private String startTime;
    private String endTime;
    private String pictureUrl;
    private String authorPictureUrl;


    public Course(long id, String title, String courseAuthorLogin, String description, String startTime, String endTime, String pictureUrl, String authorPictureUrl) {
        this.id = id;
        this.courseAuthorLogin = courseAuthorLogin;
        this.description = description;
        this.endTime = endTime;
        this.startTime = startTime;
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.authorPictureUrl = authorPictureUrl;
    }

    public Course() {
    }

    public String getAuthorPictureUrl() {
        return authorPictureUrl;
    }

    public void setAuthorPictureUrl(String authorPictureUrl) {
        this.authorPictureUrl = authorPictureUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Course(String courseAuthorLogin, String courseName) {
        this.courseAuthorLogin = courseAuthorLogin;
        this.title = courseName;
    }

    public long getId() {
        return id;
    }

    public String getCourseAuthorLogin() {
        return courseAuthorLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setCourseAuthorLogin(String courseAuthorLogin) {
        this.courseAuthorLogin = courseAuthorLogin;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
