package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Servlet order cart.
 */
@WebServlet(name = "ServletRemoveOrderCart", value = "/removeordercart")

public class ServletRemoveOrderCart extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ServletRemoveOrderCart.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession userSession = request.getSession();
            orderCart(request, response);

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
        HttpSession userSession = request.getSession();

        if (userSession.getAttribute("order") != null)
            userSession.removeAttribute("order");
        userSession.setAttribute("error", "Vous avez vidé la panier");
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

    }

}
