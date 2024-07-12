package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur.UtilisateurServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurServiceImp utilisateurServiceImp;

    @GetMapping("/obtenir/{id}")
    public ResponseEntity<Utilisateur> getSingleUser(@PathVariable int id) {
        try {
            Utilisateur utilisateur = utilisateurServiceImp.getUserById(id);
            return ResponseEntity.ok(utilisateur);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(null);  // ou une réponse JSON avec un message d'erreur
        }

    }

    @GetMapping("/obtenir/liste")
    public List<Utilisateur> usersList(){
        return utilisateurServiceImp.getAllUsers();
    }


    @PostMapping("/ajouter")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur utilisateur){
        return utilisateurServiceImp.saveUtilisateur(utilisateur);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Utilisateur>  modifyUser(@PathVariable int id,@RequestBody Utilisateur utilisateur){
        return utilisateurServiceImp.userModification(id, utilisateur);
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

