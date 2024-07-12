package edu.gdlc_project.gdlc_pckgs.service.Service_MaterielDocument;

import edu.gdlc_project.gdlc_pckgs.model.MaterielDocument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterielDocumentService {

    public MaterielDocument saveMaterielDocument(MaterielDocument materielDocument);

    public List<MaterielDocument> getAllMaterielDocument();
}
