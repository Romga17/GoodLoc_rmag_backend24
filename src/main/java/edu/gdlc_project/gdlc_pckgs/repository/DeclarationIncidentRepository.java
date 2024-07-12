package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.DeclarationIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationIncidentRepository extends JpaRepository<DeclarationIncident,Integer> {
    List<DeclarationIncident> findDeclarationIncidentsById(int id);
}
