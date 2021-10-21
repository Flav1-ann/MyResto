package servlets;

import eu.ensup.myresto.RegisterUserDto;
import eu.ensup.myresto.UserService;
import eu.ensup.myresto.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Servlet register.
 */
@WebServlet(name = "ServletRegister", value = "/register")
public class ServletRegister extends HttpServlet {
    private static final String ERROR_ARG = "error";
    private static final String PATH_REGISTER_ARG = "register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        try {
            operations(request, response);
        } catch (ServletException | IOException e) {
            request.setAttribute(ERROR_ARG, e.getMessage());
        }

    }

    /**
     * Operations.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void operations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        if (request.getParameter("login") != null) {
            RegisterUserDto user = new RegisterUserDto(0, request.getParameter("login"), request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("address"), null, null, "Client", null);
            String password1 = request.getParameter("password[1]");
            String password2 = request.getParameter("password[2]");
            if (password1.equals(password2)) {
                user.setPassword(password1);
                try {
                    if (userService.create(user) == 1) {
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                        request.setAttribute(ERROR_ARG, "L'inscription a pas été réalisée");
                    } else {
                        request.setAttribute(ERROR_ARG, "L'inscription n'a pas été réalisée");
                        request.getRequestDispatcher(PATH_REGISTER_ARG).forward(request, response);
                    }
                } catch (ServiceException e) {
                    request.setAttribute(ERROR_ARG, e.getMessage());
                    request.getRequestDispatcher(PATH_REGISTER_ARG).forward(request, response);
                }
            } else {
                request.setAttribute(ERROR_ARG, "Les mots de passes ne correspondent pas.");
                request.getRequestDispatcher(PATH_REGISTER_ARG).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(PATH_REGISTER_ARG).forward(request, response);
        }
    }
}
