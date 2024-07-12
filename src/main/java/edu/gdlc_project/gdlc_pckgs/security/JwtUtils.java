package edu.gdlc_project.gdlc_pckgs.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class JwtUtils {

    @Value("${secret.jwt}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        AppUserDetails appUserDetails = (AppUserDetails) userDetails;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .addClaims(Map.of("id", appUserDetails.utilisateur.getId()))
                .addClaims(Map.of("nom", appUserDetails.utilisateur.getNom()))
                .addClaims(Map.of("prenom", appUserDetails.utilisateur.getPrenom()))
                .addClaims(Map.of("numeroadresse", appUserDetails.utilisateur.getNumeroAdresse()))
                .addClaims(Map.of("typevoie", appUserDetails.utilisateur.getTypeVoie()))
                .addClaims(Map.of("adresse", appUserDetails.utilisateur.getAdresse()))
                .addClaims(Map.of("codepostal", appUserDetails.utilisateur.getCodePostal()))
                .addClaims(Map.of("ville", appUserDetails.utilisateur.getVille()))
                .addClaims(Map.of("telephone", appUserDetails.utilisateur.getTelephone()))
                .addClaims(Map.of("cursus", appUserDetails.utilisateur.getCursus()))
                .addClaims(Map.of("dateentree", appUserDetails.utilisateur.getDateEntree().format(formatter)))
                .addClaims(Map.of("datesortie", appUserDetails.utilisateur.getDateSortie().format(formatter)))
                .addClaims(Map.of("role", appUserDetails.utilisateur.getUserRole().getId()))
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
