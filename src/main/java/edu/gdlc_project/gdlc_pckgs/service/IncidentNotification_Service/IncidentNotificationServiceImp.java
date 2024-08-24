package edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import edu.gdlc_project.gdlc_pckgs.model.Material;
import edu.gdlc_project.gdlc_pckgs.model.User;
import edu.gdlc_project.gdlc_pckgs.repository.IncidentNotificationRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterialRepository;
import edu.gdlc_project.gdlc_pckgs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentNotificationServiceImp implements IncidentNotificationService {

    @Autowired
    private IncidentNotificationRepository incidentNotificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public ResponseEntity<IncidentNotification> saveIncidentNotification(IncidentNotification incidentNotification) {
        incidentNotificationRepository.save(incidentNotification);
        return new ResponseEntity<>(incidentNotification, HttpStatus.OK);
    }

    @Override
    public List<IncidentNotification> getAllIncidentsNotifications() {
        return incidentNotificationRepository.findAll();
    }

    @Override
    public List<IncidentNotification> getIncidentsNotificationsByID(int id) {
        List<IncidentNotification> userIncidents = incidentNotificationRepository.findIncidentsNotificationsById(id);
        return userIncidents;
    }

    @Override
    public ResponseEntity<IncidentNotification> addUserIncidentNotification(int idMat, IncidentNotification notifiedIncident, int idUtil) {
        Optional<User> userOptional = userRepository.findById(idUtil);
        Optional<Material> materialOptional = materialRepository.findById(idMat);

        if (userOptional.isPresent() && materialOptional.isPresent()) {
            User user = userOptional.get();
            Material material = materialOptional.get();

            material.setNotifiedMaterial(notifiedIncident);

            user.getUserIncidentNotification().add(notifiedIncident);

            IncidentNotification savedIncident = incidentNotificationRepository.save(notifiedIncident);

            return new ResponseEntity<>(savedIncident, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }
}
