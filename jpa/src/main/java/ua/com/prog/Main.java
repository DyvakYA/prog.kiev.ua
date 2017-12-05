package ua.com.prog;

import ua.com.prog.dao.MenuDAO;
import ua.com.prog.dao.MenuDAOImpl;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MenuDAO dao = new MenuDAOImpl();

        EntityCreator entities = new EntityCreator();

        dao.create(entities.getMenu1());
        dao.create(entities.getMenu2());
        dao.create(entities.getMenu3());
        dao.create(entities.getMenu4());
        dao.create(entities.getMenu5());

        System.out.println(dao.getMenus(500,100000));

        System.out.println(dao.getOnlyWithDiscount());

        List<Integer> list = Arrays.asList(1,3,4,5);

        System.out.println("<100500");
        System.out.println(dao.getNotMore100500Kilo(list));
    }
}
