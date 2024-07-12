package edu.gdlc_project.gdlc_pckgs.service.Service_Marque;

import edu.gdlc_project.gdlc_pckgs.model.Marque;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarqueService {
    public Marque saveMarque(Marque marque);

    public List<Marque> getAllMarques();
}
