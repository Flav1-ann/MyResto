package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Order product dao.
 */
public class OrderProductDao extends BaseDao implements IOrderProductDao {

    @Override
    public int createOrderProduct(OrderProduct orderProduct) throws DaoException {
        return 0;
    }

    @Override
    public Set<OrderProduct> getAllOrderProductsForOneUser(int idUser) throws DaoException {
        return null;
    }

    @Override
    public Set<OrderProduct> getAllOrderProduct() throws DaoException {
        return null;
    }

    @Override
    public int updateOrderProduct(OrderProduct orderProduct) throws DaoException {
        return 0;
    }

    @Override
    public int updateOrderProductById(int id, String value) throws DaoException {
        return 0;
    }

    @Override
    public int deleteOrderProduct(int idOrderProduct) throws DaoException {
        return 0;
    }

    @Override
    public OrderProduct getOneOrderProduct(int idOrderProduct) throws DaoException {
        return null;
    }
}
