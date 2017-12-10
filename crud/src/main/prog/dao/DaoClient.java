package main.prog.dao;

import main.prog.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by User on 12/9/2017.
 */
public class DaoClient implements DaoInterface{

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction transaction;

    public DaoClient(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
        this.transaction = em.getTransaction();
    }

    public void create(Client client) {
        transaction.begin();
        em.persist(client);
        transaction.commit();
    }

    public List<Client> get() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ", Client.class);
        List<Client> clientList = query.getResultList();
        return clientList;
    }

    public void delete(int id) {
        Client client = em.find(Client.class, id);
        transaction.begin();
        em.remove(client);
        transaction.commit();

    }
}
