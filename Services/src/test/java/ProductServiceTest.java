import eu.ensup.myresto.IProductDao;
import eu.ensup.myresto.Product;
import eu.ensup.myresto.ProductDto;
import eu.ensup.myresto.exceptions.DaoException;
import eu.ensup.myresto.ProductService;
import eu.ensup.myresto.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * The type Product service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private static final Logger log = LogManager.getLogger(ProductServiceTest.class);

    /**
     * The Product dao.
     */
    @Mock
    IProductDao productDao;

    /**
     * The Product service.
     */
    @InjectMocks
    ProductService productService;

    /**
     * Product create test.
     */
    @Test
    public void ProductCreateTest(){
        try{
            ProductDto productDto = new ProductDto("CocaService", 3.5f, "", "C'est du coca",0);
            when(productDao.createProduct(any(Product.class))).thenReturn(0);
            MatcherAssert.assertThat(productService.createProduct(productDto), equalTo(0));
            verify(productDao).createProduct(any(Product.class));
        } catch (ServiceException | DaoException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Productget all test.
     */
    @Test
    public void ProductgetAllTest(){
        try{
            Set<Product> products = new HashSet<>();
            for(int i =0; i<3;i++)
                products.add(new Product(i,"CocaService"+i, 3.5f, "", "C'est du coca"+i,0));

            when(productDao.getAllProducts()).thenReturn(products);
            MatcherAssert.assertThat(productService.getAllProducts().size(), equalTo(3));
            verify(productDao).getAllProducts();
        } catch (ServiceException | DaoException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Product delete test.
     */
    @Test
    public void ProductDeleteTest(){

        try{
            when(productDao.deleteProduct(1)).thenReturn(1);
            MatcherAssert.assertThat(productService.deleteProduct(1), equalTo(1));
            verify(productDao).deleteProduct(1);
        } catch (ServiceException | DaoException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Product get one test.
     */
    @Test
    public void ProductGetOneTest(){

        try{
            ProductDto productNotMock = new ProductDto(1,"CocaService", 3.5f, "", "C'est du coca",0) ;
            when(productDao.getOneProduct(1)).thenReturn(productService.convertProductDtoToProduct(productNotMock));
            ProductDto productMock = productService.getOneProduct(1);
            MatcherAssert.assertThat(productMock.getName(), equalTo(productNotMock.getName()));
            verify(productDao).getOneProduct(1);
        } catch (ServiceException | DaoException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Product update test.
     */
    @Test
    public void ProductUpdateTest(){
        try{
            when(productDao.updateProduct(any(Product.class))).thenReturn(0);
            MatcherAssert.assertThat(productService.updateProduct(new Product("CocaService", 3.5f, "", "C'est du coca",0)), equalTo(0));
            verify(productDao).updateProduct(any(Product.class));
        } catch (ServiceException | DaoException e) {
            log.error(e.getMessage());
        }
    }

}
