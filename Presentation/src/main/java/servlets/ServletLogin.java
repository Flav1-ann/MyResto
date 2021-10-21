package servlets;

import eu.ensup.myresto.LoginUserDto;
import eu.ensup.myresto.UserDto;
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
 * The type Servlet login.
 */
@WebServlet(name = "ServletLogin", value = "/login")
public class ServletLogin extends HttpServlet {

    private static final String ERROR_ATTR = "error";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
      doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        HttpSession userSession = request.getSession();
        try {
            operations(request, response,userSession);
        } catch (ServletException | IOException e) {
            userSession.setAttribute(ERROR_ATTR, e.getMessage());
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
    protected void operations(HttpServletRequest request, HttpServletResponse response,HttpSession userSession) throws ServletException, IOException {
        if (request.getParameter("login") != null) {
            LoginUserDto loginUserDto = new LoginUserDto(request.getParameter("login"), request.getParameter("password"));
            UserService userService = new UserService();
            try {
                userSession.removeAttribute("user");
                UserDto user = userService.validateUser(loginUserDto);
                userSession.setAttribute("user", user);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } catch (ServiceException e) {
                request.setAttribute(ERROR_ATTR, e.getMessage());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

}
