package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * The type Servlet add to order.
 */
@WebServlet(name = "ServletAddToOrder", value = "/addToOrder")
public class ServletAddToOrder extends HttpServlet {

    private static final String ORDER_ARG = "order";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession userSession = request.getSession();
        try {
            operations(request, response, userSession);
        } catch (IOException e) {
            userSession.setAttribute("error", e.getMessage());
        }
    }

    /**
     * Operations.
     *
     * @param request     the request
     * @param response    the response
     * @param userSession the user session
     * @throws IOException the io exception
     */
    protected void operations(HttpServletRequest request, HttpServletResponse response, HttpSession userSession) throws IOException {

        HashMap<Integer, Integer> productMap = new HashMap<>();
        int productId = Integer.parseInt(request.getParameter("productId"));

        if (userSession.getAttribute(ORDER_ARG) == null) {
            productMap.put(productId, 1);
        } else {
            productMap = (HashMap<Integer, Integer>) userSession.getAttribute(ORDER_ARG);
            productMap.merge(productId, 1, Integer::sum);
        }
        userSession.setAttribute(ORDER_ARG, productMap);
        response.sendRedirect(request.getContextPath() + "/menu");

    }


}
