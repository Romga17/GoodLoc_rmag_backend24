package edu.gdlc_project.gdlc_pckgs.service.Material_Service;

import edu.gdlc_project.gdlc_pckgs.model.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MaterialService {
    public ResponseEntity<Material> saveMaterial(Material material);

    public List<Material> getAllMaterials();

    public Material getMaterialById(int id);

    public ResponseEntity<Material> deleteMaterialById(int id);

    public ResponseEntity<Material> materialModification(int id, Material material);

}
