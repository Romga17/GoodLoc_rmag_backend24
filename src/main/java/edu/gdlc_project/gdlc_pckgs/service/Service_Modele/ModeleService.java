package edu.gdlc_project.gdlc_pckgs.service.Service_Modele;

import edu.gdlc_project.gdlc_pckgs.model.Modele;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModeleService {
    public Modele saveModele(Modele modele);

    public List<Modele> getAllModele();
}
