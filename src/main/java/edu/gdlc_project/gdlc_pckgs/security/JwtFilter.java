package edu.gdlc_project.gdlc_pckgs.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorization); // Log du header Authorization

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwt = authorization.substring(7);
            System.out.println("JWT Token: " + jwt); // Log du token JWT

            try {
                String subject = jwtUtils.getSubjectFromJwt(jwt);
                System.out.println("Subject from JWT: " + subject); // Log du sujet extrait du JWT

                if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = appUserDetailsService.loadUserByUsername(subject);
                    System.out.println("UserDetails loaded: " + userDetails.getUsername()); // Log de l'utilisateur chargé

                    if (jwtUtils.validateJwtToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        System.out.println("Authentication successful for user: " + userDetails.getUsername()); // Log de l'authentification réussie
                    } else {
                        System.out.println("JWT Token validation failed"); // Log en cas d'échec de la validation du JWT
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception during JWT processing: " + e.getMessage()); // Log de l'exception
            }
        } else {
            System.out.println("Authorization header is missing or does not start with Bearer"); // Log si le header Authorization est absent ou incorrect
        }

        filterChain.doFilter(request, response);
    }
}
