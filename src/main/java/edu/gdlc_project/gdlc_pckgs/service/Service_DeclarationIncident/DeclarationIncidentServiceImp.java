package edu.gdlc_project.gdlc_pckgs.service.Service_DeclarationIncident;

import edu.gdlc_project.gdlc_pckgs.model.DeclarationIncident;
import edu.gdlc_project.gdlc_pckgs.model.Materiel;
import edu.gdlc_project.gdlc_pckgs.model.Utilisateur;
import edu.gdlc_project.gdlc_pckgs.repository.DeclarationIncidentRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclarationIncidentServiceImp implements DeclarationIncidentService {

    @Autowired
    private DeclarationIncidentRepository declarationIncidentRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public ResponseEntity<DeclarationIncident> saveDeclarationIncident(DeclarationIncident declarationIncident) {
        declarationIncidentRepository.save(declarationIncident);
        return new ResponseEntity<>(declarationIncident, HttpStatus.OK);
    }

    @Override
    public List<DeclarationIncident> getAllDeclarationIncident() {
        return declarationIncidentRepository.findAll();
    }

    @Override
    public List<DeclarationIncident> getDeclarationIncidentByID(int id) {
        List<DeclarationIncident> userIncidents = declarationIncidentRepository.findDeclarationIncidentsById(id);
        return userIncidents;
    }

    @Override
    public ResponseEntity<DeclarationIncident> addUserDeclarationIncident(int idMat, DeclarationIncident declarationIncident, int idUtil) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(idUtil);
        Optional<Materiel> materielOptional = materielRepository.findById(idMat);

        if (utilisateurOptional.isPresent() && materielOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            Materiel materiel = materielOptional.get();

            materiel.setDeclarationMat(declarationIncident);

            utilisateur.getDeclarationUtil().add(declarationIncident);

            DeclarationIncident savedIncident = declarationIncidentRepository.save(declarationIncident);

            return new ResponseEntity<>(savedIncident, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }
}
