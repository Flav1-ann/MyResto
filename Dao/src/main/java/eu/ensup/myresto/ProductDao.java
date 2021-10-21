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
        try {
            connexion();
            String sql = "INSERT INTO `product`(name,price, description,image,id_category) VALUES (?,?,?,?,?)";
            setPs(getCn().prepareStatement(sql));
            getPs().setString(1, product.getName());
            getPs().setFloat(2, product.getPrice());
            getPs().setString(3, product.getDescription());
            getPs().setString(4, product.getPicture());
            getPs().setInt(5, product.getIdCategory());
            setResult(getPs().executeUpdate());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(ProductDao.class.getName(), "createProduct", e.getMessage(), "Une erreur s'est produite lors de la récupération du produit");
        }
        return getResult();
    }

    @Override
    public Set<Product> getAllProducts() throws DaoException {
        Set<Product> products = new HashSet<>();
        try {
            connexion();
            String sql = "SELECT * FROM `product`";
            setPs(getCn().prepareStatement(sql));
            setRs(getPs().executeQuery());
            while (getRs().next()) {
                products.add(new Product(getRs().getInt("id"), getRs().getString("name"), getRs().getFloat("price"), getRs().getString("description"), getRs().getString("image"), getRs().getInt("id_category")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(ProductDao.class.getName(), "getAllProducts", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les produits");
        }
        return products;
    }

    @Override
    public int updateProduct(Product product) throws DaoException {
        try {
            connexion();
            String sql = "UPDATE `product` SET `name`= ?,`price`= ?,`description`= ?,`image`= ?,`id_category`= ? WHERE `id`= ?";
            setPs(getCn().prepareStatement(sql));
            getPs().setString(1, product.getName());
            getPs().setFloat(2, product.getPrice());
            getPs().setString(3, product.getDescription());
            getPs().setString(4, product.getPicture());
            getPs().setInt(5, product.getIdCategory());
            getPs().setInt(6, product.getId());
            setResult(getPs().executeUpdate());
            disconnect();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(ProductDao.class.getName(), "updateProduct", e.getMessage(), "Une erreur s'est produite lors de la mise à jour du produit");
        }
        return getResult();
    }

    @Override
    public int deleteProduct(int idProduct) throws DaoException {
        try {
            connexion();
            String sql = "DELETE FROM `product` WHERE id=?";
            setPs(getCn().prepareStatement(sql));
            getPs().setInt(1, idProduct);
            setResult(getPs().executeUpdate());
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(ProductDao.class.getName(), "deleteProduct", e.getMessage(), "Une erreur s'est produite lors de la suppression du produit");
        }
        return getResult();
    }

    @Override
    public Product getOneProduct(int idProduct) throws DaoException {
        try {
            connexion();
            String sql = "SELECT * FROM `product` WHERE id=?";
            setPs(getCn().prepareStatement(sql));
            getPs().setInt(1, idProduct);
            setRs(getPs().executeQuery());
            while (getRs().next()) {
                return new Product(getRs().getInt("id"), getRs().getString("name"), getRs().getFloat("price"), getRs().getString("description"), getRs().getString("image"), getRs().getInt("id_category"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(ProductDao.class.getName(), "getOneProduct", e.getMessage(), "Une erreur s'est produite lors de la récuperation du produit");
        }
        return null;

    }
}
