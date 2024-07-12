package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.MaterielImage;
import edu.gdlc_project.gdlc_pckgs.service.Service_MaterielImage.MaterielImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materielimage")
public class MaterielImageController {

    @Autowired
    protected MaterielImageService materielImageService;

    @PostMapping("/ajouter")
    public String add(@RequestBody MaterielImage materielImage){
     materielImageService.saveMaterielImage(materielImage);
        return "L'image du matériel à bien été ajoutée.";
    }

    @GetMapping("/obtenir/liste")
    public List<MaterielImage> getAllImages(){
        return materielImageService.getAllMaterielImage();
    }
}
