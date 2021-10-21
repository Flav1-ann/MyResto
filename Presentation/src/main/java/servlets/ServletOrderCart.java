package servlets;

import eu.ensup.myresto.*;
import eu.ensup.myresto.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Servlet order cart.
 */
@WebServlet(name = "ServletOrderCart", value = "/ordercart")

public class ServletOrderCart extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ServletOrderCart.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession userSession = request.getSession();

        try {
            orderCart(request, response);
        } catch (ServletException | IOException e) {
            request.setAttribute("error", e.getMessage());
        }
    }

    /**
     * Order cart.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void orderCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderProductService orderProductService = new OrderProductService();
        HttpSession userSession = request.getSession();
        Map<Integer, Integer> productsIds = (Map<Integer, Integer>) userSession.getAttribute("order");
        List<Integer> idList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productsIds.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                idList.add(entry.getKey());
            }
        }
        UserDto userDto = (UserDto) userSession.getAttribute("user");
        OrderProductDto orderProductDto = new OrderProductDto(userDto.getId(), idList, new Date(System.currentTimeMillis()));
        try {
            orderProductService.createOrderProduct(orderProductDto);
            request.setAttribute("info", "La commande a été crée");
            userSession.removeAttribute("order");
            response.sendRedirect(request.getContextPath() + "/orders");
        } catch (ServiceException e) {
            userSession.setAttribute("error", e.getMessageViewForUser());
            log.error(e.getMessage());
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

    }

}
