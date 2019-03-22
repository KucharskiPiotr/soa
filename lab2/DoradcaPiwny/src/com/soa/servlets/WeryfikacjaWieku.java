package com.soa.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(urlPatterns = {"verify"}, name = "WeryfikacjaWieku")
public class WeryfikacjaWieku extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer yearOfBirth = Integer.parseInt(request.getParameter("yearOfBirth").split("-")[0]);
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        HttpSession session = request.getSession();

        if (currentYear - yearOfBirth >= 18) {
            session.setAttribute("isAgeValid", "Y");
        }
        else {
            session.setAttribute("isAgeValid", "N");
        }
        response.sendRedirect((String) session.getAttribute("redirect"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(400);
    }
}
