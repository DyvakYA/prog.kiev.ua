package ua.com.prog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Bios on 29.11.2017.
 */
public class ConnectionFactory {

    private static final String NAME = "JPATest";

    private EntityManagerFactory emFactory;

    private EntityManager em;

    public ConnectionFactory() {
        emFactory = Persistence.createEntityManagerFactory(NAME);
        em = emFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
