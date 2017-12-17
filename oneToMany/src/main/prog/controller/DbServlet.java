package main.prog.controller;

import main.prog.entity.one2many.Address;
import main.prog.entity.one2many.Client;
import main.prog.service.BaseService;
import main.prog.service.ClientService;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        BaseService service = new ClientService(emf);
        String[] ids = request.getParameterValues("id");
        for(String id: ids){
            service.delete(Long.parseLong(id));
        }
        request.setAttribute("list", service.get());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        BaseService service = new ClientService(emf);

        Client client = new Client.Builder()
                .setName(request.getParameter("name"))
                .setAge(Integer.parseInt(request.getParameter("age")))
                .build();

        Address address = new Address.Builder()
                .setCountry(request.getParameter("country"))
                .setCity(request.getParameter("city"))
                .setStreet(request.getParameter("street"))
                .build();

        Address address2 = new Address.Builder()
                .setCountry(request.getParameter("country2"))
                .setCity(request.getParameter("city2"))
                .setStreet(request.getParameter("street2"))
                .build();

        client.addAddress(address);
        client.addAddress(address2);

        //client.setAddress(address);
        //address.setClient(client);

        service.create(client);
        request.setAttribute("list", service.get());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
