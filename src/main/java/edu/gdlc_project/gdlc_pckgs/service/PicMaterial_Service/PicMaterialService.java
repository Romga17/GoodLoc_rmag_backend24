package edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PicMaterialService {

    public PicMaterial saveMaterialPic(PicMaterial picMaterial);

    public List<PicMaterial> getAllMaterialsPics();

    public String getItemImage(int id);
}
