package eu.ensup.myresto;

import eu.ensup.myresto.exceptions.DaoException;
import eu.ensup.myresto.exceptions.ServiceException;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Order product service.
 */
public class OrderProductService implements IOrderProductService {

    private IOrderProductDao orderProduct;

    /**
     * Instantiates a new Order product service.
     *
     * @param productDao the product dao
     */
    public OrderProductService(IOrderProductDao productDao) {
        this.orderProduct = productDao;
    }

    /**
     * Instantiates a new Order product service.
     */
    public OrderProductService() {
        this.orderProduct = new OrderProductDao();
    }

    @Override
    public int createOrderProduct(OrderProductDto orderProductDto) throws ServiceException {
        try {
            return orderProduct.createOrderProduct(convertOrderProductDtoToOrderProduct(orderProductDto));
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "createOrderProduct", e.getMessage(), "Une erreur s'est produite lors de la récupération de la commande");
        }
    }

    @Override
    public Set<OrderProductDto> getAllOrderProductsForOneUser(int idUser) throws ServiceException {
        try {
            Set<OrderProductDto> productDtoSet = new HashSet<>();
            for (OrderProduct product : orderProduct.getAllOrderProductsForOneUser(idUser)) {
                productDtoSet.add(convertOrderProductToOrderProductDto(product));
            }
            return productDtoSet;
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "getAllOrderProductsForOneUser", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les commandes");
        }
    }
    @Override
    public Set<OrderProductDto> getAllOrderProduct() throws ServiceException {
        try {
            Set<OrderProductDto> productDtoSet = new HashSet<>();
            for (OrderProduct product : orderProduct.getAllOrderProduct()) {
                productDtoSet.add(convertOrderProductToOrderProductDto(product));
            }
            return productDtoSet;
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "getAllOrderProductsForOneUser", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les commandes");
        }
    }

    @Override
    public int updateOrderProduct(OrderProductDto orderProductDto) throws ServiceException {
        try {
            return orderProduct.updateOrderProduct(convertOrderProductDtoToOrderProduct(orderProductDto));
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "updateOrderProduct", e.getMessage(), "Une erreur s'est produite lors de la mise à jour du produit");
        }
    }
    @Override
    public int updateOrderProductById(int id, String value) throws ServiceException {
        try {
            return orderProduct.updateOrderProductById(id,value);
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "updateOrderProductById", e.getMessage(), "Une erreur s'est produite lors de la mise à jour du produit");
        }
    }

    @Override
    public int deleteOrderProduct(int idOrderProductDto) throws ServiceException {
        try {
            return orderProduct.deleteOrderProduct(idOrderProductDto);
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "deleteOrderProduct", e.getMessage(), "Une erreur s'est produite lors de la suppression du produit");
        }
    }

    @Override
    public OrderProductDto getOneOrderProduct(int idOrderProductDto) throws ServiceException {
        try {
            return convertOrderProductToOrderProductDto(orderProduct.getOneOrderProduct(idOrderProductDto));
        } catch (DaoException e) {
            throw new ServiceException(OrderProductService.class.getName(), "getOneOrderProduct", e.getMessage(), "Une erreur s'est produite lors de la récupération du produit");
        }
    }

    @Override
    public OrderProductDto convertOrderProductToOrderProductDto(OrderProduct product) {
//        return new OrderProductDto(product.getId(), product.getIdUser(), product.getIdProduct(), product.getDateCreated(), product.getStatus());
        return null;

    }

    @Override
    public OrderProduct convertOrderProductDtoToOrderProduct(OrderProductDto productDto) {
//        return new OrderProduct(productDto.getId(), productDto.getIdUser(), productDto.getIdProduct(), productDto.getDateCreated(), productDto.getStatus());
return null;
    }
}
