package main.prog.controller;

import main.prog.dao.DaoClient;
import main.prog.dao.DaoInterface;
import main.prog.entity.Client;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        DaoInterface dao = new DaoClient(emf);
        String[] ids = req.getParameterValues("id");
        for(String id: ids){
            dao.delete(Integer.parseInt(id));
        }
        req.setAttribute("list", dao.get());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        DaoInterface dao = new DaoClient(emf);

        Client client = new Client.Builder()
                .setName(req.getParameter("name"))
                .setAge(Integer.parseInt(req.getParameter("age")))
                .build();
        dao.create(client);
        req.setAttribute("list", dao.get());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
