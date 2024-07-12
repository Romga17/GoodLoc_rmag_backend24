package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Categorie;
import edu.gdlc_project.gdlc_pckgs.service.Service_Categorie.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    protected CategorieService categorieService;

    @PostMapping("/categorie")
    public String addCategorie(@RequestBody Categorie categorie){
        categorieService.saveCategory(categorie);
        return "La nouvelle catégorie à été ajoutée.";
    }

    @GetMapping("/categorie/liste")
    public List<Categorie> getAllCategories(){
        return getAllCategories();
    }
}
