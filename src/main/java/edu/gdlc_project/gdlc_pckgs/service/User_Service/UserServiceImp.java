package edu.gdlc_project.gdlc_pckgs.service.User_Service;

import edu.gdlc_project.gdlc_pckgs.exception.EmailAlreadyExistsException;
import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.security.JwtUtils;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

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

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    public ResponseEntity<User> saveUser(User user) {
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // Gérer le cas où aucun utilisateur n'est trouvé, par exemple :
            throw new NotFoundException("Utilisateur non trouvé avec l'ID : " + id);
        }
    }


    @Override
    public ResponseEntity<Map<String, String>> deleteUserById(int id) {
        logger.info("Tentative de suppression de l'utilisateur avec l'ID : {}", id);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            logger.warn("Utilisateur avec l'ID {} non trouvé", id);
            return new ResponseEntity<>(Map.of("message", "Utilisateur non trouvé"),
                    HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        logger.info("Utilisateur avec l'ID {} supprimé avec succès", id);

        return new ResponseEntity<>(Map.of("message", "L'utilisateur a été supprimé"),
                HttpStatus.OK);
    }


    @Override
    public ResponseEntity<User> userModification(int id, User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Etait placé avant optional avant/ A corriger si erreur
        user.setId(id);
        user.setUserLastname(user.getUserLastname());
        user.setUserFirstname(user.getUserFirstname());
        user.setEmail(user.getEmail());

        userRepository.save(user);

        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> subscription(User user) {

        if (!isValidEmail(user.getEmail())) {
            throw new RuntimeException("Email non conforme");
        }

        Role userRole = getDefaultUserRole();

        //Role roleExist = roleRepository.findById(userRole.getId()).orElse(null);

        if (userRole == null) {
            return new ResponseEntity<>(Map.of("error", "Le rôle n'existe pas."),
                    HttpStatus.BAD_REQUEST);
        }

        user.setUserRole(userRole);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Cet email est déjà utilisé.");
        }

        user.setUserPassword(getBCryptPasswordEncoder.encode(user.getUserPassword()));

        userRepository.save(user);

        emailServiceImp.sendEmail("romain.magagna@gmail.com", "Confirmation inscription",
                "Bonjour " + user.getEmail()
                        + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir " +
                        "réserver votre" +
                        " matériel.");

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Inscription réussie");
        responseBody.put("user", user);

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> connection(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getUserPassword() == null || user.getUserPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getUserPassword())).getPrincipal();

            String token = jwtUtils.generateToken(userDetails);
            return new ResponseEntity<>(Map.of("jwt", token), HttpStatus.OK);

        } catch (AuthenticationException ex) {
            logger.error("Authentication failed for user: ", user.getEmail(), ex);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    // Méthode de validation de l'email
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }


    // Méthode pour obtenir le rôle utilisateur par défaut
    private Role getDefaultUserRole() {
        Role userRole = new Role(); // Constructeur qui attribue l'ID 2
        return roleRepository.findById(userRole.getId()).orElse(null);
    }
}
