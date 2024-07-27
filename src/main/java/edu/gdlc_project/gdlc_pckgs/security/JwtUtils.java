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
                .addClaims(Map.of("id", appUserDetails.user.getId()))
                .addClaims(Map.of("nom", appUserDetails.user.getNom()))
                .addClaims(Map.of("prenom", appUserDetails.user.getPrenom()))
                .addClaims(Map.of("numeroadresse", appUserDetails.user.getNumeroAdresse()))
                .addClaims(Map.of("typevoie", appUserDetails.user.getTypeVoie()))
                .addClaims(Map.of("adresse", appUserDetails.user.getAdresse()))
                .addClaims(Map.of("codepostal", appUserDetails.user.getCodePostal()))
                .addClaims(Map.of("ville", appUserDetails.user.getVille()))
                .addClaims(Map.of("telephone", appUserDetails.user.getTelephone()))
                .addClaims(Map.of("cursus", appUserDetails.user.getCursus()))
                .addClaims(Map.of("dateentree", appUserDetails.user.getDateEntree().format(formatter)))
                .addClaims(Map.of("datesortie", appUserDetails.user.getDateSortie().format(formatter)))
                .addClaims(Map.of("role", appUserDetails.user.getUserRole().getId()))
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {

        /*return parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }*/
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }
}
