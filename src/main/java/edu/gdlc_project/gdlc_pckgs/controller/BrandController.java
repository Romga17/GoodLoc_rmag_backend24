package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Brand;
import edu.gdlc_project.gdlc_pckgs.service.Brand_Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marque")
public class BrandController {

    @Autowired
    protected BrandService brandService;

    @PostMapping("/marque")
    public String add(@RequestBody Brand brand){
        brandService.saveMarque(brand);
        return "La nouvelle marque à été ajoutée.";
    }

    @GetMapping("/marque/liste")
    public List<Brand> getAllMarque(){
        return getAllMarque();
    }



}
