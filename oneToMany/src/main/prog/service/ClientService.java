package main.prog.service;

import main.prog.dao.BaseDao;
import main.prog.dao.DaoClient;
import main.prog.entity.one2many.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by User on 12/17/2017.
 */
public class ClientService implements BaseService {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction transaction;
    private BaseDao dao;

    public ClientService(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
        this.transaction = em.getTransaction();
        this.dao = new DaoClient(em);
    }

    public void create(Client client) {
        transaction.begin();
        dao.create(client);
        transaction.commit();
    }

    public List<Client> get() {
        return dao.get();
    }

    public void delete(Long id) {
        transaction.begin();
        dao.delete(id);
        transaction.commit();
    }
}
