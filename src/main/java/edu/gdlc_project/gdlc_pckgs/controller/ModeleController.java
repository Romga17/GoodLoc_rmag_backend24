package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Modele;
import edu.gdlc_project.gdlc_pckgs.service.Service_Modele.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modele")
public class ModeleController {

    @Autowired
    protected ModeleService modeleService;

    @PostMapping("/modele")
    public String add(@RequestBody Modele modele){
        modeleService.saveModele(modele);
        return "Le nouveau modèle à été ajouté.";
    }

    @GetMapping("/obtenir/liste")
    public List<Modele> getAllModele(){
        return modeleService.getAllModele();
    }
}
