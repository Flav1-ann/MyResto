package servlets;

import eu.ensup.myresto.CategoryService;
import eu.ensup.myresto.ProductDto;
import eu.ensup.myresto.ProductService;
import eu.ensup.myresto.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * The type Servlet menu.
 */
@WebServlet(name = "ServletMenu", value = "/menu")
public class ServletMenu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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

        ProductService productService = new ProductService();
        try {
            List<ProductDto> listBoisson = new LinkedList<>();
            List<ProductDto> listEntree = new LinkedList<>();
            List<ProductDto> listPlat = new LinkedList<>();
            List<ProductDto> listDessert = new LinkedList<>();
            List<ProductDto> products = new ArrayList<>(productService.getAllProducts());
            products.sort(Comparator.comparing(ProductDto::getName));
            for (ProductDto produc : products) {
                switch (new CategoryService().getById(produc.getIdCategory()).getName()) {
                    case ("boisson"):
                        listBoisson.add(produc);
                        break;
                    case ("entree"):
                        listEntree.add(produc);
                        break;
                    case ("plat"):
                        listPlat.add(produc);
                        break;
                    case ("dessert"):
                        listDessert.add(produc);
                        break;
                    default:
                        break;
                }
            }
            userSession.setAttribute("listBoisson", listBoisson);
            userSession.setAttribute("listEntree", listEntree);
            userSession.setAttribute("listDessert", listDessert);
            userSession.setAttribute("listPlat", listPlat);
            userSession.setAttribute("listProducts", products);
            this.getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
        } catch (ServiceException e) {
            userSession.setAttribute("error", e.getMessageViewForUser());
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }
}

