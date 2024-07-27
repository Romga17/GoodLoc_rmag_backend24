package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Brand;
import edu.gdlc_project.gdlc_pckgs.service.Brand_Service.BrandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    protected BrandServiceImp brandServiceImp;

    @PostMapping("/add")
    public String add(@RequestBody Brand brand){
        brandServiceImp.saveBrand(brand);
        return "La nouvelle marque à été ajoutée.";
    }

    @GetMapping("/list")
    public List<Brand> getBrandsList(){
        return brandServiceImp.getAllBrands();
    }
}
