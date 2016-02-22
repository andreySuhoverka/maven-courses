package epam.sukhoverka.model;


public class Teacher extends User {
    private String shortName;
    public Teacher(String login, String telephone,String shortName, String name, String surName) {
        super(login,telephone, name, surName);
        this.shortName = shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
