package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicMaterialRepository extends JpaRepository<PicMaterial,Integer> {
}
