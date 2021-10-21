package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * The type Servlet remove product from cart.
 */
@WebServlet(name = "ServlerRemoveProductCart", value = "/removeproductcart")
public class ServletRemoveProductFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession userSession = request.getSession();
        removeProductFromCart(request, response,userSession);

    }

    /**
     * Remove product from cart.
     *
     * @param request     the request
     * @param response    the response
     * @param userSession the user session
     * @throws IOException the io exception
     */
    protected void removeProductFromCart(HttpServletRequest request, HttpServletResponse response ,HttpSession userSession) throws IOException {
        Map<Integer, Integer> productsOrder = (Map<Integer, Integer>) userSession.getAttribute("order");
        int productId = Integer.parseInt(request.getParameter("id"));
        if (productsOrder.get(productId) > 1) {
            productsOrder.put(productId, productsOrder.get(productId) - 1);
        } else {
            productsOrder.remove(productId);
        }
        response.sendRedirect(request.getContextPath() + "/cart");

    }


}
