package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design,Integer> {

}
