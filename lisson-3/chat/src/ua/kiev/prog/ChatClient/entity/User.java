package ua.kiev.prog.ChatClient.entity;

import java.util.Date;

/**
 * Created by User on 11/25/2017.
 */
public class User {

    private Date registerDate;
    private String login;
    private String password;

    public static class Builder {

        User instance = new User();

        public Builder setRegisterDate() {
            instance.registerDate = new Date();
            return this;
        }

        public Builder setLogin(String login) {
            instance.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            instance.password = password;
            return this;
        }

        public User build() {
            return instance;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
