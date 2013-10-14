package az.neuron.ask.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
