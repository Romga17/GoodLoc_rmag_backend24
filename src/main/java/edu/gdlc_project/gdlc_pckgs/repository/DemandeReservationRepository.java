package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.DemandeReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeReservationRepository extends JpaRepository<DemandeReservation,Integer> {
    List<DemandeReservation> findByDemandeurId(Long id);
}
