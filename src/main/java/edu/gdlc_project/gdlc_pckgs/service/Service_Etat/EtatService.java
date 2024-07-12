package edu.gdlc_project.gdlc_pckgs.service.Service_Etat;

import edu.gdlc_project.gdlc_pckgs.model.Etat;

import java.util.List;

public interface EtatService {

    public Etat saveEtat(Etat etat);

    public List<Etat> getAllEtat();
}
