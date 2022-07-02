package com.basicauth.infrastructure.filter;

import com.basicauth.service.LoginService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.StringTokenizer;

/**
 * The type Basic auth filter.
 */
@Component
public class BasicAuthFilter implements Filter {

    /**
     * The Service.
     */
    @Autowired
    private LoginService service;

    /**
     * Init.
     *
     * @param filterConfig the filter config
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Do filter.
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @param filterChain     the filter chain
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().contains("user/login")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                StringTokenizer st = new StringTokenizer(authHeader);
                if (st.hasMoreTokens()) {
                    String basic = st.nextToken();
                    if (basic.equalsIgnoreCase("Basic")) {
                        try {
                            String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
                            int p = credentials.indexOf(":");
                            if (p != -1) {
                                String username = credentials.substring(0, p).trim();
                                String password = credentials.substring(p + 1).trim();
                                if (!service.checkAuthentication(username,password)) {
                                    errorResponse(response, "Invalid Credentials");
                                }
                                filterChain.doFilter(servletRequest, servletResponse);
                            } else {
                                errorResponse(response, "Invalid authentication");
                            }
                        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                            throw new Error("Exception in the process", e);
                        }
                    }
                }
            } else {
                errorResponse(response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * Unauthorized.
     *
     * @param response the response
     * @param message  the message
     * @throws IOException the io exception
     */
    private void errorResponse(HttpServletResponse response, String message) throws IOException {
        response.sendError(401, message);
    }

    /**
     * Unauthorized.
     *
     * @param response the response
     * @throws IOException the io exception
     */
    private void errorResponse(HttpServletResponse response) throws IOException {
        errorResponse(response, "Unauthorized");
    }

}
