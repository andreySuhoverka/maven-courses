package epam.sukhoverka.model;


public class Student extends User {

    public Student(String login, String name, String surName, String telephone) {
        super(login,telephone, name, surName);
    }

}
