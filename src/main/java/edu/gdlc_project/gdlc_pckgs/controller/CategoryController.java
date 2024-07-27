package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Category;
import edu.gdlc_project.gdlc_pckgs.service.Category_Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategoryController {

    @Autowired
    protected CategoryService categoryService;

    @PostMapping("/categorie")
    public String addCategorie(@RequestBody Category category){
        categoryService.saveCategory(category);
        return "La nouvelle catégorie à été ajoutée.";
    }

    @GetMapping("/categorie/liste")
    public List<Category> getAllCategories(){
        return getAllCategories();
    }
}
