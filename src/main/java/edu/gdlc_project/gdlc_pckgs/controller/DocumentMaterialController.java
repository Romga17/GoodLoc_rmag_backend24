package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.DocumentMaterial;
import edu.gdlc_project.gdlc_pckgs.service.DocumentMaterial_Service.DocumentMaterialService;
import edu.gdlc_project.gdlc_pckgs.service.DocumentMaterial_Service.DocumentMaterialServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/document")

public class DocumentMaterialController {
    @Autowired
    protected DocumentMaterialServiceImp documentMaterialServiceImp;

    @PostMapping("/add")
    public String addMaterielDocument(@RequestBody DocumentMaterial documentMaterial){
        documentMaterialServiceImp.saveMaterialDocument(documentMaterial);
        return "Le document technique du matériel à bien été ajouté.";
    }

    @GetMapping("/list")
    public List<DocumentMaterial> getAllMaterialsDocument(){
        return documentMaterialServiceImp.getAllMaterialsDocuments();
    }
}
