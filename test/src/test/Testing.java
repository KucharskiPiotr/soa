package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "my-servlet", urlPatterns = "/")
public class Testing extends HttpServlet {

    private abstract class MatcherState {
        public abstract MatcherState
    }

    private boolean doesMatch(String s, String p) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String a = "b";
        out.println("<div id=\"app\">\n" +
                "  <ul v-for=\"product in products\">\n" +
                "    <li>\n" +
                "      {{ product.id }} - {{ product.name }}\n" +
                "      <button @click=\"product.id += 1\">Add</button>\n" +
                "      <button @click=\"if(product.id > 0) { product.id -= 1 }\">Delete</button>\n" +
                "      <span v-if=\"product.id === 0\"> - out of stock</span>\n" +
                "    </li>\n" +
                "  </ul>\n" +
                "  <h3>Total: {{ totalA }}</h3>\n" +
                "</div>\n" +
                "\n" +
                "<!-- development version, includes helpful console warnings -->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/vue/dist/vue.js\"></script>\n" +
                "<script>\n" +
                "  const app = new Vue({\n" +
                "    el: \"#app\",\n" +
                "    data: {\n" +
                "      products: [\n" +
                "        {\n" +
                "          id: 0,\n" +
                "          name: \"asd\"\n" +
                "        },\n" +
                "        {\n" +
                "          id: 2,\n" +
                "          name: \"qwe\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    computed: {\n" +
                "      totalA() {\n" +
                "        return this.products.reduce((sum, product) => {\n" +
                "          return sum + product.id;\n" +
                "        }, 0);\n" +
                "      }\n" +
                "    }\n" +
                "  });\n" +
                "</script>\n");
    }
}
