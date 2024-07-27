package edu.gdlc_project.gdlc_pckgs.service.DocumentMaterial_Service;

import edu.gdlc_project.gdlc_pckgs.model.DocumentMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentMaterialService {

    public DocumentMaterial saveMaterielDocument(DocumentMaterial documentMaterial);

    public List<DocumentMaterial> getAllMaterielDocument();
}
