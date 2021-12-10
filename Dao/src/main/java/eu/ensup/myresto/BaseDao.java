package eu.ensup.myresto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseDao {

    protected EntityManagerFactory emf;

    protected EntityManager initTransaction() {
        emf = Persistence.createEntityManagerFactory("video-reco-jpa");
        return emf.createEntityManager();
    }

    protected void closeTransaction() {
        emf.close();
    }

}
