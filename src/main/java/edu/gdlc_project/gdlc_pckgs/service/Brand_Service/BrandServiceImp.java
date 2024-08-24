package edu.gdlc_project.gdlc_pckgs.service.Brand_Service;

import edu.gdlc_project.gdlc_pckgs.model.Brand;
import edu.gdlc_project.gdlc_pckgs.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
