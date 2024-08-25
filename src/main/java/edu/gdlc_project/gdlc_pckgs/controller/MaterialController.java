package edu.gdlc_project.gdlc_pckgs.controller;


import edu.gdlc_project.gdlc_pckgs.model.Material;
import edu.gdlc_project.gdlc_pckgs.service.Material_Service.MaterialServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    protected MaterialServiceImp materielServiceImp;

    @GetMapping("/list")
    public List<Material> getMaterialsList(){
        return materielServiceImp.getAllMaterials();
    }

    @PostMapping("/add")
    public ResponseEntity<Material> addMaterial(@RequestBody Material material){
        return materielServiceImp.saveMaterial(material);
    }

    @GetMapping("/get/{id}")
    public Material getMaterialWithId (@PathVariable int id){

        return materielServiceImp.getMaterialById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Material> deleteMaterial(@PathVariable int id) {
        return materielServiceImp.deleteMaterialById(id);
    }
}
