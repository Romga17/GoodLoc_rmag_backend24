package edu.gdlc_project.gdlc_pckgs.service.Service_Categorie;

import edu.gdlc_project.gdlc_pckgs.model.Categorie;

import java.util.List;

public interface CategorieService {

    public Categorie saveCategory(Categorie categorie);

    public List<Categorie> getAllCategories();
}
