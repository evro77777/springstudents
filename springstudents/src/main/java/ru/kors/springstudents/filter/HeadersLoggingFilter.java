package ru.kors.springstudents.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;


@Component
@Slf4j
@Order(0)
public class HeadersLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Collections.list(httpRequest.getHeaderNames())
                .forEach(header -> {
                    log.info("Header: {} ={}", header, httpRequest.getHeader(header));
                });
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
