package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Category dao.
 */
public class CategoryDao extends BaseDao implements ICategoryDao {
    private static final Logger log = LogManager.getLogger(CategoryDao.class);

    private static final String IMAGE = "image";
    private static final String ERR_ACCESS = "Une erreur s'est produite lors de la creation de la liste des categorie";

    @Override
    public List<Category> getAll() throws DaoException {
        List<Category> allCategory = new ArrayList<>();

        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM category"));
            setRs(getPs().executeQuery());

            ResultSet res = getRs();
            while (res.next()) {
                Category category = new Category(res.getInt("id"), res.getString("name"), res.getString(IMAGE));
                allCategory.add(category);
            }

            if (allCategory.isEmpty()) {
                log.info("Aucune category disponible dans la base de donnée.");
            }

            disconnect();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(UserDao.class.getName(), "getAll", e.getMessage(), ERR_ACCESS);
        }

        return allCategory;
    }

    @Override
    public Category getById(int idCategory) throws DaoException {
        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM category WHERE id = ?"));
            getPs().setInt(1, idCategory);
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(), "get", e.getMessage(), ERR_ACCESS);
        }

        return getSubMethod();
    }

    @Override
    public Category getByName(String nameCategory) throws DaoException {
        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM category Where name = ?"));
            getPs().setString(1, nameCategory);
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(), "get", e.getMessage(), ERR_ACCESS);
        }
        return getSubMethod();
    }

    private Category getSubMethod() throws DaoException {
        Category category = null;
        try {
            setRs(getPs().executeQuery());
            ResultSet res = getRs();
            String logInfo = "La categorie  n'est pas disponible dans la base de donnée.";
            if (!res.next()) {
                log.info(logInfo);
            } else {
                category = new Category(res.getInt("id"), res.getString("name"), res.getString(IMAGE));
            }
            disconnect();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(CategoryDao.class.getName(), "get", e.getMessage(), "Une erreur s'est produite lors de la recherche d'une categorie");
        }
        return category;
    }


    @Override
    public int create(Category category) throws DaoException {
        try {
            //Vérifie qu'il n'y a pas de double
            String logInfo = String.format("La categorie %s existe déjà", category.getName());
            if (getByName(category.getName()) == null) {
                connexion();

                setPs(getCn().prepareStatement("INSERT INTO category (name, image) VALUES ( ?, ? )"));

                getPs().setString(1, category.getName());
                getPs().setString(2, category.getImage());

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            } else {
                log.error(logInfo);
                throw new DaoException(CategoryDao.class.getName(), "create", null, "Cette categorie existe déjà");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(CategoryDao.class.getName(), "create", e.getMessage(), "Une erreur s'est produite lors de la creation d'une categorie");
        }
    }

    @Override
    public int update(Category category) throws DaoException {
        try {
            connexion();

            Category preCategory = getById(category.getId());

            boolean haveUpdate = category.getName() != null && !category.getName().equals(preCategory.getName());
            haveUpdate = haveUpdate || category.getImage() != null && category.getImage().equals(preCategory.getImage());
            String logInfo = String.format("Cette categorie n'as pas été modifier %s", category.getName());
            //Vérifie qu'il y a eu des modification
            if (haveUpdate) {
                setPs(getCn().prepareStatement("UPDATE category SET name = ?, image = ? WHERE id = ?"));

                getPs().setString(1, category.getName());
                getPs().setString(2, category.getImage());
                getPs().setInt(3, category.getId());

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            } else {
                log.error(logInfo);
                return 1;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(CategoryDao.class.getName(), "update", e.getMessage(), "Une erreur s'est produite lors de la mise a jour d'une categorie");
        }
    }

    @Override
    public int delete(Category category) throws DaoException {
        if (category.getId() == null)
            return delete(getByName(category.getName()).getId());
        return delete(category.getId());
    }

    @Override
    public int delete(int idCategory) throws DaoException {
        try {
            String logInfo = String.format("La categorie %d n'existe pas !", idCategory);
            if (idCategory != -1 && getById(idCategory) != null) {
                connexion();

                setPs(getCn().prepareStatement("DELETE FROM category WHERE id = ?"));
                getPs().setInt(1, idCategory);

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            } else {
                log.info(logInfo);
                throw new DaoException(CategoryDao.class.getName(), "delete", "La categorie " + idCategory + " n'existe pas !", "Cette categorie n'existe pas");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(CategoryDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
    }
}
