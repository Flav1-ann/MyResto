package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * The type Category dao.
 */
public class CategoryDao extends BaseDao implements ICategoryDao {
    private static final Logger log = LogManager.getLogger(CategoryDao.class);

    private static final String IMAGE = "image";
    private static final String ERR_ACCESS = "Une erreur s'est produite lors de la creation de la liste des categorie";

    @Override
    public List<Category> getAll() throws DaoException {
        return null;
    }

    @Override
    public Category getById(int idCategory) throws DaoException {
        return null;
    }

    @Override
    public Category getByName(String nameCategory) throws DaoException {
        return null;
    }

    @Override
    public int create(Category category) throws DaoException {
        EntityManager em = initTransaction();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(category);
        tr.commit();
        closeTransaction();
        return 0;
    }

    @Override
    public int update(Category category) throws DaoException {
        return 0;
    }

    @Override
    public int delete(Category category) throws DaoException {
        return 0;
    }

    @Override
    public int delete(int idCategory) throws DaoException {
        return 0;
    }
}
