package edu.gdlc_project.gdlc_pckgs.service.Design_Service;

import edu.gdlc_project.gdlc_pckgs.model.Design;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DesignService {

    Design saveModel(Design design);

    List<Design> getAllModels();
}
