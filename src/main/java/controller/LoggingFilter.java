package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        long startTime = System.currentTimeMillis();
        LOGGER.debug("Request URL: {}", req.getRequestURL());
        chain.doFilter(request, response);
        long finishTime = System.currentTimeMillis();
        LOGGER.debug("Request was completed in {}ms", finishTime - startTime);
    }

    @Override
    public void destroy() {
        //NOP
    }
}
