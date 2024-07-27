package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.service.User_Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UserController {

    @Autowired
    private UserServiceImp utilisateurServiceImp;

    @GetMapping("/obtenir/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable int id) {
        try {
            User user = utilisateurServiceImp.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(null);  // ou une réponse JSON avec un message d'erreur
        }

    }

    @GetMapping("/obtenir/liste")
    public List<User> usersList(){
        return utilisateurServiceImp.getAllUsers();
    }


    @PostMapping("/ajouter")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return utilisateurServiceImp.saveUtilisateur(user);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<User>  modifyUser(@PathVariable int id, @RequestBody User user){
        return utilisateurServiceImp.userModification(id, user);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        //modif du retour ResponseEntity<Utilisateur> pour le type String
        return utilisateurServiceImp.deleteUserById(id);
    }
}

    /*@GetMapping("/profil")
    @IsUser
    public ResponseEntity<?> getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getDetails();
        Utilisateur user = utilisateurService.;
        return ResponseEntity.ok(user);
    }*/

