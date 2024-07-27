package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service.PicMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materielimage")
public class PicMaterialController {

    @Autowired
    protected PicMaterialService picMaterialService;

    @PostMapping("/ajouter")
    public String add(@RequestBody PicMaterial picMaterial){
     picMaterialService.saveMaterielImage(picMaterial);
        return "L'image du matériel à bien été ajoutée.";
    }

    @GetMapping("/obtenir/liste")
    public List<PicMaterial> getAllImages(){
        return picMaterialService.getAllMaterielImage();
    }
}
