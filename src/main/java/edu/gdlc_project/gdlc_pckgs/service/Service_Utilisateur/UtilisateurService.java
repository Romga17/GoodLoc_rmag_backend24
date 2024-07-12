package edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur;

import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UtilisateurService {
    public ResponseEntity<Utilisateur> saveUtilisateur(Utilisateur utilisateur);

    public List<Utilisateur> getAllUsers();

    public Utilisateur getUserById(int id);

    public ResponseEntity<String> deleteUserById(int id);

    public ResponseEntity<Utilisateur> userModification(int id, Utilisateur utilisateur);

    public ResponseEntity<Map<String, Object>> subscription(Utilisateur utilisateur);

    public ResponseEntity<Map<String, Object>> connection(Utilisateur utilisateur);
}