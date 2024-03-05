package com.app.cryptocurrencyapp.filter;

//Import jakarta.servlet packages
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.util.Collections;

/**
 * This class is a filter for validating JWT tokens and handling CORS issues.
 */
@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Value("${app.jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        System.out.println("RequestURI::" + requestURI);     
        // Exclude registerUser and login requests from token validation
        if (requestURI.endsWith("/register") || requestURI.endsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }
        for (String header : Collections.list(httpRequest.getHeaderNames())) {
            System.out.println(header + " : " + httpRequest.getHeader(header));
        }
        // Get the token from the request
        String token = httpRequest.getHeader("Authorization");
        System.out.println("Token::" + token);
        System.out.println(httpRequest.getHeaderNames());
        // Write a logic to validate the token and if token is valid then only pass on the request to controller
        if (token == null || !token.startsWith("Bearer ")) {
            // Token is missing or invalid, return an error response or perform any other action
            // For example, you can return a 401 Unauthorized status code
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing or invalid");
            return;
        } else {
            // Extract the token from the Authorization header
            token = token.substring(7);
            try {
                // Parse the token and extract the claims
                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
                
                //Now check the validity of the token. Token should not be expired. And subject should not be empty or null. Then only pass on the request to controller
                //Check if the token is expired
                if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
                    // Token is expired, return an error response or perform any other action
                    // For example, you can return a 401 Unauthorized status code
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
                    return;
                }else if (claims.getSubject() == null || claims.getSubject().isEmpty()) {
                    // Token is invalid, return an error response or perform any other action
                    // For example, you can return a 401 Unauthorized status code
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                    return;
                }else {
                    // If token is valid, allow the request to pass through the filter chain
                    chain.doFilter(request, response);
                }
            } catch (SignatureException e) {
                // Token is invalid, return an error response or perform any other action
                // For example, you can return a 401 Unauthorized status code
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
    }

    //Override method for async support
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    //Override method for async support
    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }

    //Add Initialization code here
    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
    }
}
