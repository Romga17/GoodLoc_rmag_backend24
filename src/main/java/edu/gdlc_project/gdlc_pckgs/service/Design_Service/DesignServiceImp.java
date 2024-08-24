package edu.gdlc_project.gdlc_pckgs.service.Design_Service;

import edu.gdlc_project.gdlc_pckgs.model.Design;
import edu.gdlc_project.gdlc_pckgs.repository.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignServiceImp implements DesignService {

    @Autowired
    private DesignRepository designRepository;

    @Override
    public Design saveModel(Design design) {
        return designRepository.save(design);
    }
    @Override
    public List<Design> getAllModels() {
        return designRepository.findAll();
    }
}
