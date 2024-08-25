package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service.PicMaterialService;
import edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service.PicMaterialServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/image")
public class PicMaterialController {

    @Autowired
    protected PicMaterialServiceImp picMaterialServiceImp;

    @PostMapping("/add")
    public String add(@RequestBody PicMaterial picMaterial){
     picMaterialServiceImp.saveMaterialPic(picMaterial);
        return "L'image du matériel à bien été ajoutée.";
    }

    @GetMapping("/list")
    public List<PicMaterial> getPicsList(){
        return picMaterialServiceImp.getAllMaterialsPics();
    }
}
