package soa.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "IsLoggedFilter", servletNames = "GuestBook")
public class IsLoggedFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Boolean isLogged = (Boolean) ((HttpServletRequest) req).getSession().getAttribute("isLogged");
        if (isLogged != null) {
            chain.doFilter(req, resp);
        }
        else {
            req.setAttribute("errorMsg", "User is not logged in");
            ((HttpServletResponse) resp).sendRedirect("login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
