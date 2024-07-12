package edu.gdlc_project.gdlc_pckgs.service.Service_MaterielImage;

import edu.gdlc_project.gdlc_pckgs.model.MaterielImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterielImageService {

    public MaterielImage saveMaterielImage(MaterielImage materielImage);

    public List<MaterielImage> getAllMaterielImage();

    public String getArticleImage(int id);
}
