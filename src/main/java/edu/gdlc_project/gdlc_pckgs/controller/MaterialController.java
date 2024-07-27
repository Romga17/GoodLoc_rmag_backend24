package edu.gdlc_project.gdlc_pckgs.controller;


import edu.gdlc_project.gdlc_pckgs.model.Material;
import edu.gdlc_project.gdlc_pckgs.service.Material_Service.MaterialServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiel")
public class MaterialController {

    @Autowired
    protected MaterialServiceImp materielServiceImp;

    @GetMapping("/obtenir/liste")
    public List<Material> getAllMateriel(){
        return materielServiceImp.getAllMateriel();
    }

    @PostMapping("/add")
    public ResponseEntity<Material> add(@RequestBody Material material){
        return materielServiceImp.saveMateriel(material);
    }

    @GetMapping("/{id}")
    public Material obtenir (@PathVariable int id){

        return materielServiceImp.getMaterielById(id);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Material> supprimerMateriel(@PathVariable int id) {
        return materielServiceImp.deleteMaterielById(id);
    }
}
