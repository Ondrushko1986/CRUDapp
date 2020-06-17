package servlet;


import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/userAdd")
public class UserAddServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(("/userAdd.jsp"));
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

    private Optional<User> transformUserFromRequest(HttpServletRequest req) {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean success = login != null && name != null && password != null;

        return success ? Optional.of(new User(login,name,password)) : Optional.empty();

    }
}

