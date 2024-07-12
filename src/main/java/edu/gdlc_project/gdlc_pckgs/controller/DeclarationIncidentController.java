package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.DeclarationIncident;
import edu.gdlc_project.gdlc_pckgs.service.Service_DeclarationIncident.DeclarationIncidentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class DeclarationIncidentController {

    @Autowired
    protected DeclarationIncidentServiceImp declarationIncidentServiceImp;

    @PostMapping("/ajouter/{idMat}/{idUtil}")
    public ResponseEntity<DeclarationIncident> addIncident(@PathVariable("idMat") int idMat, @PathVariable("idUtil") int idUtil, @RequestBody DeclarationIncident typeIncident){
        return declarationIncidentServiceImp.addUserDeclarationIncident(idMat, typeIncident, idUtil);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<DeclarationIncident> saveIncident(@RequestBody DeclarationIncident declarationIncident){
        return declarationIncidentServiceImp.saveDeclarationIncident(declarationIncident);
    }

    @GetMapping("/list")
    public List<DeclarationIncident> getAllTypeIncident(){
        return declarationIncidentServiceImp.getAllDeclarationIncident();
    }

    @GetMapping("/obtenir/{id}")
    public List<DeclarationIncident> getUserIncidents(@PathVariable int id){

        return declarationIncidentServiceImp.getDeclarationIncidentByID(id);
    }
}
