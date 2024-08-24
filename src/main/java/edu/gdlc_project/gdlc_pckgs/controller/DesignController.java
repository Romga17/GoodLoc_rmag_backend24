package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Design;
import edu.gdlc_project.gdlc_pckgs.service.Design_Service.DesignServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/design")
public class DesignController {

    @Autowired
    protected DesignServiceImp designServiceImp;

    @PostMapping("/add")
    public String addModel(@RequestBody Design design){
        designServiceImp.saveModel(design);
        return "Le nouveau modèle à été ajouté.";
    }

    @GetMapping("/list")
    public List<Design> getModelsList(){
        return designServiceImp.getAllModels();
    }
}
