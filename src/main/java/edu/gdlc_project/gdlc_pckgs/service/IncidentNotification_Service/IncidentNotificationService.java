package edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IncidentNotificationService {
    public ResponseEntity<IncidentNotification> saveDeclarationIncident(IncidentNotification incidentNotification);

    public List<IncidentNotification> getAllDeclarationIncident();

    public List<IncidentNotification> getDeclarationIncidentByID(int id);

    public ResponseEntity<IncidentNotification> addUserDeclarationIncident(int idMat, IncidentNotification incidentNotification, int idUtil );
}
