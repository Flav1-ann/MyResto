import exceptions.DaoException;
import exceptions.ServiceException;
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

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    IProductDao productDao;

    @InjectMocks
    ProductService productService;

    @Test
    public void ProductCreateTest(){
        try{
            ProductDto productDto = new ProductDto("CocaService", 3.5f, "", "C'est du coca");
            when(productDao.createProduct(any(Product.class))).thenReturn(0);
            MatcherAssert.assertThat(productService.createProduct(productDto), equalTo(0));
            verify(productDao).createProduct(any(Product.class));
        } catch (ServiceException | DaoException e) {
            //TODO
        }
    }
    @Test
    public void ProductgetAllTest(){
        try{
            Set<Product> products = new HashSet<>();
            for(int i =0; i<3;i++)
                products.add(new Product(i,"CocaService"+i, 3.5f, "", "C'est du coca"+i));

            when(productDao.getAllProducts()).thenReturn(products);
            MatcherAssert.assertThat(productService.getAllProducts().size(), equalTo(3));
            verify(productDao).getAllProducts();
        } catch (ServiceException | DaoException e) {
            //TODO
        }
    }
    @Test
    public void ProductDeleteTest(){

        try{
            when(productService.deleteProduct(1)).thenReturn(1);
            MatcherAssert.assertThat(productService.deleteProduct(1), equalTo(1));
            verify(productDao).deleteProduct(1);
        } catch (ServiceException | DaoException e) {
            //TODO
        }
    }

    @Test
    public void ProductGetOneTest(){

        try{
            var productNotMock = new ProductDto(1,"CocaService", 3.5f, "", "C'est du coca") ;
            when(productDao.getOneProduct(1)).thenReturn(productService.convertProductDtoToProduct(productNotMock));
            var productMock = productService.getOneProduct(1);
            MatcherAssert.assertThat(productMock.getName(), equalTo(productNotMock.getName()));
            verify(productDao).getOneProduct(1);
        } catch (ServiceException | DaoException e) {
            //TODO
        }
    }

    @Test
    public void ProductUpdateTest(){
        try{
            when(productDao.updateProduct(any(Product.class))).thenReturn(0);
            MatcherAssert.assertThat(productService.updateProduct(new Product("CocaService", 3.5f, "", "C'est du coca")), equalTo(0));
            verify(productDao).updateProduct(any(Product.class));
        } catch (ServiceException | DaoException e) {
            //TODO
        }
    }

}
