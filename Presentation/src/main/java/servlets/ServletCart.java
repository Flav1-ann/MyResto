package servlets;

import eu.ensup.myresto.ProductDto;
import eu.ensup.myresto.ProductService;
import eu.ensup.myresto.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;


/**
 * The type Servlet cart.
 */
@WebServlet(name = "ServletPanier", value = "/cart")
public class ServletCart extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ServletCart.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession userSession = request.getSession();
        try {
            operations(request, response, userSession);
        } catch (ServletException | IOException e) {
            userSession.setAttribute("error", e.getMessage());
        }
    }

    /**
     * Operations.
     *
     * @param request     the request
     * @param response    the response
     * @param userSession the user session
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void operations(HttpServletRequest request, HttpServletResponse response, HttpSession userSession) throws ServletException, IOException {
        ProductService productService = new ProductService();
        Map<Integer, Integer> productsIds = (Map<Integer, Integer>) userSession.getAttribute("order");
        Float total = 0.0f;
        Set<ProductDto> productDtos = new HashSet<>();
        request.setAttribute("totalPrice", total);
        userSession.setAttribute("productSet", productDtos);
        if (productsIds != null)
            for (Map.Entry<Integer, Integer> entry : productsIds.entrySet()) {
                try {
                    ProductDto productDto = productService.getOneProduct(entry.getKey());
                    total += productDto.getPrice() * entry.getValue();
                    productDtos.add(productDto);
                } catch (ServiceException e) {
                    log.error(e.getMessage());
                }
            }
        request.setAttribute("totalPrice", total);
        userSession.setAttribute("productSet", productDtos);
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }

}
