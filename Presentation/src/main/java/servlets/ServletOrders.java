package servlets;

import eu.ensup.myresto.*;
import eu.ensup.myresto.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The type Servlet orders.
 */
@WebServlet(name = "ServletOrder", value = "/orders")
public class ServletOrders extends HttpServlet {
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
        var orderProductService = new OrderProductService();
        try {
            if (request.getSession().getAttribute("user") != null) {
                var orders = orderProductService.getAllOrderProductsForOneUser(((UserDto) request.getSession().getAttribute("user")).getId()).stream().collect(Collectors.toList());
                Collections.sort(orders, Comparator.comparing(OrderProductDto::getId));
                userSession.setAttribute("listOrders", orders);
                this.getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
            } else
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

        } catch (ServiceException e) {
            userSession.setAttribute("error", e.getMessageViewForUser());
            this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        }

    }
}