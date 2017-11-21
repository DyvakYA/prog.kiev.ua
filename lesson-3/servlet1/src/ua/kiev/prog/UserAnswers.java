package ua.kiev.prog;

import java.util.Date;

/**
 * Created by User on 11/21/2017.
 */
public class UserAnswers {

    private Date date;
    private String user;
    private boolean firstQuestion;
    private boolean secondQuestion;

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(boolean firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public boolean isSecondQuestion() {
        return secondQuestion;
    }

    public void setSecondQuestion(boolean secondQuestion) {
        this.secondQuestion = secondQuestion;
    }

    @Override
    public String toString() {
        return "UserAnswers{" +
                "date=" + date +
                ", user='" + user + '\'' +
                ", firstQuestion=" + firstQuestion +
                ", secondQuestion=" + secondQuestion +
                '}';
    }
}
