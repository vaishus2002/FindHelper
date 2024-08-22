package com.app.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");
        logger.info("Request token: {}", requestToken);

        String username = null;
        String jwtToken = null;

        if (requestToken != null && requestToken.trim().startsWith("Bearer ")) {
            // Extract the actual token
            jwtToken = requestToken.substring(7);

            try {
                username = jwtHelper.getUsername(jwtToken);
                logger.info("Username from token: {}", username);

            } catch (ExpiredJwtException e) {
                logger.error("JWT token expired", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token", e);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT token", e);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Validate the token
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtHelper.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);

                } else {
                    logger.warn("Invalid JWT token");
                }

            } else {
                logger.info("Username is null or authentication already exists");
            }

        } else {
            logger.info("Token does not start with Bearer");
        }

        filterChain.doFilter(request, response);
    }
}
