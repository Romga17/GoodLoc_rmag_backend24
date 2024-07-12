package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.MaterielDocument;
import edu.gdlc_project.gdlc_pckgs.service.Service_MaterielDocument.MaterielDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materieldocument")

public class MaterielDocumentController {
    @Autowired
    protected MaterielDocumentService materielDocumentService;

    @PostMapping("/materieldocument")
    public String add(@RequestBody MaterielDocument materielDocument){
        materielDocumentService.saveMaterielDocument(materielDocument);
        return "Le document technique du matériel à bien été ajouté.";
    }

    @GetMapping("/materieldocument/liste")
    public List<MaterielDocument> getAllMaterielDocument(){
        return getAllMaterielDocument();
    }
}
