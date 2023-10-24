package com.example.projectawsdeploy.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomSecurityConfig {

    private static boolean isAuthenticated = false;

    @Bean
    public FilterRegistrationBean<CustomFilter> customFilter() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    public static class CustomFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String requestURI = request.getRequestURI();
            if ("/login".equals(requestURI) || "/register".equals(requestURI) || userIsAuthenticated()) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/login");
            }
        }
    }

    private static boolean userIsAuthenticated() {
        return isAuthenticated;
    }

    void setUserAuthenticated(boolean isAuthenticated) {
        CustomSecurityConfig.isAuthenticated = isAuthenticated;
    }
}
