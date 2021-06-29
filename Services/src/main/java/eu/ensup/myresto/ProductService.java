package eu.ensup.myresto;

import java.util.HashSet;
import java.util.Set;

import eu.ensup.myresto.exceptions.DaoException;
import eu.ensup.myresto.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductService implements IProductService {

    private static final Logger log = LogManager.getLogger(ProductService.class);


    private IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductService() {
        this.productDao = new ProductDao();
    }

    @Override
    public int createProduct(ProductDto product) throws ServiceException {
        try {
            return productDao.createProduct(convertProductDtoToProduct(product));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductService.class.getName(), "createProduct", e.getMessage(), "Une erreur s'est produite lors de la récupération du produit");
        }
    }

    @Override
    public Set<ProductDto> getAllProducts() throws ServiceException {
        try {
            Set<ProductDto> productDtoSet = new HashSet<>();
            for (var product:productDao.getAllProducts()) {
                productDtoSet.add(convertProductToProductDto(product));
            }
            return productDtoSet;
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductService.class.getName(), "getAllProducts", e.getMessage(), "Une erreur s'est produite lors de la récupération de tout les produits");
        }
    }

    @Override
    public int updateProduct(Product product) throws ServiceException {
        try {
            return productDao.updateProduct(product);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductService.class.getName(), "updateProduct", e.getMessage(), "Une erreur s'est produite lors de la mise à jour du produit");
        }
    }

    @Override
    public int deleteProduct(int idProduct) throws ServiceException {
        try {
            return productDao.deleteProduct(idProduct);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductService.class.getName(), "deleteProduct", e.getMessage(), "Une erreur s'est produite lors de la suppression du produit");
        }
    }

    @Override
    public ProductDto getOneProduct(int idProduct) throws ServiceException {
        try {
            return convertProductToProductDto(productDao.getOneProduct(idProduct));
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(ProductService.class.getName(), "getOneProduct", e.getMessage(), "Une erreur s'est produite lors de la récupération du produit");
        }
    }

    @Override
    public ProductDto convertProductToProductDto(Product Product) {
        return new ProductDto(Product.getId(), Product.getName(), Product.getPrice(), Product.getPicture(), Product.getDescription(),Product.getIdCategory());
    }

    @Override
    public Product convertProductDtoToProduct(ProductDto ProductDto) {
        return new Product(ProductDto.getId(), ProductDto.getName(), ProductDto.getPrice(), ProductDto.getPicture(), ProductDto.getDescription(),ProductDto.getIdCategory());

    }
}