package edu.gdlc_project.gdlc_pckgs.controller;


import edu.gdlc_project.gdlc_pckgs.model.Materiel;
import edu.gdlc_project.gdlc_pckgs.service.Service_Materiel.MaterielServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiel")
public class MaterielController {

    @Autowired
    protected MaterielServiceImp materielServiceImp;

    @GetMapping("/obtenir/liste")
    public List<Materiel> getAllMateriel(){
        return materielServiceImp.getAllMateriel();
    }

    @PostMapping("/add")
    public ResponseEntity<Materiel> add(@RequestBody Materiel materiel){
        return materielServiceImp.saveMateriel(materiel);
    }

    @GetMapping("/{id}")
    public Materiel obtenir (@PathVariable int id){

        return materielServiceImp.getMaterielById(id);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Materiel> supprimerMateriel(@PathVariable int id) {
        return materielServiceImp.deleteMaterielById(id);
    }
}
