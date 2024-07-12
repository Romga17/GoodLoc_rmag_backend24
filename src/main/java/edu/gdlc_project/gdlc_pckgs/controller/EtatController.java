package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Etat;
import edu.gdlc_project.gdlc_pckgs.service.Service_Etat.EtatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/etat")
public class EtatController {

    @Autowired
    protected EtatService etatService;

    @PostMapping("/etat")
    public String add(@RequestBody Etat etat){
        etatService.saveEtat(etat);
        return "Le nouvel état à été ajouté.";
    }

    @GetMapping("/etat/liste")
    public List<Etat> getAllEtat(){
        return getAllEtat();
    }
}
