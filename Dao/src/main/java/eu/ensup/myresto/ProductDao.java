package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Product dao.
 */
public class ProductDao extends BaseDao implements IProductDao {

    private static final Logger log = LogManager.getLogger(ProductDao.class);


    @Override
    public int createProduct(Product product) throws DaoException {
        return 0;
    }

    @Override
    public Set<Product> getAllProducts() throws DaoException {
        return null;
    }

    @Override
    public int updateProduct(Product product) throws DaoException {
        return 0;
    }

    @Override
    public int deleteProduct(int idProduct) throws DaoException {
        return 0;
    }

    @Override
    public Product getOneProduct(int idProduct) throws DaoException {
        return null;
    }
}
