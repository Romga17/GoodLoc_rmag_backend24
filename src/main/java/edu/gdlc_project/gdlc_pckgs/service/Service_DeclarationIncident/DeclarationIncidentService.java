package edu.gdlc_project.gdlc_pckgs.service.Service_DeclarationIncident;

import edu.gdlc_project.gdlc_pckgs.model.DeclarationIncident;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeclarationIncidentService {
    public ResponseEntity<DeclarationIncident> saveDeclarationIncident(DeclarationIncident declarationIncident);

    public List<DeclarationIncident> getAllDeclarationIncident();

    public List<DeclarationIncident> getDeclarationIncidentByID(int id);

    public ResponseEntity<DeclarationIncident> addUserDeclarationIncident(int idMat, DeclarationIncident declarationIncident, int idUtil );
}
