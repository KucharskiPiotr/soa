package com.soa.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "PelnoletniFilter", urlPatterns = {"/form.html", "wybor"})
public class PelnoletniFilter implements Filter {

    private static final String AGE_VERIFICATION_PAGE = "ageVerification.html";
    private static final String UNDERAGE_PAGE = "underage.html";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        String ageParam = (String) session.getAttribute("isAgeValid");
        session.setAttribute("redirect", "form.html");
        if (ageParam != null && !ageParam.isEmpty()) {
            if (ageParam.equals("Y")) {
                chain.doFilter(req, resp);
            }
            else {
                ((HttpServletResponse) resp).sendRedirect(UNDERAGE_PAGE);
            }
        }
        else {
            ((HttpServletResponse) resp).sendRedirect(AGE_VERIFICATION_PAGE);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
