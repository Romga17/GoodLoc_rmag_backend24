package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import edu.gdlc_project.gdlc_pckgs.service.IncidentNotification_Service.IncidentNotificationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
@CrossOrigin(origins = {"http://localhost:4200", "http://185.97.144.183:8082"})
public class IncidentNotificationController {

    @Autowired
    protected IncidentNotificationServiceImp incidentNotificationServiceImp;

    @PostMapping("/add/{notifiedMaterialId}/{incidentNotifierId}")
    public ResponseEntity<IncidentNotification> addIncident(@PathVariable("notifiedMaterialId") int idMaterial, @PathVariable("incidentNotifierId") int idUser, @RequestBody IncidentNotification incidentType){
        return incidentNotificationServiceImp.addUserIncidentNotification(idMaterial, incidentType, idUser);
    }

    @PostMapping("/save")
    public ResponseEntity<IncidentNotification> saveIncident(@RequestBody IncidentNotification incidentNotification){
        return incidentNotificationServiceImp.saveIncidentNotification(incidentNotification);
    }

    @GetMapping("/list")
    public List<IncidentNotification> getincidentsList(){
        return incidentNotificationServiceImp.getAllIncidentsNotifications();
    }

    @GetMapping("/get/{id}")
    public List<IncidentNotification> getUserIncidents(@PathVariable int id){
        return incidentNotificationServiceImp.getIncidentsNotificationsByID(id);
    }
}
