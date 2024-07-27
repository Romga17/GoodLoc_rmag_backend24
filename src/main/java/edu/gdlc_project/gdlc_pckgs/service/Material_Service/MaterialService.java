package edu.gdlc_project.gdlc_pckgs.service.Material_Service;

import edu.gdlc_project.gdlc_pckgs.model.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {
    public ResponseEntity<Material> saveMateriel(Material material);

    public List<Material> getAllMateriel();

    public Material getMaterielById(int id);

    public ResponseEntity<Material> deleteMaterielById(int id);

    public ResponseEntity<Material> modificationMateriel(int id, Material material);

}
