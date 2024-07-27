package edu.gdlc_project.gdlc_pckgs.service.User_Service;

import edu.gdlc_project.gdlc_pckgs.exception.EmailAlreadyExistsException;
import edu.gdlc_project.gdlc_pckgs.exception.NotFoundException;
import edu.gdlc_project.gdlc_pckgs.model.Role;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.RoleRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import edu.gdlc_project.gdlc_pckgs.security.JwtUtils;
import edu.gdlc_project.gdlc_pckgs.service.EmailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<User> saveUser(User user){
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
    public ResponseEntity<String> deleteUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);

        return new ResponseEntity<>("L'utilisateur a été supprimé",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> userModification(int id, User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if(userOptional.isEmpty()) {
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

        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Email non conforme");
        }
        if (!user.getEmail().contains(".")) {
            throw new RuntimeException("Email non conforme");
        }

        Role userRole = new Role(); // par exemple, en utilisant l'identifiant du rôle
        // Rechercher le rôle dans la base de données pour s'assurer qu'il existe
       Role roleExist = roleRepository.findById(userRole.getId()).orElse(null);

        if (roleExist == null) {
            // Gérer le cas où le rôle n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le rôle n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le rôle récupéré à l'utilisateur
        user.setUserRole(roleExist);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Cet email est déjà utilisé.");
        }

        user.setUserPassword(getBCryptPasswordEncoder.encode(user.getUserPassword()));

        //emailServiceImp.sendEmail(utilisateur.getEmail(), "Confirmation inscription", "Bonjour " + utilisateur.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");
        emailServiceImp.sendEmail("romain.magagna@gmail.com", "Confirmation inscription", "Bonjour " + user.getEmail() + " et bienvenue chez Goodloc, vous n'êtes qu'à un clic de pouvoir réserver votre matériel.");

        userRepository.save(user);

        return new ResponseEntity<>(Map.of("message", "Inscription réussie"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> connection(User user) {
        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getUserPassword())).getPrincipal();


            return new ResponseEntity<>(Map.of("jwt",jwtUtils.generateToken(userDetails)), HttpStatus.OK);

        } catch (AuthenticationException ex) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }
    }
}
