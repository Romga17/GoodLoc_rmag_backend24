package edu.gdlc_project.gdlc_pckgs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtUtils {

    @Value("${secret.jwt}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        AppUserDetails appUserDetails = (AppUserDetails) userDetails;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return builder()
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

        return parser()
                .setSigningKey(secret)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }
}
