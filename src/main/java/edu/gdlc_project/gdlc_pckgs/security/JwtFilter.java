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

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwt = authorization.substring(7);
            try {
                String subject = jwtUtils.getSubjectFromJwt(jwt);

                if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = appUserDetailsService.loadUserByUsername(subject);

                    if (jwtUtils.validateJwtToken(jwt, userDetails)) { // Assurez-vous que la validation du JWT est complète
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                // Log l'erreur et passer à la requête suivante, sans interrompre le traitement.
                System.out.println("Impossible de définir l'authentification utilisateur : " + e.getMessage());
                // Optionnel : Vous pouvez renvoyer une réponse 401 si nécessaire
                // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
