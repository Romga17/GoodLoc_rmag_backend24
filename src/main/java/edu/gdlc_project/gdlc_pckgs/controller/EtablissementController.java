package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Etablissement;
import edu.gdlc_project.gdlc_pckgs.service.Service_Etablissement.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etablissement")
public class EtablissementController {

    @Autowired
    protected EtablissementService etablissementService;
    @PostMapping("/etablissement")
    public String add(@RequestBody Etablissement etablissement){
        etablissementService.saveEtablissement(etablissement);
        return "Le nouvel établissement à été ajouté.";
    }

    @GetMapping("/etablissement/liste")
    public List<Etablissement> getAllEtablissement(){
        return getAllEtablissement();
    }
}
