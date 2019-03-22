package soa.servlets;

import soa.models.FeedbackData;
import soa.models.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "GuestBook", urlPatterns = "guestBook")
public class GuestBookServlet extends HttpServlet {

    private Vector<FeedbackData> feedbacks = new Vector<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserData user = (UserData) request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        if (!email.matches(".*@.*")) {
            request.setAttribute("errorMsg", "Email address is invalid");
            doGet(request, response);
            return;
        }
        String comment = request.getParameter("comment");
        FeedbackData newFeedback = new FeedbackData(user, email, comment);
        feedbacks.add(newFeedback);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserData user = (UserData) request.getSession().getAttribute("user");
        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("feedbacks", feedbacks);
        request.getRequestDispatcher("WEB-INF/jsp/guestBook.jsp").forward(request, response);
    }
}
