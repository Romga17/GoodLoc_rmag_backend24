package edu.gdlc_project.gdlc_pckgs.security;

import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUtilisateur = userRepository.findByEmail(email);

        if(optionalUtilisateur.isPresent()){
            return new AppUserDetails(optionalUtilisateur.get());
        }

        throw new UsernameNotFoundException("Email introuvable");

    }
}
