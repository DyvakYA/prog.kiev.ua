package main.prog.dao;

import main.prog.entity.one2many.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by User on 12/9/2017.
 */
public class DaoClient implements BaseDao {

    EntityManager em;

    public DaoClient(EntityManager em) {
        this.em = em;
    }

    public void create(Client client) {
        em.persist(client);
    }

    public List<Client> get() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ", Client.class);
        List<Client> clientList = query.getResultList();
        return clientList;
    }

    public void delete(Long id) {
        Client client = em.find(Client.class, id);
        em.remove(client);
    }
}
