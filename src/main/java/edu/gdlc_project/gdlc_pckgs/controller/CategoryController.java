package edu.gdlc_project.gdlc_pckgs.controller;

import edu.gdlc_project.gdlc_pckgs.model.Category;
import edu.gdlc_project.gdlc_pckgs.service.Category_Service.CategoryService;
import edu.gdlc_project.gdlc_pckgs.service.Category_Service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    protected CategoryServiceImp categoryServiceImp;

    @GetMapping("/list")
    public List<Category> getCategoriesList(){
        return categoryServiceImp.getAllCategories();
    }

    @PostMapping("/add")
    public String addCategory(@RequestBody Category category){
        categoryServiceImp.saveCategory(category);
        return "La nouvelle catégorie à été ajoutée.";
    }
}
