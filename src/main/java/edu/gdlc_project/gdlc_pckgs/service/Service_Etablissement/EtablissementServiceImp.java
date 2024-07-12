package edu.gdlc_project.gdlc_pckgs.service.Service_Etablissement;

import edu.gdlc_project.gdlc_pckgs.model.Etablissement;
import edu.gdlc_project.gdlc_pckgs.repository.EtablissementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtablissementServiceImp implements EtablissementService {

    private EtablissementRepository etablissementRepository;

    @Override
    public Etablissement saveEtablissement(Etablissement etablissement) {
        return null;
    }

    @Override
    public List<Etablissement> getAllEtablissement() {
        return null;
    }
}
