package edu.gdlc_project.gdlc_pckgs.service.Service_MaterielImage;

import edu.gdlc_project.gdlc_pckgs.model.MaterielImage;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielImageRepository;
import edu.gdlc_project.gdlc_pckgs.repository.MaterielRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielImageServiceImp implements MaterielImageService {

    private final MaterielImageRepository materielImageRepository;
    private final MaterielRepository materielRepository;

    public MaterielImageServiceImp(MaterielImageRepository materielImageRepository, MaterielRepository materielRepository) {
        this.materielImageRepository = materielImageRepository;
        this.materielRepository = materielRepository;
    }

    @Override
    public MaterielImage saveMaterielImage(MaterielImage materielImage) {
        return null;
    }

    @Override
    public List<MaterielImage> getAllMaterielImage() {
        return materielImageRepository.findAll();
    }

    @Override
    public String getArticleImage(int id) {
        return materielRepository.findById(id).get().getMaterielImageList().get(id).getUrl();
    }
}
