package servlets;

import eu.ensup.myresto.OrderProductDto;
import eu.ensup.myresto.OrderProductService;
import eu.ensup.myresto.UserDto;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Servlet orders.
 */
@WebServlet(name = "ServletupdateOrder", value = "/updateOrder")
public class ServletUpdateOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        operations(request, response, userSession);

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
        OrderProductService orderProductService = new OrderProductService();
        try {
            UserDto user = ((UserDto) request.getSession().getAttribute("user"));
            if (user != null && user.getRole().equals("ADMIN")) {
                String dropPicker = request.getParameter("drop");
                if (dropPicker != null && !dropPicker.split(",")[0].equals("----")) {
                    String[] values = dropPicker.split(",");
                    orderProductService.updateOrderProductById(Integer.parseInt(values[1]), values[0]);
                }


                List<OrderProductDto> orders = orderProductService.getAllOrderProduct().stream().collect(Collectors.toList());
                Collections.sort(orders, Comparator.comparing(OrderProductDto::getId));
                request.setAttribute("info", "Vous avez actualisé une commande");

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
