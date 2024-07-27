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
    public ResponseEntity<Material> saveMaterial(Material materialToSave) {
        materialRepository.save(materialToSave);
        return new ResponseEntity<>(materialToSave, HttpStatus.OK);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material getMaterialById(int id) {
        return materialRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<Material> deleteMaterialById(int id) {
        Optional<Material> materialOptional = materialRepository.findById(id);

        if(materialOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        materialRepository.deleteById(id);

        return new ResponseEntity<>(materialOptional.get(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Material> materialModification(int id, Material materialToModify) {
        materialToModify.setId(id);
        materialToModify.setMaterialReference(materialToModify.getMaterialReference());
        materialToModify.setMaterialDescription(materialToModify.getMaterialDescription());
        materialToModify.setMaterialStatus(materialToModify.getMaterialStatus());

        Optional<Material> materialOptional = materialRepository.findById(materialToModify.getId());

        if(materialOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        materialRepository.save(materialToModify);

        return new ResponseEntity<>(materialOptional.get(), HttpStatus.OK);
    }
}
