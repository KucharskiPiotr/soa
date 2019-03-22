package soa.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("Numbers")
public class Numbers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Collection<String[]> parameterValues = request.getParameterMap().values();
        try {
            List<Double> numbers = generateValuesListFromParameters(parameterValues);
            numbers.sort(Double::compare);
            generateResponse(out, numbers);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            out.println("Invalid parameter value");
        }
    }

    private void generateResponse(PrintWriter out, List<Double> numbers) {
        out.print("Sorted numbers given: ");
        for (Double number : numbers) {
            out.print(number);
            if (numbers.indexOf(number) != numbers.size() - 1) {
                out.print(", ");
            }
        }
    }

    private List<Double> generateValuesListFromParameters(Collection<String[]> parameterValues) throws NumberFormatException {
        List<Double> numbers = new ArrayList<>();
        for (String[] values : parameterValues) {
            for (String value : values) {
                Double number = Double.parseDouble(value);
                numbers.add(number);
            }
        }
        return numbers;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Double> numbers = new ArrayList<>();
        Arrays.asList(request.getParameterValues("number")).forEach(n -> numbers.add(Double.parseDouble(n)));
        Double avg = calculateAverage(numbers);
        response.getWriter().println("Average value of numbers is: " + avg.toString());
    }

    private Double calculateAverage(List<Double> numbers) {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        numbers.forEach(n -> sum.getAndSet(sum.get() + n));
        return (sum.get() / numbers.size());
    }
}
