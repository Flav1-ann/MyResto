package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * The type User dao.
 */
public class UserDao extends BaseDao implements IUserDao {
    private static final Logger log = LogManager.getLogger(UserDao.class);



    @Override
    public int create(User user) throws DaoException {
        EntityManager em = initTransaction();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(user);
        tr.commit();
        closeTransaction();
        return 0;
    }

    @Override
    public int update(User user) throws DaoException {
        return 0;
    }

    @Override
    public int delete(int userId) throws DaoException {
        return 0;
    }

    @Override
    public User getById(int userId) throws DaoException {
        return null;
    }

    @Override
    public User getByLogin(String login) throws DaoException {
        return null;
    }
}