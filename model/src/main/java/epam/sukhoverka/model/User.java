package epam.sukhoverka.model;

//marker interface
public abstract class User {
    private String login;
    private String name;
    private String surName;
    private String telephone;

    protected User(String login, String telephone, String name, String surName) {
        this.login = login;
        this.name = name;
        this.surName = surName;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

}
