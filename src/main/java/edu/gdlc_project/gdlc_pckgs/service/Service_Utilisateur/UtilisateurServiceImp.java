package edu.gdlc_project.gdlc_pckgs.service.Service_Utilisateur;

import edu.gdlc_project.gdlc_pckgs.exception.EmailExisteDejaException;
import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import edu.gdlc_project.gdlc_pckgs.security.JwtUtils;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurServiceImp implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BCryptPasswordEncoder getBCryptPasswordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailServiceImp emailServiceImp;

    public ResponseEntity<Utilisateur> saveUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getUserById(int id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (utilisateurOptional.isPresent()) {
            return utilisateurOptional.get();
        } else {
            // Gérer le cas où aucun utilisateur n'est trouvé, par exemple :
            throw new NotFoundException("Utilisateur non trouvé avec l'ID : " + id);
        }
    }


    @Override
    public ResponseEntity<String> deleteUserById(int id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurRepository.deleteById(id);

        return new ResponseEntity<>("L'utilisateur a été supprimé",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Utilisateur> userModification(int id, Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(utilisateur.getId());

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Etait placé avant optional avant/ A corriger si erreur
        utilisateur.setId(id);
        utilisateur.setNom(utilisateur.getNom());
        utilisateur.setPrenom(utilisateur.getPrenom());
        utilisateur.setEmail(utilisateur.getEmail());

        utilisateurRepository.save(utilisateur);

            return new ResponseEntity<>(utilisateurOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> subscription(Utilisateur utilisateur) {

        if (!utilisateur.getEmail().contains("@")) {
            throw new RuntimeException("Email non conforme");
        }
        if (!utilisateur.getEmail().contains(".")) {
            throw new RuntimeException("Email non conforme");
        }

        Role roleUtilisateur = new Role(); // par exemple, en utilisant l'identifiant du rôle
        // Rechercher le rôle dans la base de données pour s'assurer qu'il existe
       Role roleExist = roleRepository.findById(roleUtilisateur.getId()).orElse(null);

        if (roleExist == null) {
            // Gérer le cas où le rôle n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le rôle n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le rôle récupéré à l'utilisateur
        utilisateur.setUserRole(roleExist);

        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new EmailExisteDejaException("Cet email est déjà utilisé.");
        }

        utilisateur.setMotDePasse(getBCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));

        //emailServiceImp.sendEmail(utilisateur.getEmail(), "Confirmation inscription", "Bonjour " + utilisateur.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");
        emailServiceImp.sendEmail("romain.magagna@gmail.com", "Confirmation inscription", "Bonjour " + utilisateur.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");

        utilisateurRepository.save(utilisateur);

        return new ResponseEntity<>(Map.of("message", "Inscription réussie"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> connection(Utilisateur utilisateur) {
        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getEmail(),
                            utilisateur.getMotDePasse())).getPrincipal();


            return new ResponseEntity<>(Map.of("jwt",jwtUtils.generateToken(userDetails)), HttpStatus.OK);

        } catch (AuthenticationException ex) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }
    }
}
