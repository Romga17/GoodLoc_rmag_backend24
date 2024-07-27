package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // On notifie le retour de la méthode comme optional car potentiellement la valeur peut être "Null"
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
