package edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IncidentNotificationService {
    public ResponseEntity<IncidentNotification> saveIncidentNotification(IncidentNotification incidentNotification);

    public List<IncidentNotification> getAllIncidentsNotifications();

    public List<IncidentNotification> getIncidentsNotificationsByID(int id);

    public ResponseEntity<IncidentNotification> addUserIncidentNotification(int idMaterial, IncidentNotification incidentNotification, int idUser );
}
