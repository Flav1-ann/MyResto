package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * The type Servlet add product to cart.
 */
@WebServlet(name = "ServletAddProductToCart", value = "/addproductcart")
public class ServletAddProductToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession userSession = request.getSession();
        try {
            addProductToCart(request, response);
        } catch (IOException e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    /**
     * Add product to cart.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession userSession = request.getSession();
        Map<Integer, Integer> productsOrder = (Map<Integer, Integer>) userSession.getAttribute("order");
        int productId = Integer.parseInt(request.getParameter("id"));
        productsOrder.put(productId, productsOrder.get(productId) + 1);
        response.sendRedirect(request.getContextPath() + "/cart");
    }


}
