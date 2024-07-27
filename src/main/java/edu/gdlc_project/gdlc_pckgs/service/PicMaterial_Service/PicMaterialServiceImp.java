package edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import edu.gdlc_project.gdlc_pckgs.repository.PicMaterialRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicMaterialServiceImp implements PicMaterialService {

    private final PicMaterialRepository picMaterialRepository;
    private final MaterialRepository materialRepository;

    public PicMaterialServiceImp(PicMaterialRepository picMaterialRepository, MaterialRepository materialRepository) {
        this.picMaterialRepository = picMaterialRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public PicMaterial saveMaterialPic(PicMaterial picMaterial) {
        return picMaterialRepository.save(picMaterial);
    }

    @Override
    public List<PicMaterial> getAllMaterialsPics() {
        return picMaterialRepository.findAll();
    }

    @Override
    public String getItemImage(int id) {
        return materialRepository.findById(id).get().getPicMaterialList().get(id).getPicmaterialUrl();
    }
}
