package edu.gdlc_project.gdlc_pckgs.service.Service_Materiel;

import edu.gdlc_project.gdlc_pckgs.model.Materiel;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterielServiceImp implements MaterielService {

    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public ResponseEntity<Materiel> saveMateriel(Materiel materiel) {
        materielRepository.save(materiel);
        return new ResponseEntity<>(materiel, HttpStatus.OK);
    }

    @Override
    public List<Materiel> getAllMateriel() {
        return materielRepository.findAll();
    }

    @Override
    public Materiel getMaterielById(int id) {
        return materielRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<Materiel> deleteMaterielById(int id) {
        Optional<Materiel> materielOptional = materielRepository.findById(id);

        if(materielOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        materielRepository.deleteById(id);

        return new ResponseEntity<>(materielOptional.get(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Materiel> modificationMateriel(int id, Materiel materiel) {
        materiel.setId(id);
        materiel.setReference(materiel.getReference());
        materiel.setDescription(materiel.getDescription());
        materiel.setStatut(materiel.getStatut());

        Optional<Materiel> materielOptional = materielRepository.findById(materiel.getId());

        if(materielOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        materielRepository.save(materiel);

        return new ResponseEntity<>(materielOptional.get(), HttpStatus.OK);
    }
}
