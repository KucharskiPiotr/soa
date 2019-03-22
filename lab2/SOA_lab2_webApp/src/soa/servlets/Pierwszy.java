package soa.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("abc")
public class Pierwszy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("imie");
        Integer age = Integer.parseInt(request.getParameter("wiek"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>\n");
        stringBuilder.append("<head><title>Odpowiedz na formularz</title></head>\n");
        stringBuilder.append("<body>\n");
        stringBuilder.append("<p>Witaj ").append(name).append(", masz ").append(age).append(" lat!</p></body></html>");
        PrintWriter out = response.getWriter();
        out.print(stringBuilder.toString());
        out.close();
    }
}
