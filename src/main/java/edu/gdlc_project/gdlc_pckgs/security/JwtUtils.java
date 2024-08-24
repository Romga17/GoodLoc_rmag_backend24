package edu.gdlc_project.gdlc_pckgs.security;


import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtUtils {

    @Value("Rmag17K1e2y3goodLocfckblRmag17K1e2y3goodLoc")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        AppUserDetails appUserDetails = (AppUserDetails) userDetails;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return builder()

                .signWith(SignatureAlgorithm.HS256, secret)
                .addClaims(Map.of("id", appUserDetails.user.getId()))
                .addClaims(Map.of("userLastname", appUserDetails.user.getUserLastname()))
                .addClaims(Map.of("userFirstname", appUserDetails.user.getUserFirstname()))
                .addClaims(Map.of("userAddressNumber", appUserDetails.user.getUserAddressNumber()))
                .addClaims(Map.of("userRoadType", appUserDetails.user.getUserRoadType()))
                .addClaims(Map.of("userAddress", appUserDetails.user.getUserAddress()))
                .addClaims(Map.of("userZipCode", appUserDetails.user.getUserZipCode()))
                .addClaims(Map.of("userCity", appUserDetails.user.getUserCity()))
                .addClaims(Map.of("userPhone", appUserDetails.user.getUserPhone()))
                .addClaims(Map.of("userCourse", appUserDetails.user.getUserCourse()))
                .addClaims(Map.of("userArrivalDate", appUserDetails.user.getUserArrivalDate().format(formatter)))
                .addClaims(Map.of("userDepartureDate", appUserDetails.user.getUserDepartureDate().format(formatter)))
                .addClaims(Map.of("userRole", appUserDetails.user.getUserRole().getId()))
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {

        return parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken, UserDetails userDetails) {
        try {
            // Valider la signature et d'autres critères ici
            return true; // retourne true si le token est valide
        } catch (Exception e) {
            // Log l'erreur
            return false;
        }
    }
}
