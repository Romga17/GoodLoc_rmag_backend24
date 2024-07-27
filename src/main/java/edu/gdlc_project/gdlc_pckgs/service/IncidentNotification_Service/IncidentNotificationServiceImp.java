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
    public ResponseEntity<IncidentNotification> saveDeclarationIncident(IncidentNotification incidentNotification) {
        incidentNotificationRepository.save(incidentNotification);
        return new ResponseEntity<>(incidentNotification, HttpStatus.OK);
    }

    @Override
    public List<IncidentNotification> getAllDeclarationIncident() {
        return incidentNotificationRepository.findAll();
    }

    @Override
    public List<IncidentNotification> getDeclarationIncidentByID(int id) {
        List<IncidentNotification> userIncidents = incidentNotificationRepository.findDeclarationIncidentsById(id);
        return userIncidents;
    }

    @Override
    public ResponseEntity<IncidentNotification> addUserDeclarationIncident(int idMat, IncidentNotification incidentNotification, int idUtil) {
        Optional<User> utilisateurOptional = userRepository.findById(idUtil);
        Optional<Material> materielOptional = materialRepository.findById(idMat);

        if (utilisateurOptional.isPresent() && materielOptional.isPresent()) {
            User user = utilisateurOptional.get();
            Material material = materielOptional.get();

            material.setDeclarationMat(incidentNotification);

            user.getUserIncidentNotification().add(incidentNotification);

            IncidentNotification savedIncident = incidentNotificationRepository.save(incidentNotification);

            return new ResponseEntity<>(savedIncident, HttpStatus.OK);
        } else {
            throw new RuntimeException("Utilisateur ou Matériel non trouvé");
        }
    }
}
