package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    // On notifie le retour de la méthode comme optional car potentiellement la valeur peut être "Null"
    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

}
