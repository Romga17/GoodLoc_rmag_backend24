package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Design;
import edu.gdlc_project.gdlc_pckgs.service.Design_Service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modele")
public class DesignController {

    @Autowired
    protected DesignService designService;

    @PostMapping("/modele")
    public String add(@RequestBody Design design){
        designService.saveModele(design);
        return "Le nouveau modèle à été ajouté.";
    }

    @GetMapping("/obtenir/liste")
    public List<Design> getAllModele(){
        return designService.getAllModele();
    }
}
