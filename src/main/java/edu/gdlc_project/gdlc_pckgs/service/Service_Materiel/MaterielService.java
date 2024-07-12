package edu.gdlc_project.gdlc_pckgs.service.Service_Materiel;

import edu.gdlc_project.gdlc_pckgs.model.Materiel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterielService {
    public ResponseEntity<Materiel> saveMateriel(Materiel materiel);

    public List<Materiel> getAllMateriel();

    public Materiel getMaterielById(int id);

    public ResponseEntity<Materiel> deleteMaterielById(int id);

    public ResponseEntity<Materiel> modificationMateriel(int id, Materiel materiel);

}
