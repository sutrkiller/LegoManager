package cz.muni.fi.pa165.lego.mvc.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Filter for adding year to the bottom of the pages.
 *
 * @author Sona Mastrakova <sona.mastrakova@gmail.com>
 * @date 18.12.2015
 */
@WebFilter("/*")
public class CurrentYearFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        request.setAttribute("currentyear", new SimpleDateFormat("yyyy", request.getLocale()).format(new Date()));
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
