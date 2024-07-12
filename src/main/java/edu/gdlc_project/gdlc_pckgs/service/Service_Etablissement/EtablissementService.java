package edu.gdlc_project.gdlc_pckgs.service.Service_Etablissement;

import edu.gdlc_project.gdlc_pckgs.model.Etablissement;

import java.util.List;

public interface EtablissementService {

    public Etablissement saveEtablissement(Etablissement etablissement);

    public List<Etablissement> getAllEtablissement();

}
