package ru.kors.springstudents.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PasswordLengthFilter extends OncePerRequestFilter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AntPathRequestMatcher registerRequestMatcher = new AntPathRequestMatcher(
            "/api/v1/users/registration",
            "POST"
    );


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CachedHttpServletRequest cachingRequest = new CachedHttpServletRequest(request);
        if (registerRequestMatcher.matches(cachingRequest)) {
            Map<String, String> requestBody = objectMapper.readValue(cachingRequest.getInputStream(), Map.class);
            String password = requestBody.get("password");
            System.out.println("password=" + password);
            if (password == null || password.length() < 8) {
                System.out.println("check pass==" + password);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");

                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Password must be at least 8 characters long.");

                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                return;
            }

        }
        filterChain.doFilter(cachingRequest, response);
    }


}
