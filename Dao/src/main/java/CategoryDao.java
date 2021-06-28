import exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BaseDao implements ICategoryDao
{
    @Override
    public List<Category> getAll() throws DaoException {
        List<Category> allCategory = new ArrayList<Category>();

        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM Category"));
            setResult(getPs().executeUpdate());

            ResultSet res = getRs();
            if(!res.next()){
                System.out.println("Aucune category disponible dans la base de donnée.");
            }
            while( res.next() ) {
                Category category = new Category(res.getInt("id"), res.getString("name"), res.getString("image"));
                allCategory.add(category);
            }

            disconnect();
        } catch (SQLException e) {
            throw new DaoException(UserDao.class.getName(),"getAll",e.getMessage(),"Une erreur s'est produite lors de la creation de la liste des categorie");
        }

        return allCategory;
    }

    @Override
    public Category get(int idCategory) throws DaoException {
        Category category = null;
        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM Category Where id = ?"));
            getPs().setInt(1, idCategory);
            setResult(getPs().executeUpdate());

            ResultSet res = getRs();
            if(!res.next()){
                System.out.println("Aucune category disponible dans la base de donnée.");
            }
            while( res.next() ) {
                category = new Category(res.getInt("id"), res.getString("name"), res.getString("image"));
            }

            disconnect();
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(),"get",e.getMessage(),"Une erreur s'est produite lors de la recherche d'une categorie");
        }

        return category;
    }

    @Override
    public Category get(String nameCategory) throws DaoException {
        Category category = null;

        try {
            connexion();
            setPs(getCn().prepareStatement("SELECT * FROM Category Where name = ?"));
            getPs().setString(1, nameCategory);
            setResult(getPs().executeUpdate());

            ResultSet res = getRs();
            if(!res.next()){
                System.out.println("Aucune category disponible dans la base de donnée.");
            }
            while( res.next() ) {
                category = new Category(res.getInt("id"), res.getString("name"), res.getString("image"));
            }

            disconnect();
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(),"get",e.getMessage(),"Une erreur s'est produite lors de la recherche d'une categorie");
        }

        return category;
    }

    @Override
    public int create(Category category) throws DaoException {
        try {
            connexion();

            //Vérifie qu'il n'y a pas de double
            if (get(category.getName()) == null) {
                if (category.getId() != -1)
                    setPs(getCn().prepareStatement("INSERT INTO Category (id, name, image) VALUES ( ?, ?, ? )"));
                else
                    setPs(getCn().prepareStatement("INSERT INTO Category (name, image) VALUES ( ?, ? )"));

                int index = 1;
                if (getPs().getParameterMetaData().getParameterCount() == 3)
                    getPs().setInt(index++, category.getId());

                getPs().setString(index++, category.getName());
                getPs().setString(index++, category.getImage());

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            }
            else
            {
                throw new DaoException(CategoryDao.class.getName(), "create", null, "Cette categorie existe déjà");
            }
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(), "create", e.getMessage(), "Une erreur s'est produite lors de la creation d'une categorie");
        }
    }

    @Override
    public int update(Category category) throws DaoException {
        try {
            connexion();

            Category preCategory = get(category.getId());
            String update = "";

            boolean haveUpdate = category.getName() != null && ! category.getName().equals(preCategory.getName());
            haveUpdate = haveUpdate || category.getImage() != null && category.getImage().equals(preCategory.getImage());

            //Vérifie qu'il y a eu des modification
            if( haveUpdate )
            {
                setPs(getCn().prepareStatement("UPDATE Category SET name = ?, image = ? WHERE id = ?"));

                int index = 1;
                getPs().setString(index++, category.getName());
                getPs().setString(index++, category.getImage());
                getPs().setInt(index++, category.getId());

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            }
            else
            {
                throw new DaoException(CategoryDao.class.getName(), "update", null, "Cette categorie n'as pas été modifier");
            }
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(), "update", e.getMessage(), "Une erreur s'est produite lors de la mise a jour d'une categorie");
        }
    }

    @Override
    public int delete(Category category) throws DaoException {
        if( category.getId() == null )
            return delete(get(category.getName()).getId());
        return delete(category.getId());
    }

    @Override
    public int delete(int idCategory) throws DaoException
    {
        try{
            if( idCategory != -1 && get(idCategory) != null )
            {
                setPs(getCn().prepareStatement("DELETE FROM Category WHERE id = ?"));

                getPs().setInt(1, idCategory);

                setResult(getPs().executeUpdate());
                disconnect();
                return getResult();
            }
        } catch (SQLException e) {
            throw new DaoException(CategoryDao.class.getName(), "delete", e.getMessage(), "Une erreur s'est produite lors de la suppression d'une categorie");
        }
        return -1;
    }
}
