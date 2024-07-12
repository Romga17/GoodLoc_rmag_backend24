package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.MaterielDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielDocumentRepository extends JpaRepository<MaterielDocument,Integer> {

}
