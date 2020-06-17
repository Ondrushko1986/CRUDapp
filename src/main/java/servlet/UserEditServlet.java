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

@WebServlet("/userEdit")
public class UserEditServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");

        if (paramId != null) {
            long id = Long.parseLong(paramId);
            User user = userService.findById(id)
                    .orElseThrow(IllegalArgumentException::new);

            request.setAttribute("user", user);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("userEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        userService.findById(id).ifPresent(user -> {
            user.setName(name);
            user.setPassword(password);
            userService.update(user);
        });

        response.sendRedirect("/userList");
    }
}
