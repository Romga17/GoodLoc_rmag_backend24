package edu.gdlc_project.gdlc_pckgs.service.Category_Service;

import edu.gdlc_project.gdlc_pckgs.model.Category;

import java.util.List;

public interface CategoryService {

    public Category saveCategory(Category category);

    public List<Category> getAllCategories();
}
