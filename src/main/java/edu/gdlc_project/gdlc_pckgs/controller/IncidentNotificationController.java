package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service.IncidentNotificationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentNotificationController {

    @Autowired
    protected IncidentNotificationServiceImp declarationIncidentServiceImp;

    @PostMapping("/ajouter/{idMat}/{idUtil}")
    public ResponseEntity<IncidentNotification> addIncident(@PathVariable("idMat") int idMat, @PathVariable("idUtil") int idUtil, @RequestBody IncidentNotification typeIncident){
        return declarationIncidentServiceImp.addUserDeclarationIncident(idMat, typeIncident, idUtil);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<IncidentNotification> saveIncident(@RequestBody IncidentNotification incidentNotification){
        return declarationIncidentServiceImp.saveDeclarationIncident(incidentNotification);
    }

    @GetMapping("/list")
    public List<IncidentNotification> getAllTypeIncident(){
        return declarationIncidentServiceImp.getAllDeclarationIncident();
    }

    @GetMapping("/obtenir/{id}")
    public List<IncidentNotification> getUserIncidents(@PathVariable int id){

        return declarationIncidentServiceImp.getDeclarationIncidentByID(id);
    }
}
