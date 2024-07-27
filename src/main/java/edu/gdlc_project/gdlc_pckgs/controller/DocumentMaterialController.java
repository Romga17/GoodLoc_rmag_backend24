package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.DocumentMaterial;
import edu.gdlc_project.gdlc_pckgs.service.DocumentMaterial_Service.DocumentMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materieldocument")

public class DocumentMaterialController {
    @Autowired
    protected DocumentMaterialService documentMaterialService;

    @PostMapping("/materieldocument")
    public String add(@RequestBody DocumentMaterial documentMaterial){
        documentMaterialService.saveMaterielDocument(documentMaterial);
        return "Le document technique du matériel à bien été ajouté.";
    }

    @GetMapping("/materieldocument/liste")
    public List<DocumentMaterial> getAllMaterielDocument(){
        return getAllMaterielDocument();
    }
}
