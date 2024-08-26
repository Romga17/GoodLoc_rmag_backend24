package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.service.User_Service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin(origins = {"http://localhost:4200", "http://185.97.144.183:8082"})
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserServiceImp userServiceImp;


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> userSubscription (@Valid @RequestBody User user){
        return userServiceImp.subscription(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String,Object>> userConnection(@RequestBody User user) {
        return userServiceImp.connection(user);
    }
    /*@GetMapping("/profil")
    public ResponseEntity<Utilisateur> getUserprofil(@AuthenticationPrincipal AppUserDetails userDetails){

        Utilisateur user = userDetails.getUtilisateur();
        //user.setListeCommandes(null);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }*/

}
