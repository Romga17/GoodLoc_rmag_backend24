package edu.gdlc_project.gdlc_pckgs.service.Service_Modele;

import edu.gdlc_project.gdlc_pckgs.model.Modele;
import edu.gdlc_project.gdlc_pckgs.repository.ModeleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeleServiceImp implements ModeleService{

    @Autowired
    private ModeleRepository modeleRepository;

    @Override
    public Modele saveModele(Modele modele) {
        return null;
    }
    @Override
    public List<Modele> getAllModele() {
        return modeleRepository.findAll();
    }
}
