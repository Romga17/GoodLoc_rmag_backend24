package edu.gdlc_project.gdlc_pckgs.service.Brand_Service;

import edu.gdlc_project.gdlc_pckgs.model.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    public Brand saveMarque(Brand brand);

    public List<Brand> getAllMarques();
}
