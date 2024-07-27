package edu.gdlc_project.gdlc_pckgs.service.Material_Service;

import edu.gdlc_project.gdlc_pckgs.model.Material;
import edu.gdlc_project.gdlc_pckgs.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public ResponseEntity<Material> saveMateriel(Material material) {
        materialRepository.save(material);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @Override
    public List<Material> getAllMateriel() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterielById(int id) {
        return materialRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<Material> deleteMaterielById(int id) {
        Optional<Material> materielOptional = materialRepository.findById(id);

        if(materielOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        materialRepository.deleteById(id);

        return new ResponseEntity<>(materielOptional.get(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Material> modificationMateriel(int id, Material material) {
        material.setId(id);
        material.setReference(material.getReference());
        material.setDescription(material.getDescription());
        material.setStatut(material.getStatut());

        Optional<Material> materielOptional = materialRepository.findById(material.getId());

        if(materielOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        materialRepository.save(material);

        return new ResponseEntity<>(materielOptional.get(), HttpStatus.OK);
    }
}
