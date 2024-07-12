package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import edu.gdlc_project.gdlc_pckgs.security.AppUserDetails;
import edu.gdlc_project.gdlc_pckgs.security.JwtUtils;
import edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur.UtilisateurServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UtilisateurServiceImp utilisateurServiceImp;


    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> userInscription (@Valid @RequestBody Utilisateur utilisateur){
        return utilisateurServiceImp.subscription(utilisateur);
    }

    @PostMapping("/connexion")
    public ResponseEntity<Map<String,Object>> userConnection(@RequestBody Utilisateur utilisateur) {
        return utilisateurServiceImp.connection(utilisateur);
    }

    /*@GetMapping("/profil")
    public ResponseEntity<Utilisateur> getUserprofil(@AuthenticationPrincipal AppUserDetails userDetails){

        Utilisateur user = userDetails.getUtilisateur();
        //user.setListeCommandes(null);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }*/

}
