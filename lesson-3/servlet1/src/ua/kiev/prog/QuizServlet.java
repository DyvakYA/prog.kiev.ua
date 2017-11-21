package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 11/21/2017.
 */
public class QuizServlet extends HttpServlet {

    static final List<UserAnswers> list = new ArrayList<>();
    UserAnswers userAnswers;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a = req.getParameter("a");
        String b = req.getParameter("b");
        String c = req.getParameter("c");
        String d = req.getParameter("d");
        String f = req.getParameter("f");
        String i = req.getParameter("i");
        HttpSession session = req.getSession();
        UserAnswers userAnswers = createUser(session);
        firstAnswer(a, b);
        secondAnswer(i);
        if(!(a==null&&b==null&&c==null&&d==null&&i==null&&f==null)) {
            list.add(userAnswers);
        }
        list
                .stream()
                .sorted(Comparator.comparing(UserAnswers::getDate));
        req.setAttribute("UserAnswers", list);
        req.getRequestDispatcher("question.jsp").forward(req, resp);
    }

    private void firstAnswer(String a, String b) {
        if (a != null && b != null && a.equals("READ_COMMITED") && b.equals("DEFAULT")) {
            userAnswers.setFirstQuestion(true);
        } else {
            userAnswers.setFirstQuestion(false);
        }
    }

    private void secondAnswer(String i) {
        if (i != null && i.equals("Perhaps")) {
            userAnswers.setSecondQuestion(true);
        } else {
            userAnswers.setSecondQuestion(false);
        }
    }

    private UserAnswers createUser(HttpSession session) {
        userAnswers = new UserAnswers();
        userAnswers.setUser(String.valueOf(session.getAttribute("user_login")));
        userAnswers.setDate();
        return userAnswers;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = String.valueOf(req.getSession().getAttribute("user_login"));
        ArrayList<UserAnswers> newList = (ArrayList<UserAnswers>) list.stream()
                .filter(s->s.getUser().equals(user))
                .collect(Collectors.toList());
        req.setAttribute("UserAnswers", newList);
        req.getRequestDispatcher("question.jsp").forward(req, resp);
    }
}
