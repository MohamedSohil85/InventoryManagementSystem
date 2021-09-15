package com.mohamed.inventorymanagementsystem.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    private static final Logger LOG=LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        LOG.info("Request Information {}: {}: {}: {}:",
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRequestURL(),
                httpServletRequest.getServerPort());
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        LOG.info("Response Information {}: {}:",httpServletResponse.getContentType(),httpServletResponse.getStatus());
    }
}
