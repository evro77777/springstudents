package ru.kors.springstudents.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class MyCustomHeaderFilter extends OncePerRequestFilter implements Ordered {

    private final ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request){
          final String myCustomHeaderName = "Custom-Header";
          final String myCustomHeaderValue="FooBar";

          @Override
          public Enumeration<String> getHeaderNames(){
              List<String> headerNames = Collections.list(super.getHeaderNames());
              headerNames.add(myCustomHeaderName);
              return Collections.enumeration(headerNames);

          }

          @Override
          public Enumeration<String> getHeaders(String name){
              if (name.equals(myCustomHeaderName)){
                  return Collections.enumeration(Collections.singletonList(myCustomHeaderValue));
              }
              return super.getHeaders(name);
          }

          public String getHeader(String name){
              if (name.equals(myCustomHeaderName)){
                  return myCustomHeaderValue;
              }
              return super.getHeader(name);
          }


        };

        filterChain.doFilter(wrappedRequest, response);


    }

    @Override
    public int getOrder() {
        if(applicationContext.containsBean("headersLoggingFilter")){
            return -1;
        }
        return LOWEST_PRECEDENCE;

    }
}
