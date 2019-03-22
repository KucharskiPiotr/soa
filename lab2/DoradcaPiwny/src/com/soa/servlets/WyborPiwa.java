package com.soa.servlets;

import com.soa.BeerColorNotSupported;
import com.soa.models.EkspertPiwny;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = {"wybor"}, name = "wybor")
public class WyborPiwa extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(400);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String colorParam = request.getParameter("kolor");
        String suggestedBeerName = null;
        PrintWriter writer = response.getWriter();
        if (colorParam != null) {
            for (EkspertPiwny.Kolory color : EkspertPiwny.Kolory.values()) {
                if (color.getKolor().equals(colorParam)) {
                    suggestedBeerName = EkspertPiwny.suggestBeer(color);
                    writer.print("Sugerujemy " + suggestedBeerName + " dla koloru " + colorParam);
                    break;
                }
            }
            if (suggestedBeerName == null) {
                writer.print("Nie znam tego koloru!");
            }
        }
        else {
            writer.print("Nie znam tego koloru!");
        }
        writer.close();
    }
}
