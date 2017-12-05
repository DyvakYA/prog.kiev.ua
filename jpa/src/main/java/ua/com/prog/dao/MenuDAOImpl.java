package ua.com.prog.dao;

import ua.com.prog.ConnectionFactory;
import ua.com.prog.entity.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bios on 29.11.2017.
 */
public class MenuDAOImpl implements MenuDAO {

    EntityManager em = new ConnectionFactory().getEntityManager();
    EntityTransaction transaction = em.getTransaction();

    public void create(Menu menu) {
        transaction.begin();
        em.persist(menu);
        transaction.commit();
    }

    public List<Menu> getMenus(long from, long to) {
        TypedQuery<Menu> query = em.createQuery("SELECT c FROM Menu c WHERE c.price > :price1 AND c.price< :price2", Menu.class);
        query.setParameter("price1", from);
        query.setParameter("price2", to);
        List<Menu> menus = query.getResultList();
        return menus;
    }

    public List<Menu> getOnlyWithDiscount() {
        TypedQuery<Menu> query = em.createQuery("SELECT c FROM Menu c WHERE c.discount = true ", Menu.class);
        List<Menu> menus = query.getResultList();
        return menus;
    }

    public List<Menu> getNotMore100500Kilo(List<Integer> list) {
        List<Menu> menus = new ArrayList<>();
        for (Integer item : list) {
            Menu menu = em.find(Menu.class, item.longValue());
            menus.add(menu);
        }
        double sum = menus.stream().mapToDouble(p -> p.getWeight()).sum();
        if (sum < 100500) {
            return menus;
        } else if (sum > 100500) {
            //throw new RuntimeException();
            menus = Collections.emptyList();
        }
        return menus;
    }
}
