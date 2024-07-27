package edu.gdlc_project.gdlc_pckgs.service.PicMaterial_Service;

import edu.gdlc_project.gdlc_pckgs.model.PicMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PicMaterialService {

    public PicMaterial saveMaterielImage(PicMaterial picMaterial);

    public List<PicMaterial> getAllMaterielImage();

    public String getArticleImage(int id);
}
