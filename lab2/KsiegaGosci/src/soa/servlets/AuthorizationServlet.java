package soa.servlets;

import soa.models.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "Authorization", urlPatterns = {"authorize"})
public class AuthorizationServlet extends HttpServlet {

    private Vector<UserData> users;

    public AuthorizationServlet() {
        users = new Vector<>();
        users.add(new UserData("Jan", "Jan", "Jan", "Kowalski"));
        users.add(new UserData("Janina", "Janina", "Janina", "Kowalska"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.isEmpty()) {
            request.setAttribute("errorMsg", "Enter login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        if (password == null || password.isEmpty()) {
            request.setAttribute("errorMsg", "Enter password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        UserData user = users.stream().filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password)).findFirst().orElse(null);
        if (user != null) {
            request.getSession().setAttribute("isLogged", true);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("guestBook");
        }
        else {
            request.setAttribute("errorMsg", "Incorrect login/password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(400);
    }
}
